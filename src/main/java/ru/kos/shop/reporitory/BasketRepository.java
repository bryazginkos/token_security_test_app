package ru.kos.shop.reporitory;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kos.shop.domain.Basket;

import java.util.Date;
import java.util.List;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {

    /**
     * Корзины, оформленные за промежуток времени
     * @param begin начало промежутка
     * @param end конец промежутка
     * @return List корзин. Пустой если не найдено.
     */
    @Query("select b from Basket b " +
            "where b.orderDate between ?1 and ?2")
    @NotNull
    List<Basket> findByDatesBetween(@NotNull Date begin, @NotNull Date end);
}
