package ru.kos.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kos.shop.domain.Product;
import ru.kos.shop.reporitory.ProductRepository;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(String name, Double price) {
        Product product = new Product(name, price);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Integer id, String name, Double price) {
        Product product = new Product(id, name, price);
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }

}
