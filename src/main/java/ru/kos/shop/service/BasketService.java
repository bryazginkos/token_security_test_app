package ru.kos.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kos.shop.domain.Basket;
import ru.kos.shop.reporitory.BasketRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Transactional
    public Basket saveBasket(Basket basket) {
        basketRepository.save(basket);
        return basket;
    }

    public List<Basket> getOrderBaskets(Date begin, Date end) {
        return basketRepository.findByDatesBetween(begin, end);
    }
}
