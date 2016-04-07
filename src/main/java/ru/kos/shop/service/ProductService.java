package ru.kos.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kos.shop.domain.Product;
import ru.kos.shop.reporitory.ProductRepository;

import java.util.List;

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
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Integer id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> findProductsByIdList(List<Integer> ids) {
        return productRepository.findByIdIn(ids);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }

}
