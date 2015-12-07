package com.bigevent.noc.bigeventdataverification.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Noc on 11/2/2015.
 */
public class HttpTokenModel {
    @SerializedName("Authorization")
    String authToken;

    void setAuthToken(String _authToken){
        authToken = _authToken;
    }

    String getAuthToken(){
        return authToken;
    }
}
