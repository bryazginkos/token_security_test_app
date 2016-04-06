package ru.kos.shop.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> productList;

    public Basket() {
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
