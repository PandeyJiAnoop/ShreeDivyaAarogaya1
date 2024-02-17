package com.sign.akp_shreedivyaaarogya.Walet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sign.akp_shreedivyaaarogya.Basic.ApiService;
import com.sign.akp_shreedivyaaarogya.Basic.ConnectToRetrofit;
import com.sign.akp_shreedivyaaarogya.Basic.GlobalAppApis;
import com.sign.akp_shreedivyaaarogya.Basic.RetrofitCallBackListenar;
import com.sign.akp_shreedivyaaarogya.FundArea.PrincipalWithdrawalRequest;
import com.sign.akp_shreedivyaaarogya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Call;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

public class MainWalletTransfer extends AppCompatActivity {
    ImageView menuImg;
    TextView wallet_amount_tv,credit_tv,debit_tv;
    TextInputEditText userid_et;
    String UserId,walletBalance,UserPass;
    AppCompatButton raise_add_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wallet_transfer);
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference",MODE_PRIVATE);
        UserId= sharedPreferences.getString("U_id", "");
        UserPass= sharedPreferences.getString("U_pass", "");
        findId();
        OnClick();

        DashboardAPI();

    }

    private void OnClick() {
        raise_add_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid_et.getText().toString().equalsIgnoreCase("")){
                    userid_et.setError("Fields can't be blank!");
                    userid_et.requestFocus();
                }
                else {
                    IDACTIVATE(userid_et.getText().toString(),UserId,walletBalance);

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
        menuImg=findViewById(R.id.menuImg);
        wallet_amount_tv=findViewById(R.id.wallet_amount_tv);
        credit_tv=findViewById(R.id.credit_tv);
        debit_tv=findViewById(R.id.debit_tv);
        userid_et=findViewById(R.id.userid_et);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainWalletTransfer.this);
                        builder.setTitle("Message!").setMessage(object.getString("Message")).setCancelable(false).setIcon(R.drawable.appicon)
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();}}); builder.create().show();
                    } } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } } }, MainWalletTransfer.this, call1, "", true);
    }

    public void IDACTIVATE(String CashWallet,String UserId,String WalletBalance) {
        String otp = new GlobalAppApis().MainWallet(CashWallet,UserId,WalletBalance);
        ApiService client = getApiClient_ByPost();
        Call<String> call = client.getMainWallet(otp);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("Status").equalsIgnoreCase("true")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainWalletTransfer.this);
                        builder.setTitle("Message!").setMessage(jsonObject.getString("Message")).setCancelable(false).setIcon(R.drawable.appicon)
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                       finish();
                                        dialog.cancel();
                                    }});
                        builder.create().show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainWalletTransfer.this);
                        builder.setTitle("Message!").setMessage(jsonObject.getString("Message")).setCancelable(false).setIcon(R.drawable.appicon)
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) { finish(); }});
                        builder.create().show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, MainWalletTransfer.this, call, "", true);
    }




}