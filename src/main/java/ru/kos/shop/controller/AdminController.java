package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kos.shop.domain.Product;
import ru.kos.shop.service.ProductService;


/**
 * Created by brjazgin on 06.04.2016.
 */
@RestController
@RequestMapping(UrlList.PREFIX)
public class AdminController {

    private static final String PRODUCT_ID_PATH_VARIBALE = "productId";

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @RequestMapping(value = UrlList.PRODUCTS + "/{" + PRODUCT_ID_PATH_VARIBALE + "}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable(value= PRODUCT_ID_PATH_VARIBALE) Integer id,
                                 @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
