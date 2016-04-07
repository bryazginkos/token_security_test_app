package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kos.shop.domain.User;
import ru.kos.shop.dto.AuthParams;
import ru.kos.shop.security.AuthorizedUsersStorage;
import ru.kos.shop.security.Roles;
import ru.kos.shop.security.TokenService;
import ru.kos.shop.service.UserService;

import java.util.Arrays;

/**
 * Created by Константин on 05.04.2016.
 */
@RestController
@RequestMapping(UrlList.PREFIX)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizedUsersStorage usersStorage;

    @Autowired
    private TokenService tokenService;

    /**
     * Получение токена
     * @param authParams логин и пароль пользователя
     * @return
     */
    @RequestMapping(value = UrlList.TOKEN, method = RequestMethod.POST)
    public ResponseEntity<String> getToken(@RequestBody AuthParams authParams) {
        User user = userService.findUser(authParams.getLogin(), authParams.getPassword());
        if (user != null) {
            usersStorage.put(user.getId(), user);
            return new ResponseEntity<>(tokenService.createJWT(user.getId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((String)null, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Метод не из ТЗ. (Для тестирования) Регистрирует администратора-пользователя
     * @param authParams логин и пароль нового пользователя
     * @return Пользователь
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody AuthParams authParams) {
        return userService.registerUser(authParams.getLogin(), authParams.getPassword(), Arrays.asList(Roles.ROLE_ADMIN));
    }
}
