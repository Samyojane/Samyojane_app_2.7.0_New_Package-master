package com.nadakacheri.samyojane_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.Arrays;

public class Upload_PDF_Viewer extends AppCompatActivity {

    Button btnBack;
    String document_Name;
    int document_ID;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;
    byte[] bytes;
    TextView tvDocumentName;


    PDFView pdfView;
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_viewer);

        btnBack = findViewById(R.id.btnBack);
        pdfView = findViewById(R.id.pdfView);
        tvDocumentName = findViewById(R.id.tvDocumentName);

        Intent i = getIntent();
        document_ID = i.getIntExtra("document_ID", 0);
        document_Name = i.getStringExtra("document_Name");

        Log.d("document_ID", ""+document_ID);
        Log.d("document_Name",""+document_Name);
        tvDocumentName.setText(document_Name);

        openHelper = new DataBaseHelperClass_btnUpload_Docs(this);
        database = openHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelperClass_btnUpload_Docs.TABLE_NAME +" where "+DataBaseHelperClass_btnUpload_Docs.Id+"="+ document_ID , null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToNext()) {
                    bytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.Document));
                    Log.d("Blob", "" + Arrays.toString(bytes));
                }
        } else {
            cursor.close();
        }

        byte[] imageAsBytes = Base64.decode(bytes, 0);
        Log.d("Blob", "" + imageAsBytes);
        pdfView.fromBytes(imageAsBytes).load();

        btnBack.setOnClickListener(v -> onBackPressed());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
