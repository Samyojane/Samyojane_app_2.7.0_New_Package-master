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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import android.graphics.pdf.PdfDocument;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.text.TextUtils;
import java.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizer;
import com.nadakacheri.samyojane_app.api.APIClient;
import com.nadakacheri.samyojane_app.api.APIInterface_NIC;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_Request extends AppCompatActivity {

    HashMap<String, String> hashMap_Down_Docs;

    Button btnBack, btnSave, btnCancel;
    TextView applicant_infor;
    String applicant_name;
    String raisedLoc, name, fatherName, motherName, address1, address2, address3, add_pin, report_no;
    int appTitle_Code, binCom_Code, fatTitle_Code;
    String appTitle_Name, binCom_Name, fatTitle_Name;
    String remarks;
    String service_name, village_name;
    String eng_certi;
    TextView txt_raiseLoc, txt_appID, txt_BinCom, txt_appName, txt_appFatherName, txt_appMotherName, txt_ID_Num, txt_appMobileNum, txt_add1, txt_add2, txt_add3, txt_add_Pin, txt_ReportNo;
    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10, txt11, txt12, tv_IDName, tvFinYear,tvCertVal;
    SqlLiteOpenHelper_Class sqlLiteOpenHelper_class;
    SQLiteAssetHelper_Masters sqLiteAssetHelper_masters;
    String amount, caste_name, IfscCode, category_name;
    TableRow Service6, Service67, Service678, trService10, Service10, trCatService9999, tr_casteService9999, trService55,
            trService551, trApplicantAge, trFinYear, trCertValidity, trews_caste;
    TextView tvHobli, tvTaluk, tvVA_Name, tvServiceName;
    String district, taluk, hobli, VA_IMEI, VA_Name,VA_Circle_Name, applicant_Id, rationCardNo, aadharNo, mobileNo;
    int district_Code, taluk_Code, hobli_Code, va_Circle_Code, town_code, ward_code;
    Button btnReport, btnDownDocs, btnViewDocs, btnUploadDocs, btnViewUploadDocs;
    private static final int CAMERA_REQUEST = 1;
    byte[] imageInByte;
    static String store = null;
    static String getString = null;
    static String pdfInString = null;
    EditText tvRemarks, etAnnualIncome, etAge;
    GPSTracker gpsTracker;
    double latitude, longitude;
    int income_len, income_Value;
    String item_position;
    String strSearchVillageName, strSearchServiceName,strImageName;
    Integer DocId;
    String villageCode, serviceCode, town_Name, ward_Name, option_Flag;
    TextView tvAmount, tvAge, tvCaste, tvCategory, tvAccnumber, tvIfsc, tvCasteOthers;
    String year, month;
    TextView tvYear, tvMonths;
    String str;
    String lang_flag;
    String appImage=null, finYear= null,certValidity =null, otherCaste;
    ImageView iv_scst;
    String Id_Name;
    int Id_Code;
    TableRow trID, trAgriConfirmation, trDetConfirmation;;
    TableLayout tl_service9999;
    Activity activity;
    Spinner spCategory;
    AutoCompleteTextView autoSearchCaste,autoImageName;
    int getCatCode = 0, getCasteCode = 0,getImageCode = 0;
    String strCategory, strSearchCaste, AccNumber, appAge;

    int documentcount=0;


    String Name;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    View view;
    LayoutInflater inflater;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;

    final int min = 1111;
    final int max = 9999;
    int random;

    RadioGroup radioGroupA, radioGroup1;
    RadioButton radioButton1A, radioButton2A, radioButton11,radioButton22;
    String optionA;

    ProgressDialog dialog;
    boolean return_Value;
    InputMethodManager imm;
    InputMethodSubtype ims;
    APIInterface_NIC apiInterface_nic;
    String uName_get;
    int DesiCode;
    SharedPreferences sharedPreferences;
    List<SpinnerObject> labelsDocs;

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
        String l1 = "abcdefghijklmnopqrstuvwxyz";
        String l2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String op1 = "~`!@#$%^&*()_-''+={}[]:/?><,.\\\"\";£€¢¥₩§|×÷¿■□♤♡◇♧°•○●☆▪¤《》¡₹Π℅®©™∆√¶1234567890೧೨೩೪೫೬೭೮೯೦";
        String blockCharacterSet = l1+l2+op1;

        for (int i = start; i < end; i++) {
            Log.d("source.charAt(i)",""+i+" : "+source.charAt(i));
            if (source != null && blockCharacterSet.contains(("" + source.charAt(i)))) {
                Log.d("Blocked",""+source);
                return "";
            }
        }
        Log.d("Filter_valid","Not blocked");
        return null;
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"ClickableViewAccessibility", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_request);

        optionA="Y";

        inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.image_alertwindow, null);

        autoImageName = view.findViewById(R.id.autoSearchName);
        btnSave = view.findViewById(R.id.btnDialogSave);
        btnCancel = view.findViewById(R.id.btnDialogCancel);

        tvTaluk = findViewById(R.id.taluk);
        tvHobli = findViewById(R.id.hobli);
        tvVA_Name = findViewById(R.id.VA_name);
        tvServiceName = findViewById(R.id.tvServiceName);

        btnBack = findViewById(R.id.btnBack);
        txt_raiseLoc = findViewById(R.id.txt_raiseLoc);
        txt_appID = findViewById(R.id.txt_appID);
        txt_appName = findViewById(R.id.txt_appName);
        txt_BinCom = findViewById(R.id.txt_BinCom);
        txt_appFatherName = findViewById(R.id.txt_appFatherName);
        txt_appMotherName = findViewById(R.id.txt_appMotherName);
        txt_ID_Num = findViewById(R.id.txt_ID_Num);
        txt_appMobileNum = findViewById(R.id.txt_appMobileNum);
        txt_add1 = findViewById(R.id.txt_add1);
        txt_add2 = findViewById(R.id.txt_add2);
        txt_add3 = findViewById(R.id.txt_add3);
        txt_add_Pin = findViewById(R.id.txt_add_Pin);
        txt_ReportNo = findViewById(R.id.txt_ReportNo);
        tvAmount = findViewById(R.id.tvAmount);
        tvCaste = findViewById(R.id.tvCaste);
        tvCategory = findViewById(R.id.tvCategory);
        tvAccnumber = findViewById(R.id.tvAccnumber);
        tvIfsc = findViewById(R.id.tvIfsc);
        btnReport = findViewById(R.id.btnReport);
        tvRemarks = findViewById(R.id.tvRemarks);
        radioGroupA = findViewById(R.id.radioGroupA);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioButton1A = findViewById(R.id.radioButton1A);
        radioButton2A = findViewById(R.id.radioButton2A);
        radioButton11 = findViewById(R.id.radioButton11);
        radioButton22 = findViewById(R.id.radioButton22);
        Service6 = findViewById(R.id.Service6);
        Service67 = findViewById(R.id.Service67);
        Service678 = findViewById(R.id.Service678);
        trService10 = findViewById(R.id.trService10);
        Service10 = findViewById(R.id.Service10);
        trCatService9999 = findViewById(R.id.trCatService9999);
        tr_casteService9999 = findViewById(R.id.tr_casteService9999);
        trService55 = findViewById(R.id.trService55);
        trService551 = findViewById(R.id.trService551);
        trApplicantAge = findViewById(R.id.trApplicantAge);
        tl_service9999 = findViewById(R.id.tl_service9999);
        trAgriConfirmation = findViewById(R.id.tr_agri_confirmation);
        trDetConfirmation = findViewById(R.id.tr_det_confirmation);
        tvCasteOthers = findViewById(R.id.tvCasteOthers);

        etAnnualIncome = findViewById(R.id.etAnnualIncome);
        etAge = findViewById(R.id.etAge);
        tvYear = findViewById(R.id.tvYear);
        tvMonths = findViewById(R.id.tvMonths);
        btnDownDocs = findViewById(R.id.btnDownDocs);
        btnViewDocs = findViewById(R.id.btnViewDocs);
        btnUploadDocs = findViewById(R.id.btnUploadDocs);
        btnViewUploadDocs = findViewById(R.id.btnViewUploadDocs);
        iv_scst = findViewById(R.id.iv_scst);
        trID = findViewById(R.id.trID);
        spCategory = findViewById(R.id.spCategory);
        autoSearchCaste = findViewById(R.id.autoSearchCaste);
        trFinYear = findViewById(R.id.trFinYear);
        trCertValidity = findViewById(R.id.trCertValidity);
        trews_caste = findViewById(R.id.ews_caste);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);
        txt7 = findViewById(R.id.txt7);
        txt8 = findViewById(R.id.txt8);
        txt9 = findViewById(R.id.txt9);
        txt10 = findViewById(R.id.txt10);
        txt11 = findViewById(R.id.txt11);
        txt12 = findViewById(R.id.txt12);
        tv_IDName = findViewById(R.id.tv_IDName);
        tvFinYear = findViewById(R.id.tvFinYear);
        tvCertVal = findViewById(R.id.tvCertValidity);

        btnViewDocs.setVisibility(View.GONE);

        radioButton1A.setChecked(true);
        radioButton11.setChecked(true);

        applicant_infor = findViewById(R.id.applicant_Information);
        applicant_infor.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        PhoneNumberUtils.formatNumber(tvRemarks.getText().toString());
        tvRemarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});

        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this);
        sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();

        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this,str);
        sqlLiteOpenHelper_class.open_Docs_Type_Tbl();
        labelsDocs = sqlLiteOpenHelper_class.GetDocsName();

        hashMap_Down_Docs  = new HashMap<>();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
        String formattedDate = df.format(c);

        Log.d("formattedDate", "" + formattedDate);

        random = new Random().nextInt((max - min) + 1) + min;
        Log.d("Random_Num", String.valueOf(+random));

        report_no = formattedDate + "\\" + random;
        Log.d("report_no", "" + report_no);

