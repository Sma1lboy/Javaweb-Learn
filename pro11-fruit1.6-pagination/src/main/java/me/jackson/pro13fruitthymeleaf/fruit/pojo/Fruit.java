package me.jackson.pro13fruitthymeleaf.fruit.pojo;

import java.math.BigDecimal;

public class Fruit {
    private Integer id ;
    private String name ;
    private BigDecimal price ;
    private BigDecimal stock ;
    private String comment ;

    public Fruit() {
    }

    public Fruit(String name, BigDecimal price, BigDecimal stock, String comment) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.comment = comment;
    }

    public Fruit(Integer id, String name, BigDecimal price, BigDecimal stock, String comment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", comment='" + comment + '\'' +
                '}';
    }
}
