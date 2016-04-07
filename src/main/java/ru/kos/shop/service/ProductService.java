package ru.kos.shop.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kos.shop.domain.Product;
import ru.kos.shop.reporitory.ProductRepository;

import java.util.List;

/**
 * Сервис для работы с продуктами <br/>
 * Created by brjazgin on 06.04.2016.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Получить все продукты
     * @return Все продукты.
     */
    @NotNull
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Найти продукт по ID
     * @param id
     * @return Продукт с данным id. Null если не найден.
     */
    @Nullable
    public Product findById(@NotNull Integer id) {
        return productRepository.findById(id);
    }

    /**
     * Создать продукт
     * @param product
     * @return Созданный продукт с заполненным id
     */
    @Transactional
    @NotNull
    public Product createProduct(@NotNull Product product) {
        return productRepository.save(product);
    }

    /**
     * Обновить продукт. В случае, если продукта с таким id нет, будет создан новый продукт.
     * @param id Id существующего продукта
     * @param product Обновленный продукт
     * @return Обновленный продукт
     */
    @Transactional
    @NotNull
    public Product updateProduct(@NotNull Integer id, @NotNull Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    /**
     * Поиск списка продуктов с заданными id
     * @param ids список заданных id
     * @return Список продуктов. Пустой список если ничего не найдено
     */
    @NotNull
    public List<Product> findProductsByIdList(@NotNull List<Integer> ids) {
        return productRepository.findByIdIn(ids);
    }

    /**
     * Удалить продукт по id.
     * @param id id продукта для удаления
     */
    @Transactional
    public void deleteProduct(@NotNull Integer id) {
        productRepository.delete(id);
    }

}
