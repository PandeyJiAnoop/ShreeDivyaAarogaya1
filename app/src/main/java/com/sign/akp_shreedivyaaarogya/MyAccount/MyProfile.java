package com.sign.akp_shreedivyaaarogya.MyAccount;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sign.akp_shreedivyaaarogya.Basic.ApiService;
import com.sign.akp_shreedivyaaarogya.Basic.ConnectToRetrofit;
import com.sign.akp_shreedivyaaarogya.Basic.GlobalAppApis;
import com.sign.akp_shreedivyaaarogya.Basic.RetrofitCallBackListenar;
import com.google.android.material.textfield.TextInputEditText;
import com.sign.akp_shreedivyaaarogya.DashboardActivity;
import com.sign.akp_shreedivyaaarogya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Call;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

public class MyProfile extends AppCompatActivity {
    ImageView menuImg;
    String UserId;
    AppCompatButton btn_submit;
    EditText sponser_id_et,name_et,mobile_et,email_et;

    EditText house_no_et,aprtment_et,complete_add_et,landamrk_et,pincode_et,account_holder_et,bank_name_et,ifsc_et,branch_et,acc_no_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("login_preference",MODE_PRIVATE);
        UserId= sharedPreferences.getString("U_id", "");
        FindId();
        OnClick();
        ProfileAPI();
    }

    private void OnClick() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProfileAPI(UserId,name_et.getText().toString(),email_et.getText().toString(),mobile_et.getText().toString(),
                        aprtment_et.getText().toString(),pincode_et.getText().toString(),
                        bank_name_et.getText().toString(),branch_et.getText().toString(),ifsc_et.getText().toString(),"Saving");
            }
        });
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void FindId() {

        SharedPreferences sharedPreferences = getSharedPreferences("login_preference",MODE_PRIVATE);
        UserId= sharedPreferences.getString("U_id", "");
        menuImg=findViewById(R.id.menuImg);
        sponser_id_et=findViewById(R.id.sponser_id_et);
        name_et=findViewById(R.id.name_et);
        mobile_et=findViewById(R.id.mobile_et);
        email_et=findViewById(R.id.email_et);
        house_no_et=findViewById(R.id.house_no_et);
        aprtment_et=findViewById(R.id.aprtment_et);
        complete_add_et=findViewById(R.id.complete_add_et);
        landamrk_et=findViewById(R.id.landamrk_et);
        pincode_et=findViewById(R.id.pincode_et);
        account_holder_et=findViewById(R.id.account_holder_et);
        acc_no_et=findViewById(R.id.acc_no_et);
        bank_name_et=findViewById(R.id.bank_name_et);
        ifsc_et=findViewById(R.id.ifsc_et);
        branch_et=findViewById(R.id.branch_et);
        btn_submit=findViewById(R.id.btn_submit);
    }

    private void UpdateProfileAPI(
            String UserId, String Name, String EmailId,String Mobile,String State,String Pincode,
            String BankName,String BankBranch,String IfscCode,String AccountType) {
        String otp1 = new GlobalAppApis().UpdateProfile(UserId,Name,EmailId,Mobile,
                State,Pincode,BankName,BankBranch,IfscCode,AccountType);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getProfileUpdate(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("getProfileUpdate",result);
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(result);
                    if(object.getString("Status").equalsIgnoreCase("true")) {
                        JSONArray Jarray = object.getJSONArray("Response");
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject jsonobject = Jarray.getJSONObject(i);
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                            Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), object.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, MyProfile.this, call1, "", true);
    }

    public void  ProfileAPI(){
        String otp1 = new GlobalAppApis().Profile(UserId);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getProfileView(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("getProfileView",result);
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(result);
                    if(object.getString("Status").equalsIgnoreCase("true")){
                        JSONArray Jarray = object.getJSONArray("Response");
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject jsonobject = Jarray.getJSONObject(i);
                            sponser_id_et.setText(jsonobject.getString("SponsorerId"));
                            name_et.setText(jsonobject.getString("CustomerName"));
                            mobile_et.setText(jsonobject.getString("MobileNo"));
                            email_et.setText(jsonobject.getString("EmailAddress"));
                            house_no_et.setText(jsonobject.getString("FullAddress"));
                            complete_add_et.setText(jsonobject.getString("FullAddress"));
                            landamrk_et.setText(jsonobject.getString("FullAddress"));
                            pincode_et.setText(jsonobject.getString("Pincode"));
                            account_holder_et.setText(jsonobject.getString("NomineeName"));
                            bank_name_et.setText(jsonobject.getString("BankName"));
                            ifsc_et.setText(jsonobject.getString("IfscCode"));
                            branch_et.setText(jsonobject.getString("BranchName"));
                            acc_no_et.setText(jsonobject.getString("AccNumber"));
//                            Toast.makeText(getApplicationContext(), object.getString("Message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), object.getString("Message"), Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, MyProfile.this, call1, "", true);
    }

}