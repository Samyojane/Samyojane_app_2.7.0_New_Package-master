package com.nadakacheri.samyojane_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.nadakacheri.samyojane_app.model.document.DocumentData;

import java.util.ArrayList;

public class Upload_Doc_List_Adapter extends BaseAdapter implements Filterable {

    Context context;
   /* ArrayList<Integer> SlNo;
    ArrayList<String> Docs_Name;
    ArrayList<String> App_ID;
    ArrayList<Integer> DocsID;*/
    ArrayList<DocumentData> DocsList;
    String Applicant_Id;
    int document_ID;
    String document_Name;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    byte[] bytes;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;

    Upload_Doc_List_Adapter(Context context, ArrayList<DocumentData> docsList) {
        this.context = context;
      this.DocsList = docsList;
    }

    @Override
    public int getCount() {
        return DocsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Docs_ViewHolder docs_viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_docs, parent, false);
            docs_viewHolder = new Docs_ViewHolder(convertView);
            convertView.setTag(docs_viewHolder);
        } else {
            docs_viewHolder = (Docs_ViewHolder) convertView.getTag();
        }

        String Docs_ID_str = DocsList.get(position).getDocsID().toString();

        docs_viewHolder.sl_No.setText(DocsList.get(position).getSlNo() + "");
        docs_viewHolder.tvAppID.setText(DocsList.get(position).getApp_ID());
        docs_viewHolder.tvDocsName.setText(DocsList.get(position).getDocs_Name());
        docs_viewHolder.tvDocsID.setText(Docs_ID_str);


        Button btnView = convertView.findViewById(R.id.btnView);

        btnView.setOnClickListener(v -> {

            Applicant_Id = docs_viewHolder.tvAppID.getText().toString();
            document_ID = Integer.parseInt(docs_viewHolder.tvDocsID.getText().toString());
            document_Name = docs_viewHolder.tvDocsName.getText().toString();

//            openHelper = new DataBaseHelperClass_btnUpload_Docs(context);
//            database = openHelper.getWritableDatabase();


//            Cursor cursor = database.rawQuery("select * from " + DataBaseHelperClass_btnUpload_Docs.TABLE_NAME + " Where "+ DataBaseHelperClass_btnUpload_Docs.GSCNO + " = '" + App_ID.get(position) + "' and " +DataBaseHelperClass_btnUpload_Docs.Id + "=" + docsID.get(position) , null);
//
//
//            if (cursor.getCount() > 0) {
//                if (cursor.moveToNext()) {
//                    bytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DataBaseHelperClass_btnUpload_Docs.Document));
//                    Log.d("Blob", "" + Arrays.toString(bytes));
//                }

//                byte[] imageAsBytes = Base64.decode(bytes, 0);
//                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//                PdfDocument document = new PdfDocument();
//                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(decodedImage.getWidth(), decodedImage.getHeight(), 1).create();
//                PdfDocument.Page page = document.startPage(pageInfo);
//                Canvas canvas = page.getCanvas();
//                Paint paint = new Paint();
//                paint.setColor(Color.parseColor("#ffffff"));
//                canvas.drawPaint(paint);
//                canvas.drawBitmap(decodedImage, 0, 0, null);
//                document.finishPage(page);

                Intent i = new Intent(context, Upload_PDF_Viewer.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Applicant_Id", Applicant_Id);
                i.putExtra("document_ID",document_ID);
                i.putExtra("document_Name", document_Name);
                context.startActivity(i);

//                builder = new AlertDialog.Builder(v.getRootView().getContext());
//                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.doc_image_dialog, null);
//                ImageView imageView = dialogView.findViewById(R.id.imageView);
//                imageView.setImageBitmap(decodedImage);
//                builder.setView(dialogView)
//                        .setCancelable(true);
//                alertDialog = builder.create();
//                alertDialog.show();
//
//            } else {
//                cursor.close();
//            }

        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

}

class Upload_Docs_ViewHolder {
    TextView sl_No, tvAppID, tvDocsID,tvDocsName;

    Upload_Docs_ViewHolder(View view) {
        sl_No = view.findViewById(R.id.sl_No);
        tvAppID = view.findViewById(R.id.tvAppID);
        tvDocsID = view.findViewById(R.id.tvDocsID);
        tvDocsName = view.findViewById(R.id.tvDocsName);

    }
}


