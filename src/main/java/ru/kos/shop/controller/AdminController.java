package ru.kos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.kos.shop.domain.Basket;
import ru.kos.shop.domain.Product;
import ru.kos.shop.security.Roles;
import ru.kos.shop.service.BasketService;
import ru.kos.shop.service.ProductService;

import java.util.Date;
import java.util.List;


/**
 * Контроллер для запросов администратора <br/>
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

    @Secured(Roles.ROLE_ADMIN)
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @Secured(Roles.ROLE_ADMIN)
    @RequestMapping(value = UrlList.PRODUCTS + "/{" + PRODUCT_ID_PATH_VARIABLE + "}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable(value= PRODUCT_ID_PATH_VARIABLE) Integer id,
                                 @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @Secured(Roles.ROLE_ADMIN)
    @RequestMapping(value = UrlList.ORDERS, method = RequestMethod.GET)
    public List<Basket> getOrders(@RequestParam(value = "begin") @DateTimeFormat(pattern="dd-MM-yyyy") Date begin,
                                  @RequestParam(value = "end") @DateTimeFormat(pattern="dd-MM-yyyy")  Date end) {
        return basketService.getOrderBaskets(begin, end);
    }

    @Secured(Roles.ROLE_ADMIN)
    @RequestMapping(value = UrlList.PRODUCTS + "/{" + PRODUCT_ID_PATH_VARIABLE + "}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(value= PRODUCT_ID_PATH_VARIABLE) Integer id) {
         productService.deleteProduct(id);
    }
}