//        openHelper = new DataBaseHelperClass_RandomNum(New_Request.this);
//        database = openHelper.getWritableDatabase();
//
//        database.execSQL("insert into " + DataBaseHelperClass_RandomNum.TABLE_NAME + " ("
//                + DataBaseHelperClass_RandomNum.RandomNum + ") values " + "('" + report_no + "')");

        Intent i = getIntent();
        district = i.getStringExtra("district");
        taluk = i.getStringExtra("taluk");
        district_Code = i.getIntExtra("district_Code", 0);
        taluk_Code = i.getIntExtra("taluk_Code", 0);
        hobli_Code = i.getIntExtra("hobli_Code", 0);
        hobli = i.getStringExtra("hobli");
        va_Circle_Code = i.getIntExtra("va_Circle_Code", 0);
        VA_Circle_Name = i.getStringExtra("VA_Circle_Name");
        applicant_name = i.getStringExtra("applicant_name");
        applicant_Id = i.getStringExtra("applicant_Id");
        VA_Name = i.getStringExtra("VA_Name");
        rationCardNo = i.getStringExtra("rationCardNo");
        aadharNo = i.getStringExtra("aadharNo");
        mobileNo = i.getStringExtra("mobileNo");
        address1 = i.getStringExtra("address1");
        item_position = i.getStringExtra("item_position");
        strSearchVillageName = i.getStringExtra("strSearchVillageName");
        strSearchServiceName = i.getStringExtra("strSearchServiceName");
        villageCode = i.getStringExtra("villageCode");
        serviceCode = i.getStringExtra("serviceCode");
        service_name = i.getStringExtra("strSearchServiceName");
        village_name = i.getStringExtra("strSearchVillageName");
        eng_certi = i.getStringExtra("eng_certi");
        report_no = i.getStringExtra("report_No");
        town_code = i.getIntExtra("town_code", 0);
        town_Name = i.getStringExtra("town_Name");
        ward_code = i.getIntExtra("ward_code", 0);
        ward_Name = i.getStringExtra("ward_Name");
        option_Flag = i.getStringExtra("option_Flag");

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        DesiCode = sharedPreferences.getInt(Constants.DesiCode_VA, 22);
        uName_get = sharedPreferences.getString(Constants.uName_get, "");

        if(report_no == null){
            Date c1 = Calendar.getInstance().getTime();

            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
            String formattedDate1 = df1.format(c1);

            Log.d("formattedDate", "" + formattedDate1);

            random = new Random().nextInt((max - min) + 1) + min;
            Log.d("Random_Num", String.valueOf(+random));

            report_no = formattedDate1 + "\\" + random;
            Log.d("report_no", "" + report_no);

//            openHelper = new DataBaseHelperClass_RandomNum(New_Request.this);
//            database = openHelper.getWritableDatabase();
//
//            database.execSQL("insert into " + DataBaseHelperClass_RandomNum.TABLE_NAME + " ("
//                    + DataBaseHelperClass_RandomNum.RandomNum + ") values " + "('" + report_no + "')");
        }

        if(eng_certi!=null){
            if(Objects.equals(eng_certi, "K")){
                lang_flag = "K";
            }else if(Objects.equals(eng_certi, "E")){
                lang_flag = "E";
            }
        }

        Log.d("New_Request_Sec_Con..", "" + district_Code);
        Log.d("New_Request_Sec_Con..", "" + taluk_Code);
        Log.d("New_Request_Sec_Con..", "" + hobli_Code);
        Log.d("New_Request_Sec_Con..", "" + va_Circle_Code);
        Log.d("Item_Position", "" + item_position);
        Log.d("Village_NameCasteIncome", "" + village_name);
        Log.d("Service_NameCasteIncome", "" + service_name);
        Log.d("villageCodeCasteIncome", "" + villageCode);
        Log.d("serviceCodeCasteIncome", "" + serviceCode);
        Log.d("eng_certi", "" + eng_certi);
        Log.d("optionA", "" + optionA);
        Log.d("New_Request", "town_code: "+town_code);
        Log.d("New_Request", "town_Name: "+town_Name);
        Log.d("New_Request", "ward_code: "+ward_code);
        Log.d("New_Request", "ward_Name: "+ward_Name);
        Log.d("New_Request","option_Flag: "+ option_Flag);

        Log.d("New_Request", "" + district_Code);
        Log.d("New_Request", "" + taluk_Code);
        Log.d("New_Request", "" + hobli_Code);
        Log.d("New_Request", "" + village_name);

        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor1 = database.rawQuery("SELECT " + DataBaseHelperClass_btnDownload_ServiceTranTable.Village_Code + ", " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + " FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME + " where "
                + DataBaseHelperClass_btnDownload_ServiceTranTable.District_Code + "=" + district_Code + " and " + DataBaseHelperClass_btnDownload_ServiceTranTable.Taluk_Code + "=" + taluk_Code
                + " and " + DataBaseHelperClass_btnDownload_ServiceTranTable.Hobli_Code + "=" + hobli_Code + " and " + DataBaseHelperClass_btnDownload_ServiceTranTable.Applicant_Name + "='" + applicant_name
                + "' and " + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
        if (cursor1.getCount() > 0) {

            openHelper = new DataBaseHelperClass_VillageNames(New_Request.this);
            database = openHelper.getWritableDatabase();

            Cursor cursor = database.rawQuery("select " +getString(R.string.village_table_habitation_name) + " from " + DataBaseHelperClass_VillageNames.TABLE_NAME
                    + " where "
                    + DataBaseHelperClass_VillageNames.HM_village_code + "='" + villageCode + "'", null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    strSearchVillageName = cursor.getString(cursor.getColumnIndex(getString(R.string.village_table_habitation_name)));
                    Log.d("VillageName", "" + strSearchVillageName);
                }
            } else {
                cursor.close();
            }

            openHelper = new DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster(New_Request.this);
            database = openHelper.getWritableDatabase();

            Cursor cursor2 = database.rawQuery("select " + getString(R.string.facility_table_name) + " from "
                    + DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster.TABLE_NAME + " where " + DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster.FM_facility_code + "=" + serviceCode, null);
            if (cursor2.getCount() > 0) {
                if (cursor2.moveToFirst()) {
                    strSearchServiceName = cursor2.getString(cursor2.getColumnIndex(getString(R.string.facility_table_name)));
                    Log.d("ServiceName", "" + strSearchServiceName);
                }
            } else {
                cursor2.close();
            }
            Log.d("village_Code1", "" + villageCode);
            Log.d("service_Code1", "" + serviceCode);
            Log.d("village_Name1", "" + strSearchVillageName);
            Log.d("service_Name1", "" + strSearchServiceName);
        } else {
            cursor1.close();
        }
        Log.d("village_Code1", "" + villageCode);
        Log.d("service_Code1", "" + serviceCode);
        Log.d("Village_NameSecScreen", "" + strSearchVillageName);
        Log.d("Service_NameSecScreen", "" + strSearchServiceName);

        tvTaluk.setText(taluk);
        tvHobli.setText(hobli);
        tvVA_Name.setText(VA_Name);
        tvServiceName.setText(strSearchServiceName);

        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request.this);
        database = openHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Applicant_Name + "='" + applicant_name + "'" + " and "
                + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
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
                eng_certi = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_Eng_Certificate));
                appTitle_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.ApplicantTiitle));
                binCom_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.BinCom));
                fatTitle_Code = cursor.getInt(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.RelationTitle));
                VA_IMEI = cursor.getString(cursor.getColumnIndex(DataBaseHelperClass_btnDownload_ServiceTranTable.VA_IMEI));
            }
        } else {
            cursor.close();
        }

        if (Id_Code == 19 || Id_Code == 0){
            trID.setVisibility(View.GONE);
        } else {
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this, str, str, str);
            sqlLiteOpenHelper_class.open_ID_Type_Tbl();
            Id_Name = sqlLiteOpenHelper_class.GetID_Name(Id_Code, getString(R.string.id_name));

            Log.d("ID_Name", ""+Id_Name);
            trID.setVisibility(View.VISIBLE);
        }

        Log.d("Raised_Location",""+raisedLoc);

        sqLiteAssetHelper_masters = new SQLiteAssetHelper_Masters(this, activity);
        sqLiteAssetHelper_masters.open_Title_MASTER_Tbl();
        appTitle_Name = sqLiteAssetHelper_masters.Get_Title_By_Code(appTitle_Code, getString(R.string.title_desc_name));
        binCom_Name = sqLiteAssetHelper_masters.Get_BinCom_By_Code(binCom_Code, getString(R.string.bincom_desc_name));
        fatTitle_Name = sqLiteAssetHelper_masters.Get_Title_By_Code(fatTitle_Code, getString(R.string.title_desc_name));

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

        if(TextUtils.isEmpty(add_pin)){
            add_pin="0";
        }

        String appName = appTitle_Name + " " + applicant_name;
        String father_or_Husband_Name = fatTitle_Name + " " + fatherName;
        txt_appID.setText(applicant_Id);
        txt_appName.setText(appName);
        txt_BinCom.setText(binCom_Name);
        txt_appFatherName.setText(father_or_Husband_Name);
        txt_appMotherName.setText(motherName);
        tv_IDName.setText(Id_Name);
        txt_ID_Num.setText(rationCardNo);
        txt_appMobileNum.setText(mobileNo);
        txt_add1.setText(address1);
        txt_add2.setText(address2);
        txt_add3.setText(address3);
        txt_add_Pin.setText(add_pin);
        txt_ReportNo.setText(report_no);

        Log.d("txt_appID", ""+applicant_Id);
        Log.d("txt_appName", "" + applicant_name);
        Log.d("fatherName", "" + fatherName);
        Log.d("motherName", "" + motherName);
        Log.d("rationCardNo", "" + rationCardNo);
        Log.d("mobileNo", "" + mobileNo);
        Log.d("address1", "" + address1);
        Log.d("address2", "" + address2);
        Log.d("address3", "" + address3);
        Log.d("add_pin", "" + add_pin);
        Log.d("Eng_Certi", "" + eng_certi);

        if (eng_certi.equals("K")){
            tvRemarks.setFilters(new InputFilter[] { filter_Kan });
        }else if (eng_certi.equals("E")){
            tvRemarks.setFilters(new InputFilter[] { filter_Eng });
        }

        tvRemarks.setOnTouchListener((v, event) -> {
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        autoSearchCaste.setOnTouchListener((v, event) -> {
            autoSearchCaste.setError(null);
            autoSearchCaste.showDropDown();
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            } else if (Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        autoImageName.setOnTouchListener((v, event) -> {
            autoImageName.setError(null);
            autoImageName.showDropDown();
            if (Objects.equals(eng_certi, "K")) {
                check_Kannada_Key_lang();
            } else if (Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request.this);
        database = openHelper.getWritableDatabase();

        switch (serviceCode) {
            case "6":
            case "34":
            case "37":{
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                iv_scst.setVisibility(View.VISIBLE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);

                if (serviceCode.equals("6")) {
                    Service6.setVisibility(View.VISIBLE);
                    Service67.setVisibility(View.VISIBLE);
                    Service678.setVisibility(View.VISIBLE);
                    tr_casteService9999.setVisibility(View.GONE);
                    trCatService9999.setVisibility(View.GONE);

                    Cursor cursor2 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                            + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                            + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                    if (cursor2.getCount() > 0) {
                        if (cursor2.moveToFirst()) {
                            getCatCode = cursor2.getInt(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                            getCasteCode = cursor2.getInt(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                            amount = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                            appImage = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));
                            Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                        }
                    } else {
                        cursor2.close();
                    }
                    if (appImage != null) {
                        iv_scst.setVisibility(View.VISIBLE);
                        byte[] im_bytes = Base64.getDecoder().decode(appImage);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                        iv_scst.setImageBitmap(bitmap);
                    } else {
                        iv_scst.setVisibility(View.GONE);
                    }
                    sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                    sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                    category_name = sqlLiteOpenHelper_class.GetCategory_BY_Code(getCatCode);
                    caste_name = sqlLiteOpenHelper_class.GetCaste_BY_Code(getCatCode, getCasteCode);

                    Log.d("get_Value", "" + category_name + "" + caste_name);
                    tvCategory.setText(category_name);
                    tvCaste.setText(caste_name);

                } else {
                    Service6.setVisibility(View.GONE);
                    Service67.setVisibility(View.GONE);
                    Service678.setVisibility(View.VISIBLE);
                    tr_casteService9999.setVisibility(View.GONE);
                    trCatService9999.setVisibility(View.GONE);

                    Cursor cursor2 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                            + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                            + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                    if (cursor2.getCount() > 0) {
                        if (cursor2.moveToFirst()) {
                            amount = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                            appImage = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));

                            Log.d("value1", "" + amount);
                        }
                    } else {
                        cursor2.close();
                    }
                    if (appImage != null) {
                        iv_scst.setVisibility(View.VISIBLE);
                        byte[] im_bytes = Base64.getDecoder().decode(appImage);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                        iv_scst.setImageBitmap(bitmap);
                    } else {
                        iv_scst.setVisibility(View.GONE);
                    }

                }
                tvAmount.setText(amount);
                etAnnualIncome.setText(amount);
                break;
            }
            case "27":
            case "11":{
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                iv_scst.setVisibility(View.VISIBLE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);

                    Service6.setVisibility(View.GONE);
                    Service67.setVisibility(View.GONE);
                    Service678.setVisibility(View.VISIBLE);
                    tr_casteService9999.setVisibility(View.GONE);
                    trCatService9999.setVisibility(View.GONE);

                    Cursor cursor2 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                            + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                            + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                    if (cursor2.getCount() > 0) {
                        if (cursor2.moveToFirst()) {
                            amount = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.IST_annual_income));
                            appImage = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));

                            Log.d("value1", "" + amount);
                        }
                    } else {
                        cursor2.close();
                    }
                    if (appImage != null) {
                        iv_scst.setVisibility(View.VISIBLE);
                        byte[] im_bytes = Base64.getDecoder().decode(appImage);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                        iv_scst.setImageBitmap(bitmap);
                    } else {
                        iv_scst.setVisibility(View.GONE);
                    }

                tvAmount.setText(amount);
                etAnnualIncome.setText(amount);
                break;
        }
            case "9":
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                Service6.setVisibility(View.VISIBLE);
                Service67.setVisibility(View.VISIBLE);
                Service678.setVisibility(View.VISIBLE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                iv_scst.setVisibility(View.VISIBLE);
                tr_casteService9999.setVisibility(View.GONE);
                trCatService9999.setVisibility(View.GONE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);

                openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request.this);
                database = openHelper.getWritableDatabase();
                Cursor cursor4 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                if (cursor4.getCount() > 0) {
                    if (cursor4.moveToFirst()) {
                        getCatCode = 9;
                        getCasteCode = cursor4.getInt(cursor4.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.SCOT_caste_app));
                        amount = cursor4.getString(cursor4.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.SCOT_annual_income));
                        appImage = cursor4.getString(cursor4.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));

                        Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                    }
                } else {
                    cursor4.close();
                }
                if (appImage != null) {
                    iv_scst.setVisibility(View.VISIBLE);
                    byte[] im_bytes = Base64.getDecoder().decode(appImage);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                    iv_scst.setImageBitmap(bitmap);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }
                sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                category_name = "OBC (Central)";
                caste_name = sqlLiteOpenHelper_class.GetCaste_OBC_BY_Code(getCasteCode);

                Log.d("get_Value", "" + category_name + "" + caste_name);
                tvCategory.setText(category_name);
                tvCaste.setText(caste_name);
                tvAmount.setText(amount);
                etAnnualIncome.setText(amount);
                break;
            case "43":
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                Service6.setVisibility(View.VISIBLE);
                Service67.setVisibility(View.VISIBLE);
                Service678.setVisibility(View.VISIBLE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                tr_casteService9999.setVisibility(View.GONE);
                trCatService9999.setVisibility(View.GONE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);
                trCertValidity.setVisibility(View.GONE);
                trFinYear.setVisibility(View.GONE);

                Cursor cursor5 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                if (cursor5.getCount() > 0) {
                    if (cursor5.moveToFirst()) {
                        getCatCode = cursor5.getInt(cursor5.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                        getCasteCode = cursor5.getInt(cursor5.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                        amount = cursor5.getString(cursor5.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                        appImage = cursor5.getString(cursor5.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));
                        finYear = cursor5.getString(cursor5.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.finYear));
                        certValidity = cursor5.getString(cursor5.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.CertValidity));
                        otherCaste = cursor5.getString(cursor5.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.CST_Caste_Desc));
                        Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                    }
                } else {
                    cursor5.close();
                }
                if(otherCaste.equals("null"))
                {
                    trews_caste.setVisibility(View.GONE);
                }else {
                    trews_caste.setVisibility(View.VISIBLE);
                }

                if (appImage != null) {
                    iv_scst.setVisibility(View.VISIBLE);
                    byte[] im_bytes = Base64.getDecoder().decode(appImage);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                    iv_scst.setImageBitmap(bitmap);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }


                sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                category_name = sqlLiteOpenHelper_class.GetCategory_BY_Code(getCatCode);
                caste_name = sqlLiteOpenHelper_class.GetCaste_BY_Code(getCatCode, getCasteCode);

                Log.d("get_Value", "" + category_name + "" + caste_name);
                tvCategory.setText(category_name);
                tvCaste.setText(caste_name);
                tvAmount.setText(amount);
                tvFinYear.setText(finYear);
                tvCertVal.setText(certValidity);
                tvCasteOthers.setText(otherCaste);
                etAnnualIncome.setText(amount);

                break;
            case "7":
            case "8":
            case "42": {
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                Service6.setVisibility(View.VISIBLE);
                Service67.setVisibility(View.VISIBLE);
                Service678.setVisibility(View.VISIBLE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                tr_casteService9999.setVisibility(View.GONE);
                trCatService9999.setVisibility(View.GONE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);

                sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this);
                sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();

                openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request.this);
                database = openHelper.getWritableDatabase();
                Cursor cursor2 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                if (cursor2.getCount() > 0) {
                    if (cursor2.moveToFirst()) {
                        getCatCode = cursor2.getInt(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                        getCasteCode = cursor2.getInt(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                        amount = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                        appImage = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));
                        Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                    }
                } else {
                    cursor2.close();
                }

                if (serviceCode.equals("8")) {
                    if (appImage != null) {
                        iv_scst.setVisibility(View.VISIBLE);
                        byte[] im_bytes = Base64.getDecoder().decode(appImage);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                        iv_scst.setImageBitmap(bitmap);
                    } else {
                        iv_scst.setVisibility(View.GONE);
                    }
                } else if (serviceCode.equals("42")){
                    iv_scst.setVisibility(View.GONE);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }
                sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                category_name = sqlLiteOpenHelper_class.GetCategory_BY_Code(getCatCode);
                caste_name = sqlLiteOpenHelper_class.GetCaste_BY_Code(getCatCode, getCasteCode);

                Log.d("get_Value", "" + category_name + "" + caste_name);
                tvCategory.setText(category_name);
                tvCaste.setText(caste_name);
                tvAmount.setText(amount);
                etAnnualIncome.setText(amount);
                break;
            }
            case "10":
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                Service6.setVisibility(View.GONE);
                Service67.setVisibility(View.GONE);
                Service678.setVisibility(View.GONE);
                trService10.setVisibility(View.VISIBLE);
                Service10.setVisibility(View.VISIBLE);
                iv_scst.setVisibility(View.VISIBLE);
                tr_casteService9999.setVisibility(View.GONE);
                trCatService9999.setVisibility(View.GONE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);

                openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request.this);
                database = openHelper.getWritableDatabase();
                Cursor cursor3 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                if (cursor3.getCount() > 0) {
                    if (cursor3.moveToFirst()) {
                        year = cursor3.getString(cursor3.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.GST_No_Years_Applied));
                        month = cursor3.getString(cursor3.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.GST_No_Mths_Applied));
                        appImage = cursor3.getString(cursor3.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));

                        Log.d("value1", "" + year + " " + month);
                    }
                } else {
                    cursor3.close();
                }
                if (appImage != null) {
                    iv_scst.setVisibility(View.VISIBLE);
                    byte[] im_bytes = Base64.getDecoder().decode(appImage);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                    iv_scst.setImageBitmap(bitmap);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }

                Log.d("get_Value", "" + year + " " + month);
                tvYear.setText(year);
                tvMonths.setText(month);
                break;
            case "9999" :
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                Service678.setVisibility(View.VISIBLE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                Service6.setVisibility(View.GONE);
                Service67.setVisibility(View.GONE);
                iv_scst.setVisibility(View.VISIBLE);
                tr_casteService9999.setVisibility(View.GONE);
                tr_casteService9999.setVisibility(View.VISIBLE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);
                GetCategory();
                spCategory.setOnItemSelectedListener(new GetCategorySelected());

                Cursor cursor2 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                if (cursor2.getCount() > 0) {
                    if (cursor2.moveToFirst()) {
                        getCatCode = cursor2.getInt(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                        getCasteCode = cursor2.getInt(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                        amount = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                        appImage = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));
                        Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                    }
                } else {
                    cursor2.close();
                }

                if (appImage != null) {
                    iv_scst.setVisibility(View.VISIBLE);
                    byte[] im_bytes = Base64.getDecoder().decode(appImage);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                    iv_scst.setImageBitmap(bitmap);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }

                sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                category_name = sqlLiteOpenHelper_class.GetCategory_BY_Code(getCatCode);
                caste_name = sqlLiteOpenHelper_class.GetCaste_BY_Code(getCatCode, getCasteCode);

                if (getCatCode!=0){
                    GetCaste(getCatCode);
                    tr_casteService9999.setVisibility(View.VISIBLE);
                    autoSearchCaste.setText(caste_name);
                    strSearchCaste = caste_name;
                }
                spCategory.setSelection(getCatCode);
                tvAmount.setText(amount);
                etAnnualIncome.setText(amount);
                break;
            case "41":
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                Service678.setVisibility(View.VISIBLE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                Service6.setVisibility(View.GONE);
                Service67.setVisibility(View.GONE);
                iv_scst.setVisibility(View.VISIBLE);
                trCatService9999.setVisibility(View.GONE);
                tr_casteService9999.setVisibility(View.VISIBLE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);


                Cursor cursor41 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                if (cursor41.getCount() > 0) {
                    if (cursor41.moveToFirst()) {
                        getCatCode = cursor41.getInt(cursor41.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                        getCasteCode = cursor41.getInt(cursor41.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                        amount = cursor41.getString(cursor41.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                        appImage = cursor41.getString(cursor41.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));
                        Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                    }
                } else {
                    cursor41.close();
                }
                if (appImage != null) {
                    iv_scst.setVisibility(View.VISIBLE);
                    byte[] im_bytes = Base64.getDecoder().decode(appImage);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                    iv_scst.setImageBitmap(bitmap);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }

                sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request.this, "samyoj", "samyoj","samyoj","samyoj", "samyoj");
                sqlLiteOpenHelper_class.open_Minority_Caste_Tbl();
                caste_name = sqlLiteOpenHelper_class.GetMinorityCaste_BY_Code(getCasteCode);




                GetMinorityCaste(getCasteCode);
                tr_casteService9999.setVisibility(View.VISIBLE);
                autoSearchCaste.setText(caste_name);
                strSearchCaste = caste_name;
                tvAmount.setText(amount);
                etAnnualIncome.setText(amount);

                break;

            case "51":
            case "52":
            case "53":
            case "55": {
                trDetConfirmation.setVisibility(View.VISIBLE);
                trAgriConfirmation.setVisibility(View.GONE);
                Service678.setVisibility(View.GONE);
                trService10.setVisibility(View.GONE);
                Service10.setVisibility(View.GONE);
                Service6.setVisibility(View.GONE);
                Service67.setVisibility(View.GONE);
                iv_scst.setVisibility(View.VISIBLE);
                tvAmount.setVisibility(View.GONE);
                etAnnualIncome.setVisibility(View.GONE);
                tr_casteService9999.setVisibility(View.GONE);
                trCatService9999.setVisibility(View.GONE);
                trService55.setVisibility(View.GONE);
                trService551.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);

                Cursor cursor6 = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
                if (cursor6.getCount() > 0) {
                    if (cursor6.moveToFirst()) {
                        getCatCode = cursor6.getInt(cursor6.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                        getCasteCode = cursor6.getInt(cursor6.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                        appImage = cursor6.getString(cursor6.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ST_applicant_photo));
                        //AccNumber = cursor6.getString(cursor6.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AccountNumber));
                       // IfscCode = cursor6.getString(cursor6.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Ifsc_code));
                        amount = cursor6.getString(cursor6.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                        Log.d("value1", " " + appAge + " " + AccNumber + " " + IfscCode + " " + amount);
                    }
                } else {
                    cursor6.close();
                }
                if (appImage != null) {
                    iv_scst.setVisibility(View.VISIBLE);
                    byte[] im_bytes = Base64.getDecoder().decode(appImage);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                    iv_scst.setImageBitmap(bitmap);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }

                sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                category_name = sqlLiteOpenHelper_class.GetCategory_BY_Code(getCatCode);
                caste_name = sqlLiteOpenHelper_class.GetCaste_BY_Code(getCatCode, getCasteCode);
                tvCategory.setText(category_name);
                tvCaste.setText(caste_name);

                tvAccnumber.setText(AccNumber);
                tvIfsc.setText(IfscCode);
                etAnnualIncome.setText(amount);
                etAge.setText(appAge);
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
            case "40":
            case "56":
            case "57":
            case "58":
            case "59":
            case "60":
            case "61":{
                trDetConfirmation.setVisibility(View.GONE);
                trAgriConfirmation.setVisibility(View.VISIBLE);
                Service6.setVisibility(View.GONE);
                Service67.setVisibility(View.GONE);
                trCatService9999.setVisibility(View.GONE);
                tr_casteService9999.setVisibility(View.GONE);
                trApplicantAge.setVisibility(View.GONE);
                tl_service9999.setVisibility(View.GONE);

                iv_scst.setVisibility(View.VISIBLE);
                tvAmount.setVisibility(View.GONE);
                etAnnualIncome.setVisibility(View.GONE);
                etAnnualIncome.setText(amount);

                if (appImage != null) {
                    iv_scst.setVisibility(View.VISIBLE);
                    byte[] im_bytes = Base64.getDecoder().decode(appImage);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(im_bytes, 0, im_bytes.length);
                    iv_scst.setImageBitmap(bitmap);
                } else {
                    iv_scst.setVisibility(View.GONE);
                }
                break;
            }
        }

        gpsTracker = new GPSTracker(getApplicationContext(), New_Request.this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            Log.d("Location", latitude + "" + longitude);
        } else {
            //gpsTracker.showSettingsAlert();
            Toast.makeText(getApplicationContext(), getString(R.string.switch_on_gps), Toast.LENGTH_SHORT).show();
        }

        dialog = new ProgressDialog(this, R.style.CustomDialog);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.downloading_please_wait));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgress(0);


        txt_appMobileNum.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + mobileNo));
            startActivity(callIntent);
        });

        radioGroupA.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.radioButton1A) {
                optionA = getString(R.string.yes);
                Log.d("optionA", "" + optionA);
            } else if (checkedId == R.id.radioButton2A) {
                optionA = getString(R.string.no);
                Log.d("optionA", "" + optionA);
                Intent i13 = new Intent(New_Request.this, New_Request_SecondScreen.class);
                i13.putExtra("applicant_name", applicant_name);
                i13.putExtra("applicant_Id", applicant_Id);
                i13.putExtra("serviceCode", serviceCode);
                i13.putExtra("strSearchServiceName", service_name);
                i13.putExtra("villageCode", villageCode);
                i13.putExtra("district_Code", district_Code);
                i13.putExtra("district", district);
                i13.putExtra("taluk_Code", taluk_Code);
                i13.putExtra("taluk", taluk);
                i13.putExtra("hobli_Code", hobli_Code);
                i13.putExtra("hobli", hobli);
                i13.putExtra("va_Circle_Code", va_Circle_Code);
                i13.putExtra("VA_Circle_Name", VA_Circle_Name);
                i13.putExtra("VA_Name", VA_Name);
                i13.putExtra("strSearchVillageName", village_name);
                i13.putExtra("report_No", report_no);
                i13.putExtra("option_Flag", option_Flag);
                i13.putExtra("town_Name", town_Name);
                i13.putExtra("town_code", town_code);
                i13.putExtra("ward_Name", ward_Name);
                i13.putExtra("ward_code", ward_code);
                startActivity(i13);
                finish();
            }
        });

        radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.radioButton11) {
                optionA = getString(R.string.yes);
                Log.d("optionA", "" + optionA);
            } else if(checkedId == R.id.radioButton22)
            {
                optionA = getString(R.string.no);
                Log.d("optionA", "" + optionA);
            }
        });

        btnDownDocs.setOnClickListener(v -> {
            if (isNetworkAvailable()) {

                dialog.show();
                //dialog1.setProgress(0);

                Log.d("Docs", "Applicant_ID:"+applicant_Id);
                openHelper = new DataBaseHelperClass_btnDownload_Docs(New_Request.this);
                database = openHelper.getWritableDatabase();

                String username = uName_get.substring(0, 3);//First three characters of username
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
                String day_num = dateFormat.format(date);//Current Day
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yy", Locale.ENGLISH);
                String year_num = dateFormat1.format(date);//last two digits of the year
                String app_name = "Samyojane";

                String fieldVerify_api_flag2 = username + day_num + app_name + year_num;

                GetDocRequestClass getDocRequestClass = new GetDocRequestClass();
                getDocRequestClass.setFlag1(getString(R.string.fieldVerify_api_flag1));
                getDocRequestClass.setFlag2(fieldVerify_api_flag2);
                getDocRequestClass.setLoginId(uName_get);
                getDocRequestClass.setDesignationCode(DesiCode);
                getDocRequestClass.setGscNoList(applicant_Id);
                GetDocsFromServer(getDocRequestClass);
            }
            else {
                Toast.makeText(getApplicationContext(), getString(R.string.connection_not_available), Toast.LENGTH_SHORT).show();
            }
        });

        btnViewDocs.setOnClickListener(v -> {
            Intent i12 = new Intent(New_Request.this, View_Docs.class);
            i12.putExtra("district_Code", district_Code);
            i12.putExtra("district", district);
            i12.putExtra("taluk_Code", taluk_Code);
            i12.putExtra("taluk", taluk);
            i12.putExtra("hobli_Code", hobli_Code);
            i12.putExtra("hobli", hobli);
            i12.putExtra("va_Circle_Code", va_Circle_Code);
            i12.putExtra("VA_Circle_Name", VA_Circle_Name);
            i12.putExtra("VA_Name", VA_Name);
            i12.putExtra("strSearchServiceName", service_name);
            i12.putExtra("villageCode", villageCode);
            i12.putExtra("strSearchVillageName", village_name);
            i12.putExtra("applicant_name", applicant_name);
            i12.putExtra("applicant_Id", applicant_Id);
            i12.putExtra("report_no", report_no);
            startActivity(i12);
        });

        btnUploadDocs.setOnClickListener(v -> {


        openHelper = new DataBaseHelperClass_btnUpload_Docs(this);
        database = openHelper.getWritableDatabase();
        Cursor cursorDoc = database.rawQuery("select * from " + DataBaseHelperClass_btnUpload_Docs.TABLE_NAME + " where "+ DataBaseHelperClass_btnUpload_Docs.GSCNO +"='" + applicant_Id+ "'", null);


        if (cursorDoc.getCount() <3) {
            saveImageDialog();
        }else
        {
            cursorDoc.close();
            Toast.makeText(New_Request.this,"Only 3 documents are allowed to upload",Toast.LENGTH_LONG).show();
        }
    });


        btnViewUploadDocs.setOnClickListener(v -> {
            Intent i11 = new Intent(New_Request.this, ViewUploadedDocs.class);
            i11.putExtra("district_Code", district_Code);
            i11.putExtra("district", district);
            i11.putExtra("taluk_Code", taluk_Code);
            i11.putExtra("taluk", taluk);
            i11.putExtra("hobli_Code", hobli_Code);
            i11.putExtra("hobli", hobli);
            i11.putExtra("va_Circle_Code", va_Circle_Code);
            i11.putExtra("VA_Circle_Name", VA_Circle_Name);
            i11.putExtra("VA_Name", VA_Name);
            i11.putExtra("strSearchServiceName", service_name);
            i11.putExtra("villageCode", villageCode);
            i11.putExtra("strSearchVillageName", village_name);
            i11.putExtra("applicant_name", applicant_name);
            i11.putExtra("applicant_Id", applicant_Id);
            i11.putExtra("report_no", report_no);
            startActivity(i11);
        });

        btnSave.setOnClickListener(v -> {
            Name = autoImageName.getText().toString();
            if (Name.isEmpty()) {
                autoImageName.setError("Field cannot be Null");
            } else {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                alertDialog.dismiss();
                ((ViewGroup) view.getParent()).removeView(view);
            }
            autoImageName.setText("");
        });

        btnCancel.setOnClickListener(v -> {
            alertDialog.dismiss();
            autoImageName.setText("");
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        });

        spCategory.setOnTouchListener((v, event) -> {
            autoSearchCaste.setText("");
            autoSearchCaste.setError(null);
            autoSearchCaste.showDropDown();
            return false;
        });

        btnReport.setOnClickListener(v -> {

            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
                Log.d("Location", latitude + "" + longitude);
            } else {
                //gpsTracker.showSettingsAlert();
                Toast.makeText(getApplicationContext(), getString(R.string.switch_on_gps), Toast.LENGTH_SHORT).show();
            }

            remarks = tvRemarks.getText().toString().trim();
            Log.d("remarks", remarks);

            amount = etAnnualIncome.getText().toString();
            Log.d("income", amount);

            appAge = etAge.getText().toString();
            Log.d("Age", appAge);


            if (latitude != 0.0 && longitude != 0.0) {
                Log.d("value", "enter first if");
                if (serviceCode.equalsIgnoreCase("9999")) {
                    if(!strCategory.equals(getString(R.string.select_category_spinner))) {
                        strSearchCaste = autoSearchCaste.getText().toString();
                        Log.d("Selected_Caste1", "" + strSearchCaste);
                        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                        sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                        getCasteCode = sqlLiteOpenHelper_class.GetCasteCode(strSearchCaste, getCatCode);
                        Log.d("Cat_Code1", "" + getCatCode);
                        Log.d("Caste_Code1", "" + getCasteCode);
                        if (getCatCode!=0) {
                            if (!strSearchCaste.equals(getString(R.string.select_caste_spinner))) {
                                if (getCasteCode != 0) {
                                    if (Objects.equals(optionA, "Y")) {
                                        if (TextUtils.isEmpty(remarks)) {
                                            tvRemarks.setError(getString(R.string.field_canno_null));
                                            Log.d("value", "enter third if");
                                        } else {
                                            //dialog.show();
                                            save_To_DB_When_Correct();

                                        }

                                    } else {
                                        Log.d("value", "enter second else");
                                    }
                                } else {
                                    autoSearchCaste.setError(getString(R.string.invalid_caste));
                                }
                            } else {
                                autoSearchCaste.setError(getString(R.string.select_caste));
                            }
                        } else {
                            ((TextView)spCategory.getSelectedView()).setError(getString(R.string.incorrect_value));
                            Toast.makeText(getApplicationContext(), getString(R.string.select_category), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ((TextView)spCategory.getSelectedView()).setError(getString(R.string.select_category));
                        Toast.makeText(getApplicationContext(), getString(R.string.select_category), Toast.LENGTH_SHORT).show();
                    }
                }
                else if(serviceCode.equals("41"))
                {
                    if (Objects.equals(optionA, "Y")) {
                        if (TextUtils.isEmpty(remarks)) {
                            tvRemarks.setError(getString(R.string.field_canno_null));
                            Log.d("value", "enter third if");
                        } else {
                            //dialog.show();
                            save_To_DB_When_Correct();

                        }

                    } else {
                        Log.d("value", "enter second else");
                    }
                }
                else if (serviceCode.equals("55") || serviceCode.equals("53")) {
                   // if (!TextUtils.isEmpty(appAge)) {
                       /* if (TextUtils.isEmpty(amount)) {
                            etAnnualIncome.setError(getString(R.string.field_canno_null));
                        } else {
                            income_len = amount.length();
                            income_Value = Integer.parseInt(amount);
                            Log.d("Income value", "" + amount + ", Length: " + income_len + ", Value: " + income_Value);
                            if (income_len <= 6 && income_Value <= 999999) {*/
                                if (Objects.equals(optionA, "Y")) {
                                    if (TextUtils.isEmpty(remarks)) {
                                        tvRemarks.setError(getString(R.string.field_canno_null));
                                        Log.d("value", "enter third if");
                                    } else {
                                        //dialog.show();
                                        save_To_DB_When_Correct();

                                    }

                                } else {
                                    Log.d("value", "enter second else");
                                }
                            /*} else {
                                etAnnualIncome.setError(getString(R.string.incorrect_value));
                            }
                        }*/
                    /*} else {
                        etAge.setError(getString(R.string.field_canno_null));
                    }*/
                }
                else if (serviceCode.matches("12") || serviceCode.matches("17") || serviceCode.matches("19")||
                        serviceCode.matches("24") || serviceCode.matches("25") || serviceCode.matches("26") ||
                        serviceCode.matches("28") || serviceCode.matches("29") || serviceCode.matches("30")) {

                        if (TextUtils.isEmpty(remarks)) {
                            tvRemarks.setError(getString(R.string.field_canno_null));
                            Log.d("value", "enter third if");
                        } else {
                            //dialog.show();
                            save_To_DB_When_Correct();

                        }

                }
                else {
                    if (Objects.equals(optionA, "Y")) {
                        if (TextUtils.isEmpty(remarks)) {
                            tvRemarks.setError(getString(R.string.field_canno_null));
                            Log.d("value", "enter third if");
                        } else {
                            //dialog.show();
                            save_To_DB_When_Correct();
                        }
                    } else {
                        Log.d("value", "enter second else");
                        if (TextUtils.isEmpty(remarks)) {
                            tvRemarks.setError(getString(R.string.field_canno_null));
                            Log.d("value", "enter third if");
                        } else {
                            //dialog.show();
                            save_To_DB_When_Correct();
                        }
                    }
                }

            } else {

                Log.d("value", "enter first else");

                runOnUiThread(() -> {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(New_Request.this);
                    dialog.setCancelable(false);
                    dialog.setTitle(getString(R.string.alert));
                    dialog.setMessage(getString(R.string.cannot_get_location));
                    dialog.setNegativeButton(getString(R.string.ok), (dialog2, which) -> {
                        //Action for "Cancel".
                        dialog2.cancel();
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

    public void save_To_DB_When_Correct(){
        try {

            openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request.this);
            database = openHelper.getWritableDatabase();

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
            String currDate = dateFormat.format(date);
            database.execSQL("delete from " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1
                    + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + "='" + applicant_Id + "'");

            database.execSQL("update " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME + " set "
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.DataUpdateFlag + "=1 where "
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'");

            database.execSQL("insert into " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + "("
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_LoginID + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Service_Code + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DesignationCode + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DifferFromAppinformation + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Can_Certificate_Given + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Remarks + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Report_No + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_ReportDate + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_AppTitle + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_BinCom + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_FatTitle + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_FatherName + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_MotherName + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_MobileNumber + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Address1 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Address2 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Address3 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_PinCode + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Applicant_Category + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Applicant_Caste + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_CasteSl + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Age + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Income + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Total_No_Years_10 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_NO_Months_10 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_App_Father_Category_8 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_App_Mother_Category_8 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_APP_Father_Caste_8 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_APP_Mother_Caste_8 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Belongs_Creamy_Layer_6 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Reason_for_Creamy_Layer_6 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Reside_At_Stated_Address_10 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Place_Match_With_RationCard_10 + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Photo + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_vLat + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_vLong + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_UploadedDate + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DataUpdateFlag + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_VA_RI_IMEI + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_VA_RI_Name + ","
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.CST_Caste_Desc
                    + ") Values ('"
                    + applicant_Id + "','"
                    + uName_get + "',"
                    + serviceCode + ","
                    + DesiCode + ","
                    + "'Y'" + ",'" //DifferFromAppinformation
                    + optionA + "','" //Can_Certificate_Given
                    + remarks + "','"
                    + report_no + "','"
                    + currDate + "',"
                    + appTitle_Code + ","
                    + binCom_Code + ","
                    + fatTitle_Code + ",'"
                    + fatherName + "','"
                    + motherName + "','"
                    + mobileNo + "','"
                    + address1 + "','"
                    + address2 + "','"
                    + address3 + "',"
                    + add_pin + ","
                    + getCatCode + ","
                    + getCasteCode + ","
                    + "0" + ",'"//CasteSl
                    + appAge + "','" //Age
                    + amount + "',"
                    + year + ","
                    + month + ","
                    + "0" + "," //App_Father_Category_8
                    + "0" + "," //App_Mother_Category_8
                    + "0" + "," //APP_Father_Caste_8
                    + "0" + "," //APP_Mother_Caste_8
                    + "''" + "," //Belongs_Creamy_Layer_6
                    + "0" + "," //Reason_for_Creamy_Layer_6
                    + "''" + "," //Reside_At_Stated_Address_10
                    + "''" + "," //Place_Match_With_RationCard_10
                    + "''" + "," //Photo
                    + latitude + ","
                    + longitude + ",'"
                    + currDate + "',"
                    + "1" + ",'" //DataUpdateFlag
                    + VA_IMEI + "','" //Updated_By_VA_IMEI
                    + VA_Name  +"','"//Updated_By_VA_Name
                    + otherCaste
                    + "')");

            Log.d("Database", DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + "Database Inserted");
            Toast.makeText(getApplicationContext(), getString(R.string.data_saves_successfully), Toast.LENGTH_SHORT).show();

            truncateDatabase_Docs();

            Intent i1 = new Intent(New_Request.this, New_Request_FirstScreen.class);
            i1.putExtra("applicant_Id", applicant_Id);
            i1.putExtra("district_Code", district_Code);
            i1.putExtra("taluk_Code", taluk_Code);
            i1.putExtra("hobli_Code", hobli_Code);
            i1.putExtra("district", district);
            i1.putExtra("taluk", taluk);
            i1.putExtra("VA_Name", VA_Name);
            i1.putExtra("hobli", hobli);
            i1.putExtra("va_Circle_Code", va_Circle_Code);
            i1.putExtra("VA_Circle_Name", VA_Circle_Name);
            i1.putExtra("strSearchServiceName", service_name);
            i1.putExtra("strSearchVillageName", village_name);
            i1.putExtra("serviceCode", serviceCode);
            i1.putExtra("villageCode", villageCode);
            i1.putExtra("option_Flag", option_Flag);
            i1.putExtra("town_Name", town_Name);
            i1.putExtra("town_code", town_code);
            i1.putExtra("ward_Name", ward_Name);
            i1.putExtra("ward_code", ward_code);

            startActivity(i1);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void GetDocsFromServer(GetDocRequestClass getDocRequestClass){
        apiInterface_nic = APIClient.getClient(getString(R.string.MobAPI_New_NIC)).create(APIInterface_NIC.class);

        Call<JsonObject> call = apiInterface_nic.GetDocs(getDocRequestClass);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject1 = response.body();
                Log.d("response_server",jsonObject1 + "");
                assert jsonObject1 != null;
                JsonObject jsonObject2 = jsonObject1.getAsJsonObject("StatusMessage");
                Log.d("response_server",jsonObject2 + "");
                String response_server = jsonObject2.toString();
                try {
                    openHelper = new DataBaseHelperClass_btnDownload_Docs(New_Request.this);
                    database = openHelper.getWritableDatabase();

                    JSONObject jsonObject = new JSONObject(response_server);
                    JSONArray array = jsonObject.getJSONArray("Table");

                    truncateDatabase_Docs();

                    //dialog1.setProgress(10);
                    Type listType = new TypeToken<List<Set_and_Get_Down_Docs>>(){}.getType();
                    List<Set_and_Get_Down_Docs> myModelList = new Gson().fromJson(array.toString(), listType);
                    for(Set_and_Get_Down_Docs set_and_get_down_docs:myModelList){

                        //dialog1.setProgress(10);
                        database.execSQL("insert into " + DataBaseHelperClass_btnDownload_Docs.TABLE_NAME + "("
                                + DataBaseHelperClass_btnDownload_Docs.GSCNO+","
                                + DataBaseHelperClass_btnDownload_Docs.DocumentName+","
                                + DataBaseHelperClass_btnDownload_Docs.DocumentID+","
                                + DataBaseHelperClass_btnDownload_Docs.Document+") values ('"
                                + set_and_get_down_docs.getGSCNO() +"','"
                                + set_and_get_down_docs.getDocumentName()+"',"
                                + set_and_get_down_docs.getDocumentID() +",'"
                                + set_and_get_down_docs.getDocument()+"')");

                        Log.d("Database", "Down_Docs Database Inserted");
                        //dialog1.setProgress(30);
                        //Toast.makeText(getApplicationContext(), "Docs Downloaded successfully", Toast.LENGTH_SHORT).show();
                    }
                    database.close();
                    //dialog1.setProgress(10);
                    runOnUiThread(() -> {
                        openHelper = new DataBaseHelperClass_btnDownload_Docs(New_Request.this);
                        database = openHelper.getWritableDatabase();
                        Cursor cursor3 = database.rawQuery("select * from " + DataBaseHelperClass_btnDownload_Docs.TABLE_NAME, null);

                        if (cursor3.getCount() > 0) {
                            dialog.dismiss();
                            btnViewDocs.setVisibility(View.VISIBLE);
                            //Toast.makeText(getApplicationContext(), "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            cursor3.close();
                            Log.d("Values", "No records Exists");
                            btnViewDocs.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),getString(R.string.document_not_found), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    database.close();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("JSONException", ""+e);
                    btnViewDocs.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),getString(R.string.document_not_found), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } catch (JsonParseException e){
                    e.printStackTrace();
                    Log.d("JsonParseException", ""+e);
                    btnViewDocs.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),getString(R.string.document_not_found), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } catch (NullPointerException e){
                    e.printStackTrace();
                    Log.d("NullPointerException", ""+e);
                    btnViewDocs.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),getString(R.string.document_not_found), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("multipleResource",""+t.getMessage());
                call.cancel();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void truncateDatabase_Docs(){
        //dialog1.setProgress(20);
        openHelper = new DataBaseHelperClass_btnDownload_Docs(New_Request.this);
        database = openHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from "+ DataBaseHelperClass_btnDownload_Docs.TABLE_NAME, null);
        if(cursor.getCount()>0) {
            database.execSQL("Delete from " + DataBaseHelperClass_btnDownload_Docs.TABLE_NAME);
            Log.d("Database", "Down_Docs Database Truncated");
        } else {
            cursor.close();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void GetCategory() {
        try {
            List<SpinnerObject> labels;
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
            sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
            labels = sqlLiteOpenHelper_class.Get_Category_Service9999(getString(R.string.select_category_spinner));
            ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_color, labels);
            dataAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
            spCategory.setAdapter(dataAdapter);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_creating_table), Toast.LENGTH_LONG).show();
        }
    }

    public class GetCategorySelected implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strCategory = ((SpinnerObject) spCategory.getSelectedItem()).getValue();
            Log.d("Selected_Item1", ""+strCategory);
            getCatCode = Integer.parseInt(((SpinnerObject) spCategory.getSelectedItem()).getId());
            Log.d("Category_Code1", ""+ getCatCode);
            if (!strCategory.equals(getString(R.string.select_category_spinner))) {
                autoSearchCaste.setVisibility(View.VISIBLE);
                tr_casteService9999.setVisibility(View.VISIBLE);
                GetCaste(getCatCode);
            }
            else {
                tr_casteService9999.setVisibility(View.GONE);
                autoSearchCaste.setVisibility(View.GONE);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public void GetCaste(int num){
        List<SpinnerObject> labels;
        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
        sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
        labels = sqlLiteOpenHelper_class.GetCaste(num);

        ArrayAdapter<SpinnerObject> adapter = new ArrayAdapter<>(this, R.layout.list_item, labels);
        adapter.setDropDownViewResource(R.layout.list_item);
        autoSearchCaste.setAdapter(adapter);
        autoSearchCaste.setOnItemClickListener((parent, view, position, id) -> {
            strSearchCaste = parent.getItemAtPosition(position).toString();
            strSearchCaste = parent.getItemAtPosition(position).toString();
            Log.d("Selected_Item", ""+strSearchCaste);
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
            sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
            getCasteCode = sqlLiteOpenHelper_class.GetCasteCode(strSearchCaste, num);
            Log.d("Selected_casteCode", ""+ getCasteCode);
        });
    }

    public void GetMinorityCaste(int num){
        List<SpinnerObject> labels;
        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request.this, "samyoj","samyoj","samyoj","samyoj","samyoj");
        sqlLiteOpenHelper_class.open_Minority_Caste_Tbl();
        caste_name = sqlLiteOpenHelper_class.GetMinorityCaste_BY_Code(getCasteCode);

        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request.this, "samyoj","samyoj","samyoj","samyoj","samyoj");
        sqlLiteOpenHelper_class.open_Minority_Caste_Tbl();
        labels = sqlLiteOpenHelper_class.GetMinorityCasteByCode(num);

        ArrayAdapter<SpinnerObject> adapter = new ArrayAdapter<>(this, R.layout.list_item, labels);
        adapter.setDropDownViewResource(R.layout.list_item);
        autoSearchCaste.setAdapter(adapter);
        autoSearchCaste.setOnItemClickListener((parent, view, position, id) -> {
            strSearchCaste = parent.getItemAtPosition(position).toString();
            Log.d("Selected_Item", ""+strSearchCaste);
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request.this, "samyoj","samyoj","samyoj","samyoj","samyoj");
            sqlLiteOpenHelper_class.open_Minority_Caste_Tbl();
            getCasteCode = sqlLiteOpenHelper_class.GetMinorityCasteCode(strSearchCaste, num);
            Log.d("Selected_casteCode", ""+ getCasteCode);
        });
    }


    public void GetImageName() {
        ArrayAdapter<SpinnerObject> adapter = new ArrayAdapter<>(this, R.layout.list_item, labelsDocs);
        adapter.setDropDownViewResource(R.layout.list_item);
        autoImageName.setAdapter(adapter);
        autoImageName.setOnItemClickListener((parent, view, position, id) -> {
            strImageName = parent.getItemAtPosition(position).toString();
            //            int imgPos = parent.getItemAtPosition(id);
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this,str);
            sqlLiteOpenHelper_class.open_Docs_Type_Tbl();
//            String getImageName = sqlLiteOpenHelper_class.GetDocs_BY_Code();
            getImageCode = sqlLiteOpenHelper_class.GetDocsCode(strImageName);
            labelsDocs.remove(position);
            Log.d("Selected_casteCode", "" + getImageCode);
        });
    }

    public void saveImageDialog() {
        GetImageName();
        builder = new AlertDialog.Builder(this);
        builder.setView(view).setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void saveDocImages_DB(String pdfInString) {
        DataBaseHelperClass_btnUpload_Docs openHelper = new DataBaseHelperClass_btnUpload_Docs(New_Request.this);

        Boolean Results = openHelper.insertData(applicant_Id, Name, pdfInString,getImageCode);

        if (Results == true) {
            Toast.makeText(this, "Image Captured successfully.", Toast.LENGTH_LONG).show();
            Log.d("save db", "Data Successfully saved");
        } else {
            Toast.makeText(this, "Couldn't capture image", Toast.LENGTH_LONG).show();
            Log.d("Not Save in DB", "Not Saved in DataBase");
        }
        alertDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(New_Request.this, New_Request_FirstScreen.class);
        i.putExtra("applicant_Id", applicant_Id);
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
        i.putExtra("villageCode", villageCode);
        i.putExtra("option_Flag", option_Flag);
        i.putExtra("town_Name", town_Name);
        i.putExtra("town_code", town_code);
        i.putExtra("ward_Name", ward_Name);
        i.putExtra("ward_code", ward_code);
        startActivity(i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == CAMERA_REQUEST) {
            Bundle extras = data.getExtras();

            if (extras != null) {
                Bitmap yourImage = extras.getParcelable("data");
                /*To recognize text in images*/
                runTextRecognition(yourImage);
            }
        }
    }
    public void runTextRecognition(Bitmap photo) {
        InputImage image = InputImage.fromBitmap(photo, 0);
        TextRecognizer recognizer = com.google.mlkit.vision.text.TextRecognition.getClient();
        recognizer.process(image)
                .addOnSuccessListener(
                        new OnSuccessListener<Text>() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onSuccess(Text texts) {
                                processTextRecognitionResult(texts,photo);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                runOnUiThread(()->
                                {
                                    Toast.makeText(New_Request.this,"Please do not capture the blank image",Toast.LENGTH_LONG).show();
                                });
                            }

                        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void processTextRecognitionResult(Text texts, Bitmap photo) {
        List<Text.TextBlock> blocks = texts.getTextBlocks();
        if(blocks.size()==0)
        {
            Toast.makeText(New_Request.this,"Please do not capture the blank image",Toast.LENGTH_LONG).show();
        }else
        {
            convertBitmapToPDF(photo);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void convertBitmapToPDF(Bitmap yourImage)
    {
        File myPDFFile=null;

        Log.e("entered", "success case");
//                store = getString;
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();//The ByteArrayOutputStream class stream creates a buffer in memory and all the data sent to the stream is stored in the buffer.
        assert yourImage != null;
        yourImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);//Specifies the known formats a bitmap can be compressed into 1.JPEG 2.PNG 3.WEBP
        imageInByte = stream.toByteArray();
        getString = Base64.getEncoder().encodeToString(imageInByte);

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(200, 300, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(myPageInfo);
        page.getCanvas().drawBitmap(yourImage, 0, 0, null);
        pdfDocument.finishPage(page);

        try {
            documentcount ++;
            String folder_main = "Samyojane_PDFs";
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            String fileName = "TestPDF_" + timeStamp;
            File mydir = getDir(folder_main, Context.MODE_PRIVATE); //Creating an internal dir;
            myPDFFile = File.createTempFile(fileName, ".pdf", mydir); //Getting a file within the dir.
            pdfDocument.writeTo(new FileOutputStream(myPDFFile));


            byte[] bytes = Files.readAllBytes(myPDFFile.toPath());

           /* int size = (int) myPDFFile.length();
            byte[] bytes = new byte[size];
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(myPDFFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();*/
            Log.d("base64String1", "" + bytes);

            pdfInString = Base64.getEncoder().encodeToString(bytes);

                    /*   encodeToString(bytes, Base64.NO_WRAP);*/
            Log.d("base64String", "" + pdfInString);
        } catch (Exception e) {
            pdfInString = null;
            e.printStackTrace();
        }
        pdfDocument.close();
        myPDFFile.delete();
        btnUploadDocs.setError(null);
        Log.d("Image in bytes", "Image Captured");
        saveDocImages_DB(pdfInString);
    }
}

