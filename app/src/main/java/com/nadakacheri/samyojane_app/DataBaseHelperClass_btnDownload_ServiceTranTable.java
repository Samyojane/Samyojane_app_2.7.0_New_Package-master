package com.nadakacheri.samyojane_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelperClass_btnDownload_ServiceTranTable extends SQLiteOpenHelper {

    static int DATABASE_VERSION=1;
    public static String DATABASE_NAME= "ServiceTranTable.db";
    public static String TABLE_NAME="ServiceTranTbl";
    public static String TABLE_NAME_1="ServiceParameterTbl";

    //ST Table Values
    public static String GSCNo = "GSCNo";
    public static String Service_Code = "FacilityCode";
    public static String District_Code = "DistrictCode";
    public static String Taluk_Code = "TalukCode";
    public static String Hobli_Code = "HobliCode";
    public static String Village_Code = "VillageCode";
    public static String Town_Code = "TownCode";
    public static String Ward_Code = "WardNo";
    public static String ApplicationDate = "ApplicationDate";
    public static String ApplicantTiitle = "ApplicantTiitle";
    public static String Applicant_Name = "ApplicantName";
    public static String BinCom = "BinCom";
    public static String RelationTitle = "RelationTitle";
    public static String FatherName = "FatherName";
    public static String MotherName = "MotherName";
    public static String Address1 = "Address1";
    public static String Address2 = "Address2";
    public static String Address3 = "Address3";
    public static String PinCode = "Pincode";
    public static String Mobile_No = "MobileNo";
    public static String ID_TYPE = "IDType";
    public static String IDNo = "IDNo";
    public static String Raised_Location = "RaisedLocation";
    public static String ST_applicant_photo = "ApplicantPhoto";
    public static String ST_Eng_Certificate = "EnglishOrKannada";
    public static String ReservationCategory = "ReservationCategory";
    public static String Caste = "Caste";
    public static String SCOT_caste_app = "SCOT_caste_app";
    public static String AnnualIncome = "AnnualIncome";
    public static String SCOT_annual_income = "SCOT_annual_income";
    public static String ApplicantAge = "ApplicantAge";
    public static String AccountNumber ="AccountNumber";
    public static String Ifsc_code = "IFSC_Code";
    public static String Due_Date = "DueDate";
    public static String GST_No_Mths_Applied = "NoOfMonths_Applied";
    public static String GST_No_Years_Applied = "NoOfYears_Applied";
    public static String Service_Name = "Service_Name";
    public static String Service_Name_k = "Service_Name_k";
    public static String DataUpdateFlag = "DataUpdateFlag";
    public static String Push_Flag = "isVAModifiedVillage";
    public static String VA_IMEI = "VA_IMEI";
    public static String VA_Name = "VA_Name";
    public static String finYear = "FYAppliedFor";
    public static String CertValidity = "CertValidityYear";
    public static String IST_annual_income = "IST_annual_income";
    public static String IST_annual_income_asper_VA = "IST_Annual_Income_asper_VA";
    public static String ReservationCategory_VA = "ReservationCategory_VA";
    public static String Caste_VA = "Caste_VA";
    public static String Income_VA = "Income_VA";
    public static String CST_Caste_Desc = "CST_Caste_Desc";

    //Updated ST Table Values
    public static String UPD_GSCNo = "GscNo";
    public static String UPD_LoginID = "LoginID";
    public static String UPD_Service_Code = "FacilityCode";
    public static String UPD_DesignationCode = "DesignationCode";
    public static String UPD_DifferFromAppinformation = "DifferFromApplicant";
    public static String UPD_Can_Certificate_Given = "CanbeIssued";
    public static String UPD_Remarks = "Remarks";
    public static String UPD_Report_No = "ReportNo";
    public static String UPD_ReportDate = "ReportDate";
    public static String UPD_AppTitle = "AppTitle";
    public static String UPD_BinCom = "BinCom";
    public static String UPD_FatTitle = "FatTitle";
    public static String UPD_FatherName = "FatherName";
    public static String UPD_MotherName = "MotherName";
    public static String UPD_MobileNumber = "MobileNumber";
    public static String UPD_Address1 = "Address1";
    public static String UPD_Address2 = "Address2";
    public static String UPD_Address3 = "Address3";
    public static String UPD_PinCode = "Pincode";
    public static String UPD_Applicant_Category = "ResCatCode";
    public static String UPD_Applicant_Caste = "CasteCode";
    public static String UPD_CasteSl = "CasteSl";
    public static String UPD_Age = "Age";
    public static String UPD_Income = "Income";
    public static String UPD_SCOT_annual_income="SCOT_annual_income";
    public static String UPD_Total_No_Years_10 = "NoofYears";
    public static String UPD_NO_Months_10 = "NoofMonths";
    public static String UPD_App_Father_Category_8 = "FatherCategory";
    public static String UPD_App_Mother_Category_8 = "MotherCategory";
    public static String UPD_APP_Father_Caste_8 = "FatherCaste";
    public static String UPD_APP_Mother_Caste_8 = "MotherCaste";
    public static String UPD_Belongs_Creamy_Layer_6 = "CreamyLayer";
    public static String UPD_Reason_for_Creamy_Layer_6 = "ReasonCreamyLayer";
    public static String UPD_Reside_At_Stated_Address_10 = "ResAddress";
    public static String UPD_Place_Match_With_RationCard_10 = "PlaceMatchWithRationCard";
    public static String UPD_Getting_Other_Pension = "GettingOtherPension";
    public static String UPD_Which_Dept = "WhichDept";
    public static String UPD_Which_Scheme = "WhichScheme";
    public static String UPD_From_Which_Date = "FromWhichDate";
    public static String UPD_Dept_Amount = "DeptAmount";
    public static String UPD_App_Reside_In_Karnataka = "ApplicantResideInKarnataka";
    public static String UPD_Attested_Address = "AttestedAddress";
    public static String UPD_Photo = "Photo";
    public static String UPD_vLat = "vLat";
    public static String UPD_vLong = "vLong";
    public static String UPD_UploadedDate = "UploadedDate";
    public static String UPD_DataUpdateFlag = "DataUpdateFlag";
    public static String UPD_VA_RI_IMEI = "IMEI";
    public static String UPD_VA_RI_Name = "VARIName";


    String CREATE_TABLE ="CREATE TABLE " + TABLE_NAME +"("
            + District_Code+" int,"+Taluk_Code+" int,"+Hobli_Code+" int,"+Village_Code+" int,"+ Town_Code+" int,"
            + Ward_Code+" int,"+ApplicationDate+" TEXT,"+Service_Code+" int,"+ Service_Name+" TEXT,"
            + Service_Name_k+" TEXT,"+ GSCNo +" bigint," + ApplicantTiitle+ " int,"+ Applicant_Name+" TEXT,"
            + Due_Date+" datetime,"+Raised_Location+" TEXT,"+ BinCom+ " int,"+ RelationTitle+ " int,"
            + FatherName +" TEXT,"+ MotherName +" TEXT,"+ ID_TYPE +" int,"+ IDNo +" TEXT," + Mobile_No+" decimal(10,0),"
            + Address1+" TEXT,"+Address2+" TEXT,"+Address3+" TEXT,"+ PinCode +" int,"+ ST_applicant_photo+" TEXT, "
            + ST_Eng_Certificate+" TEXT," + ReservationCategory+ " int,"+ Caste+ " int,"+ SCOT_caste_app+ " int,"+ GST_No_Mths_Applied+ " int,"
            + GST_No_Years_Applied+ " int," + ApplicantAge+ " TEXT," +AnnualIncome + " TEXT," + SCOT_annual_income + " TEXT," + AccountNumber +" bigint,"
            + Ifsc_code + " TEXT," + Push_Flag + " TEXT,"+ VA_IMEI + " TEXT,"+finYear+ " TEXT ," + CertValidity + " TEXT ,"
            + VA_Name + " TEXT," +IST_annual_income+" int," +IST_annual_income_asper_VA+" int,"+ReservationCategory_VA+" int,"+ CST_Caste_Desc+" TEXT,"
            + Caste_VA+" int,"+ Income_VA+" TEXT,"+ DataUpdateFlag+" int)";

    String CREATE_TABLE_1 ="CREATE TABLE " + TABLE_NAME_1 +"("
            + UPD_GSCNo +" TEXT," + UPD_LoginID+" TEXT," + UPD_Service_Code +" int," + UPD_DesignationCode +" int,"
            + UPD_DifferFromAppinformation +" TEXT,"//character
            + UPD_Can_Certificate_Given +" TEXT,"//character
            + UPD_Remarks +" TEXT," + UPD_Report_No +" TEXT," + UPD_ReportDate +" datetime," + UPD_AppTitle +" int," + UPD_BinCom +" int,"
            + UPD_FatTitle +" int," + UPD_FatherName +" TEXT," + UPD_MotherName +" TEXT," + UPD_MobileNumber +" decimal(10,0),"
            + UPD_Address1 +" TEXT," + UPD_Address2 +" TEXT," + UPD_Address3 +" TEXT," + UPD_PinCode +" int," + UPD_Applicant_Category +" int,"
            + UPD_Applicant_Caste +" int," + UPD_CasteSl +" int," + UPD_Age +" TEXT,"+UPD_Income +" int," + UPD_SCOT_annual_income +" int,"+ UPD_Total_No_Years_10 +" int,"
            + UPD_NO_Months_10 +" int," + UPD_App_Father_Category_8 +" int," + UPD_App_Mother_Category_8 +" int,"
            + UPD_APP_Father_Caste_8 +" int," + UPD_APP_Mother_Caste_8 +" int," + UPD_Belongs_Creamy_Layer_6 +" TEXT,"
            + UPD_Reason_for_Creamy_Layer_6 +" int," + UPD_Reside_At_Stated_Address_10 +" TEXT,"
            + UPD_Place_Match_With_RationCard_10 +" TEXT," + UPD_Getting_Other_Pension +" TEXT," + UPD_Which_Dept +" TEXT," + UPD_Which_Scheme +" TEXT,"
            + UPD_From_Which_Date +" TEXT," + UPD_Dept_Amount +" int," + UPD_App_Reside_In_Karnataka +" TEXT," + UPD_Attested_Address +" TEXT,"
            + UPD_Photo +" int," + UPD_vLat +" double," + UPD_vLong +" double,"
            + UPD_UploadedDate +" datetime," + UPD_DataUpdateFlag +" int," + UPD_VA_RI_IMEI +" bigint,"
            +IST_annual_income+" int,"+IST_annual_income_asper_VA+" int,"+ReservationCategory_VA+" int,"+ CST_Caste_Desc+" TEXT,"
            + Caste_VA+" int,"+ Income_VA+" TEXT,"+ UPD_VA_RI_Name +" TEXT)";

    public DataBaseHelperClass_btnDownload_ServiceTranTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("Note", "DataBase Opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_1);
        Log.i("Note",TABLE_NAME+" Table Created");
        Log.i("Note",TABLE_NAME_1+" Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
        Log.i("Note","Table Upgraded");
        db.execSQL(CREATE_TABLE);
    }
}