package com.sign.akp_shreedivyaaarogya.rechargebills;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sign.akp_shreedivyaaarogya.DashboardActivity;
import com.sign.akp_shreedivyaaarogya.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class New_MobileRecharge extends AppCompatActivity {
    ImageView ivBack;
    LinearLayout provider_ll;
    TextView provoider_et;
    String service_name, UserId;
    String getProvider_id, roletype;
    Button btnSubmit;
    private SharedPreferences sharedPreferences;
    EditText mob_et, amount_et;
    AlertDialog alertDialog;
    String get_operator_ref, get_payid, getonlyservice, GetMobile;
    TextView title_tv, mob_code_tv;
    TextView txtMarquee, view_plan_tv, plan_dis_tv;
    private Dialog alertDialog1;
    private final ArrayList<HashMap<String, String>> arrLegalList = new ArrayList<>();
    private LegalListAdapter pdfAdapTer;
    RecyclerView plan_rv;
    SearchableSpinner sp_state;
    String stateid;
    ArrayList<String> StateName = new ArrayList<>();
    ArrayList<String> StateId = new ArrayList<>();
    String amount;
    SearchableSpinner sp_state1;
    String stateid1;
    ArrayList<String> StateName1 = new ArrayList<>();
    ArrayList<String> StateId1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mobile_recharge);
        FindId();
        OnClickListner();

    }
    private void OnClickListner() {
        // Now we will call setSelected() method
        // and pass boolean value as true
        txtMarquee.setSelected(true);
        sp_state.setTitle("");
//        Toast.makeText(getApplicationContext(),getonlyservice,Toast.LENGTH_LONG).show();

//        if (getonlyservice.equalsIgnoreCase("Mobile")){
//            mob_et.setHint("Enter your mobile number");
//            mob_code_tv.setVisibility(View.VISIBLE);
//            mob_et.setInputType(InputType.TYPE_CLASS_NUMBER);
//            view_plan_tv.setVisibility(View.VISIBLE);
//            mob_et.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(10) });
//        }
//        else {
//            mob_et.setHint("Enter your Unique Id Number");
//            mob_code_tv.setVisibility(View.GONE);
//            view_plan_tv.setVisibility(View.GONE);
//        }

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    for (int j = 0; j <= StateId.size(); j++) {
                        if (sp_state.getSelectedItem().toString().equalsIgnoreCase(StateName.get(j))) {
                            // position = i;
                            stateid = StateId.get(j);
                            break;
                        }}}}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }});

        sp_state1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    for (int j = 0; j <= StateId1.size(); j++) {
                        if (sp_state1.getSelectedItem().toString().equalsIgnoreCase(StateName1.get(j))) {
                            // position = i;
                            stateid1 = StateId1.get(j);
                            break;
                        }}}}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }});

        view_plan_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp_state.getSelectedItem().toString().equalsIgnoreCase("AIRTEL")) {
                    arrLegalList.clear();
                    GetAppPlanAPI("A");
                } else if (sp_state.getSelectedItem().toString().equalsIgnoreCase("VODAFONE")) {
                    arrLegalList.clear();
                    GetAppPlanAPI("V");
                } else if (sp_state.getSelectedItem().toString().equalsIgnoreCase("IDEA")) {
                    arrLegalList.clear();
                    GetAppPlanAPI("I");
                } else if (sp_state.getSelectedItem().toString().equalsIgnoreCase("BSNL")) {
                    arrLegalList.clear();
                    GetAppPlanAPI("BT");
                } else if (sp_state.getSelectedItem().toString().equalsIgnoreCase("RELIANCE - JIO")) {
                    arrLegalList.clear();
                    GetAppPlanAPI("J");
                }}});
        provoider_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getonlyservice.equalsIgnoreCase("Mobile")) {
                    Intent intent = new Intent(getApplicationContext(), New_MobileRecharge.class);
                    startActivity(intent);
                } else if (getonlyservice.equalsIgnoreCase("DTH")) {
                    Intent intent = new Intent(getApplicationContext(), DTHBill.class);
                    startActivity(intent);
                } else if (getonlyservice.equalsIgnoreCase("Electricity")) {
                    Intent intent = new Intent(getApplicationContext(), ElectricityBill.class);
                    startActivity(intent);
                }}});

        if (service_name == null) {
        } else {
            provoider_et.setText(service_name);
        }
        GetOperatorList(getonlyservice);
        GetRechargeCircleList();
        final Handler handler = new Handler();
        final long delayMillis = 5000; // Delay in milliseconds
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mob_et.getText().toString().equalsIgnoreCase("")) {
                    mob_et.setError("Fields can't be blank!");
                    mob_et.requestFocus();
                } else if (mob_et.getText().toString().length() < 10) {
                    mob_et.setError("Enter 10 digit Mobile number!");
                    // show error message here
                } else if (amount_et.getText().toString().equalsIgnoreCase("")) {
                    amount_et.setError("Fields can't be blank!");
                    amount_et.requestFocus();
                } else {
                    amount = amount_et.getText().toString().replaceAll("\\.\\d+$", "");
                    btnSubmit.setEnabled(false);
                    // Enable the button after a delay
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnSubmit.setEnabled(true);
                        }
                    }, delayMillis);
