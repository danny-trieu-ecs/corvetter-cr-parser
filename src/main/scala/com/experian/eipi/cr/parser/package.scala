package com.experian.eipi.cr

/**
 * Credit report parser abstraction.
 *
 * Created by dtrieu on 9/24/15.
 */
package object parser {

  /**
   * Created by dtrieu on 9/19/15.
   *
   * The parser that parse the input {{{String}}}.
   *
   * @tparam A the parsing dictionary
   * @tparam B the parsed result
   */
  trait Parser[A, B] {

    def parse(input: String)(implicit dictionary: A): Either[ParseError, B]

  }

  /** Common parsing error-code. */
  object ErrorCodes extends Enumeration {
    type ErrorCodes = Value
    val MALFORMED_INPUT
    , INVALID_SEGMENT_ID
    , INVALID_KEY_VALUE_FIELDS
    , MISSING_LENGTH_FIELD
    , NUMBER_FORMAT_EXCEPTION
    , RECORD_LENGTH_NOT_MATCH
    = Value
  }

  import ErrorCodes._

  /** Parsing error marker. */
  sealed trait ParseError

  /** Terminal error. */
  case class Error(code: ErrorCodes, message: String, raw: String = "") extends ParseError

  /** Composite error. */
  case class Errors(causes: List[ParseError]) extends ParseError {

    def add(error: ParseError) = copy(causes = causes :+ error)

  }

}
