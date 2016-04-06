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
import ru.kos.shop.service.BasketHolder;
import ru.kos.shop.service.BasketService;
import ru.kos.shop.service.ProductService;

import javax.transaction.Transactional;
import java.time.*;
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
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class BasketServiceTest extends AbstractSessionTest {

    public static final String PRODUCT_1 = "product1";
    public static final String PRODUCT_2 = "product2";
    public static final double PRICE_1 = 12.11;
    public static final double PRICE_2 = 12.12;
    public static final String CUSTOMER_PHONE = "+7 999 233 23 23";

    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketHolder basketHolder;

    @Test
    public void testSaveBasket() {
        LocalDateTime basketDateTime = LocalDateTime.of(2010, Month.FEBRUARY, 6, 0, 0);
        LocalDateTime beforeBasketDateTime = LocalDateTime.of(2008, Month.FEBRUARY, 6, 0, 0);
        LocalDateTime afterBasketDateTime = LocalDateTime.of(2012, Month.FEBRUARY, 6, 0, 0);

        List<Product> products = addProductsToDatabase();
        products.forEach(basketHolder::addProduct);

        basketService.saveBasket(CUSTOMER_PHONE, convert(basketDateTime));

        List<Basket> orderBaskets = basketService.getOrderBaskets(convert(beforeBasketDateTime), convert(afterBasketDateTime));
        assertTrue(orderBaskets.size() == 1);

        Basket foundBasket = orderBaskets.get(0);
        assertEquals(CUSTOMER_PHONE, foundBasket.getCustomerPhone());
        assertEquals(convert(basketDateTime), foundBasket.getOrderDate());
        assertEquals(products, foundBasket.getProductList());
    }

    private List<Product> addProductsToDatabase() {
        Product product1 = productService.createProduct(PRODUCT_1, PRICE_1);
        Product product2 = productService.createProduct(PRODUCT_2, PRICE_2);
        return Arrays.asList(product1, product2);
    }

    private Date convert(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
