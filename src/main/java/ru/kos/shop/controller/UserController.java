package ru.kos.shop.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.kos.shop.domain.Basket;
import ru.kos.shop.domain.Product;
import ru.kos.shop.service.BasketService;
import ru.kos.shop.service.ProductService;

import java.util.List;

/**
 * Created by Константин on 04.04.2016.
 */
@RestController
@RequestMapping(UrlList.PREFIX)
public class UserController {

    private static final String PRODUCT_ID_PATH_VARIABLE = "productId";

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    @RequestMapping(value = UrlList.PRODUCTS, method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return Lists.newArrayList(productService.findAll());
    }

    @RequestMapping(value = UrlList.TO_BASKET + "/{" + PRODUCT_ID_PATH_VARIABLE + "}", method  = RequestMethod.POST)
    public Product addProduct(@PathVariable(value= PRODUCT_ID_PATH_VARIABLE) Integer productId) {
        return basketService.putInBasket(productId);
    }

    @RequestMapping(value = UrlList.ORDERS, method = RequestMethod.POST)
    public Basket saveBasket(@RequestBody String customerPhone) {
        return basketService.saveBasket(customerPhone);
    }


}
