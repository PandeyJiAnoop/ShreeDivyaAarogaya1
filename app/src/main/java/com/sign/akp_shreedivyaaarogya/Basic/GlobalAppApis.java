package com.sign.akp_shreedivyaaarogya.Basic;

import org.json.JSONException;
import org.json.JSONObject;

public class GlobalAppApis {
    public String Login(String UserName, String Password, String TokenNo) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserName", UserName);
            jsonObject1.put("Password", Password);
            jsonObject1.put("TokenNo", TokenNo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String BindDashboard(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("PackageId", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String NewAccount(String UserName, String SponsorId, String Name,String EmailAddress,String ContactNo,
                             String DOB,String Pincode,String State,String District,String HouseNumber,String ApartmentName,
                             String Landmark,String Address,String Gender,String Password) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserName", UserName);
            jsonObject1.put("SponsorId", SponsorId);
            jsonObject1.put("Name", Name);
            jsonObject1.put("EmailAddress", EmailAddress);
            jsonObject1.put("ContactNo", ContactNo);
            jsonObject1.put("DOB", DOB);
            jsonObject1.put("Pincode", Pincode);
            jsonObject1.put("State", State);
            jsonObject1.put("District", District);
            jsonObject1.put("HouseNumber", HouseNumber);
            jsonObject1.put("ApartmentName", ApartmentName);
            jsonObject1.put("Landmark", Landmark);
            jsonObject1.put("Address", Address);
            jsonObject1.put("Gender", Gender);
            jsonObject1.put("Password", Password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String CheckSponsor(String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String UpdateProfile(String UserId, String Name, String EmailId,String Mobile,String State,String Pincode,
                                String BankName,String BankBranch,String IfscCode,String AccountType) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("Name", Name);
            jsonObject1.put("EmailId", EmailId);
            jsonObject1.put("Mobile", Mobile);
            jsonObject1.put("State", State);
            jsonObject1.put("Pincode", Pincode);
            jsonObject1.put("BankName", BankName);
            jsonObject1.put("BankBranch", BankBranch);
            jsonObject1.put("IfscCode", IfscCode);
            jsonObject1.put("AccountType", AccountType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String ChangePassword(String NewPassword,
                                String OldPassword,String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("NewPass", NewPassword);
            jsonObject1.put("OldPass", OldPassword);
            jsonObject1.put("UserId", UserId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }







    public String Profile(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String Wallet(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String TopupDetails(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String parm_BusinessProposal(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

//    public String parm_BusinessProposal(String UserId) {
//        JSONObject jsonObject1 = new JSONObject();
//        try {
//            jsonObject1.put("UserId", UserId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return jsonObject1.toString();
//    }

    public String MyReferral(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String MyTeam(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }



    public String DirectIncomeAPI(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String LevelIncomeAPI(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }



    public String ContractIncomeAPI(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String ShowWithdrwarAPI(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String WalletHistoryAPI(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String AccountActivationAPI(String UserId,String PackageId,String PayMode,String ActivationId,
                                       String SizeId,String Walletpwd,String Packtype) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("PackageId", PackageId);
            jsonObject1.put("PayMode", PayMode);
            jsonObject1.put("ActivationId", ActivationId);
            jsonObject1.put("SizeId", SizeId);
            jsonObject1.put("Walletpwd", Walletpwd);
            jsonObject1.put("Packtype", Packtype);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String FundRequestAPI(String UserType,String Type,String Amount,String RetailerCode,String ReceiptFile) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserType", UserType);
            jsonObject1.put("Type", Type);
            jsonObject1.put("Amount", Amount);
            jsonObject1.put("RetailerCode", RetailerCode);
            jsonObject1.put("ReceiptFile", ReceiptFile);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String GetFundHistory(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String GetPerformanceIncome(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String GetClubLeaderIncomeReport(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String RewardIncome(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String Inbox(String CustomerId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("CustomerId", CustomerId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String Outbox(String CustomerId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("CustomerId", CustomerId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String MyDirect(String UserId,String StartDate,String EndDate,String status) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("StartDate", StartDate);
            jsonObject1.put("EndDate", EndDate);
            jsonObject1.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String TransferHistory(String action,String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }





    public String MainWallet(String CashWallet,String UserId,String WalletBalance) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", "");
            jsonObject1.put("CashWallet", CashWallet);
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("WalletBalance", WalletBalance);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String P2PTransfer(String Amount,String ReceiverId,String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", "");
            jsonObject1.put("Amount", Amount);
            jsonObject1.put("ReceiverId", ReceiverId);
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String TicketGeneration(String Message,String Subject,String CustomerCode) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Message", Message);
            jsonObject1.put("Subject", Subject);
            jsonObject1.put("CustomerCode", CustomerCode);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String WithdrawlRequest(String CutomerId,String MemberName,String Amount) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("CutomerId", CutomerId);
            jsonObject1.put("MemberName", MemberName);
            jsonObject1.put("Amount", Amount);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String WithdrawlRequestReport(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("StartDate", "");
            jsonObject1.put("EndDate", "");
            jsonObject1.put("status", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String GetPrinciplePackageList(String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Userid", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String PrincipalWithdrawlRequestAPI(String Balance,String PacakgeId,String RequestWalletAmt,String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", "");
            jsonObject1.put("Balance", Balance);
            jsonObject1.put("PacakgeId", PacakgeId);
            jsonObject1.put("RequestWalletAmt", RequestWalletAmt);
            jsonObject1.put("UserId", UserId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
}

