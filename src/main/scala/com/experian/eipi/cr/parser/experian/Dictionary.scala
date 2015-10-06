package com.experian.eipi.cr.parser.experian

import com.experian.eipi.cr.parser.Parser
import com.experian.eipi.cr.parser.experian.Dictionary.Segment

import scala.collection.immutable.ListMap

/**
 * Experian's Automated Respoonse Format(ARF) [[Parser]] dictionary.
 *
 * Created by dtrieu on 9/26/15.
 */
trait Dictionary {

  val map: Map[String, Segment]

  def withKey(key: String): Option[Segment] = map get key

}


object Dictionary {


  /** Enumerated ARF field names */
  object FieldName extends Enumeration {
    type FieldName = Value
    val Account_Condition_Code
    , Account_Number
    , Account_Number_Length
    , Account_Type_Code
    , Action_Indicator
    , Actual_Payment_Amount
    , Actual_Payment_Amount_Sub_Segment_Indicator
    , Additional_Names_Addresses_For_ID_Match_Condition_C
    , Additional_Names_Addresses_For_ID_Match_Condition_D
    , Additional_Names_Addresses_For_ID_Match_Condition_E
    , Address_Count
    , Address_Date
    , Address_Error_Code
    , Address_Text
    , Address_Text_Length
    , Adjustment_Percent
    , Amount
    , Amount_Available
    , Amount_Of_Balloon_Payment
    , Amount_Past_Due
    , Amount_1
    , Amount_2
    , Amount_1_Qualifier
    , Amount_2_Qualifier
    , Applicant_Code
    , Application_ID
    , Asset_Amount
    , Attribute_ID
    , Attribute_Value
    , Auto_Detail_Financed_Amount
    , Auto_Detail_Item_Number
    , Auto_Detail_Monthly_Payment_Amount
    , Auto_Detail_Number_Of_Months_Remaining
    , Auto_Detail_Status_Text
    , Auto_Detail_Term
    , Balance_Amount
    , Balance_Date
    , Balloon_Sub_Segment_Indicator
    , Bankruptcy__Sub_Segment_Indicator
    , Bankruptcy_Vol_Invol_Sub_Segment_Indicator
    , Book_Page_Sub_Segment_Indicator
    , BK_Chapter_Number
    , BK_Chapter_Sub_Segment_Indicator
    , Census_GEO_Code
    , Charge_Off_Amount
    , Checksum
    , Compliance_Condition_Code
    , Confirmed_Verified_Sub_Segment_Indicator
    , Consumer_Comment
    , Consumer_Comment_Length
    , Consumer_Dispute_Flag
    , Consumer_Dispute_Sub_Segment_Indicator
    , Consumer_Information_Indicator_Code
    , Country_Code
    , Court_Code
    , Court_Name_Length
    , Court_Name_Text
    , Court_Name_Sub_Segment_Indicator
    , Credit_Class_Code
    , Credit_Grantor_Phone_Number
    , Credit_Grantor_Phone_Number_Sub_Segment_Indicator
    , Credit_Limit_Amount
    , CRS_City_State_ZIP_Sub_Segment_Indicator
    , CRS_City_State_ZIP_Text
    , CRS_Office_Name_Sub_Segment_Indicator
    , CRS_Office_Name_Text
    , CRS_Phone_Number_Sub_Segment_Indicator
    , CRS_Phone_Number_Text
    , CRS_PO_Box_Sub_Segment_Indicator
    , CRS_PO_Box_Text
    , CRS_Street_Name_Sub_Segment_Indicator
    , CRS_Street_Name_Text
    , CSI_Member_Number // new for s314
    , Customer_Sales_Message
    , Date_Of_Birth
    , Date_Of_Birth_Sub_Segment_Indicator
    , Date_Of_Death
    , Date_Open
    , Date_Payment_Due
    , Date_Reported
    , Debt_Counseling_Indicator
    , Delinquency_Dates_Payment_Code_Sub_Segment_Indicator
    , Disputed_Accounts_Excluded
    , Derog_Counter
    , Do_Not_Combine_Indicator // new for s314
    , Dwelling_Type
    , ECOA
    , ECOA_Code
    , Enhanced_Payment_Data_Sub_Segment_Indicator
    , Enhanced_Payment_Status
    , Enhanced_Special_Comments
    , Enhanced_Type_Codes
    , Employer_Name
    , Employer_Name_Length
    , Employer_ZIP_Code
    , Encrypted_PIN
    , Error_Code
    , Evaluation
    , Extra_Address_Line
    , Extra_Address_Line_Length
    , First_Delinquency_Date
    , First_Name
    , First_Possible_Year
    , First_Reported_Date
    , Frequency
    , Fraud_Services_Counters_Sub_Segment_Indicator
    , Fraud_Services_Retired_SSN_Sub_Segment_Indicator
    , Fraud_Services_SSN_Issued_Sub_Segment_Indicator
    , Fraud_Services_Sub_Segment_Indicator
    , Full_Credit_Report_Description
    , Gender_Code // new for s314
    , Gender_Code_Soft_Delete_Indicator // new sor s314
    , Generation_Code
    , Geo_Country_Code_Sub_Segment_Indicator
    , Geo_State_MSA_Code_Sub_Segment_Indicator
    , High_Balance_Amount
    , High_Risk_Address_Alert
    , Host_ID
    , Indicator_1
    , Indicator_2
    , Indicator_3
    , Indicator_4
    , Initial_Payment_Level_Date
    , Input_Type
    , Inquiries_During_Last_6_Months_Counter
    , Inquiring_Sub_Code
    , Inquiry_Amount
    , Inquiry_Date
    , Inquiry_Terms
    , Inquiry_Type
    , Installment_Balance
    , ID_Match_Condition
    , KOB
    , Last_Payment_Date
    , Last_Possible_Year
    , Last_Updated_Date
    , Last_Sub_Code_Reporting
    , Length_Of_Customer_Sales_Message
    , Length_Of_Mortgage_ID
    , Length_Of_Original_Input_Name
    , Liabilities_Amount
    , Maximum_Delinquency_Date
    , Maximum_Payment_Code
    , Message_Code
    , Message_Text
    , Middle_Initial
    , Model_Type_Indicator
    , Monthly_Payment
    , Monthly_Payment_Amount
    , Monthly_Payment_Partial_Flag
    , Monthly_Payment_Sub_Segment_Indicator
    , Monthly_Payment_Type
    , Months_Reviewed
    , Module_Ident
    , Mortgage_ID
    , MSA_Code
    , M_Keyword_Length
    , M_Keyword_Text
    , Name_Length
    , Name_Text
    , Name_Type
    , Name_Type_Sub_Segment_Indicator
    , Nine_SSN_Match_Indicator
    , Now_Delinquent_Derog_Counter
    , Number_Of_Creditors_Reporting_Same_SSN
    , Number_Of_Times_Reported
    , Oldest_Trade_Date
    , Open_Close
    , Original_Creditor
    , Original_Creditor_Classification_Code
    , Original_Creditor_Sub_Segment_Indicator
    , Original_Credit_Information_Sub_Segment_Indicator
    , Original_Filing_Date
    , Original_Filing_Date_Sub_Segment_Indicator
    , Original_Input_Name_Text
    , Original_Loan_Amount
    , Origination_Code
    , Paid_Accounts_Counter
    , Past_Due_Amount
    , Payment_Counters_Sub_Segment_Indicator
    , Payment_Profile
    , Payment_Profile_Sub_Segment_Indicator
    , Percentile
    , Phone
    , Phone_Number
    , Phone_Number_Source
    , Phone_Number_Sub_Segment_Indicator
    , Phone_Number_Type
    , Pin_SubSegment_Indicator // new for s314
    , Pin_Text // new for s314
    , Plaintiff_Name
    , Plaintiff_Sub_Segment_Indicator
    , Preamble
    , Public_Record_Alert_Bankruptcy
    , Public_Record_Alert_Bankruptcy_Dismissed_Or_Discharged
    , Public_Record_Alert_Judgement
    , Public_Record_Alert_Judgement_Satisfied_Vacated
    , Public_Record_Alert_Tax_Lien
    , Public_Record_Alert_Tax_Lien_released
    , Public_Record_ECOA_Sub_Segment_Indicator
    , Public_Records_Count
    , Purchased_From_Sub_Segment_Indicator
    , Real_Estate_Balance
    , Real_Estate_Payment
    , Real_Estate_Payment_Partial_Flag
    , Recent_Opened_Indicator
    , Record_ID
    , Record_Length
    , Record_Type
    , Reference_ID_Sub_Segment_Indicator
    , Reference_Number
    , Reference_Number_Sub_Segment_Indicator
    , Region_Code
    , Repayment_Percent
    , Report_Code
    , Report_Date
    , Report_Text
    , Report_Time
    , Report_Type
    , Report_Type_SubSegment_Indicator
    , Reserved
    , Reserved_For_Future_Use
    , Revolving_Available_Partial_Flag
    , Rev_Install
    , Risk_Score
    , Satisfactory__Accounts_Counter
    , Score
    , Score_Factor_Codes
    , Score_Percentile_SubSegment_Indicator
    , Secondary_Agency_Code
    , Secondary_Agency_ID
    , Second_Delinquency_Date
    , SIC_Codes
    , Spacing_Control
    , Social_Count
    , Social_Date
    , Social_Error_Code
    , Social_Security_Number
    , Social_Security_Number_Match
    , Sold_To_Sub_Segment_Indicator
    , Sold_To_Text
    , Special_Comment
    , Special_Comment_Code
    , Special_Payment_Amount
    , Special_Payment_Code
    , Special_Payment_Date
    , Special_Trades_Sub_Segment_Indicator
    , Spouse_Data_Sub_Segment_Indicator
    , Spouse_Initial
    , Spouse_Name
    , Spouse_SSN
    , SSN_Variation_Indicator
    , Statement_Text
    , State
    , State_Code
    , Status
    , Status_Code
    , Status_Date
    , Status_Indicator
    , Street_Initial
    , Street_Number
    , Subscriber_Address
    , Subscriber_Address_Length
    , Subscriber_Address_Sub_Segment_Indicator
    , Subscriber_City_Length
    , Subscriber_City
    , Subscriber_ID_Sub_Segment_Indicator
    , Subscriber_Name
    , Subscriber_Name_Length
    , Subscriber_Name_Phone_Sub_Segment_Indicator
    , Subscriber_Number
    , Subscriber_Phone_Number
    , Subscriber_State
    , Subscriber_ZIP_Code
    , Sub_Code
    , Sub_Name_Length
    , Sub_Segment_Length
    , Suppress_Code_1 // new for s314
    , Suppress_Code_2 // new for s314
    , Surname
    , Terms
    , Terms_Duration
    , Terms_Frequency
    , Text_Data
    , Text_Length
    , Text_Message
    , Total_Length
    , Total_Number_Of_Auto_Loans_And_Leases
    , Total_Number_Of_Open_Auto_Loans_And_Leases
    , Total_Number_30_Day_Delinquencies_For_All_Auto_Loans_Lease
    , Total_Number_60_Day_Delinquencies_For_All_Auto_Loans_Lease
    , Total_Number_90_Day_Delinquencies_For_All_Auto_Loans_Lease
    , Total_Inquiries_Counter
    , Total_Open_Auto_Loan_Lease_Balance
    , Total_Open_Auto_Loan_Lease_Monthly_Payment_Amount
    , Total_Revolving_Available_Percent
    , Total_Revolving_Balance
    , Total_Segments
    , Total_Trade_Items_Counter
    , Trade_Special_Payment_Sub_Segment_Indicator
    , Type
    , Type_Category
    , Type_Code
    , Verification_Indicator
    , Verified_Social_Security_Counter
    , Version_Number
    , Vol_Invold_Value
    , Was_Delinquent_Derog_Counter
    , Worst_Delinquency_All_Auto_Loans_Lease
    , Worst_Delinquency_Date_All_Auto_Loans_Lease
    , Year_Of_Birth
    , Y2K_Address_Date
    , Y2K_Balance_Date
    , Y2K_Date_Sub_Segment_Indicator
    , Y2K_Date_Open
    , Y2K_First_Delinquency_Date
    , Y2K_First_Reported_Date
    , Y2K_Initial_Payment_Level_Date
    , Y2K_Inquiry_Date
    , Y2K_Last_Payment_Date
    , Y2K_Last_Updated_Date
    , Y2K_Maximum_Delinquency_Date
    , Y2k_Oldest_Trade_Date
    , Y2K_Original_Filing_Date
    , Y2K_Report_Date
    , Y2K_Second_Delinquency_Date
    , Y2K_Social_Date
    , Y2K_Status_Date
    , Zip_Code
    , _1_Address_Line
    , _1_Address_Line_Length
    , _2_Address_Line
    , _2_Address_Line_Length
    , _30_Day_Counter
    , _60_Day_Counter
    , _84_Month_Grid_Sub_Segment_Indicator
    , _90_Plus_Day_Counter
    = Value
  }

