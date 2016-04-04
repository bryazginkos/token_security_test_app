package ru.kos.shop.controller;

import ru.kos.shop.domain.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Константин on 04.04.2016.
 */
@RestController
public class UserController {

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        Product product1 = new Product(1, "TV", 12000d);
        Product product2 = new Product(2, "PC", 14000d);
        Product product3 = new Product(3, "Laptop", 42000d);
        return Arrays.asList(product1, product2, product3);
    }
}
