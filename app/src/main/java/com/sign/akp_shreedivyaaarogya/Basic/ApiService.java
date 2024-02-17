package com.sign.akp_shreedivyaaarogya.Basic;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<String> getLogin(
            @Body String body);
    @Headers("Content-Type: application/json")
    @POST("BindDashboard")
    Call<String> getBindDashboard(
            @Body String body);
    @Headers("Content-Type: application/json")
    @POST("UserRegistration")
    Call<String> getNewAccount(
            @Body String body);
    @Headers("Content-Type: application/json")
    @POST("CheckSponsor")
    Call<String> get_CheckSponsor(
            @Body String body);
    @Headers("Content-Type: application/json")
    @POST("ProfileUpdate")
    Call<String> getProfileUpdate(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("ChangePassword")
    Call<String> getChangePassword(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("ProfileView")
    Call<String> getProfileView(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("TopUpHistory")
    Call<String> getTopupDetails(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("BusinessProposal")
    Call<String> api_BusinessProposal(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("GetWallet")
    Call<String> getWallet(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("WithdrawalReport")
    Call<String> getWithdrawalReport(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("MyRefferal")
    Call<String> getReferral(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("MyTeam")
    Call<String> getMyTeam(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("DirectIncome")
    Call<String> getDirectincome(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("LevelIncome")
    Call<String> getLevelincome(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("ContractIncome")
    Call<String> getContractincome(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("OpenWithdrawal")
    Call<String> getwithdrwar(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("WithdrawalHistory")
    Call<String> getWalletHistory(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("IDActivation")
    Call<String> getAccountActivation(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("FundRequest")
    Call<String> getFundRequest(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("FundRequestReport")
    Call<String> getFundRequestReport(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("PerformanceIncome")
    Call<String> getPerformanceIncome(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("ClubLeaderIncomeReport")
    Call<String> getClubLeaderIncomeReport(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("RewardIncome")
    Call<String> getRewardIncome(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("GetCustomerInboxDetails")
    Call<String> GetCustomerInboxDetails(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("GetCustomerOutboxDetails")
    Call<String> GetCustomerOutboxDetails(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("MyDirectTeamReport")
    Call<String> getMyDirect(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("MyDownlineReport")
    Call<String> getMyDownlineReport(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("TransferHistory")
    Call<String> getTransferHistory(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("MainWallet")
    Call<String> getMainWallet(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("P2PTransfer")
    Call<String> getP2PTransfer(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("TicketGeneration")
    Call<String> getTicketGeneration(
            @Body String body);
    @Headers("Content-Type: application/json")
    @POST("WithdrawalReq")
    Call<String> getWithdrawlRequest(
            @Body String body);


    @Headers("Content-Type: application/json")
    @GET("GetCountryList")
    Call<String> API_GetCountryList();

    @Headers("Content-Type: application/json")
    @GET("PackageList")
    Call<String> API_GetPackageList();

    @Headers("Content-Type: application/json")
    @POST("GetPrinciplePackageList")
    Call<String> API_GetPrinciplePackageList(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("PrincipalWithdrawlRequest")
    Call<String> API_PrincipalWithdrawlRequest(
            @Body String body);

}