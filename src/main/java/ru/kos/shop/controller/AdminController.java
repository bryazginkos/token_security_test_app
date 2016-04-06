package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kos.shop.domain.Basket;
import ru.kos.shop.domain.Product;
import ru.kos.shop.service.BasketService;
import ru.kos.shop.service.ProductService;

import java.util.Date;
import java.util.List;


/**
 * Created by brjazgin on 06.04.2016.
 */
@RestController
@RequestMapping(UrlList.PREFIX)
public class AdminController {

    private static final String PRODUCT_ID_PATH_VARIABLE = "productId";

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = UrlList.PRODUCTS + "/{" + PRODUCT_ID_PATH_VARIABLE + "}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable(value= PRODUCT_ID_PATH_VARIABLE) Integer id,
                                 @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = UrlList.ORDERS, method = RequestMethod.GET)
    public List<Basket> getOrders(@RequestParam(value = "begin") Date begin,
                                  @RequestParam(value = "end") Date end) {
        return basketService.getOrderBaskets(begin, end);
    }
}
