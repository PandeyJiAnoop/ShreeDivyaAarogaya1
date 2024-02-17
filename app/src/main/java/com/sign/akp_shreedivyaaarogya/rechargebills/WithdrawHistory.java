package com.sign.akp_shreedivyaaarogya.rechargebills;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

import android.app.ProgressDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import java.util.Map;

import retrofit2.Call;

public class WithdrawHistory extends AppCompatActivity {
    ImageView menuImg;
    String UserId;
    SwipeRefreshLayout srl_refresh;
    RecyclerView chat_recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_history);
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
                if (NetworkConnectionHelper.isOnline(WithdrawHistory.this)) {
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
                    Toast.makeText(WithdrawHistory.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TopupDetails();

    }

    public void  TopupDetails(){
        String otp1 = new GlobalAppApis().WithdrawlRequestReport(UserId);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getWithdrawalReport(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("getWithdrawalReport",result);
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray Jarray = object.getJSONArray("Response");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject jsonObject1 = Jarray.getJSONObject(i);
                        HashMap<String, String> hm = new HashMap<>();
                        hm.put("RequestAmt", jsonObject1.getString("RequestAmt"));
                        hm.put("ApproveDate", jsonObject1.getString("ApproveDate"));
                        hm.put("EntredDate", jsonObject1.getString("EntredDate"));
                        hm.put("ApproveStatus", jsonObject1.getString("ApproveStatus"));
                        hm.put("Remarks", jsonObject1.getString("Remarks"));
                        hm.put("AdminCharge", jsonObject1.getString("AdminCharge"));
                        arrayList.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(WithdrawHistory.this, 1);
                    WithdrawHistory.FundistoryAdapter walletHistoryAdapter = new WithdrawHistory.FundistoryAdapter(WithdrawHistory.this, arrayList);
                    chat_recyclerView.setLayoutManager(gridLayoutManager);
                    chat_recyclerView.setAdapter(walletHistoryAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, WithdrawHistory.this, call1, "", true);
    }


    public class FundistoryAdapter extends RecyclerView.Adapter<WithdrawHistory.FundistoryAdapter.VH> {
        Context context;
        List<HashMap<String,String>> arrayList;
        public FundistoryAdapter(Context context, List<HashMap<String,String>> arrayList) {
            this.arrayList=arrayList;
            this.context=context;
        }
        @NonNull
        @Override
        public WithdrawHistory.FundistoryAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.dynamic_withdrawalhistory, viewGroup, false);
            WithdrawHistory.FundistoryAdapter.VH viewHolder = new WithdrawHistory.FundistoryAdapter.VH(view);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull WithdrawHistory.FundistoryAdapter.VH vh, int i) {
            vh.srtv.setText(String.valueOf(" "+(i+1)));
            vh.tv.setText(arrayList.get(i).get("RequestAmt"));
            vh.tv1.setText(arrayList.get(i).get("ApproveDate"));
            vh.tv2.setText(arrayList.get(i).get("EntredDate"));
            vh.tv3.setText(arrayList.get(i).get("ApproveStatus"));
            vh.tv4.setText(arrayList.get(i).get("Remarks"));
            vh.tv5.setText(arrayList.get(i).get("AdminCharge"));

            if (arrayList.get(i).get("ApproveStatus").equalsIgnoreCase("Approved")){
                vh.tv3.setTextColor(Color.GREEN);
            }
            else {
                vh.tv3.setTextColor(Color.RED);
            }
        }
        @Override
        public int getItemCount() {
            return arrayList.size();
        }
        public class VH extends RecyclerView.ViewHolder {
            TextView tv,tv1,tv2,tv3,tv4,tv5,srtv;
            //        CircleImageView img_icon;
            public VH(@NonNull View itemView) {
                super(itemView);
                srtv = itemView.findViewById(R.id.srtv);
                tv = itemView.findViewById(R.id.tv);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);
                tv3 = itemView.findViewById(R.id.tv3);
                tv4 = itemView.findViewById(R.id.tv4);
                tv5 = itemView.findViewById(R.id.tv5);
            }}}

}