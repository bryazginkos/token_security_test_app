package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kos.shop.domain.User;
import ru.kos.shop.security.AuthorizedUsersStorage;
import ru.kos.shop.security.TokenService;
import ru.kos.shop.service.UserService;

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

    @RequestMapping(value = "/token")
    public ResponseEntity<String> getToken(@RequestParam(value = "user") String userName, @RequestParam(value = "pass") String password) {
        User user = userService.findUser(userName, password);
        if (user != null) {
            usersStorage.put(user.getId(), user);
            return new ResponseEntity<>(tokenService.createJWT(user.getId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((String)null, HttpStatus.UNAUTHORIZED);
        }
    }
}
