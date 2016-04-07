package ru.kos.shop.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.kos.shop.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Component
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class BasketHolder {

    private List<Integer> productIds;

    public BasketHolder() {
        productIds = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product.getId() != null) {
            productIds.add(product.getId());
        }
    }

    public void clear() {
        productIds = new ArrayList<>();
    }

    public List<Integer> getProductIds() {
        return productIds;
    }
}