//                    Payment("RAP01019", mob_et.getText().toString(), stateid1, stateid, amount);
                    Toast.makeText(getApplicationContext(),"Coming Soon!",Toast.LENGTH_LONG).show();
                }}});
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void FindId() {
        title_tv = findViewById(R.id.title_tv);
        mob_code_tv = findViewById(R.id.mob_code_tv);
        plan_rv = (RecyclerView) findViewById(R.id.plan_rv);
        roletype = getIntent().getStringExtra("RoleType");
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        GetMobile = sharedPreferences.getString("mobile", "");
        service_name = getIntent().getStringExtra("servicename");
        getProvider_id = getIntent().getStringExtra("provider_id");
        getonlyservice = getIntent().getStringExtra("onlyservice");
        plan_dis_tv = findViewById(R.id.plan_dis_tv);
        btnSubmit = findViewById(R.id.btnSubmit);
        sp_state = findViewById(R.id.stateet);
        sp_state1 = findViewById(R.id.stateet1);
        // casting of textview
        txtMarquee = (TextView) findViewById(R.id.marqueeText);
//        Toast.makeText(getApplicationContext(),getonlyservice,Toast.LENGTH_LONG).show();

        ivBack = findViewById(R.id.ivBack);
        provider_ll = findViewById(R.id.provider_ll);
        provoider_et = findViewById(R.id.provoider_et);
        mob_et = findViewById(R.id.mob_et);
        amount_et = findViewById(R.id.amount_et);
        title_tv.setText(getonlyservice + " Service");
        view_plan_tv = findViewById(R.id.view_plan_tv);
        mob_et.setText(GetMobile);
    }

    private void showpopupwindow() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(New_MobileRecharge.this).inflate(R.layout.successfullycreated_popup, viewGroup, false);
        Button ok = (Button) dialogView.findViewById(R.id.btnDialog);
        TextView txt_msg = dialogView.findViewById(R.id.txt_msg);
        TextView id_tv = dialogView.findViewById(R.id.id_tv);
        TextView pass_tv = dialogView.findViewById(R.id.pass_tv);
        id_tv.setText("Operator_ref- " + get_operator_ref + " (" + UserId + ")");
