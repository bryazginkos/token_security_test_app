package ru.kos.shop;

import com.google.common.collect.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import ru.kos.shop.domain.Product;
import ru.kos.shop.service.ProductService;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by brjazgin on 06.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { TestConfig.class} )
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class ProductServiceTest {

    public static final String PRODUCT_1 = "product1";
    public static final Double PRICE_1 = 19.34;
    public static final String PRODUCT_2 = "product2";
    public static final Double PRICE_2 = 19.35;
    @Autowired
    private ProductService productService;

    @Test
    public void testFindAllWhenNoProducts() {
        Iterable<Product> all = productService.findAll();
        assertTrue(Iterables.isEmpty(all));
    }

    @Test
    public void testAddProduct() {
        Product product = productService.createProduct(PRODUCT_1, PRICE_1);
        Iterable<Product> all = productService.findAll();
        assertTrue(Iterables.contains(all, product));
        assertTrue(Iterables.size(all) == 1);
    }

    @Test
    public void testAddTwo0Products() {
        Product product1 = productService.createProduct(PRODUCT_1, PRICE_1);
        Product product2 = productService.createProduct(PRODUCT_2, PRICE_2);
        Iterable<Product> all = productService.findAll();
        assertTrue(Iterables.contains(all, product1));
        assertTrue(Iterables.contains(all, product2));
        assertTrue(Iterables.size(all) == 2);
    }

    @Test
    public void testFindById() {
        Product product1 = productService.createProduct(PRODUCT_1, PRICE_1);
        productService.createProduct(PRODUCT_2, PRICE_2);

        Product foundProduct = productService.findById(product1.getId());

        assertTrue(foundProduct == product1);
    }

    @Test
    public void testDeleteProduct() {
        Product product1 = productService.createProduct(PRODUCT_1, PRICE_1);
        Product product2 = productService.createProduct(PRODUCT_2, PRICE_2);

        productService.deleteProduct(product2.getId());

        Iterable<Product> all = productService.findAll();

        assertTrue(Iterables.contains(all, product1));
        assertFalse(Iterables.contains(all, product2));
    }

    @Test
    public void testUpdateProduct() {
        Product product1 = productService.createProduct(PRODUCT_1, PRICE_1);

        Product product2 = productService.updateProduct(product1.getId(), PRODUCT_2, PRICE_2);

        Product foundProduct = productService.findById(product1.getId());

        assertTrue(foundProduct == product2);
        assertEquals(PRODUCT_2, foundProduct.getName());
        assertEquals(product1.getId(),  foundProduct.getId());
        assertEquals(PRICE_2, foundProduct.getPrice());

    }
}
