package com.nadakacheri.samyojane_app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class RI_Village_ListAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<String> SlNo;
    ArrayList<String> TotalCount;
    ArrayList<String> VillageName;
    ArrayList<String> VillageCode;
    String total;
    String villageName, villageCode;

    RI_Village_ListAdapter(Context context, ArrayList<String> slNo, ArrayList<String> villageName,
                        ArrayList<String> totalCount, ArrayList<String> villageCode){
        this.context = context;
        this.SlNo = slNo;
        this.TotalCount = totalCount;
        this.VillageName = villageName;
        this.VillageCode = villageCode;
    }
    @Override
    public int getCount() {
        return VillageName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final RI_Village_ViewHolder viewHolder;

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_village,parent, false);
            viewHolder=new RI_Village_ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(RI_Village_ViewHolder) convertView.getTag();
        }

        viewHolder.sl_No.setText(SlNo.get(position));
        viewHolder.tvTotalPendency.setText(TotalCount.get(position));
        viewHolder.tvVillageName.setText(VillageName.get(position));
        viewHolder.tvVillageCode.setText(VillageCode.get(position));

        total = TotalCount.get(position);

        viewHolder.btnEdit.setOnClickListener(view -> {
            villageName = viewHolder.tvVillageName.getText().toString();
            villageCode = viewHolder.tvVillageCode.getText().toString();

            Log.d("villageCode_Vill",""+villageCode);
            Log.d("VillageName_Vill",""+VillageName);

            Intent i = new Intent(context, RI_Applicant_wise_report.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("villageCode",villageCode);
            i.putExtra("townCode","9999");
            i.putExtra("wardCode","255");
            context.startActivity(i);
            //((Activity) context).finish();

        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}

class RI_Village_ViewHolder{
    TextView sl_No, tvTotalPendency,btnEdit, tvVillageName, tvVillageCode;
    RI_Village_ViewHolder(View view){
        sl_No = view.findViewById(R.id.sl_No);
        tvTotalPendency = view.findViewById(R.id.tvTotalPendency);
        btnEdit = view.findViewById(R.id.btnEdit);
        tvVillageName = view.findViewById(R.id.tvVillageName);
        tvVillageCode = view.findViewById(R.id.tvVillageCode);
    }
}
