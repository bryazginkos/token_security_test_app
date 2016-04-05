package ru.kos.shop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Created by Константин on 05.04.2016.
 */
@Component
public final class PasswordEncrypter {

    @Value("${authentication.password.salt}")
    private String salt;

    public String encrypt(String pass){
        return BCrypt.hashpw(pass, salt);
    }

}
