package ru.kos.shop.domain;

import javax.persistence.*;
import java.util.Date;

/**
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

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
