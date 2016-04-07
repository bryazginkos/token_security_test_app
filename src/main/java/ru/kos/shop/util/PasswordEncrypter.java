package ru.kos.shop.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Шифровщик паролей <br/>
 * Created by Константин on 05.04.2016.
 */
@Component
public final class PasswordEncrypter {

    @Value("${authentication.password.salt}")
    private String salt;

    /**
     * Зашифровать пароль
     * @param pass пароль
     * @return Шифрованный пароль
     */
    @NotNull
    public String encrypt(@NotNull String pass){
        //утверждается, что это потокобезопасный метод
        return BCrypt.hashpw(pass, salt);
    }

}
