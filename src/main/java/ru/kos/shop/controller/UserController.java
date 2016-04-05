package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kos.shop.domain.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kos.shop.domain.User;
import ru.kos.shop.security.Roles;
import ru.kos.shop.service.UserService;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Константин on 04.04.2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        Product product1 = new Product(1, "TV", 12000d);
        Product product2 = new Product(2, "PC", 14000d);
        Product product3 = new Product(3, "Laptop", 42000d);
        return Arrays.asList(product1, product2, product3);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/safe")
    public List<Product> getSafeProducts() {
        Product product1 = new Product(4, "TV", 12000d);
        Product product2 = new Product(5, "PC", 14000d);
        Product product3 = new Product(6, "Laptop", 42000d);
        return Arrays.asList(product1, product2, product3);
    }

    public Iterable<User> findAll() {
        return userService.findAll();
    }


    @RequestMapping("/register")
    public User registerAdmin(@RequestParam(value = "name") String userName,
                              @RequestParam(value = "pass")String password) {
        return userService.registerUser(userName, password, Arrays.asList(Roles.ROLE_ADMIN));
    }

    @RequestMapping("/find")
    public User findUser(@RequestParam(value = "name") String userName,
                              @RequestParam(value = "pass")String password) {
        return userService.findUser(userName, password);
    }


}
