package com.sign.akp_shreedivyaaarogya.Adapterclass;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sign.akp_shreedivyaaarogya.R;

import java.util.HashMap;
import java.util.List;



public class ActivationAdapter extends RecyclerView.Adapter<ActivationAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    public ActivationAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public ActivationAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_top_up_repoort, viewGroup, false);
        ActivationAdapter.VH viewHolder = new ActivationAdapter.VH(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ActivationAdapter.VH vh, int i) {
        vh.sr.setText(String.valueOf(i+1));
        vh.date_tv.setText(arrayList.get(i).get("Mdate"));
        vh.cust_id_tv.setText(arrayList.get(i).get("PackageBV"));
        vh.package_tv.setText(arrayList.get(i).get("Package_name"));
        vh.amount_tv.setText("\u20B9"+arrayList.get(i).get("PackagePrice"));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class VH extends RecyclerView.ViewHolder {
        TextView date_tv,cust_id_tv,package_tv, amount_tv,sr;
        //        CircleImageView img_icon;
        public VH(@NonNull View itemView) {
            super(itemView);
            date_tv = itemView.findViewById(R.id.date_tv);
            cust_id_tv = itemView.findViewById(R.id.cust_id_tv);
            package_tv = itemView.findViewById(R.id.package_tv);
            amount_tv = itemView.findViewById(R.id.amount_tv);
            sr = itemView.findViewById(R.id.sr);
        }
    }
}
