package com.sign.akp_shreedivyaaarogya.IncomeDetails;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class Income_BusinessProposal extends AppCompatActivity {
    ImageView menuImg;
    String UserId;
    SwipeRefreshLayout srl_refresh;
    RecyclerView chat_recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_business_proposal);
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
                if (NetworkConnectionHelper.isOnline(Income_BusinessProposal.this)) {
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
                    Toast.makeText(Income_BusinessProposal.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                } }});
        TopupDetails(UserId);
    }

    public void  TopupDetails(String userid){
        String otp1 = new GlobalAppApis().parm_BusinessProposal(userid);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.api_BusinessProposal(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                Log.d("api_BusinessProposal",result);
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray Jarray = object.getJSONArray("Response");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject jsonObject1 = Jarray.getJSONObject(i);
                        HashMap<String, String> hm = new HashMap<>();
                        hm.put("PackagePrice", jsonObject1.getString("PackagePrice"));
                        hm.put("Mdate", jsonObject1.getString("Mdate"));
                        hm.put("PackageBV", jsonObject1.getString("PackageBV"));
                        hm.put("Package_name", jsonObject1.getString("Package_name"));
//                        hm.put("show_amount", jsonObject1.getString("show_amount"));
//                        hm.put("updated_at", jsonObject1.getString("updated_at"));
                        arrayList.add(hm);
//                        JSONObject getDetails = jsonArrayr.getJSONObject(i);
//                        String experiance = getDetails.getString("AstroAccountId");
//                        String images = getDetails.getString("ChatId");
//                        String language = getDetails.getString("CustomerAccountId");
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(Income_BusinessProposal.this, 1);
                    ActivationAdapter walletHistoryAdapter = new ActivationAdapter(Income_BusinessProposal.this, arrayList);
                    chat_recyclerView.setLayoutManager(gridLayoutManager);
                    chat_recyclerView.setAdapter(walletHistoryAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, Income_BusinessProposal.this, call1, "", true); }





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
            View view = LayoutInflater.from(context).inflate(R.layout.dynamic_busines_proposal, viewGroup, false);
            ActivationAdapter.VH viewHolder = new ActivationAdapter.VH(view);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull ActivationAdapter.VH vh, int i) {
            vh.sr.setText(String.valueOf(i+1));
            vh.tv.setText(arrayList.get(i).get("Mdate"));
            vh.tv1.setText(arrayList.get(i).get("PackageBV"));
            vh.tv2.setText(arrayList.get(i).get("Package_name"));
            vh.tv3.setText("\u20B9"+arrayList.get(i).get("PackagePrice"));


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
        public class VH extends RecyclerView.ViewHolder {
            TextView tv,tv1,tv2, tv3,tv4,sr;
            //        CircleImageView img_icon;
            public VH(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);
                tv3 = itemView.findViewById(R.id.tv3);
                tv4 = itemView.findViewById(R.id.tv4);
                sr = itemView.findViewById(R.id.sr);
            }
        }
    }

}