package com.nadakacheri.samyojane_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.nadakacheri.samyojane_app.Utils.Utils;
import com.nadakacheri.samyojane_app.api.APIClient;
import com.nadakacheri.samyojane_app.api.APIInterface_NIC;
import com.nadakacheri.samyojane_app.model.document.DocumentData;
import com.nadakacheri.samyojane_app.model.document.DocumentRequest;
import com.nadakacheri.samyojane_app.model.document.DocumentResponse;

import org.checkerframework.checker.units.qual.A;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUploadedDocs extends AppCompatActivity {
    Button btnBack, btnUpload;
    TextView tvHobli, tvTaluk, tvVA_Name, tvServiceName, tvAppName, txt_ReportNo;
    String district, taluk, hobli, VA_Circle_Name, VA_Name;
    int district_Code, taluk_Code, hobli_Code, va_Circle_Code;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;
    String applicant_name;
    String applicant_Id, report_no;
    TextView emptyTxt;
    ListView listView;
    Upload_Doc_List_Adapter list_adapter;
    String villageCode, service_name, village_name;
    int serviceCode;
    String docs_Name;
    int docs_Id;
    ProgressDialog dialog;
    APIInterface_NIC apiInterface_nic;
    SharedPreferences sharedPreferences;



    SqlLiteOpenHelper_Class sqlLiteOpenHelper_class;

    /*ArrayList<Integer> SlNo = new ArrayList<>();
    ArrayList<String> App_ID = new ArrayList<>();
    ArrayList<Integer> Docs_ID = new ArrayList<>();
    ArrayList<String> Docs_Name = new ArrayList<>();*/
    ArrayList<DocumentData> docList = new ArrayList<>();


    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploaded_docs);


        tvTaluk = findViewById(R.id.taluk);
        tvHobli = findViewById(R.id.hobli);
        tvVA_Name = findViewById(R.id.VA_name);
        txt_ReportNo = findViewById(R.id.txt_ReportNo);

        btnBack = findViewById(R.id.btnBack);
        listView = findViewById(R.id.list);
        emptyTxt = findViewById(R.id.emptyTxt);
        tvServiceName = findViewById(R.id.tvServiceName);
        tvAppName = findViewById(R.id.tvAppName);
        btnUpload = findViewById(R.id.btnUpload);

        Intent i = getIntent();
        district_Code = i.getIntExtra("district_Code", 0);
        district = i.getStringExtra("district");
        taluk_Code = i.getIntExtra("taluk_Code", 0);
        taluk = i.getStringExtra("taluk");
        hobli_Code = i.getIntExtra("hobli_Code", 0);
        hobli = i.getStringExtra("hobli");
        va_Circle_Code = i.getIntExtra("va_Circle_Code", 0);
        VA_Circle_Name = i.getStringExtra("VA_Circle_Name");
        VA_Name = i.getStringExtra("VA_Name");
        service_name = i.getStringExtra("strSearchServiceName");
        villageCode = i.getStringExtra("villageCode");
        village_name = i.getStringExtra("strSearchVillageName");
        applicant_name = i.getStringExtra("applicant_name");
        applicant_Id = i.getStringExtra("applicant_Id");
        report_no = i.getStringExtra("report_no");

        dialog = new ProgressDialog(this, R.style.CustomDialog);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        sqlLiteOpenHelper_class = new SqlLiteOpenHelper_Class(this, "str");
        sqlLiteOpenHelper_class.open_Docs_Type_Tbl();

        openHelper = new DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster(ViewUploadedDocs.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster.TABLE_NAME + " where "
                + DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster.FM_facility_edesc + "='" + service_name + "'", null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                serviceCode = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnDownload_NewRequest_FacilityMaster.FM_facility_code));
            }
        } else {
            cursor.close();
        }

        tvTaluk.setText(taluk);
        tvHobli.setText(hobli);
        tvVA_Name.setText(VA_Name);
        tvServiceName.setText(service_name);
        tvAppName.setText(applicant_name);
        txt_ReportNo.setText(report_no);

        displayData();

        btnBack.setOnClickListener(v -> onBackPressed());

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(ViewUploadedDocs.this)) {
                    dialog.show();
                    //tvAfterUploaded.setVisibility(View.GONE);

                    UploadDocs();
                }
                else{
                    Utils.buildAlertMessageConnection(ViewUploadedDocs.this);
                }

            }
        });
    }
    public void UploadDocs(){

        String Applicant_Id;
        DocumentRequest documentRequest;
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        dialog.show();
        apiInterface_nic = APIClient.getClient(getString(R.string.MobAPI_New_NIC)).create(APIInterface_NIC.class);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = df.format(c);

        String uName_get = sharedPreferences.getString(Constants.uName_get, "");
        String username = uName_get.substring(0, 3);//First three characters of username
        Date c1 = Calendar.getInstance().getTime();
        SimpleDateFormat df1 = new SimpleDateFormat("dd", Locale.ENGLISH);
        String day_num = df1.format(c1);//Current Day
        SimpleDateFormat df2 = new SimpleDateFormat("yy", Locale.ENGLISH);
        String year_num = df2.format(c);//last two digits of the year
        String app_name = "Samyojane";

        String fieldVerify_api_flag2 = username + day_num + app_name + year_num;
        openHelper = new DataBaseHelperClass_btnUpload_Docs(ViewUploadedDocs.this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from "
                + DataBaseHelperClass_btnUpload_Docs.TABLE_NAME, null);
        try {
            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        Log.d("Loop_entering_here", "");

                        try {
                            Applicant_Id = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.GSCNO));
                            documentRequest = new DocumentRequest();
                            documentRequest.setUDGscNo(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.GSCNO)));
                            documentRequest.setFlag1(getString(R.string.fieldVerify_api_flag1));
                            documentRequest.setFlag2(fieldVerify_api_flag2);
                            documentRequest.setLoginId(uName_get);
                            documentRequest.setDocumentId(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.DocumentID)));
                            documentRequest.setFile(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.Document)));


                            Call<JsonObject> call = apiInterface_nic.UploadDocument(documentRequest);
                            String finalApplicant_Id = Applicant_Id;
                            call.enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    if (response.isSuccessful()) {
                                        JsonObject jsonObject1 = response.body();
                                        Log.d("response_server", jsonObject1 + "");
                                        if (jsonObject1.isJsonNull()) {
                                            Toast.makeText(getApplicationContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        } else {
                                            JsonPrimitive jsonObject2 = jsonObject1.getAsJsonPrimitive("StatusMessage");
                                            JsonPrimitive jsonObject3 = jsonObject1.getAsJsonPrimitive("StatusCode");
                                            String StatusMessage = jsonObject2.toString();
                                            String StatusCode = jsonObject3.toString();
                                            Log.d("StatusCode", StatusCode + ", StatusMessage: " + StatusMessage);

                                            if (StatusCode.equals("1")) {
                                                runOnUiThread(() -> {
                                                    Toast.makeText(getApplicationContext(), "Data Uploaded Successfully" , Toast.LENGTH_SHORT).show();
                                                Log.d("Request_", "UpdateServiceParameterTable" + "Data Uploaded Successfully");
                                                  //  count_AfterUpload++;
                                                    //count_BalanceRecord--;
                                                    //tvTotalUpload.setText(String.valueOf(count_TotalCaptured));
                                                   // tvAlreadyUploaded.setText(String.valueOf(count_AfterUpload));
                                                    //tvNotUploaded.setText(String.valueOf(count_BalanceRecord));
                                               //     Log.d("Count_of_Records", "count_TotalCaptured : " + count_TotalCaptured + "\ncount_AfterUpload : " + count_AfterUpload + "\ncount_AfterUpload : " + count_BalanceRecord);
                                                  /*  if (count_TotalCaptured == count_AfterUpload && count_BalanceRecord == 0) {
                                                        tvAfterUploaded.setVisibility(View.VISIBLE);
                                                        btnok.setVisibility(View.VISIBLE);
                                                        btnUpload.setVisibility(View.GONE);
                                                    }*/

                                                });
                                                dialog.dismiss();
                                                database.execSQL("delete from " + DataBaseHelperClass_btnUpload_Docs.TABLE_NAME
                                                        + " where " + DataBaseHelperClass_btnDownload_ServiceTranTable.GSCNo + "='" + finalApplicant_Id + "'");

                                                Log.d("Local_Result", "A row deleted Successfully");
                                                displayData();
                                            } else {
                                                dialog.dismiss();
                                                Toast.makeText(ViewUploadedDocs.this, ""+StatusMessage, Toast.LENGTH_SHORT).show();
                                                Log.d("Request_", "UpdateServiceParameterTable" + " Data not uploaded");
                                            }
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {
                                    dialog.dismiss();
                                    t.printStackTrace();
                                    Toast.makeText(getApplicationContext(), ""+t.getLocalizedMessage() , Toast.LENGTH_SHORT).show();
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i("Error1", e.getMessage());
                            runOnUiThread(() -> {
                                Toast.makeText(getApplicationContext(), getString(R.string.server_exception) , Toast.LENGTH_SHORT).show();
                                Log.d("InsertServiceParaTable", "Server Exception Occurred");
                                dialog.dismiss();
                            });
                        }
                    }while (cursor.moveToNext());
                }

            } else {
                cursor.close();
                runOnUiThread(() -> {
                    //Toast.makeText(getApplicationContext(), "There is no Updated data to Upload in Server " , Toast.LENGTH_SHORT).show();
                    Log.d("InsertServiceParaTable", "There is no Updated data to Upload in Server");
                    dialog.dismiss();
                });
            }


        } catch (Exception e) {
            Log.d("InsertServiceParaTable1", e.getMessage());
            dialog.dismiss();
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), getString(R.string.server_exception) , Toast.LENGTH_SHORT).show());
        }

    }

    @SuppressLint("SetTextI18n")
    public void displayData() {
        int i = 1;

        openHelper = new DataBaseHelperClass_btnUpload_Docs(this);
        database = openHelper.getWritableDatabase();

        Cursor cursor2 = database.rawQuery("select * from " + DataBaseHelperClass_btnUpload_Docs.TABLE_NAME + " where "+ DataBaseHelperClass_btnUpload_Docs.GSCNO +"='" + applicant_Id+ "'", null);

        /*SlNo.clear();
        App_ID.clear();
        Docs_Name.clear();*/
        docList.clear();

        if (cursor2.getCount() > 0) {
            emptyTxt.setVisibility(View.GONE);
            if (cursor2.moveToNext()) {
                do {
                    Log.d("InDisplayIf", "" + i);

                    docs_Name = cursor2.getString(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.DocumentName));
                    Log.d("docs_Name", "" + i + docs_Name);
                    docs_Id = cursor2.getInt(cursor2.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.Id));

                    DocumentData documentData = new DocumentData();
                    documentData.setSlNo(i);
                    documentData.setApp_ID(applicant_Id);
                    documentData.setDocs_Name(docs_Name);
                    documentData.setDocsID(docs_Id);
                    docList.add(documentData);
                    /*App_ID.add(applicant_Id);
                    Docs_Name.add(docs_Name);
                    Docs_ID.add(docs_Id);*/
                    i++;
                } while (cursor2.moveToNext());
            }

            list_adapter = new Upload_Doc_List_Adapter(ViewUploadedDocs.this,docList);
            listView.setAdapter(list_adapter);
            //Toast.makeText(getApplicationContext(), "Data Displayed Successfully", Toast.LENGTH_SHORT).show();
        } else {
            cursor2.close();

            emptyTxt.setVisibility(View.VISIBLE);
            emptyTxt.setText("No Data Found");
//            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}