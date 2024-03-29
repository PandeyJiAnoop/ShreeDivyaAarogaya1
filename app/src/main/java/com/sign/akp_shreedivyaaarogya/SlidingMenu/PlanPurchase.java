package com.sign.akp_shreedivyaaarogya.SlidingMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sign.akp_shreedivyaaarogya.Basic.ApiService;
import com.sign.akp_shreedivyaaarogya.Basic.ConnectToRetrofit;
import com.sign.akp_shreedivyaaarogya.Basic.GlobalAppApis;
import com.sign.akp_shreedivyaaarogya.Basic.RetrofitCallBackListenar;
import com.google.android.material.textfield.TextInputEditText;
import com.sign.akp_shreedivyaaarogya.FundArea.PrincipalWithdrawalRequest;
import com.sign.akp_shreedivyaaarogya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Call;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

public class PlanPurchase extends AppCompatActivity {

    ImageView menuImg;
    TextView wallet_amount_tv,credit_tv,debit_tv;
    TextInputEditText activation_id_et;
    String UserId,UserPass,walletBalance;
    AppCompatButton raise_add_tv;
    TextInputEditText amount_et;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_purchase);
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference",MODE_PRIVATE);
        UserId= sharedPreferences.getString("U_id", "");
        UserPass= sharedPreferences.getString("U_pass", "");
        findId();
        OnClick();
        WalletAPI();
        DashboardAPI();
    }

    private void OnClick() {
        raise_add_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amount_et.getText().toString().equalsIgnoreCase("")){
                    amount_et.setError("Fields can't be blank!");
                    amount_et.requestFocus();
                }
                else {
                    Intent intent=new Intent(getApplicationContext(),RaiseAddRequest.class);
                    intent.putExtra("amount",amount_et.getText().toString());
                    startActivity(intent);
                }

            }
        });
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findId() {
        amount_et=findViewById(R.id.amount_et);
        menuImg=findViewById(R.id.menuImg);
        wallet_amount_tv=findViewById(R.id.wallet_amount_tv);
        credit_tv=findViewById(R.id.credit_tv);
        debit_tv=findViewById(R.id.debit_tv);
        activation_id_et=findViewById(R.id.activation_id_et);

        raise_add_tv=findViewById(R.id.raise_add_tv);
    }

    private void DashboardAPI()  {
        String otp1 = new GlobalAppApis().BindDashboard(UserId);
        Log.d("das_response",otp1);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getBindDashboard(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("das_response",result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("Status").equals("true")){
                        JSONArray Jarray = object.getJSONArray("Response");
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject jsonobject = Jarray.getJSONObject(i);
                            walletBalance  = jsonobject.getString("Avail_Balance");
                            wallet_amount_tv.setText("\u20B9"+walletBalance);
                        } }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PlanPurchase.this);
                        builder.setTitle("Message!").setMessage(object.getString("Message")).setCancelable(false).setIcon(R.drawable.appicon)
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();}}); builder.create().show();
                    } } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } } }, PlanPurchase.this, call1, "", true);
    }
    public void  WalletAPI(){
        String otp1 = new GlobalAppApis().Wallet(UserId);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getWallet(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray Jarray = object.getJSONArray("Response");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject jsonobject = Jarray.getJSONObject(i);
                        String credit       = jsonobject.getString("FCredit");
                        String debit    = jsonobject.getString("FDebit");
//                        String walletBalance      = jsonobject.getString("WalletBalance");
                        String walletBalance      = jsonobject.getString("EWallet");
                        wallet_amount_tv.setText("\u20B9"+walletBalance);
                        credit_tv.setText("\u20B9"+credit);
                        debit_tv.setText("\u20B9"+debit);
                        activation_id_et.setText(UserId);

//                        Toast.makeText(getApplicationContext(), object.getString("Message"), Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, PlanPurchase.this, call1, "", true);
    }

}
