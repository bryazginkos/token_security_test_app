package ru.kos.shop.security;

import java.util.Date;

/**
 * Часть информации, расшифрованная из токена <br/>
 * Created by Константин on 05.04.2016.
 */
public class TokenInfo {

    private Integer userId;
    private Date expireDate;

    /**
     *
     * @param userId ID пользователя
     * @param expireDate Дата, после которой токен признан устаревшим
     */
    public TokenInfo(Integer userId, Date expireDate) {
        this.userId = userId;
        this.expireDate = expireDate;
    }

    /**
     * ID пользователя
     * @return ID пользователя
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Является ли токен устаревшим
     * @return Является ли токен устаревшим
     */
    public boolean isExpired() {
        if (expireDate == null) {
            return false;
        }
        return new Date().after(expireDate);
    }
}
