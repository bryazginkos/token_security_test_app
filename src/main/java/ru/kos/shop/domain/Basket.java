package ru.kos.shop.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Корзина. Корзина не содержит в себе внешние ключи на продукты, поскольку продукты могут быть изменены,
 * а отчет о продажах должен содержать те товары, которые реально были проданы. Поэтому продукты храним строкой. <br/>
 * Created by brjazgin on 06.04.2016.
 */
@Entity
public class Basket {

    @Id
    @GeneratedValue
    private Integer id;

    private String customerPhone;

    private Date orderDate;

    private String products;

    public Basket() {
    }


    public Integer getId() {
        return id;
    }

    /**
     * Установить телефон клиента
     * @param customerPhone телефон клиента
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Установить дату покупки
     * @param orderDate дата покупки
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Получить список покупок строкой
     * @return Строка получаемая методом toString у коллекции продуктов
     */
    public String getProducts() {
        return products;
    }

    /**
     * Установить список покупок строкой. По договоренности это строка получаемая методом toString у коллекции продуктов
     * @param products
     */
    public void setProducts(String products) {
        this.products = products;
    }

    /**
     * Получить телефон клиента
     * @return телефон клиента
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Получить дату покупки
     * @return дата покупки
     */
    public Date getOrderDate() {
        return orderDate;
    }
}
