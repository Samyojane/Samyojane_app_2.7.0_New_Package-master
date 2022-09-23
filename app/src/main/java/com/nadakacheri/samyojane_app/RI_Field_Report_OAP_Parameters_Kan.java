package com.nadakacheri.samyojane_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class RI_Field_Report_OAP_Parameters_Kan extends AppCompatActivity {

    Spinner spinnerDept;
    TextView applicant_infor, tvRI_Name, tvServiceName, tv_V_T_Name,RI_Recommendation;
    String RI_Name,district, taluk, hobli, VA_Circle_Name, VA_Name, applicant_Id, applicant_name;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;
    String raisedLoc, name, fatherName, motherName, rationCardNo, aadharNo, mobileNo, address1, address2, address3, report_No, appAge, strRemarks;
    String appID, appName, add_pin, appAnnualIncome, appReservationGiven;
    String appImage=null;
    int getCatCode=0,getCasteCode=0;
    int income_len, income_Value, amount_len,amount_Value;
    int appTitle_Code, binCom_Code, fatTitle_Code;
    String appTitle_Name, binCom_Name, fatTitle_Name;
    AutoCompleteTextView autoSearchAppTitle, autoSearchBinCom, autoSearchFatTitle;
    EditText etFatherName, etMotherName;
    TextView tvName, tvMobile;
    EditText etAddress1, etAddress2, etAddress3, etPinCode, etAppAge,etAnnualIncome,etScheme, etDeptAmount, etDate, etRemarks;
    TextView txt_raiseLoc, tvHobli, tvTaluk, tvVA_Name, tv_IDName;
    String VA_IMEI;
    int district_Code, taluk_Code, hobli_Code, va_Circle_Code, town_code, ward_code, villageCode;
    RadioGroup radioGroup, radioGroup1, radioGroup2, radioGroup3;
    RadioButton radioButtonA, radioButtonB, radioButton1, radioButton11, radioButton2, radioButton22, radioButton3, radioButton33;
    LinearLayout lnDept, lnDeptScheme, lnDeptDate, lnDeptAmount;
    Button btnSave, btnBack;
    String item_position;
    String option, option1, option2, option3;
    String strDeptName, strDeptAmount,strScheme,strDate,str;
    String strSearchVillageName, strSearchServiceName;
    GPSTracker gpsTracker;
    double latitude, longitude, accuracy;
    ProgressDialog dialog;
    String serviceCode, habitationCode, service_name, village_name, town_Name, ward_Name, option_Flag,report_no;
    Set_and_Get_Service_Parameter set_and_get_service_parameter;
    SqlLiteOpenHelper_Class sqlLiteOpenHelper_class;
    SQLiteAssetHelper_Masters sqLiteAssetHelper_masters;
    String eng_certi;
    boolean return_Value;
    InputMethodManager imm;
    InputMethodSubtype ims;
    TextView txt_ReportNo;
    int Id_Code,getDeptCode;
    String Id_Name;
    LinearLayout trID;
    final int min = 1111;
    final int max = 9999;
    int random;

    String uName_get;
    int DesiCode;
    SharedPreferences sharedPreferences;
    Activity activity;
    List<AutoCompleteTextBox_Object> objects_appTitle = new ArrayList<>();
    List<AutoCompleteTextBox_Object> objects_bincom = new ArrayList<>();
    List<AutoCompleteTextBox_Object> objects_FatTitle = new ArrayList<>();
    ArrayAdapter<AutoCompleteTextBox_Object> adapter_appTitle, adapter_bincom, adapter_fatTitle;

    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";
    public static Key key;
    public static String decryptedValue;

    InputFilter filter_Eng = (source, start, end, dest, dstart, dend) -> {
        Log.d("Source",""+source);
        String l1 = "ಅಆಇಈಉಊಋಎಏಐಒಓಔಅಂಅಃ";
        String l2 = "ಕಕಾಕಿಕೀಕುಕೂಕೃಕೆಕೇಕೈಕೊಕೋಕೌಕಂಕಃಕ್";
        String l3 = "ಖಖಾಖಿಖೀಖುಖೂಖೃಖೆಖೇಖೈಖೊಖೋಖೌಖಂಖಃಖ್";
        String l4 = "ಗಗಾಗಿಗೀಗುಗೂಗೃಗೆಗೇಗೈಗೊಗೋಗೌಗಂಗಃಗ್";
        String l5 = "ಘಘಾಘಿಘೀಘುಘೂಘೃಘೆಘೇಘೈಘೊಘೋಘೌಘಂಘಃಘ್";
        String l6 = "ಙಙಾಙಿಙೀಙುಙೂಙೃಙೆಙೇಙೈಙೊಙೋಙೌಙಂಙಃಙ್";
        String l7 = "ಚಚಾಚಿಚೀಚುಚೂಚೃಚೆಚೇಚೈಚೊಚೋಚೌಚಂಚಃಚ್";
        String l8 = "ಛಛಾಛಿಛೀಛುಛೂಛೃಛೆಛೇಛೈಛೊಛೋಛೌಛಂಛಃಛ್";
        String l9 = "ಜಜಾಜಿಜೀಜುಜೂಜೃಜೆಜೇಜೈಜೊಜೋಜೌಜಂಜಃಜ್";
        String l10 = "ಝಝಾಝಿಝೀಝುಝೂಝೃಝೆಝೇಝೈಝೊಝೋಝೌಝಂಝಃಝ್";
        String l11 = "ಞಞಾಞಿಞೀಞುಞೂಞೃಞೆಞೇಞೈಞೊಞೋಞೌಞಂಞಃಞ್";
        String l12 = "ಟಟಾಟಿಟೀಟುಟೂಟೃಟೆಟೇಟೈಟೊಟೋಟೌಟಂಟಃಟ್";
        String l13 = "ಠಠಾಠಿಠೀಠುಠೂಠೃಠೆಠೇಠೈಠೊಠೋಠೌಠಂಠಃಠ್";
        String l14 = "ಡಡಾಡಿಡೀಡುಡೂಡೃಡೆಡೇಡೈಡೊಡೋಡೌಡಂಡಃಡ್";
        String l15 = "ಢಢಾಢಿಢೀಢುಢೂಢೃಢೆಢೇಢೈಢೊಢೋಢೌಢಂಢಃಢ್";
        String l16 = "ಣಣಾಣಿಣೀಣುಣೂಣೃಣೆಣೇಣೈಣೊಣೋಣೌಣಂಣಃಣ್";
        String l17 = "ತತಾತಿತೀತುತೂತೃತೆತೇತೈತೊತೋತೌತಂತಃತ್";
        String l18 = "ಥಥಾಥಿಥೀಥುಥೂಥೃಥೆಥೇಥೈಥೊಥೋಥೌಥಂಥಃಥ್";
        String l19 = "ದದಾದಿದೀದುದೂದೃದೆದೇದೈದೊದೋದೌದಂದಃದ್";
        String l20 = "ಧಧಾಧಿಧೀಧುಧೂಧೃಧೆಧೇಧೈಧೊಧೋಧೌಧಂಧಃಧ್";
        String l21 = "ನನಾನಿನೀನುನೂನೃನೆನೇನೈನೊನೋನೌನಂನಃನ್";
        String l22 = "ಪಪಾಪಿಪೀಪುಪೂಪೃಪೆಪೇಪೈಪೊಪೋಪೌಪಂಪಃಪ್";
        String l23 = "ಫಫಾಫಿಫೀಫುಫೂಫೃಫೆಫೇಫೈಫೊಫೋಫೌಫಂಫಃಫ್";
        String l24 = "ಬಬಾಬಿಬೀಬುಬೂಬೃಬೆಬೇಬೈಬೊಬೋಬೌಬಂಬಃಬ್";
        String l25 = "ಭಭಾಭಿಭೀಭುಭೂಭೃಭೆಭೇಭೈಭೊಭೋಭೌಭಂಭಃಭ್";
        String l26 = "ಮಮಾಮಿಮೀಮುಮೂಮೃಮೆಮೇಮೈಮೊಮೋಮೌಮಂಮಃಮ್";
        String l27 = "ಯಯಾಯಿಯೀಯುಯೂಯೃಯೆಯೇಯೈಯೊಯೋಯೌಯಂಯಃಯ್";
        String l28 = "ರರಾರಿರೀರುರೂರೃರೆರೇರೈರೊರೋರೌರಂರಃರ್";
        String l29 = "ಱಱಾಱಿಱೀಱುಱೂಱೃಱೆಱೇಱೈಱೊಱೋಱೌಱಂಱಃಱ್";
        String l30 = "ಲಲಾಲಿಲೀಲುಲೂಲೃಲೆಲೇಲೈಲೊಲೋಲೌಲಂಲಃಲ್";
        String l31 = "ವವಾವಿವೀವುವೂವೃವೆವೇವೈವೊವೋವೌವಂವಃವ್";
        String l32 = "ಶಶಾಶಿಶೀಶುಶೂಶೃಶೆಶೇಶೈಶೊಶೋಶೌಶಂಶಃಶ್";
        String l33 = "ಷಷಾಷಿಷೀಷುಷೂಷೃಷೆಷೇಷೈಷೊಷೋಷೌಷಂಷಃಷ್";
        String l34 = "ಸಸಾಸಿಸೀಸುಸೂಸೃಸೆಸೇಸೈಸೊಸೋಸೌಸಂಸಃಸ್";
        String l35 = "ಹಹಾಹಿಹೀಹುಹೂಹೃಹೆಹೇಹೈಹೊಹೋಹೌಹಂಹಃಹ್";
        String l36 = "ಳಳಾಳಿಳೀಳುಳೂಳೃಳೆಳೇಳೈಳೊಳೋಳೌಳಂಳಃಳ್";
        String l37 = "ೞೞಾೞಿೞೀೞುೞೂೞೃೞೆೞೇೞೈೞೊೞೋೞೌೞಂೞಃೞ್";
        String op1 = "~`!@#$%^&*()_-''+={}[]:/?><,.\\\"\";£€¢¥₩§|×÷¿■□♤♡◇♧°•○●☆▪¤《》¡₹Π℅®©™∆√¶1234567890೧೨೩೪೫೬೭೮೯೦";

        String blockCharacterSet = l1+l2+l3+l4+l5+l6+l7+l8+l9+l10+l11+l12+l13+l14+l15+l16+l17+l18+l19+l20
                +l21+l22+l23+l24+l25+l26+l27+l28+l29+l30+l31+l32+l33+l34+l35+l36+l37+op1;
        if (source != null && blockCharacterSet.contains(("" + source))) {
            Log.d("Blocked",""+source);
            return "";
        }
//        for ( int i = start ; i < end ; i++) {
//            String checkMe = String. valueOf (source.charAt(i));
//            //Pattern pattern = Pattern.compile("[\\u0C80-\\u0CFF]");
//            Pattern pattern = Pattern.compile("a-zA-Z");
//            Matcher re = pattern.matcher(checkMe);
//            if (!re.matches()) {
//                Log.d("Filter_valid","blocked");
//                return "";
//            }
//        }
        Log.d("Filter_valid","Not blocked");
        return null;
    };

    InputFilter filter_Kan = (source, start, end, dest, dstart, dend) -> {
        Log.d("Source",""+source);
        String l1 = "abcdefghijklmnopqrstuvwxyz";
        String l2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String op1 = "~`!@#$%^&*()_-''+={}[]:/?><,.\\\"\";£€¢¥₩§|×÷¿■□♤♡◇♧°•○●☆▪¤《》¡₹Π℅®©™∆√¶1234567890೧೨೩೪೫೬೭೮೯೦";
        String blockCharacterSet = l1+l2+op1;

        for (int i = start; i < end; i++) {
            Log.d("source.charAt(i)",""+i+" : "+source.charAt(i));
            if (blockCharacterSet.contains(("" + source.charAt(i)))) {
                Log.d("Blocked",""+source);
                return "";
            }
        }
        Log.d("Filter_valid","Not blocked");
        return null;
    };

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility", "HardwareIds"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ri_field_report_oap_parameters);

        option ="N";
        option1 = "Y";
        option2="Y";
        option3="Y";


        tvTaluk = findViewById(R.id.taluk);
        tvHobli = findViewById(R.id.hobli);
        tvVA_Name = findViewById(R.id.VA_name);

        tvRI_Name = findViewById(R.id.RI_name);
        btnBack = findViewById(R.id.btnBack);
        txt_raiseLoc = findViewById(R.id.txt_raiseLoc);
        tvName = findViewById(R.id.tvName);
        autoSearchAppTitle = findViewById(R.id.autoSearchAppTitle);
        autoSearchBinCom = findViewById(R.id.autoSearchBinCom);
        autoSearchFatTitle = findViewById(R.id.autoSearchFatTitle);
        etFatherName = findViewById(R.id.etFatherNme);
        etMotherName = findViewById(R.id.etMotherName);
        tv_IDName = findViewById(R.id.tv_IDName);
        tvMobile = findViewById(R.id.tvMobile);
        etAddress1 = findViewById(R.id.etAddress1);
        etAddress2 = findViewById(R.id.etAddress2);
        etAddress3 = findViewById(R.id.etAddress3);
        etPinCode = findViewById(R.id.etPinCode);
        etAppAge = findViewById(R.id.etAppAge);
        etAnnualIncome = findViewById(R.id.etAnnualIncome);
        etRemarks = findViewById(R.id.etRemarks);
        btnSave = findViewById(R.id.btnSave);
        tvServiceName = findViewById(R.id.tvServiceName);
        txt_ReportNo = findViewById(R.id.txt_ReportNo);
        tv_V_T_Name = findViewById(R.id.tv_V_T_Name);

        RI_Recommendation = findViewById(R.id.RI_Recommendation);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton11 = findViewById(R.id.radioButton11);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton22 = findViewById(R.id.radioButton22);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton33 = findViewById(R.id.radioButton33);
        spinnerDept = findViewById(R.id.spDept);
        etDate = findViewById(R.id.etDate);
        etScheme = findViewById(R.id.etScheme);
        etDeptAmount = findViewById(R.id.etAmount);
        lnDept = findViewById(R.id.lnDept);
        lnDeptScheme = findViewById(R.id.lnDeptScheme);
        lnDeptDate = findViewById(R.id.lnDeptDate);
        lnDeptAmount = findViewById(R.id.lnDeptAmount);

        applicant_infor= findViewById(R.id.applicant_Information);
        applicant_infor.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        PhoneNumberUtils.formatNumber(etPinCode.getText().toString());


        RI_Recommendation.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        Intent i = getIntent();
        district_Code = i.getIntExtra("district_Code",0);
        district = i.getStringExtra("district");
        taluk_Code = i.getIntExtra("taluk_Code",0);
        taluk = i.getStringExtra("taluk");
        hobli_Code = i.getIntExtra("hobli_Code",0);
        hobli = i.getStringExtra("hobli");
        va_Circle_Code = i.getIntExtra("va_Circle_Code",0);
        VA_Circle_Name = i.getStringExtra("VA_Circle_Name");
        applicant_Id = i.getStringExtra("applicant_Id");
        applicant_name = i.getStringExtra("applicant_name");
        RI_Name = i.getStringExtra("RI_Name");
        VA_Name = i.getStringExtra("VA_Name");
        village_name = i.getStringExtra("strSearchVillageName");
        service_name = i.getStringExtra("strSearchServiceName");
        villageCode = i.getIntExtra("villageCode", 0);
        habitationCode = i.getStringExtra("habitationCode");
        serviceCode = i.getStringExtra("serviceCode");
        town_code = i.getIntExtra("town_code", 0);
        town_Name = i.getStringExtra("town_Name");
        ward_code = i.getIntExtra("ward_code", 0);
        ward_Name = i.getStringExtra("ward_Name");
        option_Flag = i.getStringExtra("option_Flag");
        eng_certi = i.getStringExtra("eng_certi");
        report_no = i.getStringExtra("report_No");

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        DesiCode = sharedPreferences.getInt(Constants.DesiCode_RI, 19);
        uName_get = sharedPreferences.getString(Constants.uName_get, "");

//        if(report_no == null){
//            Date c1 = Calendar.getInstance().getTime();
//
//            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
//            String formattedDate1 = df1.format(c1);
//
//            Log.d("formattedDate", "" + formattedDate1);
//
//            random = new Random().nextInt((max - min) + 1) + min;
//            Log.d("Random_Num", String.valueOf(+random));
//
//            report_no = formattedDate1 + "\\" + random;
//            Log.d("report_no", "" + report_no);
//        }

        RI_Recommendation.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        openHelper = new DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI(this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from "+ DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.TABLE_NAME+" where "
                + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.GSCNo+"='"+applicant_Id+"'", null);
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                eng_certi = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.ST_Eng_Certificate));
                raisedLoc = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Raised_Location));
                appID = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.GSCNo));
                appName = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Applicant_Name));
                fatherName = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.FatherName));
                motherName = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.MotherName));
                mobileNo = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Mobile_No));
                address1 = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Address1));
                address2 = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Address2));
                address3 = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Address3));
                add_pin = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.PinCode));
                appTitle_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.ApplicantTiitle));
                binCom_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.BinCom));
                fatTitle_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.RelationTitle));
                getCatCode = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.ReservationCategory));
                getCasteCode = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Caste));
