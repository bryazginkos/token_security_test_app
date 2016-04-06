package ru.kos.shop.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.kos.shop.domain.Basket;
import ru.kos.shop.domain.Product;

import java.util.ArrayList;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Component
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class BasketHolder {

    private Basket basket;

    public BasketHolder() {
        basket = new Basket();
    }

    public void addProduct(Product product) {
        if (basket.getProductList() == null) {
            basket.setProductList(new ArrayList<>());
        }
        basket.getProductList().add(product);
    }

    public void clear() {
        basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }
}
