package ru.kos.shop.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Продукт <br/>
 * Created by Константин on 04.04.2016.
 */
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("price", price)
                .toString();
    }
}
