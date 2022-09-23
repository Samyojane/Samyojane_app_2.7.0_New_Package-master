package com.nadakacheri.samyojane_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class New_Request_SecondScreen extends AppCompatActivity{
    TextView applicant_infor, tvServiceName, tv_for_Aadhaar;
    String applicant_name;
    String applicant_Id;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;
    String raisedLoc, name, fatherName, motherName, rationCardNo, aadharNo, mobileNo, address1, address2, address3, report_No;
    String add_pin, add_annualIncome, add_age;
    String appImage=null;
    int appTitle_Code, binCom_Code, fatTitle_Code;
    String appTitle_Name, binCom_Name, fatTitle_Name;
    AutoCompleteTextView autoSearchAppTitle, autoSearchBinCom, autoSearchFatTitle;
    EditText tvFatherName, tvMotherName;
    TextView tvName, tvMobile;
    EditText tvAddress1, tvAddress2, tvAddress3, tvPinCode,tvAnnualIncome,tvAge;
    TextView txt_raiseLoc, tvHobli, tvTaluk, tvVA_Name, tv_IDName;
    String district, taluk,hobli,VA_Circle_Name, VA_Name, VA_IMEI;
    int district_Code, taluk_Code, hobli_Code, va_Circle_Code, town_code, ward_code;
    Button btnNext, btnBack;
    String item_position;
    String village_Code, service_Code;
    String strSearchVillageName, strSearchServiceName;
    GPSTracker gpsTracker;
    double latitude, longitude, accuracy;
    ProgressDialog dialog;
    String service_name, village_name, town_Name, ward_Name, option_Flag;
    Set_and_Get_Service_Parameter set_and_get_service_parameter;
    SqlLiteOpenHelper_Class sqlLiteOpenHelper_class;
    SQLiteAssetHelper_Masters sqLiteAssetHelper_masters;
    String eng_certi;
    boolean return_Value;
    InputMethodManager imm;
    InputMethodSubtype ims;
    TextView txt_ReportNo;
    String str;
    int Id_Code;
    String Id_Name;
    LinearLayout trID,llAnnIncome,llAge;

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
        String num = "1234567890೧೨೩೪೫೬೭೮೯೦";
        String op1 = "~`!@#$%^&*()_-''+={}[]:/?><,.\\\"\";£€¢¥₩§|×÷¿■□♤♡◇♧°•○●☆▪¤《》¡₹Π℅®©™∆√¶";
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

        String blockCharacterSet = num+op1+l1+l2+l3+l4+l5+l6+l7+l8+l9+l10+l11+l12+l13+l14+l15+l16+l17+l18+l19+l20
                +l21+l22+l23+l24+l25+l26+l27+l28+l29+l30+l31+l32+l33+l34+l35+l36;

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

    InputFilter filter_Kan = (source, start, end, dest, dstart, dend) -> {
        Log.d("Source",""+source);
        String num = "1234567890೧೨೩೪೫೬೭೮೯೦";
        String l1 = "abcdefghijklmnopqrstuvwxyz";
        String l2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String op1 = "~`!@#$%^&*()_-''+={}[]:/?><,.\\\"\";£€¢¥₩§|×÷¿■□♤♡◇♧°•○●☆▪¤《》¡₹Π℅®©™∆√¶";
        String blockCharacterSet = num+l1+l2+op1;

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

    InputFilter address_filter_Eng = (source, start, end, dest, dstart, dend) -> {
        Log.d("Source",""+source);
        String op1 = "~`!@$%^&*()_-''+={}[]:/?><.\\\"\";£€¢¥₩§|×÷¿■□♤♡◇♧°•○●☆▪¤《》¡₹Π℅®©™∆√¶";
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

        String blockCharacterSet = op1+l1+l2+l3+l4+l5+l6+l7+l8+l9+l10+l11+l12+l13+l14+l15+l16+l17+l18+l19+l20
                +l21+l22+l23+l24+l25+l26+l27+l28+l29+l30+l31+l32+l33+l34+l35+l36;

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

    InputFilter address_filter_Kan = (source, start, end, dest, dstart, dend) -> {
        Log.d("Source",""+source);
        String l1 = "abcdefghijklmnopqrstuvwxyz";
        String l2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String op1 = "~`!@$%^&*()_-''+={}[]:/?><.\\\"\";£€¢¥₩§|×÷¿■□♤♡◇♧°•○●☆▪¤《》¡₹Π℅®©™∆√¶";
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_request_secondscreen);

        tvTaluk = findViewById(R.id.taluk);
        tvHobli = findViewById(R.id.hobli);
        tvVA_Name = findViewById(R.id.VA_name);

        btnBack = findViewById(R.id.btnBack);
        txt_raiseLoc = findViewById(R.id.txt_raiseLoc);
        tvName = findViewById(R.id.tvName);
        autoSearchAppTitle = findViewById(R.id.autoSearchAppTitle);
        autoSearchBinCom = findViewById(R.id.autoSearchBinCom);
        autoSearchFatTitle = findViewById(R.id.autoSearchFatTitle);
        tvFatherName = findViewById(R.id.tvFatherNme);
        tvMotherName = findViewById(R.id.tvMotherName);
        tv_IDName = findViewById(R.id.tv_IDName);
        tvMobile = findViewById(R.id.tvMobile);
        tvAddress1 = findViewById(R.id.tvAddress1);
        tvAddress2 = findViewById(R.id.tvAddress2);
        tvAddress3 = findViewById(R.id.tvAddress3);
        tvPinCode = findViewById(R.id.tvPinCode);
        tvAnnualIncome = findViewById(R.id.tvAnnualIncome);
        tvAge = findViewById(R.id.tvAge);
        btnNext = findViewById(R.id.btnNext);
        tvServiceName = findViewById(R.id.tvServiceName);
        txt_ReportNo = findViewById(R.id.txt_ReportNo);
        tv_for_Aadhaar = findViewById(R.id.tv_for_Aadhaar);
        trID = findViewById(R.id.trID);
        llAnnIncome = findViewById(R.id.ll_annualIncome);
        llAge = findViewById(R.id.ll_age);

        applicant_infor= findViewById(R.id.applicant_Information);
        applicant_infor.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        PhoneNumberUtils.formatNumber(tvPinCode.getText().toString());
        tvPinCode.setFilters(new InputFilter[] {new InputFilter.LengthFilter(6)});

        Intent i = getIntent();
        district = i.getStringExtra("district");
        taluk = i.getStringExtra("taluk");
        VA_Name = i.getStringExtra("VA_Name");
        applicant_name = i.getStringExtra("applicant_name");
        applicant_Id = i.getStringExtra("applicant_Id");
        district_Code = i.getIntExtra("district_Code", 0);
        taluk_Code = i.getIntExtra("taluk_Code", 0);
        hobli_Code = i.getIntExtra("hobli_Code", 0);
        hobli = i.getStringExtra("hobli");
        va_Circle_Code = i.getIntExtra("va_Circle_Code", 0);
        VA_Circle_Name = i.getStringExtra("VA_Circle_Name");
        item_position = i.getStringExtra("item_position");
        village_Code = i.getStringExtra("villageCode");
        service_Code = i.getStringExtra("serviceCode");
        service_name = i.getStringExtra("strSearchServiceName");
        village_name = i.getStringExtra("strSearchVillageName");
        report_No = i.getStringExtra("report_No");
        town_code = i.getIntExtra("town_code", 0);
        town_Name = i.getStringExtra("town_Name");
        ward_code = i.getIntExtra("ward_code", 0);
        ward_Name = i.getStringExtra("ward_Name");
        option_Flag = i.getStringExtra("option_Flag");

        if(service_Code.equals("6")||service_Code.equals("9")|| service_Code.equals("11")||service_Code.equals("34")||
                service_Code.equals("37")||service_Code.equals("43")|| service_Code.equals("7")|| service_Code.equals("8")||
                service_Code.equals("42")||service_Code.equals("10")|| service_Code.equals("9999")||service_Code.equals("41")||
        service_Code.equals("27"))
        {
            llAnnIncome.setVisibility(View.GONE);
            llAge.setVisibility(View.GONE);
        }else
        {
            llAge.setVisibility(View.VISIBLE);
            llAnnIncome.setVisibility(View.VISIBLE);
        }

        Log.d("New_Request_Sec", ""+district_Code);
        Log.d("New_Request_Sec", ""+taluk_Code);
        Log.d("New_Request_Sec",""+hobli_Code);
        Log.d("New_Request_Sec",""+village_name);
        Log.d("New_Request_Sec", "town_code: "+town_code);
        Log.d("New_Request_Sec", "town_Name: "+town_Name);
        Log.d("New_Request_Sec", "ward_code: "+ward_code);
        Log.d("New_Request_Sec", "ward_Name: "+ward_Name);
        Log.d("New_Request_Sec","option_Flag: "+ option_Flag);
        Log.d("New_Request_Sec","service_name: "+ service_name);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading));
        dialog.setIndeterminate(true);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        DesiCode = sharedPreferences.getInt(Constants.DesiCode_VA, 22);
        uName_get = sharedPreferences.getString(Constants.uName_get, "");


//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        assert imm != null;
//        ims = imm.getCurrentInputMethodSubtype();
//        String locale = ims.getLocale();
//        Locale locale2 = new Locale(locale);
//        String currentLanguage = locale2.getDisplayLanguage();
//        Log.d("lang:", "" + currentLanguage);
//        if (!Objects.equals(currentLanguage, "kn_in")) {
//            return_Value = searchPackage();
//            Log.d("return_Value", "" +return_Value);
//            if(!return_Value){
//                buildAlertMessage();
//            }else {
//                imm.showInputMethodPicker();
//            }
//        }


        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request_SecondScreen.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor1=database.rawQuery("SELECT "+DataBaseHelperClass_btnDownload_ServiceTranTable.Village_Code+", "+DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code+" FROM "+ DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME+" where "
                +DataBaseHelperClass_btnDownload_ServiceTranTable.District_Code+"="+district_Code+" and "+DataBaseHelperClass_btnDownload_ServiceTranTable.Taluk_Code+"="+taluk_Code
                +" and "+DataBaseHelperClass_btnDownload_ServiceTranTable.Hobli_Code+"="+hobli_Code+" and "+DataBaseHelperClass_btnDownload_ServiceTranTable.Applicant_Name+"='"+applicant_name
                +"' and "+DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo +"='"+applicant_Id+"'", null);
        if (cursor1.getCount()>0){
//            if (cursor1.moveToFirst()){
//                village_Code = cursor1.getString(cursor1.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.Village_Code));
//                service_Code = cursor1.getString(cursor1.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code));
//            }

            openHelper = new DataBaseHelperClass_VillageNames(New_Request_SecondScreen.this);
            database = openHelper.getWritableDatabase();

            Cursor cursor = database.rawQuery("select "+getString(R.string.village_table_habitation_name)+" from "+ DataBaseHelperClass_VillageNames.TABLE_NAME
                    +" where "+DataBaseHelperClass_VillageNames.HM_village_code+"='"+village_Code+"'", null);
            if(cursor.getCount()>0) {
                if(cursor.moveToFirst()){
                    strSearchVillageName = cursor.getString(cursor.getColumnIndex(getString(R.string.village_table_habitation_name)));
                    Log.d("VillageName", ""+strSearchVillageName);
                }
            } else {
                cursor.close();
            }

            openHelper = new DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster(New_Request_SecondScreen.this);
            database = openHelper.getWritableDatabase();

            Cursor cursor2 = database.rawQuery("select "+getString(R.string.facility_table_name)+" from "
                    +DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster.TABLE_NAME+" where "+DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster.FM_facility_code+"="+service_Code, null);
            if(cursor2.getCount()>0) {
                if(cursor2.moveToFirst()){
                    strSearchServiceName = cursor2.getString(cursor2.getColumnIndex(getString(R.string.facility_table_name)));
                    Log.d("ServiceName", ""+strSearchServiceName);
                }
            } else {
                cursor2.close();
            }
            Log.d("village_Code1", ""+village_Code);
            Log.d("service_Code1", ""+service_Code);
            Log.d("village_Name1", ""+strSearchVillageName);
            Log.d("service_Name1", ""+strSearchServiceName);
        } else {
            cursor1.close();
        }
        Log.d("village_Code1", ""+village_Code);
        Log.d("service_Code1", ""+service_Code);
        Log.d("Village_NameSecScreen", ""+strSearchVillageName);
        Log.d("Service_NameSecScreen", ""+strSearchServiceName);

        tvTaluk.setText(taluk);
        tvHobli.setText(hobli);
        tvVA_Name.setText(VA_Name);
        tvServiceName.setText(strSearchServiceName);

        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request_SecondScreen.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM "+ DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                +" where "+DataBaseHelperClass_btnDownload_ServiceTranTable.Applicant_Name+"='"+applicant_name+"'"+" and "
                +DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo +"='"+applicant_Id+"'", null);
        if(cursor.getCount()>0) {
            if(cursor.moveToFirst()){
                raisedLoc = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Raised_Location));
                name = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.Applicant_Name));
                fatherName = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.FatherName));
                motherName = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.MotherName));
                Id_Code = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ID_TYPE));
                rationCardNo = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.IDNo));
                mobileNo = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.Mobile_No));
                address1 = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.Address1));
                address2 = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.Address2));
                address3 = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.Address3));
                add_pin = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.PinCode));
                add_annualIncome = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
               // add_age = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.ApplicantAge));
                eng_certi = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_Eng_Certificate));
                appTitle_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.ApplicantTiitle));
                binCom_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.BinCom));
                fatTitle_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.RelationTitle));
                VA_IMEI = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.VA_IMEI));
            }
        } else {
            cursor.close();
        }


        if (Id_Code == 19 || Id_Code == 0 || rationCardNo.isEmpty()){
            trID.setVisibility(View.GONE);
        } else {
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this, str, str, str);
            sqlLiteOpenHelper_class.open_ID_Type_Tbl();
            Id_Name = sqlLiteOpenHelper_class.GetID_Name(Id_Code, getString(R.string.id_name));

            Log.d("ID_Name", ""+Id_Name);
            trID.setVisibility(View.VISIBLE);
        }

        Log.d("ID_Name: ", ""+Id_Name);
        Log.d("Eng_Certi", eng_certi);
        Log.d("Raised_Location: ",""+raisedLoc);

        sqLiteAssetHelper_masters = new SQLiteAssetHelper_Masters(this, activity);
        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
        appTitle_Name = sqLiteAssetHelper_masters.Get_Title_By_Code(appTitle_Code, getString(R.string.title_desc_name));
        binCom_Name = sqLiteAssetHelper_masters.Get_BinCom_By_Code(binCom_Code, getString(R.string.bincom_desc_name));
        fatTitle_Name = sqLiteAssetHelper_masters.Get_Title_By_Code(fatTitle_Code, getString(R.string.title_desc_name));

        GetAppTitle();
        GetBincom();
        GetFatTitle();

        autoSearchAppTitle.setText(appTitle_Name);
        autoSearchBinCom.setText(binCom_Name);
        autoSearchFatTitle.setText(fatTitle_Name);

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

        tvName.setText(applicant_name);
        tvFatherName.setText(fatherName);
        tvMotherName.setText(motherName);
        tv_IDName.setText(Id_Name);
        tv_for_Aadhaar.setText(rationCardNo);

        tvMobile.setText(mobileNo);
        tvAddress1.setText(address1);
        tvAddress2.setText(address2);
        tvAddress3.setText(address3);
        tvPinCode.setText(add_pin);
        tvAnnualIncome.setText(add_annualIncome);
        tvAge.setText(add_age);
        txt_ReportNo.setText(report_No);

        if (eng_certi.equals("K")){
            tvFatherName.setFilters(new InputFilter[] { filter_Kan });
            tvMotherName.setFilters(new InputFilter[] { filter_Kan });
            tvAddress1.setFilters(new InputFilter[] { address_filter_Kan });
            tvAddress2.setFilters(new InputFilter[] { address_filter_Kan });
            tvAddress3.setFilters(new InputFilter[] { address_filter_Kan });
        }else if (eng_certi.equals("E")){
            tvFatherName.setFilters(new InputFilter[] { filter_Eng });
            tvMotherName.setFilters(new InputFilter[] { filter_Eng });
            tvAddress1.setFilters(new InputFilter[] { address_filter_Eng });
            tvAddress2.setFilters(new InputFilter[] { address_filter_Eng });
            tvAddress3.setFilters(new InputFilter[] { address_filter_Eng });
        }

        tvFatherName.setOnTouchListener((v, event) -> {
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        tvMotherName.setOnTouchListener((v, event) -> {
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        tvAddress1.setOnTouchListener((v, event) -> {
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        tvAddress2.setOnTouchListener((v, event) -> {
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        tvAddress3.setOnTouchListener((v, event) -> {
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
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
                Log.d("Selected_Item", ""+fatTitle_Name+", ID:"+fatTitle_Code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gpsTracker = new GPSTracker(getApplicationContext(), this);

        btnNext.setOnClickListener(v -> {

            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
                accuracy = gpsTracker.getAccuracy();
                Log.d("Location", "\nLat: "+latitude+"\nLong: "+longitude+"\nAccuracy: "+accuracy);
            } else {
                //gpsTracker.showSettingsAlert();
                Toast.makeText(getApplicationContext(), getString(R.string.switch_on_gps), Toast.LENGTH_SHORT).show();
            }

            appTitle_Name = autoSearchAppTitle.getText().toString();
            binCom_Name = autoSearchBinCom.getText().toString();
            fatTitle_Name = autoSearchFatTitle.getText().toString();
            fatherName = tvFatherName.getText().toString();
            motherName = tvMotherName.getText().toString();
            address1 = tvAddress1.getText().toString();
            address2 = tvAddress2.getText().toString();
            address3 = tvAddress3.getText().toString();
            add_pin = tvPinCode.getText().toString();
            add_annualIncome= tvAnnualIncome.getText().toString();
            add_age= tvAge.getText().toString();

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

            openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request_SecondScreen.this);
            database = openHelper.getWritableDatabase();

            Cursor cursor2=database.rawQuery("SELECT * FROM "+ DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                    +" where "+DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code+"='"+service_Code+"'"+" and "
                    +DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo +"='"+applicant_Id+"'", null);
            if(cursor2.getCount()>0) {
                if(cursor2.moveToFirst()){
                    appImage = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));
                }
            }else {
                cursor2.close();
                appImage=null;
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
                                                              /*  if(!add_annualIncome.isEmpty()){
                                                                    if(!add_age.isEmpty()){*/
                                                                        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request_SecondScreen.this);
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
                                                                        Insert_ServiceParameterTbl();
                                                                   /* }else{
                                                                        tvAge.setError("Enter Annual Income");
                                                                    }
                                                                }else{
                                                                    tvAnnualIncome.setError("Enter Annual Income");
                                                                }*/
                                                            } else {
                                                                tvPinCode.setError(getString(R.string.enter_address));
                                                            }
                                                        } else {
                                                            tvAddress3.setError(getString(R.string.enter_address));
                                                        }
                                                    } else {
                                                        tvAddress2.setError(getString(R.string.enter_address));
                                                    }
                                                } else {
                                                    tvAddress1.setError(getString(R.string.enter_address));
                                                }
                                            } else {
                                                tvMotherName.setError(getString(R.string.enter_mother_name));
                                            }
                                        } else {
                                            tvFatherName.setError(getString(R.string.enter_father_husband_name));
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
            } else {
                runOnUiThread(() -> {
                    btnNext.setText(getString(R.string.certified));
                    AlertDialog.Builder dialog = new AlertDialog.Builder(New_Request_SecondScreen.this);
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
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
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
        if (TextUtils.isEmpty(cur_value)){
            cur_value = Locale.getDefault().getLanguage();
        }
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
                    New_Request_SecondScreen.super.onBackPressed();
                    Intent i = new Intent(New_Request_SecondScreen.this, New_Request.class);
                    i.putExtra("applicant_Id", applicant_Id);
                    i.putExtra("applicant_name",applicant_name);
                    i.putExtra("district_Code", district_Code);
                    i.putExtra("taluk_Code", taluk_Code);
                    i.putExtra("hobli_Code", hobli_Code);
                    i.putExtra("district", district);
                    i.putExtra("taluk", taluk);
                    i.putExtra("RI_Name", VA_Name);
                    i.putExtra("hobli", hobli);
                    i.putExtra("va_Circle_Code", va_Circle_Code);
                    i.putExtra("VA_Circle_Name", VA_Circle_Name);
                    i.putExtra("VA_Name", VA_Name);
                    i.putExtra("strSearchServiceName", service_name);
                    i.putExtra("strSearchVillageName", village_name);
                    i.putExtra("villageCode", village_Code);
                    i.putExtra("serviceCode", service_Code);
                    i.putExtra("report_No", report_No);
                    i.putExtra("option_Flag", option_Flag);
                    i.putExtra("town_Name", town_Name);
                    i.putExtra("town_code", town_code);
                    i.putExtra("ward_Name", ward_Name);
                    i.putExtra("ward_code", ward_code);
                    i.putExtra("eng_certi",eng_certi);
                    startActivity(i);
                    finish();
                })
                .setNegativeButton(getString(R.string.no), (dialog, id) -> dialog.cancel());
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean isValidMobile(String phone) {
        boolean check;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            check = phone.length() == 10;
        } else {
            check=false;
        }
        return check;
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

    public void Insert_ServiceParameterTbl(){
        Intent i;
        btnNext.setText(getString(R.string.loading_C));

        database.execSQL("delete from "+DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1
                + " where "+ DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo+"='"+applicant_Id+"'");

        set_and_get_service_parameter = new Set_and_Get_Service_Parameter();
        set_and_get_service_parameter.setGSCNo1(applicant_Id);
        set_and_get_service_parameter.setLoginID(uName_get);
        set_and_get_service_parameter.setDesignationCode(DesiCode);
        set_and_get_service_parameter.setService_Code(Integer.parseInt(service_Code));
        set_and_get_service_parameter.setAppTitle(appTitle_Code);
        set_and_get_service_parameter.setBinCom(binCom_Code);
        set_and_get_service_parameter.setFatTitle(fatTitle_Code);
        set_and_get_service_parameter.setFatherName(fatherName);
        set_and_get_service_parameter.setMotherName(motherName);
        set_and_get_service_parameter.setUpd_MobileNumber(mobileNo);
        set_and_get_service_parameter.setReport_No(report_No);
        set_and_get_service_parameter.setDifferFromAppinformation("N");
        set_and_get_service_parameter.setAddress1(address1);
        set_and_get_service_parameter.setAddress2(address2);
        set_and_get_service_parameter.setAddress3(address3);
        set_and_get_service_parameter.setPinCode(Integer.parseInt(add_pin));
        if(service_Code.equals("6")||service_Code.equals("9")|| service_Code.equals("11")||service_Code.equals("34")||
                service_Code.equals("37")||service_Code.equals("43")|| service_Code.equals("7")|| service_Code.equals("8")||
                service_Code.equals("42")||service_Code.equals("10")|| service_Code.equals("9999") ||service_Code.equals("41")
        || service_Code.equals("27"))
        {
            llAnnIncome.setVisibility(View.GONE);
            llAge.setVisibility(View.GONE);

        }else
        {
            if(add_annualIncome == null || add_annualIncome.length()==0) {
                set_and_get_service_parameter.setIncome(Integer.parseInt(add_annualIncome));
                set_and_get_service_parameter.setApplicant_age(Integer.parseInt(add_age));
            }
        }

        set_and_get_service_parameter.setApplicant_Category(0);
        set_and_get_service_parameter.setApplicant_Caste(0);
        set_and_get_service_parameter.setCasteSl(0);
        set_and_get_service_parameter.setIncome(0);
        set_and_get_service_parameter.setTotal_No_Years_10(0);
        set_and_get_service_parameter.setNO_Months_10(0);
        set_and_get_service_parameter.setApp_Father_Category_8(0);
        set_and_get_service_parameter.setAPP_Father_Caste_8(0);
        set_and_get_service_parameter.setApp_Mother_Category_8(0);
        set_and_get_service_parameter.setAPP_Mother_Caste_8(0);
        set_and_get_service_parameter.setBelongs_Creamy_Layer_6("");
        set_and_get_service_parameter.setReason_for_Creamy_Layer_6(0);
        set_and_get_service_parameter.setUpdated_By_VA_IMEI(VA_IMEI);
        set_and_get_service_parameter.setUpdated_By_VA_Name(VA_Name);
        set_and_get_service_parameter.setDataUpdateFlag(0);

        database.execSQL("insert into " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + "("
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_LoginID+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DesignationCode+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Service_Code+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_AppTitle+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_BinCom+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_FatTitle+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_FatherName+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_MotherName+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_MobileNumber +","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Report_No+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DifferFromAppinformation+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Address1+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Address2+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Address3+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_PinCode+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Applicant_Category+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Applicant_Caste+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_CasteSl+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Income+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Total_No_Years_10+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_NO_Months_10+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_App_Father_Category_8+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_App_Mother_Category_8+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_APP_Father_Caste_8+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_APP_Mother_Caste_8+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Belongs_Creamy_Layer_6+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Reason_for_Creamy_Layer_6+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_VA_RI_IMEI+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_VA_RI_Name+","
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DataUpdateFlag
                +") values ('"
                + set_and_get_service_parameter.getGSCNo1() + "','"
                + set_and_get_service_parameter.getLoginID() + "',"
                + set_and_get_service_parameter.getDesignationCode() + ","
                + set_and_get_service_parameter.getService_Code() + ","
                + set_and_get_service_parameter.getAppTitle() + ","
                + set_and_get_service_parameter.getBinCom() + ","
                + set_and_get_service_parameter.getFatTitle() + ",'"
                + set_and_get_service_parameter.getFatherName() + "','"
                + set_and_get_service_parameter.getMotherName() + "','"
                + set_and_get_service_parameter.getUpd_MobileNumber() + "','"
                + set_and_get_service_parameter.getReport_No() + "','"
                + set_and_get_service_parameter.getDifferFromAppinformation() + "','"
                + set_and_get_service_parameter.getAddress1() + "','"
                + set_and_get_service_parameter.getAddress2() + "','"
                + set_and_get_service_parameter.getAddress3() + "',"
                + set_and_get_service_parameter.getPinCode() + ","
                + set_and_get_service_parameter.getApplicant_Category() + ","
                + set_and_get_service_parameter.getApplicant_Caste() + ","
                + set_and_get_service_parameter.getCasteSl() + ","
                + set_and_get_service_parameter.getIncome() + ","
                + set_and_get_service_parameter.getTotal_No_Years_10() + ","
                + set_and_get_service_parameter.getNO_Months_10() + ","
                + set_and_get_service_parameter.getApp_Father_Category_8() + ","
                + set_and_get_service_parameter.getApp_Mother_Category_8() + ","
                + set_and_get_service_parameter.getAPP_Father_Caste_8() + ","
                + set_and_get_service_parameter.getAPP_Mother_Caste_8() + ",'"
                + set_and_get_service_parameter.getBelongs_Creamy_Layer_6() + "',"
                + set_and_get_service_parameter.getReason_for_Creamy_Layer_6() + ",'"
                + set_and_get_service_parameter.getUpdated_By_VA_IMEI() + "','"
                + set_and_get_service_parameter.getUpdated_By_VA_Name() + "',"
                + set_and_get_service_parameter.getDataUpdateFlag() + ")");

        Log.d("Database", "ServiceParameterTable Database Inserted");

        switch (service_Code) {
            case "6":
            case "9":
            case "11":
            case "34":
            case "37":
            case "43":
            case "27":{
                Log.d("Service:", "6 or 9");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_Caste_Income_Parameters.class);
                } else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_Caste_Income_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "7":
            case "8":
            case "42":{
                Log.d("Service:", "8");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_Caste_sc_st_certi_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_Caste_sc_st_certi_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "10": {
                Log.d("Service:", "10");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_Resident_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_Resident_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "9999" :
            case "41":{
                Log.d("Service:", "9999");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_CasteCertificate.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_CasteCertificate_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "55" : {
                Log.d("Service:", "55");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_OAP_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_OAP_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "51" : {
                Log.d("Service:", "51");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_PHP_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_PHP_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "52" : {
                //widow pension
                Log.d("Service:", "52");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_DWP_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_DWP_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "53" : {
                //SSY
                Log.d("Service:", "53");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "56" : {
                //MYTRI
                Log.d("Service:", "56");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "57" : {
                //MANASWINI
                Log.d("Service:", "57");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "59" : {
                //ACID VICTIM PENSION.
                Log.d("Service:", "59");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "60" : {
                //FARMER WIDOW PENSION.
                Log.d("Service:", "60");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "61" : {
                //ENDOSALPHAN VICTIM PENSION.
                Log.d("Service:", "61");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_SSY_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            case "12":
            case "17":
            case "19":
            case "24":
            case "25":
            case "26":
            case "28":
            case "29":
            case "30":
            case "40":{
                //AGRI Certificate.
                Log.d("Service:", "12,17");
                if(Objects.equals(eng_certi, "E")) {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_AGRI_Parameters.class);
                }else {
                    i = new Intent(New_Request_SecondScreen.this, New_Request_AGRI_Parameters_Kan.class);
                }
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("rationCardNo", rationCardNo);
                i.putExtra("aadharNo", aadharNo);
                i.putExtra("mobileNo", mobileNo);
                i.putExtra("address1", address1);
                i.putExtra("item_position", item_position);
                i.putExtra("strSearchServiceName", strSearchServiceName);
                i.putExtra("strSearchVillageName", strSearchVillageName);
                i.putExtra("serviceCode", service_Code);
                i.putExtra("villageCode", village_Code);
                i.putExtra("eng_certi", eng_certi);
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                break;
            }
            default:
                Toast.makeText(getApplicationContext(), getString(R.string.service_not_availabel), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static String encrypt(String value) throws Exception
    {
        String encryptedValue64;
        Key key = generateKey();
        @SuppressLint("GetInstance")
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
        return encryptedValue64;

    }

    public static String decrypt(String value) throws Exception
    {
        Key key = generateKey();
        @SuppressLint("GetInstance")
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        decryptedValue = new String(decryptedByteValue, StandardCharsets.UTF_8);
        return decryptedValue;

    }

    private static Key generateKey() {
        key = new SecretKeySpec(KEY.getBytes(),ALGORITHM);
        return key;
    }

    @Override
    public void onBackPressed() {
        buildAlertMessageGoingBack();
    }
}
