package com.sign.akp_shreedivyaaarogya.Basic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sign.akp_shreedivyaaarogya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Call;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;


public class SignupScreen extends AppCompatActivity {
    //    public class SignupScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] courses = { "Direction", "Left", "Right" };
    EditText sponser_et,name_et,email_et,mob_et,c_pass_et,pass_et,country_et;
    TextView signin_tv,spname_tv;
    AppCompatButton signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        findId();
        OnClick();

//        Spinner spin = findViewById(R.id.direction_sp);
//        spin.setOnItemSelectedListener(this);
//        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(ad);
    }

    private void OnClick() {
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sponser_et.getText().toString().equalsIgnoreCase("")){
                    sponser_et.setError("Fields can't be blank!");
                    sponser_et.requestFocus();
                }
                else if (name_et.getText().toString().equalsIgnoreCase("")){
                    name_et.setError("Fields can't be blank!");
                    name_et.requestFocus();
                }
                else if (email_et.getText().toString().equalsIgnoreCase("")){
                    email_et.setError("Fields can't be blank!");
                    email_et.requestFocus();
                }
                else if (mob_et.getText().toString().equalsIgnoreCase("")){
                    mob_et.setError("Fields can't be blank!");
                    mob_et.requestFocus();
                }
                else if (country_et.getText().toString().equalsIgnoreCase("")){
                    country_et.setError("Fields can't be blank!");
                    country_et.requestFocus();
                }
                else if (pass_et.getText().toString().equalsIgnoreCase("")){
                    pass_et.setError("Fields can't be blank!");
                    pass_et.requestFocus();
                }
                else if (c_pass_et.getText().toString().equalsIgnoreCase("")){
                    c_pass_et.setError("Fields can't be blank!");
                    c_pass_et.requestFocus();
                }
                else if (!pass_et.getText().toString().equalsIgnoreCase(c_pass_et.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password Not Matched!",Toast.LENGTH_LONG).show();
                }
                else {
                    getRegAPI(name_et.getText().toString(),sponser_et.getText().toString(),spname_tv.getText().toString(),
                            email_et.getText().toString(),mob_et.getText().toString(),"","","",
                            "","","","",country_et.getText().toString(),"Male",pass_et.getText().toString());
                } }
        });
        signin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginScreen.class);
                startActivity(intent);
            }}); }

    private void findId() {
        country_et=findViewById(R.id.country_et);
        sponser_et=findViewById(R.id.sponser_et);
        name_et=findViewById(R.id.name_et);
        email_et=findViewById(R.id.email_et);
        mob_et=findViewById(R.id.mob_et);
        pass_et=findViewById(R.id.pass_et);
        c_pass_et=findViewById(R.id.c_pass_et);
        signup_btn=findViewById(R.id.signup_btn);
        signin_tv=findViewById(R.id.signin_tv);
        spname_tv=findViewById(R.id.spname_tv);

        sponser_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


    }
//    // Performing action when ItemSelected
//    // from spinner, Overriding onItemSelected method
//    @Override
//    public void onItemSelected(AdapterView arg0, View arg1, int position, long id)
//    {
//        Toast.makeText(getApplicationContext(),courses[position], Toast.LENGTH_LONG).show();
//    }
//    @Override
//    public void onNothingSelected(AdapterView arg0)
//    {
//        // Auto-generated method stub
//    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public void  getRegAPI(String UserName, String SponsorId, String Name,String EmailAddress,String ContactNo,
                           String DOB,String Pincode,String State,String District,String HouseNumber,String ApartmentName,
                           String Landmark,String Address,String Gender,String Password){
        String otp1 = new GlobalAppApis().NewAccount(UserName,SponsorId,Name,EmailAddress,ContactNo,DOB,Pincode,State,District,
                HouseNumber,ApartmentName,Landmark,Address,Gender,Password);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getNewAccount(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("resppp",result);
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("Status").equalsIgnoreCase("true")){
                        JSONArray Jarray = object.getJSONArray("Response");
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject jsonobject = Jarray.getJSONObject(i);
                        Toast.makeText(getApplicationContext(), jsonobject.getString("msg"), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupScreen.this);
                        builder.setTitle("Registration Successfully!")
                                .setMessage(jsonobject.getString("msg")+"\nUserId:- "+jsonobject.getString("loginid")
                                        +"\nPassword:- "+jsonobject.getString("Pwd"))
                                .setCancelable(false)
                                .setIcon(R.drawable.appicon)
                                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent=new Intent(getApplicationContext(),LoginScreen.class);
                                        startActivity(intent);
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    } }); builder.create().show();
                    }}
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupScreen.this);
                        builder.setTitle("ERROR!")
                                .setMessage(object.getString("Message"))
                                .setCancelable(false)
                                .setIcon(R.drawable.appicon)
                                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent=new Intent(getApplicationContext(),LoginScreen.class);
                                        startActivity(intent);
                                        dialog.cancel();
                                    } }); builder.create().show();
                    }
                } catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupScreen.this);
                    builder.setTitle("ERROR!")
                            .setMessage("Something Went Wrong!!")
                            .setCancelable(false)
                            .setIcon(R.drawable.appicon)
                            .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent=new Intent(getApplicationContext(),LoginScreen.class);
                                    startActivity(intent);
                                    dialog.cancel();
                                } });builder.create().show(); e.printStackTrace();
                } }
        }, SignupScreen.this, call1, "", true); }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        getDataFromSponser(sponser_et.getText().toString());
        Log.d("ressss",sponser_et.getText().toString());
    }

    private void getDataFromSponser(String refid)
    {
        String otp1 = new GlobalAppApis().CheckSponsor(refid);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.get_CheckSponsor(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("refsfsd",result);
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("Status").equals("false")){
                        spname_tv.setText("");
                        spname_tv.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), object.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        JSONArray Jarray = object.getJSONArray("Response");
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject jsonobject = Jarray.getJSONObject(i);
                            String Name = jsonobject.getString("CustomerName");
                            spname_tv.setText(Name);
                            spname_tv.setVisibility(View.VISIBLE);
//                        Toast.makeText(getApplicationContext(), object.getString("Message"), Toast.LENGTH_SHORT).show();
                        } }} catch (JSONException e) {
                    e.printStackTrace();
                } }}, SignupScreen.this, call1, "", true);
    }
}