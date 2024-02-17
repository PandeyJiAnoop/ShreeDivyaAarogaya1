package com.sign.akp_shreedivyaaarogya;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sign.akp_shreedivyaaarogya.ActivationArea.AA_ActivationHistory;
import com.sign.akp_shreedivyaaarogya.ActivationArea.AA_FundHistory;
import com.sign.akp_shreedivyaaarogya.Basic.ApiService;
import com.sign.akp_shreedivyaaarogya.Basic.ConnectToRetrofit;
import com.sign.akp_shreedivyaaarogya.Basic.GlobalAppApis;
import com.sign.akp_shreedivyaaarogya.Basic.RetrofitCallBackListenar;
import com.sign.akp_shreedivyaaarogya.Basic.SplashScreen;
import com.sign.akp_shreedivyaaarogya.IncomeDetails.Income_BusinessProposal;
import com.sign.akp_shreedivyaaarogya.IncomeDetails.Income_MonthlyTeam;
import com.sign.akp_shreedivyaaarogya.MyAccount.ChangePassword;
import com.sign.akp_shreedivyaaarogya.MyAccount.MyProfile;
import com.sign.akp_shreedivyaaarogya.MyNetwork.DownLine;
import com.sign.akp_shreedivyaaarogya.MyNetwork.MyDirect;
import com.sign.akp_shreedivyaaarogya.MyNetwork.MyTree;
import com.sign.akp_shreedivyaaarogya.SlidingMenu.IdActivation;
import com.sign.akp_shreedivyaaarogya.SlidingMenu.InboxActivity;
import com.sign.akp_shreedivyaaarogya.SlidingMenu.LevelTreeWebview;
import com.sign.akp_shreedivyaaarogya.SlidingMenu.OutboxActivity;
import com.sign.akp_shreedivyaaarogya.SlidingMenu.RaiseAddRequest;
import com.sign.akp_shreedivyaaarogya.SlidingMenu.ReferralList;
import com.sign.akp_shreedivyaaarogya.SlidingMenu.Withdraw;
import com.sign.akp_shreedivyaaarogya.rechargebills.DTHBill;
import com.sign.akp_shreedivyaaarogya.rechargebills.ElectricityBill;
import com.sign.akp_shreedivyaaarogya.rechargebills.GasBill;
import com.sign.akp_shreedivyaaarogya.rechargebills.InsuranceBill;
import com.sign.akp_shreedivyaaarogya.rechargebills.InsuranceBillNew;
import com.sign.akp_shreedivyaaarogya.rechargebills.New_MobileRecharge;
import com.sign.akp_shreedivyaaarogya.rechargebills.WithdrawHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Call;

import static com.sign.akp_shreedivyaaarogya.Basic.API_Config.getApiClient_ByPost;

public class DashboardActivity extends AppCompatActivity {
    LinearLayout one, two, three, four, five, six;
    private ImageView sliding_menu;
    private DrawerLayout mDrawer;
    LinearLayout one_ll, ll_two, ll_three, ll_four, ll_five, ll_six;
    int i = 0;
    LinearLayout logout_ll, dash_ll;
    String UserId, UserPass, UserRole, UserName;
    SwipeRefreshLayout srl_refresh;
    LinearLayout mobile_ll,dth_ll,electricity_ll,water_ll,gaspay_ll,insurance_ll;

    TextView das_total_ref_tv,das_active_ref_tv,das_active_team_tv,das_inactive_team_tv,das_business_propasal_tv,das_global_pool_tv,das_monthly_pool_tv,
            das_team_reward_tv,das_royality_proposal_tv,das_today_income_tv,das_topup_tv,das_credit_tv,das_debit_tv,das_balance_tv;

    LinearLayout myprofile_ll,password_ll,idactivation_ll,topuphistory_ll,addfund_ll,addfundhistory_ll,
    business_proposal_ll,monthly_team_ll,mytree_ll,autopool_ll,downline_ll,direct_ll,withdrawlrequest_ll,withdrawl_history_ll;

    LinearLayout outbox_ll,inbox_ll,ticket_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("U_id", "");
        UserPass = sharedPreferences.getString("U_pass", "");
        UserRole = sharedPreferences.getString("U_role", "");
        UserName = sharedPreferences.getString("U_name", "");
        FindId();
        OnClick();