//        pass_tv.setText("Payid- "+get_payid);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                alertDialog.dismiss();
            }});
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void Payment(String id, String mob, String circleid, String opcode, String amount) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://amazepay.net/api/Amazepay/MobileDoRecharge", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString("Response"));
                    if (jsonObject1.getString("status").equalsIgnoreCase("false")) {
                        String msg = jsonObject1.getString("message");
                        Toast.makeText(getApplicationContext(), jsonObject1.getString("message"), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(New_MobileRecharge.this);
                        builder.setTitle("Response!")
                                .setMessage(msg)
                                .setCancelable(false)
                                .setIcon(R.drawable.logo)
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    } });
                        builder.create().show();
                    } else {
                        get_operator_ref = jsonObject1.getString("refid") + "\n" + jsonObject1.getString("message");
//                        SubmitRechargeAPI(amount, mob_et.getText().toString());
                        showpopupwindow();
                        Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(New_MobileRecharge.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("MemberId", id);
                params.put("MobileNo", mob);
                params.put("CircleCode", circleid);
                params.put("OperatorCode", opcode);
                params.put("Amount", amount);
                return params;
            }};
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(New_MobileRecharge.this);
        requestQueue.add(stringRequest);
    }

    public void GetAppPlanAPI(String op_code) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://amazepay.net/api/Amazepay/OPERATOR_CODE?operatorcode=" + op_code+"&MobileNo="+GetMobile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d("res_p", response);
                try {
                    JSONObject objectlist = new JSONObject(response);
                    JSONObject Responobj = objectlist.getJSONObject("Response");
                    if (Responobj.getString("status").equalsIgnoreCase("true")){
                    JSONObject Respon = Responobj.getJSONObject("data");
                    JSONArray Response1 = Respon.getJSONArray("plans");
                    for (int i = 0; i < Response1.length(); i++) {
//                            title_tv.setText("Passbook History("+Response1.length()+")");
                        JSONObject jsonObject = Response1.getJSONObject(i);
                        HashMap<String, String> hashlist = new HashMap();
                        hashlist.put("planType", jsonObject.getString("planType"));
                        hashlist.put("planCode", jsonObject.getString("planCode"));
                        hashlist.put("amount", jsonObject.getString("amount"));
                        hashlist.put("validity", jsonObject.getString("validity"));
                        hashlist.put("planName", jsonObject.getString("planName"));
                        hashlist.put("planDescription", jsonObject.getString("planDescription"));
                        arrLegalList.add(hashlist);
                    }
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(New_MobileRecharge.this, 1);
                    pdfAdapTer = new LegalListAdapter(New_MobileRecharge.this, arrLegalList);
                    plan_rv.setLayoutManager(layoutManager);
                    plan_rv.setAdapter(pdfAdapTer);
                }else {
                    Toast.makeText(New_MobileRecharge.this, Responobj.getString("message"), Toast.LENGTH_SHORT).show();
                }
            }

            catch (JSONException e) {
                e.printStackTrace();
            }

                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(New_MobileRecharge.this, "Internet connection is slow Or no internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(New_MobileRecharge.this);
        requestQueue.add(stringRequest);

    }

    public class LegalListAdapter extends RecyclerView.Adapter<LegalList> {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        public LegalListAdapter(Context applicationContext, ArrayList<HashMap<String, String>> arrLegalList) {
            data = arrLegalList;
        }

        public LegalList onCreateViewHolder(ViewGroup parent, int viewType) {
            return new LegalList(LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_viewplan, parent, false));
        }

        @SuppressLint("SetTextI18n")
        public void onBindViewHolder(final LegalList holder,  final int position) {
            holder.head_rb.setText(data.get(position).get("planName"));
            holder.des_tv.setText(data.get(position).get("planDescription"));
            holder.tv.setText("Plan Amount:- " + data.get(position).get("amount"));
            holder.tv1.setText("Validity:- " + data.get(position).get("validity"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.head_rb.setChecked(true);
//                    alertDialog1.dismiss();
                    amount_et.setText(data.get(position).get("amount"));
                    plan_dis_tv.setText(data.get(position).get("planDescription"));
                }
            });
        }

        public int getItemCount() {
            return data.size();
        }
    }

    public class LegalList extends RecyclerView.ViewHolder {
        TextView des_tv, tv1, tv;
        RadioButton head_rb;

        public LegalList(View itemView) {
            super(itemView);
            head_rb = itemView.findViewById(R.id.head_rb);
            des_tv = itemView.findViewById(R.id.des_tv);
            tv = itemView.findViewById(R.id.tv);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }

    public void GetOperatorList(String op_code) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://amazepay.net/api/Amazepay/RechargeOperatorList?OperatorName=" + op_code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d("res_p", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("Status").equalsIgnoreCase("false")) {
                        String msg = object.getString("msg");
                        Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                    } else {
                        JSONArray jsonArray = object.getJSONArray("Response");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            StateId.add(jsonObject1.getString("OperatorCode"));
                            String statename = jsonObject1.getString("OperatorName");
                            StateName.add(statename);
                        }
                        sp_state.setAdapter(new ArrayAdapter<String>(New_MobileRecharge.this, android.R.layout.simple_spinner_dropdown_item, StateName));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(New_MobileRecharge.this, "Internet connection is slow Or no internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(New_MobileRecharge.this);
        requestQueue.add(stringRequest);
    }

    public void GetRechargeCircleList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://amazepay.net/api/Amazepay/RechargeCircleList", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d("res_p", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("Status").equalsIgnoreCase("false")) {
                        String msg = object.getString("msg");
                        Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                    } else {
                        JSONArray jsonArray = object.getJSONArray("Response");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            StateId1.add(jsonObject1.getString("CircleId"));
                            String statename1 = jsonObject1.getString("CircleName");
                            StateName1.add(statename1);
                        }
                        sp_state1.setAdapter(new ArrayAdapter<String>(New_MobileRecharge.this, android.R.layout.simple_spinner_dropdown_item, StateName1));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(New_MobileRecharge.this, "Internet connection is slow Or no internet connection", Toast.LENGTH_SHORT).show();
            } });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(New_MobileRecharge.this);
        requestQueue.add(stringRequest);
    }

    public void GetWallet(String amt) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://wellyug.in/webservicenew.asmx/GetBalance", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                Log.d("res_p", response);
                try {
                    JSONObject object = new JSONObject(jsonString);
                    if (object.getString("status").equalsIgnoreCase("false")) {
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONArray jsonArray = object.getJSONArray("Response");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1.getString("Balance1").equalsIgnoreCase("Proceed")) {
                                Payment("RAP01019", mob_et.getText().toString(), stateid1, stateid, amount);
                                Log.d("res_amount","RAP01019"+mob_et.getText().toString()+stateid1+stateid+amount);
                            } else {
                                Toast.makeText(New_MobileRecharge.this, jsonObject1.getString("Balance1"), Toast.LENGTH_SHORT).show();
                            } }}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(New_MobileRecharge.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UserId", UserId);
                params.put("Amount", amt);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(New_MobileRecharge.this);
        requestQueue.add(stringRequest);
    }

    public void SubmitRechargeAPI(String amt, String mob) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://wellyug.in/webservicenew.asmx/SubmitRecharge", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                Log.d("SubmitRecharge", response);
                try {
                    JSONObject object = new JSONObject(jsonString);
                    if (object.getString("status").equalsIgnoreCase("false")) {
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), object.getString("Response"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            } }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(New_MobileRecharge.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            } }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UserId", UserId);
                params.put("Amount", amt);
                params.put("Mobile", mob);
                params.put("optr", "");
                params.put("circle", "");
                return params;
            }};
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(New_MobileRecharge.this);
        requestQueue.add(stringRequest);
    }

}