package com.nadakacheri.samyojane_app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class New_Request_SSY_Parameters_Kan extends AppCompatActivity {

    TextView applicant_infor;
    TextView tvHobli, tvTaluk, tvVA_Name, tvServiceName;
    String district, taluk, hobli, VA_Name,VA_Circle_Name, applicant_Id, rationCardNo, aadharNo, mobileNo, address1, item_position;
    int district_Code, taluk_Code, hobli_Code, va_Circle_Code, town_code, ward_code;
    String strSearchServiceName, strSearchVillageName;
    String villageCode, serviceCode;
    TableRow trDept, trDeptScheme, trDeptDate, trDeptAmount;
    Spinner spinnerDept;
    int getDeptCode;
    ImageView imageView;
    String strDept,strScheme,strDeptAmount,strDate,strDeptName;
    EditText scheme,deptAmount,etDate,etRemarks;
    RadioGroup radioGroup,radioGroup1,radioGroup2,radioGroup3;
    RadioButton radioButton1,radioButton11;
    RadioButton radioButton2,radioButton21;
    RadioButton radioButton3,radioButton31;
    RadioButton radioButton4,radioButton41;
    String option, option1, option2, option3;
    EditText tvIncome;
    SqlLiteOpenHelper_Class sqlLiteOpenHelper_class;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;
    Button btnSave, btnCamera, btnBack;
    private static final int CAMERA_REQUEST = 1;
    byte[] imageInByte;
    static String store=null;
    static String getString=null;
    String strRemarks;
    int income_len, income_Value;

    GPSTracker gpsTracker;
    double latitude, longitude;
    AppCompatActivity activity;
    String strIncome;
    ProgressDialog dialog;
    String str, returnStr;
    String service_name, village_name, town_Name, ward_Name, option_Flag;
    String eng_certi;
    String appImage=null;
    boolean return_Value;
    InputMethodManager imm;
    InputMethodSubtype ims;

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
            if (blockCharacterSet.contains("" + source.charAt(i))) {
                Log.d("Blocked",""+source);
                return "";
            }
        }
        Log.d("Filter_valid","Not blocked");
        return null;
    };

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint({"ClickableViewAccessibility", "HardwareIds"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_request_ssy_parameters);

        option ="N";
        option1 = "Y";
        option2 = "Y";
        option3 = "Y";

        tvTaluk = findViewById(R.id.taluk);
        tvHobli = findViewById(R.id.hobli);
        tvVA_Name = findViewById(R.id.VA_name);

        btnCamera = findViewById(R.id.btnCamera);
        btnSave = findViewById(R.id.btnSave);
        imageView = findViewById(R.id.store_image);
        btnBack = findViewById(R.id.btnBack);

        spinnerDept = findViewById(R.id.spDept);
        etDate = findViewById(R.id.etDate);
        scheme = findViewById(R.id.etScheme);
        deptAmount = findViewById(R.id.etAmount);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioButton1 = findViewById(R.id.rdBtn_yes_pen);
        radioButton11 = findViewById(R.id.rdBtn_no_pen);
        radioButton2 = findViewById(R.id.rdBtn_yes_res);
        radioButton21 = findViewById(R.id.rdBtn_no_res);
        radioButton3 = findViewById(R.id.rdBtn_yes_resc);
        radioButton31 = findViewById(R.id.rdBtn_no_resc);
        radioButton4 = findViewById(R.id.rdBtn_yes_scheme);
        radioButton41 = findViewById(R.id.rdBtn_no_scheme);
        trDept = findViewById(R.id.trDept);
        trDeptScheme = findViewById(R.id.trDeptScheme);
        trDeptDate = findViewById(R.id.trDeptDate);
        trDeptAmount = findViewById(R.id.trDeptAmount);
        etRemarks = findViewById(R.id.etRemarks);

        tvServiceName = findViewById(R.id.tvServiceName);
        applicant_infor= findViewById(R.id.applicant_Information);
        applicant_infor.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

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

        PhoneNumberUtils.formatNumber(etRemarks.getText().toString().trim());
        etRemarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});

        if (eng_certi.equals("K")){
            etRemarks.setFilters(new InputFilter[] { filter_Kan });
        }else if (eng_certi.equals("E")){
            etRemarks.setFilters(new InputFilter[] { filter_Eng });
        }
        etRemarks.setOnTouchListener((v, event) -> {
            if(Objects.equals(eng_certi, "K")){
                check_Kannada_Key_lang();
            }
            else if(Objects.equals(eng_certi, "E")) {
                check_English_Key_lang();
            }
            return false;
        });

        GetDept();

        etDate.setFocusable(false);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDate.setError(null);
                showDateDialog(etDate);
            }
        });

        tvTaluk.setText(taluk);
        tvHobli.setText(hobli);
        tvVA_Name.setText(VA_Name);
        tvServiceName.setText(strSearchServiceName);

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



        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.rdBtn_yes_pen) {
                option ="Y";
                trDept.setVisibility(View.VISIBLE);
                trDeptScheme.setVisibility(View.VISIBLE);
                trDeptDate.setVisibility(View.VISIBLE);
                trDeptAmount.setVisibility(View.VISIBLE);
                //Toast.makeText(getApplicationContext(), "choice: ", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.rdBtn_no_pen) {
                option = "N";
                trDept.setVisibility(View.GONE);
                trDeptScheme.setVisibility(View.GONE);
                trDeptDate.setVisibility(View.GONE);
                trDeptAmount.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), "choice: ", Toast.LENGTH_SHORT).show();
            }
            Log.d("option", option);
        });

        radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.rdBtn_yes_res) {
                //Toast.makeText(getApplicationContext(), "choice: male", Toast.LENGTH_SHORT).show();
                option1 ="Y";
            } else if (checkedId == R.id.rdBtn_no_res) {
                option1 ="N";
                //Toast.makeText(getApplicationContext(), "choice: Female", Toast.LENGTH_SHORT).show();

            }
            Log.d("option1", ""+option1);
        });

        radioGroup2.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.rdBtn_yes_resc) {
                //Toast.makeText(getApplicationContext(), "choice: male", Toast.LENGTH_SHORT).show();
                option2 = "Y";
            } else if (checkedId == R.id.rdBtn_no_resc) {
                option2 = "N";
                //Toast.makeText(getApplicationContext(), "choice: Female", Toast.LENGTH_SHORT).show();

            }
            Log.d("option2", ""+option2);
        });

        radioGroup3.setOnCheckedChangeListener((group, checkedId) -> {
            // find which radio button is selected
            if (checkedId == R.id.rdBtn_yes_scheme) {
                //Toast.makeText(getApplicationContext(), "choice: male", Toast.LENGTH_SHORT).show();
                option3 ="Y";
            } else if (checkedId == R.id.rdBtn_no_scheme) {
                option3 = "N";
                //Toast.makeText(getApplicationContext(), "choice: Female", Toast.LENGTH_SHORT).show();

            }
            Log.d("option3", ""+option3);
        });

        gpsTracker = new GPSTracker(getApplicationContext(), this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            Log.d("Location", ""+latitude+""+longitude);
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

                strScheme = scheme.getText().toString().trim();
                strDate = etDate.getText().toString().trim();
                strDeptAmount = deptAmount.getText().toString().trim();
                strRemarks = etRemarks.getText().toString().trim();
                Log.d("Scheme", "" + strScheme);
                Log.d("Date value", "" + strDate);
                Log.d("Amount value", "" + strDeptAmount);
                Log.d("Remarks", "" + strRemarks);


                if (latitude != 0.0 && longitude != 0.0) {
                    if(option.equals("Y")){
                        if(!strDeptName.equals(getString(R.string.select_Dept_spinner))){
                            if(!TextUtils.isEmpty(strScheme)){
                                if(!TextUtils.isEmpty(strDate)){
                                    if(TextUtils.isEmpty(strDeptAmount)){
                                        deptAmount.setError(getString(R.string.field_canno_null));
                                    }else{
                                        income_len = strDeptAmount.length();
                                        income_Value = Integer.parseInt(strDeptAmount);
                                        Log.d("Income value", "" + strDeptAmount + ", Length: " + income_len + ", Value: " + income_Value);

                                        if (income_len >= 4 && income_Value >= 1000) {
                                            if (TextUtils.isEmpty(strRemarks)) {
                                                etRemarks.setError(getString(R.string.field_canno_null));
                                            } else {
                                                StoreData_in_DB();
                                            }
                                        } else {
                                            deptAmount.setError(getString(R.string.incorrect_value));
                                        }
                                    }
                                }else{
                                    etDate.setError(getString(R.string.incorrect_value));
                                }
                            }else{
                                scheme.setError(getString(R.string.incorrect_value));
                            }
                        }else{
                            ((TextView)spinnerDept.getSelectedView()).setError(getString(R.string.select_department));
                            Toast.makeText(getApplicationContext(), getString(R.string.select_category), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        if (TextUtils.isEmpty(strRemarks)) {
                            etRemarks.setError(getString(R.string.field_canno_null));
                        }else{
                            StoreData_in_DB();
                        }
                    }
                }else {
                    runOnUiThread(() -> {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(New_Request_SSY_Parameters_Kan.this);
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
                Toast.makeText(getApplicationContext(), "Exception : "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(v -> onBackPressed());
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

        new DatePickerDialog(New_Request_SSY_Parameters_Kan.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
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

    public void truncateDatabase_Docs(){
        //dialog1.setProgress(20);
        openHelper = new DataBaseHelperClass_btnDownload_Docs(New_Request_SSY_Parameters_Kan.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from "+ DataBaseHelperClass_btnDownload_Docs.TABLE_NAME, null);
        if(cursor.getCount()>0) {
            database.execSQL("Delete from " + DataBaseHelperClass_btnDownload_Docs.TABLE_NAME);
            Log.d("Database", "Down_Docs Database Truncated");
        } else {
            cursor.close();
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


    public String StoreData_in_DB(){
        String str;
        dialog.show();
        btnCamera.setError(null);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        String currDate = dateFormat.format(date);


        openHelper = new DataBaseHelperClass_btnDownload_ServiceTranTable(New_Request_SSY_Parameters_Kan.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + " where "
                + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + "='" + applicant_Id + "'", null);
        if (cursor.getCount() > 0) {
            try {
                database.execSQL("update " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME + " set "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.DataUpdateFlag + "=1 where "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + applicant_Id + "'");

                database.execSQL("update " + DataBaseHelperClass_btnDownload_ServiceTranTable.TABLE_NAME_1 + " set "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Getting_Other_Pension + "='" + option + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Which_Dept + "='" + strDeptName + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Which_Scheme + "='" + strScheme + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_From_Which_Date + "='" + strDate + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Dept_Amount + "='" + strDeptAmount + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_App_Reside_In_Karnataka + "='" + option1 + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Attested_Address + "='" + option2 + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Photo + "='" + store + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_vLat + "=" + latitude + ","
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_vLong + "=" + longitude + ","
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Can_Certificate_Given + "='" + option3 + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_Remarks + "='" + strRemarks + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_ReportDate + "='" + currDate + "',"
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_DataUpdateFlag + "=1" + " where "
                        + DataBaseHelperClass_btnDownload_ServiceTranTable.UPD_GSCNo + "='" + applicant_Id + "'");

                Log.d("Database", "ServiceParameters Database Updated");
                Toast.makeText(getApplicationContext(), getString(R.string.updated_successfully), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(New_Request_SSY_Parameters_Kan.this, New_Request_FirstScreen.class);
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

    private  void buildAlertMessageGoingBack() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.discard_changes))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), (dialog, id) -> {
                    New_Request_SSY_Parameters_Kan.super.onBackPressed();
                    Intent i = new Intent(New_Request_SSY_Parameters_Kan.this, New_Request_FirstScreen.class);
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

    @SuppressLint({"LongLogTag", "SetTextI18n"})
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
                store = getString;
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(yourImage);
                btnCamera.setError(null);
                Log.d("Image in bytes", "Image Captured");
            }
        }
    }

    @Override
    public void onBackPressed() {
        buildAlertMessageGoingBack();
    }

}
