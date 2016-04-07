package ru.kos.shop.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.kos.shop.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранит id продуктов, помещенных в корзину <br/>
 * Created by brjazgin on 06.04.2016.
 */
@Component
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class BasketHolder {

    private List<Integer> productIds;

    public BasketHolder() {
        productIds = new ArrayList<>();
    }

    /**
     * Добавить продукт. Продукт не проверяется на существование в базе. Просто добавляется id.
     * @param product продукт для добавления
     */
    public void addProduct(@NotNull Product product) {
        if (product.getId() != null) {
            productIds.add(product.getId());
        }
    }

    /**
     * Очистить корзину
     */
    public void clear() {
        productIds = new ArrayList<>();
    }


    /**
     * Вернуть id продуктов
     * @return List of id продуктов
     */
    @NotNull
    public List<Integer> getProductIds() {
        return productIds;
    }
}