  import FieldName._

  /** ARF subsegment names. */
  object SubSegmentIndicator extends Enumeration {
    type SubSegmentIndicator = Value
    val A1
    , A2
    , A3
    , A4
    , A6
    , A7
    , A8
    , A9
    , B1
    , B2
    , B3
    , B4
    , B5
    , B6
    , B7
    , B8
    , B9
    , C1
    , C2
    , C3
    , C4
    , C5
    , C6
    , C7
    , C8
    , C9
    , D1
    , D2
    , D3
    , E1
    , E3
    , E4
    , E5
    , E6
    , F1
    , F2
    , F3
    , J2
    , J3
    , J4
    , J5
    , J6
    , K1
    , K2
    , K3
    , K4
    , K5
    , K6
    , K7
    , K8
    , R1
    , SP
    , X1
    , X2
    , X3
    , X4
    , X5
    , Y2
    , Y7
    = Value
  }

  import SubSegmentIndicator._

  object ApplicationIds extends Enumeration {
    type ApplicationIds = Value
    val C, D, N = Value
  }

  object InquiryDataElements extends Enumeration {
    type InquiryDataElements = Value
    val S, A, N = Value
  }

  object IDVerificationResultCode extends Enumeration {
    type IDVerificationResultCode = Value
    val A, B, C, D, E = Value
  }

