package ru.kos.shop.reporitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kos.shop.domain.Product;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findById(Integer id);

}