//                appAge =
                appAnnualIncome = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.AnnualIncome));
                appReservationGiven = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.Can_Certificate_Given));
                strRemarks = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.VA_Remarks));
            }
        } else {
            cursor.close();
        }


        if (eng_certi.equals("K")){
            etRemarks.setFilters(new InputFilter[] { filter_Kan });
        }else if (eng_certi.equals("E")){
            etRemarks.setFilters(new InputFilter[] { filter_Eng });
        }


        Log.d("RI_caste_income", ""+district_Code);
        Log.d("RI_caste_income", ""+taluk_Code);
        Log.d("RI_caste_income",hobli_Code+" RI_Name :"+RI_Name+" VA_Name :"+VA_Name
                +" VillageName :"+village_name+"ServiceName:"+service_name);
        Log.d("RI_caste_income", ""+applicant_Id);
        Log.d("RI_caste_income", ""+habitationCode);
        Log.d( "town_code: ",""+town_code);
        Log.d("town_Name: ",""+town_Name);
        Log.d( "ward_code: ",""+ward_code);
        Log.d( "ward_Name: ",""+ward_Name);
        Log.d("option_Flag: ",""+ option_Flag);

        if(Objects.equals(raisedLoc, "N") || Objects.equals(raisedLoc, "S")){
            txt_raiseLoc.setText(getString(R.string.nadakacheri_raised));
        }else if(Objects.equals(raisedLoc, "P") || Objects.equals(raisedLoc, "B") || Objects.equals(raisedLoc, "K") || Objects.equals(raisedLoc, "G") || Objects.equals(raisedLoc, "I")){
            txt_raiseLoc.setText(getString(R.string.online));
        }else if(Objects.equals(raisedLoc, "E")){
            txt_raiseLoc.setText(getString(R.string.seva_sindhu));
        }else if(Objects.equals(raisedLoc, "W")){
            txt_raiseLoc.setText(getString(R.string.wallet));
        }else {
            txt_raiseLoc.setText(getString(R.string.not_specified));
        }

        tvTaluk.setText(taluk);
        tvHobli.setText(hobli);
        tvRI_Name.setText(RI_Name);
        tvServiceName.setText(service_name);
        txt_ReportNo.setText(report_no);

        gpsTracker = new GPSTracker(getApplicationContext(), this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            Log.d("Location", ""+latitude+""+longitude);
        } else {
            //gpsTracker.showSettingsAlert();
            Toast.makeText(getApplicationContext(), getString(R.string.switch_on_gps), Toast.LENGTH_SHORT).show();
        }

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading));
        dialog.setIndeterminate(true);

        tvName.setText(appName);
        etFatherName.setText(fatherName);
        etMotherName.setText(motherName);
