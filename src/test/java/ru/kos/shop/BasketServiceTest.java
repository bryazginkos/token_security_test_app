package ru.kos.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import ru.kos.shop.domain.Basket;
import ru.kos.shop.domain.Product;
import ru.kos.shop.service.BasketService;
import ru.kos.shop.service.ProductService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Константин on 06.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { TestConfig.class} )
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class BasketServiceTest {

    public static final String PRODUCT_1 = "product1";
    public static final String PRODUCT_2 = "product2";
    public static final double PRICE_1 = 12.11;
    public static final double PRICE_2 = 12.12;
    public static final String CUSTOMER_PHONE = "+7 999 233 23 23";

    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductService productService;


    @Test
    public void testSaveBasket() {
        //добавляем продукты в базу
        List<Product> products = addProductsToDatabase();

        //добавляем продукты в корзину
        basketService.putInBasket(products.get(0).getId());
        basketService.putInBasket(products.get(1).getId());

        //сохраняем корзину
        Basket savedBasket = basketService.saveBasket(CUSTOMER_PHONE);

        Date orderDate = savedBasket.getOrderDate();
        Date future = new Date(orderDate.getTime() + 1000);
        Date past = new Date(orderDate.getTime() - 1000);

        //поиск корзины по дате
        List<Basket> orderBaskets = basketService.getOrderBaskets(past, future);
        assertTrue(orderBaskets.size() == 1);

        Basket foundBasket = orderBaskets.get(0);

        assertEquals(CUSTOMER_PHONE, foundBasket.getCustomerPhone());
        assertEquals(orderDate, foundBasket.getOrderDate());
        assertEquals(products.toString(), foundBasket.getProducts());
    }

    private List<Product> addProductsToDatabase() {
        Product product1 = productService.createProduct(new Product(PRODUCT_1, PRICE_1));
        Product product2 = productService.createProduct(new Product(PRODUCT_2, PRICE_2));
        return Arrays.asList(product1, product2);
    }
}
