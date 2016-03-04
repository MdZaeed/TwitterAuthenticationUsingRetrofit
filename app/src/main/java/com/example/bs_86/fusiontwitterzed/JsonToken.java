package com.example.bs_86.fusiontwitterzed;

/**
 * Created by BS-86 on 1/19/2016.
 */
public class JsonToken {
    String token_type;
    String access_token;

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public String getAccessToken() {
        return access_token;
    }
    
    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }
}
