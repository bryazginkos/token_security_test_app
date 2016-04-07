package ru.kos.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kos.shop.domain.Basket;
import ru.kos.shop.domain.Product;
import ru.kos.shop.reporitory.BasketRepository;

import java.util.Date;
import java.util.List;

/**
 * Сервис для работы с корзинами <br/>
 * Created by brjazgin on 06.04.2016.
 */
@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketHolder basketHolder;

    @Autowired
    private ProductService productService;

    /**
     * Сохранить текущую корзину из {@link BasketHolder}  <br/>
     * В качестве даты заказа проставляется текущая.
     * Перед сохранениям все существующие в корзине продукты проверяются на существование. Несуществующие игнорируются.
     * За время нахождения продукта в корзине продукт мог быть модифицирован! <br/>
     * После оформления текущая корзина очищается
     * @param customerPhone телефон клиента.
     * @return Сохраненную корзину
     */
    @Transactional
    public Basket saveBasket(String customerPhone) {
        List<Integer> productIds = basketHolder.getProductIds();
        List<Product> actualProducts = productService.findProductsByIdList(productIds);
        Basket basket = new Basket();
        basket.setCustomerPhone(customerPhone);
        basket.setOrderDate(new Date());
        basket.setProducts(actualProducts.toString());
        basketHolder.clear();
        basketRepository.save(basket);
        return basket;
    }

    /**
     * Получить список корзин, проданных за промежуток
     * @param begin начало
     * @param end конец
     * @return Список корзин
     */
    public List<Basket> getOrderBaskets(Date begin, Date end) {
        return basketRepository.findByDatesBetween(begin, end);
    }

    /**
     * Положить в текущую корзину продукт
     * @param productId id продукта
     * @return Продукт, если он существуют в базе, null иначе
     */
    public Product putInBasket(Integer productId) {
        Product product = productService.findById(productId);
        if (product != null) {
            basketHolder.addProduct(product);
        }
        return product;
    }
}
