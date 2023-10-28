package com.edu.ishop.client.services;


import com.edu.ishop.helpers.entity.RefreshToken;

public class TokenResponse {
    private String token;
    private RefreshToken refreshToken;

    public TokenResponse(String token, RefreshToken refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }
}
