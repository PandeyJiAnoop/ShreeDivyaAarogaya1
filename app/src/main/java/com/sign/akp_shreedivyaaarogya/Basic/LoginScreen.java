package com.sign.akp_shreedivyaaarogya.Basic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.sign.akp_shreedivyaaarogya.DashboardActivity;
import com.sign.akp_shreedivyaaarogya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Call;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

public class LoginScreen extends AppCompatActivity {
    TextView btn_signup;
    EditText user_name_et,pass_et;
    AppCompatButton btn_login;
    private PopupWindow popupWindow;
    RelativeLayout mail_rl;
    private SharedPreferences login_preference;
    private SharedPreferences.Editor login_editor;

    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_ID = "id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_SAVE_CREDENTIALS = "save_credentials";

    private CheckBox checkBoxSaveCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        findId();
        OnClick();
    }

    private void OnClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_name_et.getText().toString().equalsIgnoreCase("")){
                    user_name_et.setError("Fields can't be blank!");
                    user_name_et.requestFocus();
                } else if (pass_et.getText().toString().equalsIgnoreCase("")){
                    pass_et.setError("Fields can't be blank!");
                    pass_et.requestFocus();
                } else {
                    // Save login credentials if the checkbox is checked
                    if (checkBoxSaveCredentials.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_ID, user_name_et.getText().toString());
                        editor.putString(KEY_PASSWORD, pass_et.getText().toString());
                        editor.putBoolean(KEY_SAVE_CREDENTIALS, true);
                        editor.apply();
                    } else {
                        // Clear saved credentials if checkbox is unchecked
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(KEY_ID);
                        editor.remove(KEY_PASSWORD);
                        editor.remove(KEY_SAVE_CREDENTIALS);
                        editor.apply();
                    }

                    getLoginAPI(user_name_et.getText().toString(),pass_et.getText().toString(),"");
                }} });

//        forget_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                forgetpasswordpopup();
//            }
//        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignupScreen.class);
                startActivity(intent);
            }});
    }

    private void findId() {
        user_name_et=findViewById(R.id.user_name_et);
        pass_et=findViewById(R.id.pass_et);
//        forget_tv=findViewById(R.id.forget_tv);
        btn_login=findViewById(R.id.btn_login);
        mail_rl=findViewById(R.id.mail_rl);
        btn_signup=findViewById(R.id.btn_signup);


        checkBoxSaveCredentials = findViewById(R.id.checkBoxSaveCredentials);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        boolean saveCredentials = sharedPreferences.getBoolean(KEY_SAVE_CREDENTIALS, false);
        if (saveCredentials) {
            String savedId = sharedPreferences.getString(KEY_ID, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
            user_name_et.setText(savedId);
            pass_et.setText(savedPassword);
            checkBoxSaveCredentials.setChecked(true);
        }

    }

    private void forgetpasswordpopup() {
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.forget_password,null);
        ImageView Goback = (ImageView) customView.findViewById(R.id.Goback);
        EditText email_et = (EditText) customView.findViewById(R.id.email_et);
        AppCompatButton continue_btn = (AppCompatButton) customView.findViewById(R.id.continue_btn);
        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //display the popup window
        popupWindow.showAtLocation(mail_rl, Gravity.BOTTOM, 0, 0);
        popupWindow.setFocusable(true);
        // Settings disappear when you click somewhere else
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.update();

        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void  getLoginAPI(String UserName, String Password, String TokenNo){
        String otp1 = new GlobalAppApis().Login(UserName,Password,TokenNo);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.getLogin(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
                Log.d("login_response",result);
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("Status").equals("true")){
                        JSONArray Jarray = object.getJSONArray("Response");
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject jsonobject = Jarray.getJSONObject(i);
                            String customerId    = jsonobject.getString("UserName");
                            String name       = jsonobject.getString("Name");
                            String role       = jsonobject.getString("RoleType");
                            Intent intent=new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                            login_preference = getSharedPreferences("login_preference", MODE_PRIVATE);
                            login_editor = login_preference.edit();
                            login_editor.putString("U_id",customerId);
                            login_editor.putString("U_name",name);
                            login_editor.putString("U_pass",pass_et.getText().toString());
                            login_editor.putString("U_role",role);
                            login_editor.commit(); }
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                        builder.setTitle("Message!").setMessage(object.getString("Message")).setCancelable(false).setIcon(R.drawable.appicon)
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }});
                        builder.create().show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } }, LoginScreen.this, call1, "", true);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}