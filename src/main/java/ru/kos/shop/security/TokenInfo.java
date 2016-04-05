package ru.kos.shop.security;

import java.util.Date;

/**
 * Created by Константин on 05.04.2016.
 */
public class TokenInfo {

    private Integer userId;
    private Date expireDate;

    public TokenInfo(Integer userId, Date expireDate) {
        this.userId = userId;
        this.expireDate = expireDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public boolean isExpired() {
        if (expireDate == null) {
            return false;
        }
        return new Date().after(expireDate);
    }
}