//        tv_IDName.setText(Id_Name);

        tvMobile.setText(mobileNo);
        etAddress1.setText(address1);
        etAddress2.setText(address2);
        etAddress3.setText(address3);
        etPinCode.setText(add_pin);
        etRemarks.setText(strRemarks);

        sqLiteAssetHelper_masters = new SQLiteAssetHelper_Masters(this, activity);
        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
        appTitle_Name = sqLiteAssetHelper_masters.Get_Title_By_Code(appTitle_Code, getString(R.string.title_desc_name));
        binCom_Name = sqLiteAssetHelper_masters.Get_BinCom_By_Code(binCom_Code, getString(R.string.bincom_desc_name));
        fatTitle_Name = sqLiteAssetHelper_masters.Get_Title_By_Code(fatTitle_Code, getString(R.string.title_desc_name));

        GetDept();
        GetAppTitle();
        GetBincom();
        GetFatTitle();

        autoSearchAppTitle.setText(appTitle_Name);
        autoSearchBinCom.setText(binCom_Name);
        autoSearchFatTitle.setText(fatTitle_Name);

        spinnerDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strDeptName = ((SpinnerObject) spinnerDept.getSelectedItem()).getValue();
                Log.d("Selected_Item1", ""+strDeptName);
                getDeptCode = Integer.parseInt(((SpinnerObject) spinnerDept.getSelectedItem()).getId());
                Log.d("Dept_Code1", ""+ getDeptCode);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etDate.setFocusable(false);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDate.setError(null);
                showDateDialog(etDate);
            }
        });


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.radioButtonA) {
                option = "Y";
                lnDept.setVisibility(View.VISIBLE);
                lnDeptScheme.setVisibility(View.VISIBLE);
                lnDeptDate.setVisibility(View.VISIBLE);
                lnDeptAmount.setVisibility(View.VISIBLE);
                //Toast.makeText(getApplicationContext(), "choice: ", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioButtonB) {
                option = "N";
                lnDept.setVisibility(View.GONE);
                lnDeptScheme.setVisibility(View.GONE);
                lnDeptDate.setVisibility(View.GONE);
                lnDeptAmount.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), "choice: ", Toast.LENGTH_SHORT).show();
            }
            Log.d("option", option);
        });

        radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.radioButton1) {
                option1 = "Y";
            } else if (checkedId == R.id.radioButton11) {
                option1 ="N";
            }
            Log.d("option1", ""+option1);
        });

        radioGroup2.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.radioButton2) {
                option2 = "Y";
            } else if (checkedId == R.id.radioButton22) {
                option2 ="N";
            }
            Log.d("option2", ""+option2);
        });

        radioGroup3.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.radioButton3) {
                option3 ="Y";
            } else if (checkedId == R.id.radioButton33) {
                option3 ="N";
            }
            Log.d("option3", ""+option3);
        });

        autoSearchAppTitle.setOnTouchListener((v, event) -> {
            autoSearchAppTitle.showDropDown();
            autoSearchAppTitle.setError(null);
            return false;
        });

        autoSearchBinCom.setOnTouchListener((v, event) -> {
            autoSearchBinCom.showDropDown();
            autoSearchBinCom.setError(null);
            return false;
        });

        autoSearchFatTitle.setOnTouchListener((v, event) -> {
            autoSearchFatTitle.showDropDown();
            autoSearchFatTitle.setError(null);
            return false;
        });

        autoSearchAppTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                appTitle_Name = parent.getItemAtPosition(position).toString();
                sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
                appTitle_Code = sqLiteAssetHelper_masters.Get_Title_By_NAME(appTitle_Name, getString(R.string.title_desc_name));
                Log.d("Selected_Item", ""+appTitle_Name+", ID:"+appTitle_Code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        autoSearchBinCom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binCom_Name = parent.getItemAtPosition(position).toString();
                sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
                binCom_Code = sqLiteAssetHelper_masters.Get_BinCom_By_Name(binCom_Name, getString(R.string.bincom_desc_name));
                Log.d("Selected_Item", ""+binCom_Name+", ID:"+binCom_Code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        autoSearchFatTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fatTitle_Name = parent.getItemAtPosition(position).toString();
                sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
                fatTitle_Code = sqLiteAssetHelper_masters.Get_Title_By_NAME(fatTitle_Name, getString(R.string.title_desc_name));
                Log.d("Selected_Item", "" + fatTitle_Name + ", ID:" + fatTitle_Code);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(v -> {
            try {
                appTitle_Name = autoSearchAppTitle.getText().toString();
                binCom_Name = autoSearchBinCom.getText().toString();
                fatTitle_Name = autoSearchFatTitle.getText().toString();
                fatherName = etFatherName.getText().toString();
                motherName = etMotherName.getText().toString();
                address1 = etAddress1.getText().toString();
                address2 = etAddress2.getText().toString();
                address3 = etAddress3.getText().toString();
                add_pin = etPinCode.getText().toString();

                appAge = etAppAge.getText().toString();
                appAnnualIncome = etAnnualIncome.getText().toString();
                strDate = etDate.getText().toString().trim();
                strScheme = etScheme.getText().toString().trim();
                strDeptAmount = etDeptAmount.getText().toString().trim();
                strRemarks = etRemarks.getText().toString().trim();


                Log.d("appTitle_Name", ""+appTitle_Name);
                Log.d("binCom_Name", ""+binCom_Name);
                Log.d("fatTitle_Name", ""+fatTitle_Name);
                Log.d("fatherName", ""+fatherName);
                Log.d("motherName", ""+motherName);
                Log.d("add1", ""+address1);
                Log.d("add2", ""+address2);
                Log.d("add3", ""+address3);
                Log.d("add_pin", ""+add_pin);
                Log.d("mobileNo", ""+mobileNo);
                Log.d("report_No", ""+report_No);
                Log.d("Income value", "" + strRemarks);

                if (gpsTracker.canGetLocation()) {
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();
                    Log.d("Location", latitude + "" + longitude);
                } else {
                    //gpsTracker.showSettingsAlert();
                    Toast.makeText(getApplicationContext(), getString(R.string.switch_on_gps), Toast.LENGTH_SHORT).show();
                }

                if (latitude != 0.0 && longitude != 0.0) {
                    if (!appTitle_Name.isEmpty()) {
                        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
                        appTitle_Code = sqLiteAssetHelper_masters.Get_Title_By_NAME(appTitle_Name, getString(R.string.title_desc_name));
                        if (appTitle_Code != 0) {
                            if (!binCom_Name.isEmpty()) {
                                sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
                                binCom_Code = sqLiteAssetHelper_masters.Get_BinCom_By_Name(binCom_Name, getString(R.string.bincom_desc_name));
                                if (binCom_Code != 0) {
                                    if (!fatTitle_Name.isEmpty()) {
                                        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
                                        fatTitle_Code = sqLiteAssetHelper_masters.Get_Title_By_NAME(fatTitle_Name, getString(R.string.title_desc_name));
                                        if (fatTitle_Code != 0) {
                                            if (!fatherName.isEmpty()) {
                                                if (!motherName.isEmpty()) {
                                                    if (!address1.isEmpty()) {
                                                        if (!address2.isEmpty()) {
                                                            if (!address3.isEmpty()) {
                                                                if (!add_pin.isEmpty()) {
                                                                    if (!appAge.isEmpty()) {
                                                                        if (TextUtils.isEmpty(appAnnualIncome)) {
                                                                            etAnnualIncome.setError(getString(R.string.field_canno_null));
                                                                        } else {
                                                                            income_len = appAnnualIncome.length();
                                                                            income_Value = Integer.parseInt(appAnnualIncome);
                                                                            Log.d("Income value", "" + appAnnualIncome + ", Length: " + income_len + ", Value: " + income_Value);
                                                                            if (income_len >= 4 && income_Value >= 1000) {
                                                                                if (option.equals("Y")) {
                                                                                    if (!strDeptName.equals(getString(R.string.select_Dept_spinner))) {
                                                                                        if (!strScheme.isEmpty()) {
                                                                                            if (!strDate.isEmpty()) {
                                                                                                if (TextUtils.isEmpty(strDeptAmount)) {
                                                                                                    etDeptAmount.setError(getString(R.string.field_canno_null));
                                                                                                } else {
                                                                                                    amount_len = strDeptAmount.length();
                                                                                                    amount_Value = Integer.parseInt(strDeptAmount);
                                                                                                    Log.d("Income value", "" + strDeptAmount + ", Length: " + amount_len + ", Value: " + amount_Value);

                                                                                                    if (amount_len >= 4 && amount_Value >= 1000) {
                                                                                                        if (TextUtils.isEmpty(strRemarks)) {
                                                                                                            etRemarks.setError(getString(R.string.field_canno_null));

                                                                                                            openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(RI_Field_Report_OAP_Parameters_Kan.this);
                                                                                                            database = openHelper.getWritableDatabase();

                                                                                                            Cursor cursor3 = database.rawQuery("select * from " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + " where "
                                                                                                                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + "='" + applicant_Id + "'", null);
                                                                                                            if (cursor3.getCount() > 0) {

                                                                                                                database.execSQL("delete from " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + " where "
                                                                                                                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + "='" + applicant_Id + "'");

                                                                                                                Log.d("Database", "ServiceParameterTable delete GSC_No:" + applicant_Id);

                                                                                                            } else {
                                                                                                                cursor3.close();
                                                                                                            }
                                                                                                        } else {

                                                                                                            StoreData_in_DB();
                                                                                                        }
                                                                                                    } else {
                                                                                                        etDeptAmount.setError(getString(R.string.incorrect_value));
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                etDate.setError(getString(R.string.field_canno_null));
                                                                                            }
                                                                                        } else {

                                                                                            etScheme.setError(getString(R.string.field_canno_null));
                                                                                        }
                                                                                    } else {
                                                                                        ((TextView) spinnerDept.getSelectedView()).setError(getString(R.string.select_department));
                                                                                        Toast.makeText(getApplicationContext(), getString(R.string.select_category), Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                } else {
                                                                                    if (TextUtils.isEmpty(strRemarks)) {
                                                                                        etRemarks.setError(getString(R.string.field_canno_null));
                                                                                    }
                                                                                    else{
                                                                                        StoreData_in_DB();
                                                                                    }

                                                                                    Log.d("value", "Working else");
                                                                                }
                                                                            } else {
                                                                                etAnnualIncome.setError(getString(R.string.incorrect_value));
                                                                            }
                                                                        }
                                                                    } else {
                                                                        etAppAge.setError(getString(R.string.field_canno_null));
                                                                    }
                                                                } else {
                                                                    etPinCode.setError(getString(R.string.enter_address));
                                                                }
                                                            } else {
                                                                etAddress3.setError(getString(R.string.enter_address));
                                                            }
                                                        } else {
                                                            etAddress2.setError(getString(R.string.enter_address));
                                                        }
                                                    } else {
                                                        etAddress1.setError(getString(R.string.enter_address));
                                                    }
                                                } else {
                                                    etMotherName.setError(getString(R.string.enter_mother_name));
                                                }
                                            } else {
                                                etFatherName.setError(getString(R.string.enter_father_husband_name));
                                            }
                                        } else {
                                            autoSearchFatTitle.setError(getString(R.string.incorrect_value));
                                        }
                                    } else {
                                        autoSearchFatTitle.setError(getString(R.string.select));
                                    }
                                } else {
                                    autoSearchBinCom.setError(getString(R.string.incorrect_value));
                                }
                            } else {
                                autoSearchBinCom.setError(getString(R.string.select));
                            }
                        } else {
                            autoSearchAppTitle.setError(getString(R.string.incorrect_value));
                        }
                    } else {
                        autoSearchAppTitle.setError(getString(R.string.select));
                    }
                }else {
                    runOnUiThread(() -> {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(RI_Field_Report_OAP_Parameters_Kan.this);
                        dialog.setCancelable(false);
                        dialog.setTitle(getString(R.string.alert));
                        dialog.setMessage(getString(R.string.cannot_get_location));
                        dialog.setNegativeButton(getString(R.string.ok), (dialog1, which) -> {
                            //Action for "Cancel".
                            dialog1.cancel();
                        });

                        final AlertDialog alert = dialog.create();
                        alert.show();
                    });
                    //Toast.makeText(getApplicationContext(), "Please Switch on the GPS", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    public String StoreData_in_DB(){

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        String currDate = dateFormat.format(date);

        openHelper = new DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI(RI_Field_Report_OAP_Parameters_Kan.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.TABLE_NAME + " where "
                + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.GSCNo + "='"+applicant_Id+"'", null);
        if(cursor.getCount()>0){
            try {
                database.execSQL("update " + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.TABLE_NAME + " set "
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.DataUpdateFlag + "=1 where "
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.GSCNo + "='" + applicant_Id + "'");

                database.execSQL("update " + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.TABLE_NAME_1 + " set "
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Applicant_Category + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Applicant_Caste + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Belongs_Creamy_Layer_6 + "=null,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Reason_for_Creamy_Layer_6 + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_App_Father_Category_8 + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_APP_Father_Caste_8 + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_App_Mother_Category_8 + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_APP_Mother_Caste_8 + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Income + "=0,"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Remarks + "='" + strRemarks + "',"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_vLat + "=" + latitude + ","
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_vLong + "=" + longitude + ","
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Can_Certificate_Given + "='" + option3 + "',"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_DifferFromAppinformation + "='" + option1 + "',"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_Report_No + "='" + report_no + "',"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_ReportDate + "='" + currDate + "',"
                        + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_DataUpdateFlag + "=1"
                        + " where " + DataBaseHelperClass_btnDownload_ServiceParameter_Tbl_RI.UPD_GSCNo + "='" + applicant_Id + "'");

                Log.d("Database", "ServiceParameters Database Updated");
                Toast.makeText(getApplicationContext(), getString(R.string.updated_successfully), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(RI_Field_Report_OAP_Parameters_Kan.this, RI_Field_Report_FirstScreen.class);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("RI_Name", RI_Name);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("strSearchServiceName", service_name);
                i.putExtra("strSearchVillageName", village_name);
                i.putExtra("serviceCode", serviceCode);
                i.putExtra("villageCode", villageCode);
                i.putExtra("habitationCode", habitationCode);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();

                Log.d("Data", "StoreData_in_DB_When_Wrong : Update_success");
                return "Update_success";
            } catch (Exception e){
                e.printStackTrace();
                return "Failed";
            }
        }
        else {
            cursor.close();
            Log.d("Data", "StoreData_in_DB : Update_Failed");
            return "Update_Failed";
        }
    }

    public void GetDept() {
        try {
            List<SpinnerObject> labels;
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this,str,str,str,str);
            sqlLiteOpenHelper_class.open_Dept_Master_Tbl();
            labels = sqlLiteOpenHelper_class.GetDept_Name();
            ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_color, labels);
            dataAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
            spinnerDept.setAdapter(dataAdapter);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_creating_table), Toast.LENGTH_LONG).show();
        }
    }

    private void showDateDialog(final EditText etDate) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                etDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(RI_Field_Report_OAP_Parameters_Kan.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void GetAppTitle(){
        sqLiteAssetHelper_masters = new SQLiteAssetHelper_Masters(this, activity);
        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
        objects_appTitle = sqLiteAssetHelper_masters.Get_Salutation_title(getString(R.string.title_desc_name));
        adapter_appTitle = new ArrayAdapter<>(this, R.layout.list_item, objects_appTitle);
        adapter_appTitle.setDropDownViewResource(R.layout.list_item);
        autoSearchAppTitle.setAdapter(adapter_appTitle);
    }

    public void GetBincom(){
        sqLiteAssetHelper_masters = new SQLiteAssetHelper_Masters(this, activity);
        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
        objects_bincom = sqLiteAssetHelper_masters.Get_BinCome(getString(R.string.bincom_desc_name));
        adapter_bincom = new ArrayAdapter<>(this, R.layout.list_item, objects_bincom);
        adapter_bincom.setDropDownViewResource(R.layout.list_item);
        autoSearchBinCom.setAdapter(adapter_bincom);
    }

    public void GetFatTitle(){
        sqLiteAssetHelper_masters = new SQLiteAssetHelper_Masters(this, activity);
        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
        objects_FatTitle = sqLiteAssetHelper_masters.Get_Salutation_title(getString(R.string.title_desc_name));
        adapter_fatTitle = new ArrayAdapter<>(this, R.layout.list_item, objects_FatTitle);
        adapter_fatTitle.setDropDownViewResource(R.layout.list_item);
        autoSearchFatTitle.setAdapter(adapter_fatTitle);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void check_Kannada_Key_lang(){
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        ims = imm.getCurrentInputMethodSubtype();
        String locale = ims.getLocale();
        Locale locale2 = new Locale(locale);
        String currentLanguage = locale2.getDisplayLanguage();
        Log.d("lang:", "" + currentLanguage);
        if (!Objects.equals(currentLanguage, "kn_in")) {
            Toast.makeText(getApplicationContext(), getString(R.string.switch_kannada), Toast.LENGTH_SHORT).show();
            return_Value = searchPackage();
            Log.d("return_Value", "" +return_Value);
            if(!return_Value){
                Log.d("Enter", "!return_value");
                buildAlertMessage();
            }else {
                imm.showInputMethodPicker();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void check_English_Key_lang(){
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        ims = imm.getCurrentInputMethodSubtype();
        String locale = ims.getLocale();
        Locale locale2 = new Locale(locale);
        String currentLanguage = locale2.getDisplayLanguage();
        String[] split_curr = currentLanguage.split("_");
        String cur_value = split_curr[0];
        Log.d("cur_value", cur_value);
        Log.d("lang:", "" + currentLanguage);
        if (!Objects.equals(cur_value, "en")) {
            Toast.makeText(getApplicationContext(), getString(R.string.switch_english), Toast.LENGTH_SHORT).show();
            imm.showInputMethodPicker();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean searchPackage(){

        InputMethodInfo inputMethodInfo;
        PackageManager packageManager = getPackageManager();
        List<InputMethodInfo> str;
        str = imm.getEnabledInputMethodList();
        System.out.println(str);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < str.size(); i++) {
            System.out.println(str.get(i));
            inputMethodInfo = imm.getEnabledInputMethodList().get(i);
            String str2 = inputMethodInfo.loadLabel(packageManager).toString();
            Log.d("Package_List", str2);
            list.add(str2);
        }
        Log.d("Print_List", String.valueOf(list));

        boolean get = false;

        for(String s1 : list){
            if(s1.contains("Samyojane")){
                get=true;
            }
        }
        Log.d("search", String.valueOf(get));
        return get;

    }

    private  void buildAlertMessage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.keyboard_language))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), (dialog, id) -> {
                    startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                    onBackPressed();
                })
                .setNegativeButton(getString(R.string.no), (dialog, id) -> buildAlert());
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private  void buildAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.must_change_keyboard))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok), (dialog, id) -> {
                    startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                    onBackPressed();
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private  void buildAlertMessageGoingBack() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.discard_changes))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), (dialog, id) -> {
                    RI_Field_Report_OAP_Parameters_Kan.super.onBackPressed();
                    Intent i = new Intent(RI_Field_Report_OAP_Parameters_Kan.this, RI_Field_Report_FirstScreen.class);
                    i.putExtra("applicant_Id", applicant_Id);
                    i.putExtra("district_Code", district_Code);
                    i.putExtra("taluk_Code", taluk_Code);
                    i.putExtra("hobli_Code", hobli_Code);
                    i.putExtra("district", district);
                    i.putExtra("taluk", taluk);
                    i.putExtra("hobli", hobli);
                    i.putExtra("va_Circle_Code", va_Circle_Code);
                    i.putExtra("VA_Circle_Name", VA_Circle_Name);
                    i.putExtra("RI_Name", RI_Name);
                    i.putExtra("VA_Name", VA_Name);
                    i.putExtra("strSearchServiceName", service_name);
                    i.putExtra("strSearchVillageName", village_name);
                    i.putExtra("serviceCode", serviceCode);
                    i.putExtra("villageCode", villageCode);
                    i.putExtra("habitationCode", habitationCode);
                    i.putExtra("option_Flag", option_Flag);
                    i.putExtra("town_Name", town_Name);
                    i.putExtra("town_code", town_code);
                    i.putExtra("ward_Name", ward_Name);
                    i.putExtra("ward_code", ward_code);
                    startActivity(i);
                    finish();
                })
                .setNegativeButton(getString(R.string.no), (dialog, id) -> dialog.cancel());
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        buildAlertMessageGoingBack();
    }

}