  object PresentOptionIndicator extends Enumeration {
    type PresentOptionIndicator = Value
    val Y, N, X = Value
  }

  object PresetOptionIndicator extends Enumeration {
    type PresetOptionIndicator = Value
    val Y, N, M, X, Blank, Error = Value
  }

  object VerificationIndicator extends Enumeration {
    type VerificationIndicator = Value
    val _1, _2, _3, Blank = Value
  }

  object SignOfRiskScore extends Enumeration {
    type SignOfRiskScore = Value
    val N, P = Value
  }

  object ActionIndicator extends Enumeration {
    type ActionIndicator = Value
    val I, C, R, S = Value
  }

  object GenerationCode extends Enumeration {
    type GenerationCode = Value
    val J, S, _2, _3, _4, _5, _6, _7, _8, _9, Blank = Value
  }

  object SsnVariationIndicator extends Enumeration {
    type GenerationCode = Value
    val *, Blank = Value
  }

  object MatchIndicator extends Enumeration {
    type GenerationCode = Value
    val Y, N = Value
  }

  object PhoneNumberSourceIndicator extends Enumeration {
    type PhoneNumberSourceIndicator = Value
    val P, U, I, D, Blank = Value
  }

  object PhoneNumberTypeIndicator extends Enumeration {
    type PhoneNumberSourceIndicator = Value
    val R, B, P, F, C, T, I, Blank = Value
  }

