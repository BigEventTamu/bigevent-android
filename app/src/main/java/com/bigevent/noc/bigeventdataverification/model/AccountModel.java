package com.bigevent.noc.bigeventdataverification.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Noc on 11/2/2015.
 */
public class AccountModel {

    @Expose
    @SerializedName("username")
    String username;

    @Expose(deserialize = false)
    @SerializedName("password")
    String password;

    @Expose
    String tokenauth;


    public String getUsername(){
        return username;
    }
    void setUsername(String _username){
        username = _username;
    }

    String getPassword(){
        return password;
    }

    void setPassword(String _password){
        password = _password;
    }

    String getTokenauth(){
        return tokenauth;
    }

    void setTokenauth(String _tokenauth){
        tokenauth = _tokenauth;
    }

    String basicLogin(){
        return tokenauth;
    }
}
