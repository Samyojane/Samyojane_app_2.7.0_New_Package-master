package com.nadakacheri.samyojane_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.nadakacheri.samyojane_app.api.APIClient;
import com.nadakacheri.samyojane_app.api.APIInterface_NIC;
import com.nadakacheri.samyojane_app.model.GenLogin.LoginRequest;
import com.nadakacheri.samyojane_app.model.GenLogin.LoginResponse;
import com.nadakacheri.samyojane_app.model.GenResult.genderRequest;
import com.nadakacheri.samyojane_app.model.GenResult.genderResponse;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nischitha on 28,December,2021
 **/
public class New_Request_CasteCertificate extends AppCompatActivity {
    TextView applicant_infor;
    Spinner spCategory;
    String strCategory, strSearchCaste;
    TextView tvHobli, tvTaluk, tvVA_Name, tvServiceName;
    String district, taluk, hobli, VA_Name,VA_Circle_Name, applicant_Id, rationCardNo, aadharNo, mobileNo, address1;
    int district_Code, taluk_Code, hobli_Code, va_Circle_Code, town_code, ward_code;
    String eng_certi;
    Button btnCamera, btnSave, btnBack;
    private static final int CAMERA_REQUEST = 1;
    byte[] imageInByte;
    static String store=null;
    static String getString=null;
    ImageView imageView;
    EditText tvIncome, tvRemarks;
    int income_len, income_Value;
    String strIncome, strRemarks;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;
    GPSTracker gpsTracker;
    double latitude, longitude;
    String item_position;
    String strSearchVillageName, strSearchServiceName, town_Name, ward_Name, option_Flag;
    String villageCode, serviceCode;
    TableRow trCaste, trCategory;
    RadioGroup radioGroup1;
    RadioButton radioButton11, radioButton22;
    String option1;
    SqlLiteOpenHelper_Class sqlLiteOpenHelper_class;
    AutoCompleteTextView autoSearchCaste;
    int getCatCode=0, getCasteCode=0;
    ProgressDialog dialog;
    String service_name, village_name;
    String amount,gender;
    boolean return_Value;
    InputMethodManager imm;
    InputMethodSubtype ims;
    private APIInterface_NIC apiInterface_nic;



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
            if (blockCharacterSet.contains("" + source.charAt(i))) {
                Log.d("Blocked",""+source);
                return "";
            }
        }
        Log.d("Filter_valid","Not blocked");
        return null;
    };
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_request_caste_certificate);

        strSearchCaste = getString(R.string.select_caste_spinner);
        option1 = "Y";

        tvTaluk = findViewById(R.id.taluk);
        tvHobli = findViewById(R.id.hobli);
        tvVA_Name = findViewById(R.id.VA_name);

        autoSearchCaste = findViewById(R.id.autoSearchCaste);
        spCategory = findViewById(R.id.spCategory);
        btnCamera = findViewById(R.id.btnCamera);
        btnSave = findViewById(R.id.btnSave);
        tvIncome = findViewById(R.id.tvIncome);
        tvRemarks = findViewById(R.id.tvRemarks);
        imageView = findViewById(R.id.store_image);
        btnBack = findViewById(R.id.btnBack);
        tvServiceName = findViewById(R.id.tvServiceName);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioButton11 = findViewById(R.id.radioButton11);
        radioButton22 = findViewById(R.id.radioButton22);
        trCaste = findViewById(R.id.trCaste);
        trCategory = findViewById(R.id.tr_category);


        applicant_infor= findViewById(R.id.applicant_Information);
        applicant_infor.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this);
        sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();

        imageView.setVisibility(View.GONE);

        Intent i = getIntent();
        district = i.getStringExtra("district");
        taluk = i.getStringExtra("taluk");
        district_Code = i.getIntExtra("district_Code", 0);
        taluk_Code = i.getIntExtra("taluk_Code", 0);
        hobli_Code = i.getIntExtra("hobli_Code", 0);
        hobli = i.getStringExtra("hobli");
        va_Circle_Code = i.getIntExtra("va_Circle_Code", 0);
        VA_Circle_Name = i.getStringExtra("VA_Circle_Name");
        applicant_Id = i.getStringExtra("applicant_Id");
        VA_Name = i.getStringExtra("VA_Name");
        rationCardNo = i.getStringExtra("rationCardNo");
        aadharNo = i.getStringExtra("aadharNo");
        mobileNo = i.getStringExtra("mobileNo");
        address1 = i.getStringExtra("address1");
        item_position = i.getStringExtra("item_position");
        strSearchVillageName = i.getStringExtra("strSearchVillageName");
        strSearchServiceName =i.getStringExtra("strSearchServiceName");
        villageCode = i.getStringExtra("villageCode");
        serviceCode = i.getStringExtra("serviceCode");
        service_name = i.getStringExtra("strSearchServiceName");
        village_name = i.getStringExtra("strSearchVillageName");
        eng_certi = i.getStringExtra("eng_certi");
        town_code = i.getIntExtra("town_code", 0);
        town_Name = i.getStringExtra("town_Name");
        ward_code = i.getIntExtra("ward_code", 0);
        ward_Name = i.getStringExtra("ward_Name");
        option_Flag = i.getStringExtra("option_Flag");

        Log.d("New_Request_Sec_Con..", ""+district_Code);
        Log.d("New_Request_Sec_Con..", ""+taluk_Code);
        Log.d("New_Request_Sec_Con..", ""+hobli_Code);
        Log.d("New_Request_Sec_Con..", ""+va_Circle_Code);
        Log.d("Item_Position", ""+item_position);
        Log.d("Village_NameCasteIncome", ""+village_name);
        Log.d("Service_NameCasteIncome", ""+service_name);
        Log.d("villageCodeCasteIncome", ""+villageCode);
        Log.d("serviceCodeCasteIncome", ""+serviceCode);
        Log.d("eng_certi",""+ eng_certi);
        Log.d( "town_code: ",""+town_code);
        Log.d("town_Name: ",""+town_Name);
        Log.d( "ward_code: ",""+ward_code);
        Log.d( "ward_Name: ",""+ward_Name);
        Log.d("option_Flag: ",""+ option_Flag);

        dialog = new ProgressDialog(this, R.style.CustomDialog);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading));
        dialog.setIndeterminate(true);
        dialog.setProgress(1);

        tvRemarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});

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

        autoSearchCaste.setVisibility(View.GONE);
        autoSearchCaste.setOnTouchListener((v, event) -> {
            autoSearchCaste.showDropDown();
            autoSearchCaste.setError(null);
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        spCategory.setOnTouchListener((v, event) -> {
            autoSearchCaste.setText("");
            autoSearchCaste.setError(null);
            autoSearchCaste.showDropDown();
            return false;
        });

        tvTaluk.setText(taluk);
        tvHobli.setText(hobli);
        tvVA_Name.setText(VA_Name);
        tvServiceName.setText(strSearchServiceName);

        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request_CasteCertificate.this);
        sqlLiteOpenHelper_class.open_Reasons_Master_Tbl();

        radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.radioButton11) {
                option1 = "Y";
            } else if (checkedId == R.id.radioButton22) {
                option1 = "N";
            }
        });

        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request_CasteCertificate.this);
        database = openHelper.getWritableDatabase();
        Log.d("serviceCode", "" + serviceCode);
        if ("9999".equals(serviceCode)) {
            GetCategory();
            spCategory.setOnItemSelectedListener(new New_Request_CasteCertificate.GetCategorySelected());
            trCategory.setVisibility(View.VISIBLE);
            trCaste.setVisibility(View.GONE);
            Cursor cursor = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                    + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    getCatCode = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                    getCasteCode = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                    amount = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                    Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                }
            } else {
                cursor.close();
            }
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
            sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
            strCategory = sqlLiteOpenHelper_class.GetCategory_BY_Code(getCatCode);
            strSearchCaste = sqlLiteOpenHelper_class.GetCaste_BY_Code(getCatCode, getCasteCode);

            Log.d("get_Value", "" + strCategory + "" + strSearchCaste);

            tvIncome.setText(amount);

            if (getCatCode!=0) {
                trCaste.setVisibility(View.VISIBLE);
                GetCaste(getCatCode);
            }

            spCategory.setSelection(getCatCode);
            autoSearchCaste.setText(strSearchCaste);
        }
        else if("41".equals(serviceCode))
        {
            trCategory.setVisibility(View.GONE);
            trCaste.setVisibility(View.VISIBLE);

            Cursor cursor = database.rawQuery("SELECT * FROM " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME
                    + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.Service_Code + "='" + serviceCode + "'" + " and "
                    + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'", null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    getCatCode = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.ReservationCategory));
                    getCasteCode = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.Caste));
                    amount = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_ServiceTranTable.AnnualIncome));
                    Log.d("value1", "" + getCatCode + " " + getCasteCode + " " + amount);
                }
            } else {
                cursor.close();
            }
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request_CasteCertificate.this, "samyoj", "samyoj","samyoj","samyoj", "samyoj");
            sqlLiteOpenHelper_class.open_Minority_Caste_Tbl();
            strSearchCaste = sqlLiteOpenHelper_class.GetMinorityCaste_BY_Code(getCasteCode);


            Log.d("get_Value", "" + strCategory + "" + strSearchCaste);

            tvIncome.setText(amount);

           //  if (getCatCode!=0) {
            GetMinorityCaste(getCasteCode);
            //}
            autoSearchCaste.setVisibility(View.VISIBLE);
            autoSearchCaste.setText(strSearchCaste);
        }

        gpsTracker = new GPSTracker(getApplicationContext(), this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            Log.d("Location", latitude+""+longitude);
        } else {
            //gpsTracker.showSettingsAlert();
            Toast.makeText(getApplicationContext(), getString(R.string.switch_on_gps), Toast.LENGTH_SHORT).show();
        }

        btnCamera.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });

        btnSave.setOnClickListener(v -> {
            try {
                if (gpsTracker.canGetLocation()) {
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();
                    Log.d("Location", "" + latitude + "" + longitude);
                } else {
                    //gpsTracker.showSettingsAlert();
                    Toast.makeText(getApplicationContext(), getString(R.string.switch_on_gps), Toast.LENGTH_SHORT).show();
                }

                strIncome = tvIncome.getText().toString().trim();
                strRemarks = tvRemarks.getText().toString().trim();
                Log.d("Income value", "" + strRemarks);

                if ("9999".equals(serviceCode) ) {
                    if (!strCategory.equals(getString(R.string.select_category_spinner))) {
                        strSearchCaste = autoSearchCaste.getText().toString();
                        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
                        sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
                        getCasteCode = sqlLiteOpenHelper_class.GetCasteCode(strSearchCaste, getCatCode);
                    }
                    Log.d("casteCategoryCode", "" + getCatCode + ", " + getCasteCode);
                    Log.d("casteCategoryName", "" + strCategory + ", " + strSearchCaste);
                    saveService_9_and_6();
                }else if("41".equals(serviceCode))
                {
                    Log.d("casteCategoryCode", ", " + getCasteCode);

                    saveService_9_and_6();

                }
            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    public void saveService_9_and_6(){
        if(latitude!=0.0 && longitude!=0.0) {
            if (serviceCode.equals("9999")) {
                if(!strCategory.equals(getString(R.string.select_category_spinner))){
                    if(!strSearchCaste.equals(getString(R.string.select_caste_spinner))) {
                        if (getCasteCode != 0) {
                            // if(store!=null) {
                            if (TextUtils.isEmpty(strIncome)) {
                                tvIncome.setError(getString(R.string.field_canno_null));
                            } else {
                                income_len = strIncome.length();
                                income_Value = Integer.parseInt(strIncome);
                                Log.d("Income value", "" + strIncome + ", Length: " + income_len + ", Value: " + income_Value);

                                if (income_len >= 4 && income_Value >= 1000) {
                                    if (TextUtils.isEmpty(strRemarks)) {
                                        tvRemarks.setError(getString(R.string.field_canno_null));
                                    } else {
                                        StoreData_in_DB();
                                    }
                                } else {
                                    tvIncome.setError(getString(R.string.incorrect_value));
                                }
                            }
                        /*}else
                        {
                            Toast.makeText(New_Request_CasteCertificate_Kan.this,
                                    "Please capture beneficaiary photo",
                                    Toast.LENGTH_LONG).show();
                        }*/
                        } else {
                            autoSearchCaste.setError(getString(R.string.invalid_caste));
                        }
                    } else {
                        autoSearchCaste.setError(getString(R.string.select_caste));
                    }
                }
            }
            else if(serviceCode.equals("41")) {
                if(!strSearchCaste.equals(getString(R.string.select_caste_spinner))) {
                    if (getCasteCode != 0) {
                        if (TextUtils.isEmpty(strIncome)) {
                            tvIncome.setError(getString(R.string.field_canno_null));
                        } else {
                            income_len = strIncome.length();
                            income_Value = Integer.parseInt(strIncome);
                            Log.d("Income value", "" + strIncome + ", Length: " + income_len + ", Value: " + income_Value);

                            if (income_len >= 4 && income_Value >= 1000) {
                                if (TextUtils.isEmpty(strRemarks)) {
                                    tvRemarks.setError(getString(R.string.field_canno_null));
                                } else {
                                    StoreData_in_DB();
                                }
                            } else {
                                tvIncome.setError(getString(R.string.incorrect_value));
                            }

                        }
                    }else {
                        autoSearchCaste.setError(getString(R.string.invalid_caste));
                    }
                } else {
                    autoSearchCaste.setError(getString(R.string.select_caste));
                }
            }
            else {
                ((TextView)spCategory.getSelectedView()).setError(getString(R.string.select_category));
                Toast.makeText(getApplicationContext(), getString(R.string.select_category), Toast.LENGTH_SHORT).show();
            }
        } else {
            runOnUiThread(() -> {

                AlertDialog.Builder dialog = new AlertDialog.Builder(New_Request_CasteCertificate.this);
                dialog.setCancelable(false);
                dialog.setTitle("Alert");
                dialog.setMessage(getString(R.string.cannot_get_location) );
                dialog.setNegativeButton(getString(R.string.ok), (dialog1, which) -> {
                    //Action for "Cancel".
                    dialog1.cancel();
                });

                final AlertDialog alert = dialog.create();
                alert.show();
            });
            //Toast.makeText(getApplicationContext(), "Please Switch on the GPS", Toast.LENGTH_SHORT).show();
        }
    }
    public void truncateDatabase_Docs(){
        //dialog1.setProgress(20);
        openHelper = new DataBaseHelperClass_btnDownload_Docs(New_Request_CasteCertificate.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from "+ DataBaseHelperClass_btnDownload_Docs.TABLE_NAME, null);
        if(cursor.getCount()>0) {
            database.execSQL("Delete from " + DataBaseHelperClass_btnDownload_Docs.TABLE_NAME);
            Log.d("Database", "Down_Docs Database Truncated");
        } else {
            cursor.close();
        }

    }

    public String StoreData_in_DB(){
        String str;
        dialog.show();

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        String currDate = dateFormat.format(date);

        btnCamera.setError(null);
        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request_CasteCertificate.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + " where "
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + "='" + applicant_Id + "'", null);
        if (cursor.getCount() > 0) {
            try {
                database.execSQL("update " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME + " set "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.DataUpdateFlag + "=1 where "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'");

                database.execSQL("update " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + " set "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Applicant_Category + "=" + getCatCode + ","
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Applicant_Caste + "=" + getCasteCode + ","
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Income + "='" + strIncome + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Photo + "='" + store + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_vLat + "=" + latitude + ","
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_vLong + "=" + longitude + ","
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Can_Certificate_Given + "='" + option1 + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Remarks + "='" + strRemarks + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_ReportDate + "='" + currDate + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DataUpdateFlag + "=1" + " where "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + "='" + applicant_Id + "'");

                Log.d("Database", "ServiceParameters Database Updated");
                Toast.makeText(getApplicationContext(), getString(R.string.updated_successfully), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(New_Request_CasteCertificate.this, New_Request_FirstScreen.class);
                i.putExtra("applicant_Id", applicant_Id);
                i.putExtra("district_Code", district_Code);
                i.putExtra("taluk_Code", taluk_Code);
                i.putExtra("hobli_Code", hobli_Code);
                i.putExtra("district", district);
                i.putExtra("taluk", taluk);
                i.putExtra("VA_Name", VA_Name);
                i.putExtra("hobli", hobli);
                i.putExtra("va_Circle_Code", va_Circle_Code);
                i.putExtra("VA_Circle_Name", VA_Circle_Name);
                i.putExtra("strSearchServiceName", service_name);
                i.putExtra("strSearchVillageName", village_name);
                i.putExtra("serviceCode", serviceCode);
                i.putExtra("villageCode", String.valueOf(villageCode));
                i.putExtra("option_Flag", option_Flag);
                i.putExtra("town_Name", town_Name);
                i.putExtra("town_code", town_code);
                i.putExtra("ward_Name", ward_Name);
                i.putExtra("ward_code", ward_code);
                startActivity(i);
                finish();
                dialog.dismiss();
                truncateDatabase_Docs();
                str = "Success";
            } catch (Exception e){
                e.printStackTrace();
                str="Failure";
            }
        } else {
            cursor.close();
            dialog.dismiss();
            truncateDatabase_Docs();
            str="Failure";
            Toast.makeText(getApplicationContext(), getString(R.string.could_not_save_the_data_please_try_again), Toast.LENGTH_SHORT).show();
        }
        return str;
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
            if (s1.contains("Samyojane")) {
                get = true;
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
                    super.onBackPressed();
                    Intent i = new Intent(New_Request_CasteCertificate.this, New_Request_FirstScreen.class);
                    i.putExtra("applicant_Id", applicant_Id);
                    i.putExtra("district_Code", district_Code);
                    i.putExtra("taluk_Code", taluk_Code);
                    i.putExtra("hobli_Code", hobli_Code);
                    i.putExtra("district", district);
                    i.putExtra("taluk", taluk);
                    i.putExtra("VA_Name", VA_Name);
                    i.putExtra("hobli", hobli);
                    i.putExtra("va_Circle_Code", va_Circle_Code);
                    i.putExtra("VA_Circle_Name", VA_Circle_Name);
                    i.putExtra("strSearchServiceName", service_name);
                    i.putExtra("strSearchVillageName", village_name);
                    i.putExtra("serviceCode", serviceCode);
                    i.putExtra("villageCode", String.valueOf(villageCode));
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
                trCaste.setVisibility(View.VISIBLE);
                GetCaste(getCatCode);
            }
            else {
                trCaste.setVisibility(View.GONE);
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
            Log.d("Selected_Item", ""+strSearchCaste);
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class();
            sqlLiteOpenHelper_class.open_Cat_Caste_Tbl();
            getCasteCode = sqlLiteOpenHelper_class.GetCasteCode(strSearchCaste, num);
            Log.d("Selected_casteCode", ""+ getCasteCode);
        });
    }
    public void GetMinorityCaste(int num){
        List<SpinnerObject> labels;
        int itemid=0;
        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request_CasteCertificate.this, "samyoj","samyoj","samyoj","samyoj","samyoj");
        sqlLiteOpenHelper_class.open_Minority_Caste_Tbl();
        labels = sqlLiteOpenHelper_class.GetMinorityCasteByCode(num);

        ArrayAdapter<SpinnerObject> adapter = new ArrayAdapter<>(this, R.layout.list_item, labels);
        adapter.setDropDownViewResource(R.layout.list_item);
        autoSearchCaste.setAdapter(adapter);
        autoSearchCaste.setOnItemClickListener((parent, view, position, id) -> {
            strSearchCaste = parent.getItemAtPosition(position).toString();
            Log.d("Selected_Item", ""+strSearchCaste);
            Log.d("Selected_Position", ""+itemid);
            sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(New_Request_CasteCertificate.this, "samyoj","samyoj","samyoj","samyoj","samyoj");
            sqlLiteOpenHelper_class.open_Minority_Caste_Tbl();
            getCasteCode = sqlLiteOpenHelper_class.GetMinorityCasteCode(strSearchCaste, num);
            Log.d("Selected_casteCode", ""+ getCasteCode);
        });
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
                // convert bitmap to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();//The ByteArrayOutputStream class stream creates a buffer in memory and all the data sent to the stream is stored in the buffer.
                assert yourImage != null;
                yourImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);//Specifies the known formats a bitmap can be compressed into 1.JPEG 2.PNG 3.WEBP
                imageInByte = stream.toByteArray();
                getString = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                //Log.e("output before conversion", Arrays.toString(imageInByte));
                GetAuthGenderAPI(getString);
                store = getString;
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(yourImage);
                btnCamera.setError(null);
                Log.d("Image in bytes", "Image Captured");
            }
        }
    }
    public void GetAuthGenderAPI(String photo)
    {
        apiInterface_nic = APIClient.getClient("http://genage_api.kar.nic.in/api/").create(APIInterface_NIC.class);

        Call<LoginResponse> call = apiInterface_nic.getAuthentication(prepareLoginBody());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    try{
                        GetGenderDetAPI("Bearer "+response.body().getAccess(), photo);
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        Log.e("OutOfMemoryError", "" + e.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), getString(R.string.server_exception), Toast.LENGTH_SHORT).show();
                        });
                    } catch (NullPointerException e) {
                        Log.e("NullPointerException", "" + e.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), getString(R.string.server_exception), Toast.LENGTH_SHORT).show();
                        });
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("multipleResource",""+t.getMessage());
                call.cancel();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LoginRequest prepareLoginBody()
    {
        LoginRequest logReq = new LoginRequest(getString(R.string.gender_username),getString(R.string.gender_password));
        return logReq;
    }
    public void GetGenderDetAPI(String access_token, String photo)
    {
        apiInterface_nic = APIClient.getClient("http://genage_api.kar.nic.in/api/").create(APIInterface_NIC.class);

        Call<genderResponse> call = apiInterface_nic.getGender(access_token,prepareBody(photo));
        call.enqueue(new Callback<genderResponse>() {
            @Override
            public void onResponse(Call<genderResponse> call, Response<genderResponse> response) {
                if (response.isSuccessful()) {
                    try{
                        Log.e("isSucc::", "" + response.body().getResults());
                        gender = response.body().getResults().get(0).getGender();
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "Gender:"+response.body().getResults().get(0).getGender(), Toast.LENGTH_SHORT).show();
                        });
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        Log.e("OutOfMemoryError", "" + e.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), getString(R.string.server_exception), Toast.LENGTH_SHORT).show();
                        });
                    } catch (NullPointerException e) {
                        Log.e("NullPointerException", "" + e.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), getString(R.string.server_exception), Toast.LENGTH_SHORT).show();
                        });
                    }
                } else {
                    Log.e("isSucc::", "::" + response.body().getResults());
                    Toast.makeText(getApplicationContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<genderResponse> call, Throwable t) {
                Log.d("multipleResource",""+t.getMessage());
                call.cancel();
                t.printStackTrace();
                if(t.getMessage().contains("java.lang.IllegalStateException")){
                    Toast.makeText(getApplicationContext(), "Person not found", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public genderRequest prepareBody(String bitmap)
    {
        genderRequest genReq = new  genderRequest(bitmap);
        return genReq;
    }

    @Override
    public void onBackPressed() {
        buildAlertMessageGoingBack();
    }
}