  object NameTypeIndicator extends Enumeration {
    type NameTypeIndicator = Value
    val A, N, S = Value
  }

  object DwellingTypeIndicator extends Enumeration {
    type DwellingTypeIndicator = Value
    val A, F, G, M, P, R, S, U, _1, _2, _3 = Value
  }

  object AmountQualifierIndicator extends Enumeration {
    type AmountQualifierIndicator = Value
    val L, H, O, C, Blank = Value
  }

  object TermsDurationIndicator extends Enumeration {
    type TermsDurationIndicator = Value
    val _001, _010, REV, UNK, LOC = Value
  }

  object TermsFrequencyIndicator extends Enumeration {
    type TermsFrequencyIndicator = Value
    val D, P, W, B, E, M, L, Q, T, S, Y, Blank = Value
  }

  object OriginalCreditorIndicator extends Enumeration {
    type OriginalCreditorIndicator = Value
    val _01, _02, _03, _04, _05, _06, _07, _08, _09, _10, _11, _12, _13, _14, _15 = Value
  }

  object SecondaryAgencyIndicator extends Enumeration {
    type SecondaryAgencyIndicator = Value
    val _01, _02 = Value
  }

  object ComplianceConditionIndicator extends Enumeration {
    type ComplianceConditionIndicator = Value
    val XA, XB, XC, XD, XE, XF, XG, XH, XJ = Value
  }

  object ConsumerInformationIndicator extends Enumeration {
    type ConsumerInformationIndicator = Value
    val A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, Y, Z, _1A, _67 = Value
  }

  object _84MonthGridIndicator extends Enumeration {
    type _84MonthGridIndicator = Value
    val B, C, G, H, J, K, L, _0, _1, _2, _3, _4, _5, _6, _7, _8, _9, _Blank = Value
  }

  object SpecialTradesStatusIndicator extends Enumeration {
    type SpecialTradesStatusIndicator = Value
    val C, D, P, U, X = Value
  }

  object TypeCategoryIndicator extends Enumeration {
    type TypeCategoryIndicator = Value
    val AUT, ILN, REL, BCC, RET, CHG, OTH = Value
  }

  object SocialErrorCodeIndicator extends Enumeration {
    type TypeCategoryIndicator = Value
    val _0, _2, _3 = Value
  }

  object AddressErrorCodeIndicator extends Enumeration {
    type TypeCategoryIndicator = Value
    val _0, _2, _3 = Value
  }

  type Field = (FieldName, Int, Boolean)

  type Fields = List[Field]

  type SubSegments = Map[SubSegmentIndicator, Fields]

  case class Segment(id: String, occurrence: Int = 1, fields: Fields, subSegments: SubSegments = Map())

