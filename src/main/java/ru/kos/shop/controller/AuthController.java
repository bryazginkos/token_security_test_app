package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizedUsersStorage usersStorage;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/token")
    public String getToken(@RequestParam(value = "user") String userName, @RequestParam(value = "pass") String password) {
        User user = userService.findUser(userName, password);
        if (user != null) {
            usersStorage.put(user.getId(), user);
            return tokenService.createJWT(user.getId());
        } else {
            throw new BadCredentialsException("User not found");
        }
    }




}
