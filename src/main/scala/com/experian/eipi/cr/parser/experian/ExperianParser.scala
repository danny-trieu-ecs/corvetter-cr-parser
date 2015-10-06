package com.experian.eipi.cr.parser.experian

import com.experian.eipi.cr.parser.ErrorCodes._
import com.experian.eipi.cr.parser.experian.ExperianParser.Result
import com.experian.eipi.cr.parser.{Error, ParseError, Parser}

import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.util.{Failure, Left, Success, Try}

/**
 * Created by dtrieu on 9/26/15.
 */
trait ExperianParser extends Parser[Dictionary, Result]

object ExperianParser {

  import Dictionary._
  import SubSegmentIndicator._

  case class SegmentDescriptor(descriptor: Segment, input: String, length: Int = 0)

  case class Record(id: String, fields: Map[String, String], subRecords: Map[SubSegmentIndicator, Map[String, String]] = ListMap())

  type Result = List[Record]

  implicit object ARFParser extends ExperianParser {

    override def parse(input: String)(implicit dictionary: Dictionary): Either[ParseError, Result] =
      if (input isEmpty)
        Left(Error(MALFORMED_INPUT, "Invalid input string", input))
      else
        parseRecord(input)

    @tailrec
    def parseRecord(input: String, result: Result = List())(implicit dictionary: Dictionary): Either[ParseError, Result] =
      if (input isEmpty)
        Right(result)
      else descriptor(input) match {
        case Right(SegmentDescriptor(Segment(id, _, fields, subSegments), input, length)) =>
          parseFields(input, fields, None, ListMap(), maxLength = resolveFieldLength(id, length)) match {
            case Right((parsedFields, actualLength, input)) =>
              length - actualLength match {
                case 0 => parseRecord(input, result :+ Record(id, parsedFields))
                case 1 => input head match {
                  case '@' => parseRecord(input tail, result :+ Record(id, parsedFields))
                  case _ => Left(Error(MALFORMED_INPUT, "Unrecognized segment delimiter", input))
                }
                case remainLength if 1 < remainLength => parseSubFields(input, subSegments, ListMap(), remainingLength = resolveFieldLength(id, remainLength)) match {
                  case Right((parsedSubFields, actualLength, input)) => remainLength - actualLength match {
                    case 0 => parseRecord(input, result :+ Record(id, parsedFields, parsedSubFields))
                    case 1 => input head match {
                      case '@' => parseRecord(input tail, result :+ Record(id, parsedFields, parsedSubFields))
                      case _ => Left(Error(MALFORMED_INPUT, "Unrecognized segment delimiter", input))
                    }
                  }
                  case Left(error) => Left(error)
                }
                case _ => Left(Error(RECORD_LENGTH_NOT_MATCH, s"length[$length], actualLength[$actualLength]", input))
              }
            case Left(error) => Left(error)
          }
        case Left(error) => Left(error)
      }

    def resolveFieldLength(input: String, length: Int) = input match {
      case "100" | "200" | "900" => length
      case _ => length - 1
    }

    @tailrec
    def parseSubFields(input: String, subSegments: SubSegments, returnSubRecords: Map[SubSegmentIndicator, Map[String, String]], returnLength: Int = 0, remainingLength: Int): Either[ParseError, (Map[SubSegmentIndicator, Map[String, String]], Int, String)] =
      if (remainingLength == 0)
        Right((returnSubRecords, returnLength, input))
      else {
        val (indicatorValue, lengthIput) = chop(input)(2)
        val (lengthValue, remainingInput) = chop(lengthIput)(2)
        val expectedLength = (lengthValue toInt) - 4
        val indicator = SubSegmentIndicator withName indicatorValue
        subSegments get indicator match {
          case Some(fields) => parseFields(remainingInput, fields, Some(expectedLength toString), ListMap(), maxLength = expectedLength) match {
            case Right((parsedFields, actualLength, input)) =>
              expectedLength - actualLength match {
                case 0 => parseSubFields(input, subSegments, returnSubRecords + (indicator -> parsedFields), returnLength + actualLength + 4, remainingLength - actualLength - 4)
                case _ => Left(Error(MALFORMED_INPUT, s"expected sub segment length[$expectedLength] doesn't match actual parsed length[$actualLength]", input))
              }
            case Left(error) => Left(error)
          }
          case _ => Left(Error(INVALID_SEGMENT_ID, s"Sub Segment[$indicator] not found", input))
        }
      }

    @tailrec
    def parseFields(input: String, fields: Fields, previous: Option[String], returnFields: Map[String, String], returnLength: Int = 0, maxLength: Int): Either[ParseError, (Map[String, String], Int, String)] = {

      fields match {
        case (_, _, repeated) :: tail if repeated => parseRepeatedFields(input, fields, returnFields, returnLength, maxLength)
        case (name, length, repeated) :: tail => actualLength(length, previous, input) match {
          case Right(length) =>
            val (parsedValue, remainInput) = chop(input)(length)
            val returnField = (name toString, parsedValue)
            val updatedReturnLength = returnLength + length

            val updatedFields = if (repeated && updatedReturnLength + length <= maxLength) {
              tail :+ fields.head
            } else {
              tail
            }
            parseFields(remainInput, updatedFields, Some(parsedValue), returnFields + returnField, updatedReturnLength, maxLength)
          case Left(error) => Left(error)
        }
        case _ => Right((returnFields, returnLength, input))
      }

    }

    @tailrec
    def parseRepeatedFields(input: String, fields: Fields, returnFields: Map[String, String], returnLength: Int = 0, maxLength: Int): Either[ParseError, (Map[String, String], Int, String)] =
      if (returnLength == maxLength)
        Right((returnFields, returnLength, input))
      else
        fields match {
          case (_, key, _) :: (_, value, _) :: tail =>
            val (parsedKey, remainingInput) = chop(input)(key)
            val (parsedValue, newInput) = chop(remainingInput)(value)
            val returnField = (parsedKey -> parsedValue)
            val updatedReturnLength = returnLength + key + value
            parseRepeatedFields(newInput, fields, returnFields + returnField, updatedReturnLength, maxLength)
          case _ => Left(Error(INVALID_KEY_VALUE_FIELDS, s"Fields[$fields] aren't repeated key-value fields ", input))
        }

    def actualLength(length: Int, previous: Option[String], input: String): Either[ParseError, Int] =
      if (length == 0) {
        previous match {
          case Some(intString) => Try {
            intString toInt
          } match {
            case Success(length) => Right(length)
            case _ => Left(Error(NUMBER_FORMAT_EXCEPTION, s"Previous field value[$intString] is not a number", input))
          }
          case _ => Left(Error(MISSING_LENGTH_FIELD, "Previous length field is required", input))
        }
      }
      else
        Right(length)

    def descriptor(input: String)(implicit dictionary: Dictionary): Either[ParseError, SegmentDescriptor] = {
      val (id, lengthInput) = chop(input)(3)
      dictionary withKey id match {
        case Some(segment) =>
          val lengthWidth = id match {
            case "100" | "200" | "900" => 3
            case _ => 4
          }
          val (length, remainInput) = chop(lengthInput)(lengthWidth)
          Try {
            length toInt
          } match {
            case Success(intLength) => Right(SegmentDescriptor(segment, remainInput, length = intLength - 3 - lengthWidth))
            case Failure(cause) => Left(Error(NUMBER_FORMAT_EXCEPTION, s"Unable to parse record length value: ${cause getMessage}", lengthInput))
          }
        case _ => Left(Error(INVALID_SEGMENT_ID, s"Segment id[$id}] not found", input))
      }
    }

    def chop(input: String)(by: Int) = (input take by, input drop by)

  }

}