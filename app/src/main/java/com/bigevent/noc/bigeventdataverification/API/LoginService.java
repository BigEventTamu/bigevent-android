package com.bigevent.noc.bigeventdataverification.API;
import com.bigevent.noc.bigeventdataverification.model.TokenModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Noc on 11/2/2015.
 */
public interface LoginService {

    @GET("/get-token/") // This calls the function get token on the API. CSRF
    Call<TokenModel> getToken();

    // This does a post using authentication - provides in the body username admin password admin
    //x-www-form-urlencoded
    //https://steven.tamu.edu/be/json/get-token/
    //Gets http token
    @FormUrlEncoded
    @POST("/get-token/")
    Call<TokenModel> getHttpToken(@Body String username, @Body String password); //Change this

    //https://steven.tamu.edu/be/json/formtypes/
    @GET("/formtypes/")
    Call<TokenModel> getFormTypes(); // Change this

}