        GetBinddashboardAPI();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        sliding_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//                mDrawer.openDrawer(Gravity.START);
                mDrawer.openDrawer(Gravity.START);
//                close.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDrawer.closeDrawer(Gravity.START);
//                    }
//                });
            } });
    }



    private void OnClick() {
        mobile_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, New_MobileRecharge.class);
                intent.putExtra("onlyservice", "Mobile");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }});
        dth_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DTHBill.class);
                intent.putExtra("onlyservice", "DTH");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }});
        electricity_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ElectricityBill.class);
                intent.putExtra("onlyservice", "ELECTRICITY");
                startActivity(intent);
            }});
        water_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, InsuranceBill.class);
                intent.putExtra("onlyservice", "LIC");
                startActivity(intent);
            }});

        gaspay_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, GasBill.class);
                intent.putExtra("onlyservice", "Gas");
                startActivity(intent);
            }});
        insurance_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Intent intent=new Intent(DashboardActivity.this, InsuranceBillNew.class);
                intent.putExtra("onlyservice", "Insurance");
                startActivity(intent); overridePendingTransition(0, 0);
            }});
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    one_ll.setVisibility(View.GONE);
                    i++;
                } else if (i == 1) {
                    one_ll.setVisibility(View.VISIBLE);
                    i = 0;
                }
            } });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    ll_two.setVisibility(View.GONE);
                    i++;
                } else if (i == 1) {
                    ll_two.setVisibility(View.VISIBLE);
                    i = 0;
                }
            } });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    ll_three.setVisibility(View.GONE);
                    i++;
                } else if (i == 1) {
                    ll_three.setVisibility(View.VISIBLE);
                    i = 0;
                }
            } });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    ll_four.setVisibility(View.GONE);
                    i++;
                } else if (i == 1) {
                    ll_four.setVisibility(View.VISIBLE);
                    i = 0;
                }
            } });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    ll_five.setVisibility(View.GONE);
                    i++;
                } else if (i == 1) {
                    ll_five.setVisibility(View.VISIBLE);
                    i = 0;
                }
            } });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    ll_six.setVisibility(View.GONE);
                    i++;
                } else if (i == 1) {
                    ll_six.setVisibility(View.VISIBLE);
                    i = 0;
                }
            } });
        logout_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this).setTitle("Logout")
                        .setMessage("Are you sure want to Logout").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences myPrefs = getSharedPreferences("login_preference", MODE_PRIVATE);
                                SharedPreferences.Editor editor = myPrefs.edit();
                                editor.clear();
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                                startActivity(intent);
                                Intent i = new Intent();
                                i.putExtra("finish", true);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
                                //startActivity(i);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            } });

        dash_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
            }
        });
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectionHelper.isOnline(DashboardActivity.this)) {
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
                    Toast.makeText(DashboardActivity.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                }
            } });

        myprofile_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), MyProfile.class);
                startActivity(intent);
            }
        });
        password_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent);
            }
        });
        idactivation_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), IdActivation.class);
                startActivity(intent);
            }
        });
        topuphistory_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), ActivateScreen.class);
                startActivity(intent);
            }
        });

        addfund_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), RaiseAddRequest.class);
                startActivity(intent);
            }
        });
        addfundhistory_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), AA_FundHistory.class);
                startActivity(intent);
            }
        });
        business_proposal_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), Income_BusinessProposal.class);
                startActivity(intent);
            }
        });
        monthly_team_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), Income_MonthlyTeam.class);
                startActivity(intent);
            }
        });
        autopool_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), LevelTreeWebview.class);
                startActivity(intent);
            }
        });
        mytree_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), MyTree.class);
                startActivity(intent);
            }
        });
        direct_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), MyDirect.class);
                startActivity(intent);
            }
        });
        downline_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), DownLine.class);
                startActivity(intent);
            }
        });

        withdrawlrequest_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), Withdraw.class);
                startActivity(intent);
            }
        });
        withdrawl_history_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), WithdrawHistory.class);
                startActivity(intent);
            }
        });
        ticket_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), Tickets.class);
                startActivity(intent);
            }
        });

        inbox_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), InboxActivity.class);
                startActivity(intent);
            }
        });

        outbox_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(Gravity.START);
                Intent intent=new Intent(getApplicationContext(), OutboxActivity.class);
                startActivity(intent);
            }
        });


    }

    private void FindId() {
        srl_refresh = findViewById(R.id.srl_refresh);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);

        one_ll = findViewById(R.id.one_ll);
        ll_two = findViewById(R.id.ll_two);
        ll_three = findViewById(R.id.ll_three);
        ll_four = findViewById(R.id.ll_four);
        ll_five = findViewById(R.id.ll_five);
        ll_six = findViewById(R.id.ll_six);
        logout_ll = findViewById(R.id.logout_ll);
        myprofile_ll = findViewById(R.id.myprofile_ll);
        password_ll = findViewById(R.id.password_ll);
        dash_ll = findViewById(R.id.dash_ll);

        sliding_menu = (ImageView) findViewById(R.id.sliding_menu);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mobile_ll=findViewById(R.id.mobile_ll);
        dth_ll=findViewById(R.id.dth_ll);
        electricity_ll=findViewById(R.id.electricity_ll);
        water_ll=findViewById(R.id.water_ll);
        gaspay_ll=findViewById(R.id.gaspay_ll);
        insurance_ll=findViewById(R.id.insurance_ll);

        das_total_ref_tv=findViewById(R.id.das_total_ref_tv);
        das_active_ref_tv=findViewById(R.id.das_active_ref_tv);
        das_active_team_tv=findViewById(R.id.das_active_team_tv);
        das_inactive_team_tv=findViewById(R.id.das_inactive_team_tv);
        das_business_propasal_tv=findViewById(R.id.das_business_propasal_tv);
        das_global_pool_tv=findViewById(R.id.das_global_pool_tv);
        das_monthly_pool_tv=findViewById(R.id.das_monthly_pool_tv);
        das_team_reward_tv=findViewById(R.id.das_team_reward_tv);
        das_royality_proposal_tv=findViewById(R.id.das_royality_proposal_tv);
        das_today_income_tv=findViewById(R.id.das_today_income_tv);
        das_topup_tv=findViewById(R.id.das_topup_tv);
        das_credit_tv=findViewById(R.id.das_credit_tv);
        das_debit_tv=findViewById(R.id.das_debit_tv);
        das_balance_tv=findViewById(R.id.das_balance_tv);

        idactivation_ll=findViewById(R.id.idactivation_ll);
        topuphistory_ll=findViewById(R.id.topuphistory_ll);
        addfund_ll=findViewById(R.id.addfund_ll);
        addfundhistory_ll=findViewById(R.id.addfundhistory_ll);
        business_proposal_ll=findViewById(R.id.business_proposal_ll);
        monthly_team_ll=findViewById(R.id.monthly_team_ll);
        mytree_ll=findViewById(R.id.mytree_ll);
        autopool_ll=findViewById(R.id.autopool_ll);
        downline_ll=findViewById(R.id.downline_ll);
        direct_ll=findViewById(R.id.direct_ll);
        withdrawlrequest_ll=findViewById(R.id.withdrawlrequest_ll);
        withdrawl_history_ll=findViewById(R.id.withdrawl_history_ll);
        ticket_ll=findViewById(R.id.ticket_ll);
        inbox_ll=findViewById(R.id.inbox_ll);
        outbox_ll=findViewById(R.id.outbox_ll);

       TextView marqueeText=findViewById(R.id.marqueeText);
       marqueeText.setSelected(true);


    }

    private void GetBinddashboardAPI() {
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
                            das_total_ref_tv.setText("Total\nReferral\n"+jsonobject.getString("DirectActive"));
                            das_active_ref_tv.setText("Active\nReferral\n"+jsonobject.getString("ActiveTeam"));
                            das_active_team_tv.setText("Active\nTeam\n"+jsonobject.getString("ActiveTeam1"));
                            das_inactive_team_tv.setText("In-Active\nTeam\n"+jsonobject.getString("InactiveTeam1"));
                            das_business_propasal_tv.setText("Business\nProposal\n\u20B9 "+jsonobject.getString("BusinessprosalIncome"));
                            das_global_pool_tv.setText("Global\nPool\n\u20B9 "+jsonobject.getString("GlobalPoolIncome"));
                            das_monthly_pool_tv.setText("Monthly Team\nGrowth\n\u20B9 "+jsonobject.getString("monthlyTeamGrowthIncome"));
                            das_team_reward_tv.setText("Team\nReward\n\u20B9 "+jsonobject.getString("RewardIncome"));
                            das_royality_proposal_tv.setText("Royalty\nProposal\n\u20B9 "+jsonobject.getString("MonthlyRoyalityIncome"));
                            das_today_income_tv.setText("Today\nIncome\n\u20B9 "+jsonobject.getString("TodayIncome"));
                            das_topup_tv.setText("TopUp\n\u20B9 "+jsonobject.getString("tot_topup"));
                            das_credit_tv.setText("Credit\n\u20B9 "+jsonobject.getString("Credit"));
                            das_debit_tv.setText("Debit\n\u20B9 "+jsonobject.getString("Debit"));
                            das_balance_tv.setText("Balance\n\u20B9 "+jsonobject.getString("Avail_Balance"));
                        } }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                        builder.setTitle("Message!").setMessage(object.getString("Message")).setCancelable(false).setIcon(R.drawable.appicon)
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();}}); builder.create().show();
                    } } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Data Not Found!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } } }, DashboardActivity.this, call1, "", true);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bnav_Home:
                    Intent intents1 = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intents1);
                    return true;
                case R.id.bnav_addfund:
                    Intent intent = new Intent(getApplicationContext(), RaiseAddRequest.class);
                    startActivity(intent);
                    return true;
                case R.id.bnav_account:
                    Intent intent1 = new Intent(getApplicationContext(), MyProfile.class);
                    startActivity(intent1);
                    return true;
                case R.id.bnav_help:
                    Intent intents = new Intent(getApplicationContext(), Tickets.class);
                    startActivity(intents);
                    return true;
            }
            return false;
        }
    };

}