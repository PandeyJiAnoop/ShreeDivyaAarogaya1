package com.sign.akp_shreedivyaaarogya.MyNetwork;


import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sign.akp_shreedivyaaarogya.Adapterclass.ReferralAdapter;
import com.sign.akp_shreedivyaaarogya.Basic.ApiService;
import com.sign.akp_shreedivyaaarogya.Basic.ConnectToRetrofit;
import com.sign.akp_shreedivyaaarogya.Basic.GlobalAppApis;
import com.sign.akp_shreedivyaaarogya.Basic.RetrofitCallBackListenar;
import com.sign.akp_shreedivyaaarogya.NetworkConnectionHelper;
import com.sign.akp_shreedivyaaarogya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;


public class DownLine extends AppCompatActivity {
    ImageView menuImg;
    String UserId;
    SwipeRefreshLayout srl_refresh;
    RecyclerView chat_recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_line);
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference",MODE_PRIVATE);
        UserId= sharedPreferences.getString("U_id", "");
        srl_refresh = findViewById(R.id.srl_refresh);
        chat_recyclerView = findViewById(R.id.chat_recyclerView);
        menuImg=findViewById(R.id.menuImg);
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectionHelper.isOnline(DownLine.this)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                            srl_refresh.setRefreshing(false);
                        }
                    }, 2000);
                } else {
                    Toast.makeText(DownLine.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TopupDetails();

    }



    public void  TopupDetails(){
        String otp1 = new GlobalAppApis().MyDirect(UserId,"","","");
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getMyDownlineReport(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("getMyDownlineReport",result);

//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray Jarray = object.getJSONArray("Response");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject jsonObject1 = Jarray.getJSONObject(i);
                        HashMap<String, String> hm = new HashMap<>();
                        hm.put("CustomerId", jsonObject1.getString("CustomerId"));
                        hm.put("CustomerName", jsonObject1.getString("CustomerName"));
                        hm.put("RegDate", jsonObject1.getString("RegDate"));
                        hm.put("ActiveDate", jsonObject1.getString("ActiveDate"));
                        hm.put("Package", jsonObject1.getString("Package"));
                        hm.put("Status", jsonObject1.getString("Status"));
                        hm.put("Previousbv", jsonObject1.getString("Previousbv"));
                        hm.put("curruntBv", jsonObject1.getString("curruntBv"));
                        hm.put("Cumulative", jsonObject1.getString("Cumulative"));

//                        hm.put("show_amount", jsonObject1.getString("show_amount"));
//                        hm.put("updated_at", jsonObject1.getString("updated_at"));
                        arrayList.add(hm);
//                        JSONObject getDetails = jsonArrayr.getJSONObject(i);
//                        String experiance = getDetails.getString("AstroAccountId");
//                        String images = getDetails.getString("ChatId");
//                        String language = getDetails.getString("CustomerAccountId");
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(DownLine.this, 1);
                    DownlineAdapter walletHistoryAdapter = new DownlineAdapter(DownLine.this, arrayList);
                    chat_recyclerView.setLayoutManager(gridLayoutManager);
                    chat_recyclerView.setAdapter(walletHistoryAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, DownLine.this, call1, "", true);
    }


    public class DownlineAdapter extends RecyclerView.Adapter<DownlineAdapter.VH> {
        Context context;
        List<HashMap<String,String>> arrayList;
        public DownlineAdapter(Context context, List<HashMap<String,String>> arrayList) {
            this.arrayList=arrayList;
            this.context=context;
        }
        @NonNull
        @Override
        public DownlineAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.dynamic_referal_list, viewGroup, false);
            DownlineAdapter.VH viewHolder = new DownlineAdapter.VH(view);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull DownlineAdapter.VH vh, int i) {
            vh.tv.setText(arrayList.get(i).get("ActiveDate"));
            vh.tv1.setText(arrayList.get(i).get("CustomerId"));
            vh.tv2.setText(arrayList.get(i).get("CustomerName"));
            vh.tv4.setText(arrayList.get(i).get("Package"));
            vh.tv5.setText(arrayList.get(i).get("RegDate"));
            if (arrayList.get(i).get("Status").equalsIgnoreCase("Active")){
                vh.tv6.setText(arrayList.get(i).get("Status"));
                vh.tv6.setTextColor(Color.GREEN);
            }
            else {
                vh.tv6.setText(arrayList.get(i).get("Status"));
                vh.tv6.setTextColor(Color.RED);
            }
            vh.tv7.setText(arrayList.get(i).get("Previousbv"));
            vh.tv8.setText(arrayList.get(i).get("curruntBv"));
            vh.tv9.setText(arrayList.get(i).get("Cumulative"));

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
        public class VH extends RecyclerView.ViewHolder {
            TextView tv,tv1,tv2,tv4,tv5,tv6,tv7,tv8,tv9;
            //        CircleImageView img_icon;
            public VH(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);
                tv4 = itemView.findViewById(R.id.tv4);
                tv5 = itemView.findViewById(R.id.tv5);
                tv6 = itemView.findViewById(R.id.tv6);
                tv7 = itemView.findViewById(R.id.tv7);
                tv8 = itemView.findViewById(R.id.tv8);
                tv9 = itemView.findViewById(R.id.tv9);
            }
        }
    }

}