  implicit object ARFDictionary extends Dictionary {

    override val map: Map[String, Segment] = ListMap(

      "100" -> Segment(
        id = "100"
        , fields = List(
          (Host_ID, 1, false)
          , (Application_ID, 1, false)
          , (Report_Date, 6, false)
          , (Report_Time, 6, false)
          , (Report_Type, 1, false)
          , (Preamble, 4, false)
          , (Region_Code, 1, false)
          , (Version_Number, 2, false)
          , (Surname, 10, false)
          , (First_Name, 3, false)
          , (M_Keyword_Length, 2, false)
          , (M_Keyword_Text, 0, false)
        )
        , subSegments = ListMap(
          K1 -> List(
            (Y2K_Report_Date, 8, false)
          )
        )
      )
      , "110" -> Segment(
        id = "110"
        , fields = List(
          (Report_Date, 6, false)
          , (Report_Time, 6, false)
          , (Preamble, 4, false)
          , (Version_Number, 2, false)
          , (M_Keyword_Length, 2, false)
          , (M_Keyword_Text, 0, false)
        )
        , subSegments = ListMap(
          K1 -> List(
            (Y2K_Report_Date, 8, false)
          )
          , R1 -> List(
            (Inquiring_Sub_Code, 7, false)
            , (Report_Code, 2, false)
            , (Applicant_Code, 1, false)
          )
        )
      )
      , "111" -> Segment(
        id = "111"
        , fields = List(
          (Input_Type, 1, false)
          , (ID_Match_Condition, 1, false)
          , (Reserved_For_Future_Use, 1, false)
          , (Credit_Class_Code, 1, false)
          , (High_Risk_Address_Alert, 1, false)
          , (Additional_Names_Addresses_For_ID_Match_Condition_C, 1, false)
          , (Additional_Names_Addresses_For_ID_Match_Condition_D, 1, false)
          , (Additional_Names_Addresses_For_ID_Match_Condition_E, 1, false)
          , (Score, 1, false)
          , (Full_Credit_Report_Description, 1, false)
          , (Public_Record_Alert_Bankruptcy, 1, false)
          , (Public_Record_Alert_Judgement, 1, false)
          , (Public_Record_Alert_Tax_Lien, 1, false)
          , (Public_Record_Alert_Bankruptcy_Dismissed_Or_Discharged, 1, false)
          , (Public_Record_Alert_Judgement_Satisfied_Vacated, 1, false)
          , (Public_Record_Alert_Tax_Lien_released, 1, false)
          , (Length_Of_Original_Input_Name, 2, false)
          , (Original_Input_Name_Text, 0, false)
          , (Length_Of_Customer_Sales_Message, 2, false)
          , (Customer_Sales_Message, 0, false)
        )
      )
      , "112" -> Segment(
        id = "112", occurrence = 0 // UNDEFINE
        , fields = List(
          (Sub_Code, 7, false)
          , (Status_Code, 2, false)
          , (Account_Type_Code, 2, false)
          , (Special_Comment, 2, false)
          , (Balance_Amount, 8, false)
          , (Balance_Date, 6, false)
          , (Reserved_For_Future_Use, 17, false)
          , (Account_Number_Length, 2, false)
          , (Account_Number, 0, false)
          , (Reserved_For_Future_Use, 26, false)
        )
      )
      , "113" -> Segment(
        id = "113", occurrence = 0 // UNDEFINE
        , fields = List(
          (Sub_Code, 7, false)
          , (Inquiry_Date, 6, false)
          , (Inquiry_Type, 2, false)
          , (Inquiry_Terms, 3, false)
          , (Inquiry_Amount, 8, false)
          , (Account_Number_Length, 2, false)
          , (Account_Number, 0, false)
          , (Reserved_For_Future_Use, 26, false)
        )
      )
      , "120" -> Segment(
        id = "120"
        , fields = List(
          (Encrypted_PIN, 15, false)
          , (Checksum, 2, false)
          , (Verification_Indicator, 1, false)
          , (Reserved_For_Future_Use, 8, false)
        )
      )
      , "125" -> Segment(
        id = "125", occurrence = -1 // UNLIMITED
        , fields = List(
          (Risk_Score, 4, false)
          , (Evaluation, 1, false)
          , (Score_Factor_Codes, 8, false)
          , (Model_Type_Indicator, 1, false)
        )
      )
      , "130" -> Segment(
        id = "130", occurrence = -1 // UNLIMITED
        , fields = List(
          (Risk_Score, 4, false)
          , (Evaluation, 1, false)
          , (Score_Factor_Codes, 8, false)
          , (Model_Type_Indicator, 2, false)
        )
        , subSegments = ListMap(
          SP -> List(
            (Percentile, 2, false)
          )
        )
      )
      , "200" -> Segment(
        id = "200"
        , fields = List(
          (Error_Code, 3, false)
          , (Action_Indicator, 1, false)
          , (Module_Ident, 3, false)
        )
      )
      , "260" -> Segment(
        id = "260"
        , fields = List(
          (Text_Length, 2, false)
          , (Text_Message, 0, false)
        )
      )
      , "314" -> Segment(
        id = "314"
        , fields = List(
          (Surname, 10, false)
          , (First_Name, 3, false)
          , (Middle_Initial, 1, false)
          , (Spouse_Initial, 1, false)
          , (Generation_Code, 1, false)
          , (Street_Number, 4, false)
          , (Street_Initial, 1, false)
          , (Zip_Code, 5, false)
          , (Year_Of_Birth, 4, false)
          , (Verified_Social_Security_Counter, 2, false)
        )
        , subSegments = ListMap(
          Y7 -> List(
            (Pin_Text, 10, false)
            , (Do_Not_Combine_Indicator, 1, false)
            , (CSI_Member_Number, 12, false)
            , (Gender_Code, 1, false)
            , (Gender_Code_Soft_Delete_Indicator, 1, false)
            , (Suppress_Code_1, 1, false)
            , (Suppress_Code_2, 1, false)
          )
        )
      )
      , "322" -> Segment(
        id = "322"
        , fields = List(
          (SSN_Variation_Indicator, 1, false)
          , (Social_Security_Number, 9, false)
        )
        , subSegments = ListMap(
          A3 -> List(
            (Nine_SSN_Match_Indicator, 9, false)
          )
        )
      )
      , "335" -> Segment(
        id = "335"
        , fields = List(
          (Name_Length, 2, false)
          , (Name_Text, 0, false)
          , (Year_Of_Birth, 4, false)
        )
        , subSegments = ListMap(
          A1 -> List(
            (Spouse_Name, 11, false)
            , (Spouse_SSN, 9, false)
          )
          , A2 -> List(
            (Phone_Number, 10, false)
            , (Phone_Number_Source, 1, false)
            , (Phone_Number_Type, 1, false)
          )
          , E1 -> List(
            (Name_Type, 1, false)
          )
          , E6 -> List(
            (Date_Of_Birth, 8, false)
          )
          , X4 -> List(
            (Number_Of_Creditors_Reporting_Same_SSN, 2, false)
            , (Social_Security_Number, 9, false)
          )
        )
      )
      , "336" -> Segment(
        id = "336"
        , fields = List(
          (First_Reported_Date, 4, false)
          , (Last_Updated_Date, 4, false)
          , (Origination_Code, 1, false)
          , (Number_Of_Times_Reported, 2, false)
          , (Last_Sub_Code_Reporting, 7, false)
          , (Dwelling_Type, 1, false)
          , (Reserved_For_Future_Use, 1, false)
          , (Census_GEO_Code, 7, false)
          , (Address_Text_Length, 2, false)
          , (Address_Text, 0, false)
        )
        , subSegments = ListMap(
          D2 -> List(
            (Country_Code, 3, false)
          )
          , D3 -> List(
            (State_Code, 2, false)
            , (MSA_Code, 4, false)
          )
          , K2 -> List(
            (Y2K_First_Reported_Date, 8, false)
            , (Y2K_Last_Updated_Date, 8, false)
          )
          , X1 -> List(
            (Phone, 10, false)
            , (Subscriber_Name_Length, 2, false)
            , (Subscriber_Name, 0, false)
          )
          , X5 -> List(
            (Subscriber_Address_Length, 2, false)
            , (Subscriber_Address, 0, false)
            , (Subscriber_City_Length, 2, false)
            , (Subscriber_City, 0, false)
            , (Subscriber_State, 2, false)
            , (Subscriber_ZIP_Code, 5, false)
          )
        )
      )
      , "337" -> Segment(
        id = "337"
        , fields = List(
          (First_Reported_Date, 4, false)
          , (Last_Updated_Date, 4, false)
          , (Origination_Code, 1, false)
          , (Employer_Name_Length, 2, false)
          , (Employer_Name, 0, false)
          , (_1_Address_Line_Length, 2, false)
          , (_1_Address_Line, 0, false)
          , (_2_Address_Line_Length, 2, false)
          , (_2_Address_Line, 0, false)
          , (Extra_Address_Line_Length, 2, false)
          , (Extra_Address_Line, 0, false)
          , (Employer_ZIP_Code, 10, false)
        )
        , subSegments = ListMap(
          K2 -> List(
            (Y2K_First_Reported_Date, 8, false)
            , (Y2K_Last_Updated_Date, 8, false)
          )
        )
      )
      , "350" -> Segment(
        id = "350"
        , fields = List(
          (Status, 2, false)
          , (Evaluation, 1, false)
          , (Status_Date, 6, false)
          , (Amount, 8, false)
          , (Consumer_Comment_Length, 4, false)
          , (Consumer_Comment, 0, false)
        )
        , subSegments = ListMap(
          A6 -> List(
            (Court_Code, 7, false)
            , (Court_Name_Length, 2, false)
            , (Court_Name_Text, 0, false)
          )
          , A7 -> List(
            (Reference_Number, 0, false)
          )
          , A8 -> List(
            (Plaintiff_Name, 0, false)
          )
          , C1 -> List(
            (Consumer_Dispute_Flag, 1, false)
          )
          , E3 -> List(
            (ECOA_Code, 1, false)
          )
          , E4 -> List(
            (Asset_Amount, 8, false)
            , (Liabilities_Amount, 8, false)
            , (Repayment_Percent, 3, false)
            , (Adjustment_Percent, 2, false)
          )
          , E5 -> List(
            (Plaintiff_Name, 0, false)
          )
          , F1 -> List(
            (Vol_Invold_Value, 1, false)
          )
          , K3 -> List(
            (Y2K_Status_Date, 8, false)
            , (Y2K_Original_Filing_Date, 8, false)
          )
          , Y2 -> List(
            (Original_Filing_Date, 6, false)
          )
        )
      )
      , "357" -> Segment(
        id = "357"
        , fields = List(
          (Special_Comment_Code, 2, false)
          , (Evaluation, 1, false)
          , (Date_Open, 4, false)
          , (Status_Date, 4, false)
          , (Maximum_Delinquency_Date, 4, false)
          , (Type_Code, 2, false)
          , (Terms, 3, false)
          , (ECOA, 1, false)
          , (Amount_1, 8, false)
          , (Amount_1_Qualifier, 1, false)
          , (Amount_2, 8, false)
          , (Amount_2_Qualifier, 1, false)
          , (Balance_Date, 6, false)
          , (Balance_Amount, 8, false)
          , (Status, 2, false)
          , (Reserved, 2, false)
          , (Amount_Past_Due, 8, false)
          , (Open_Close, 1, false)
          , (Rev_Install, 1, false)
          , (Consumer_Comment_Length, 4, false)
          , (Consumer_Comment, 0, false)
        )
        , subSegments = ListMap(
          A9 -> List(
            (Account_Number, 0, false)
          )
          , B1 -> List(
            (Date_Payment_Due, 8, false)
            , (Amount_Of_Balloon_Payment, 8, false)
          )
          , B2 -> List(
            (Months_Reviewed, 2, false)
            , (_30_Day_Counter, 2, false)
            , (_60_Day_Counter, 2, false)
            , (_90_Plus_Day_Counter, 2, false)
            , (Derog_Counter, 2, false)
          )
          , B3 -> List(
            (BK_Chapter_Number, 0, false)
          )
          , B4 -> List(
            (Payment_Profile, 0, false)
          )
          , B5 -> List(
            (Monthly_Payment_Amount, 8, false)
            , (Monthly_Payment_Type, 1, false)
            , (Frequency, 1, false)
            , (Last_Payment_Date, 6, false)
          )
          , B6 -> List(
            (Sub_Code, 7, false)
            , (KOB, 2, false)
            , (Sub_Name_Length, 2, false)
            , (Subscriber_Name, 0, false)
          )
          , B7 -> List(
            (Original_Creditor, 0, false)
          )
          , B8 -> List(
            (Sold_To_Text, 0, false)
          )
          , B9 -> List(
            (Payment_Profile, 0, false)
          )
          , C1 -> List(
            (Consumer_Dispute_Flag, 1, false)
          )
          , F2 -> List(
            (Maximum_Payment_Code, 1, false)
            , (First_Delinquency_Date, 4, false)
            , (Second_Delinquency_Date, 4, false)
          )
          , F3 -> List(
            (Initial_Payment_Level_Date, 4, false)
            , (Account_Condition_Code, 2, false)
            , (Enhanced_Payment_Status, 2, false)
            , (Enhanced_Type_Codes, 2, false)
            , (Enhanced_Special_Comments, 2, false)
          )
          , J2 -> List(
            (Special_Payment_Code, 2, false)
            , (Special_Payment_Date, 8, false)
            , (Special_Payment_Amount, 8, false)
          )
          , J3 -> List(
            (Actual_Payment_Amount, 8, false)
          )
          , J4 -> List(
            (Terms_Duration, 3, false)
            , (Terms_Frequency, 1, false)
            , (Original_Creditor_Classification_Code, 2, false)
            , (Credit_Limit_Amount, 10, false)
            , (High_Balance_Amount, 10, false)
            , (Original_Loan_Amount, 10, false)
            , (Charge_Off_Amount, 10, false)
            , (Secondary_Agency_Code, 2, false)
            , (Secondary_Agency_ID, 18, false)
            , (Compliance_Condition_Code, 2, false)
            , (Consumer_Information_Indicator_Code, 2, false)
            , (Length_Of_Mortgage_ID, 2, false)
            , (Mortgage_ID, 0, false)
          )
          , J5 -> List(
            (Payment_Profile, 0, false)
          )
          , J6 -> List(
            (Payment_Profile, 0, false)
          )
          , K4 -> List(
            (Y2K_Date_Open, 8, false)
            , (Y2K_Status_Date, 8, false)
            , (Y2K_Maximum_Delinquency_Date, 8, false)
            , (Y2K_Balance_Date, 8, false)
            , (Y2K_Last_Payment_Date, 8, false)
            , (Y2K_First_Delinquency_Date, 8, false)
            , (Y2K_Second_Delinquency_Date, 8, false)
            , (Y2K_Initial_Payment_Level_Date, 8, false)
          )
          , X2 -> List(
            (Status_Indicator, 1, false)
            , (Recent_Opened_Indicator, 1, false)
            , (Debt_Counseling_Indicator, 1, false)
            , (Amount_Available, 8, false)
            , (Type_Category, 3, false)
          )
          , X3 -> List(
            (Credit_Grantor_Phone_Number, 10, false)
          )
          , X5 -> List(
            (Subscriber_Address_Length, 2, false)
            , (Subscriber_Address, 0, false)
            , (Subscriber_City_Length, 2, false)
            , (Subscriber_City, 0, false)
            , (Subscriber_State, 2, false)
            , (Subscriber_ZIP_Code, 5, false)
          )
        )
      )
      , "359" -> Segment(
        id = "359"
        , fields = List(
          (Inquiry_Date, 6, false)
          , (Amount, 8, false)
          , (Type, 2, false)
          , (Terms, 3, false)
        )
        , subSegments = ListMap(
          A9 -> List(
            (Account_Number, 0, false)
          )
          , B6 -> List(
            (Sub_Code, 7, false)
            , (KOB, 2, false)
            , (Sub_Name_Length, 2, false)
            , (Subscriber_Name, 0, false)
          )
          , K5 -> List(
            (Y2K_Inquiry_Date, 8, false)
          )
          , X3 -> List(
            (Credit_Grantor_Phone_Number, 10, false)
          )
          , X5 -> List(
            (Subscriber_Address_Length, 2, false)
            , (Subscriber_Address, 0, false)
            , (Subscriber_City_Length, 2, false)
            , (Subscriber_City, 0, false)
            , (Subscriber_State, 2, false)
            , (Subscriber_ZIP_Code, 5, false)
          )
        )
      )
      , "361" -> Segment(
        id = "361"
        , fields = List(
          (Message_Code, 2, false)
          , (Text_Length, 2, false)
          , (Message_Text, 0, false)
        )
      )
      , "365" -> Segment(
        id = "365"
        , fields = List(
          (Type_Code, 2, false)
          , (Date_Reported, 6, false)
          , (Text_Length, 4, false)
          , (Statement_Text, 0, false)
        )
        , subSegments = ListMap(
          K6 -> List(
            (Y2K_Report_Date, 8, false)
          )
        )
      )
      , "370" -> Segment(
        id = "370"
        , fields = List(
          (Subscriber_Number, 7, false)
          , (Subscriber_Phone_Number, 10, false)
          , (Subscriber_Name_Length, 2, false)
          , (Subscriber_Name, 0, false)
          , (Subscriber_Address_Length, 2, false)
          , (Subscriber_Address, 0, false)
          , (Subscriber_City_Length, 2, false)
          , (Subscriber_City, 0, false)
          , (State, 2, false)
          , (Zip_Code, 5, false)
        )
      )
      , "382" -> Segment(
        id = "382"
        , fields = List(
          (Record_Type, 2, false)
          , (SIC_Codes, 8, false)
          , (Text_Length, 3, false)
          , (Text_Data, 0, false)
        )
        , subSegments = ListMap(
          C2 -> List(
            (Social_Date, 6, false)
            , (Social_Count, 4, false)
            , (Social_Error_Code, 1, false)
            , (Address_Date, 6, false)
            , (Address_Count, 4, false)
            , (Address_Error_Code, 1, false)
          )
          , C3 -> List(
            (First_Possible_Year, 4, false)
            , (Last_Possible_Year, 4, false)
          )
          , C4 -> List(
            (Indicator_1, 2, false)
            , (Indicator_2, 2, false)
            , (Indicator_3, 2, false)
            , (Indicator_4, 2, false)
          )
          , D1 -> List(
            (Date_Of_Birth, 8, false)
            , (Date_Of_Death, 8, false)
          )
          , K7 -> List(
            (Y2K_Social_Date, 8, false)
            , (Y2K_Address_Date, 8, false)
          )
        )
      )
      , "700" -> Segment(
        id = "700"
        , fields = List(
          (Spacing_Control, 1, false)
          , (Report_Text, 0, false)
        )
      )
      , "800" -> Segment(
        id = "800"
        , fields = List()
        , subSegments = ListMap(
          C5 -> List(
            (CRS_Office_Name_Text, 0, false)
          )
          , C6 -> List(
            (CRS_Street_Name_Text, 0, false)
          )
          , C7 -> List(
            (CRS_PO_Box_Text, 0, false)
          )
          , C8 -> List(
            (CRS_City_State_ZIP_Text, 0, false)
          )
          , C9 -> List(
            (CRS_Phone_Number_Text, 10, false)
            , (Reserved, 1, false)
          )
        )
      )
      , "835" -> Segment(
        id = "835"
        , fields = List(
          (Message_Code, 2, false)
          , (Attribute_ID, 6, true)
          , (Attribute_Value, 1, true)
        )
      )
      , "836" -> Segment(
        id = "836"
        , fields = List(
          (Message_Code, 2, false)
          , (Attribute_ID, 7, true)
          , (Attribute_Value, 9, true)
        )
      )
      , "838" -> Segment(
        id = "838"
        , fields = List(
          (Message_Code, 2, false)
          , (Attribute_ID, 6, true)
          , (Attribute_Value, 1, true)
        )
      )
      , "840" -> Segment(
        id = "840"
        , fields = List(
          (Total_Open_Auto_Loan_Lease_Balance, 8, false)
          , (Total_Open_Auto_Loan_Lease_Monthly_Payment_Amount, 8, false)
          , (Total_Number_Of_Auto_Loans_And_Leases, 8, false)
          , (Total_Number_Of_Open_Auto_Loans_And_Leases, 8, false)
          , (Total_Number_30_Day_Delinquencies_For_All_Auto_Loans_Lease, 8, false)
          , (Total_Number_60_Day_Delinquencies_For_All_Auto_Loans_Lease, 8, false)
          , (Total_Number_90_Day_Delinquencies_For_All_Auto_Loans_Lease, 8, false)
          , (Worst_Delinquency_All_Auto_Loans_Lease, 12, false)
          , (Worst_Delinquency_Date_All_Auto_Loans_Lease, 8, false)
        )
        , subSegments = ListMap(
          A4 -> List(
            (Auto_Detail_Item_Number, 3, false)
            , (Auto_Detail_Financed_Amount, 8, false)
            , (Auto_Detail_Monthly_Payment_Amount, 8, false)
            , (Auto_Detail_Term, 8, false)
            , (Auto_Detail_Number_Of_Months_Remaining, 8, false)
            , (Auto_Detail_Status_Text, 12, false)
          )
        )
      )
      , "850" -> Segment(
        id = "850"
        , fields = List(
          (Disputed_Accounts_Excluded, 3, false)
          , (Public_Records_Count, 3, false)
          , (Installment_Balance, 8, false)
          , (Real_Estate_Balance, 8, false)
          , (Total_Revolving_Balance, 8, false)
          , (Past_Due_Amount, 8, false)
          , (Monthly_Payment, 8, false)
          , (Monthly_Payment_Partial_Flag, 1, false)
          , (Real_Estate_Payment, 8, false)
          , (Real_Estate_Payment_Partial_Flag, 1, false)
          , (Total_Revolving_Available_Percent, 3, false)
          , (Revolving_Available_Partial_Flag, 1, false)
          , (Total_Inquiries_Counter, 3, false)
          , (Inquiries_During_Last_6_Months_Counter, 3, false)
          , (Total_Trade_Items_Counter, 3, false)
          , (Paid_Accounts_Counter, 3, false)
          , (Satisfactory__Accounts_Counter, 3, false)
          , (Now_Delinquent_Derog_Counter, 3, false)
          , (Was_Delinquent_Derog_Counter, 3, false)
          , (Oldest_Trade_Date, 4, false)
          , (_30_Day_Counter, 2, false)
          , (_60_Day_Counter, 2, false)
          , (_90_Plus_Day_Counter, 2, false)
          , (Derog_Counter, 2, false)
        )
        , subSegments = ListMap(
          K8 -> List(
            (Y2k_Oldest_Trade_Date, 3, false)
          )
        )
      )


      , "950" -> Segment(
        id = "950"
        , fields = List(
          (Total_Segments, 3, false)
          , (Total_Length, 5, false)
        )
      )


    )
  }

}