package ru.kos.shop.reporitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kos.shop.domain.Product;

import java.util.List;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    /**
     * Поиск продукта по id
     * @param id
     * @return Продукт, null если не найдет
     */
    Product findById(Integer id);

    /**
     * Поиск продуктов по списку id
     * @param IdList список id
     * @return List продуктов. Пустой если ничего не найдено.
     */
    List<Product> findByIdIn(List<Integer> IdList);

}
