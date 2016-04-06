package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kos.shop.domain.Product;
import ru.kos.shop.service.ProductService;


/**
 * Created by brjazgin on 06.04.2016.
 */
@RestController
@RequestMapping(UrlList.PREFIX)
public class AdminController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "price") Double price) {
        return productService.createProduct(name, price);
    }

    public Product updateProduct(@RequestParam(name = "id") Integer id,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "price") Double price) {
        return productService.updateProduct(id, name, price);
    }
}
