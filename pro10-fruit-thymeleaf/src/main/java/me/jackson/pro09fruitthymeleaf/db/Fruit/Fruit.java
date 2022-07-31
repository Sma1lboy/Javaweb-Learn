package me.jackson.pro09fruitthymeleaf.db.Fruit;

public class Fruit {

    public String name;
    public double price;
    public double stock;
    public String comment = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Fruit(String name, double price, double stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Fruit(String name, double price, double stock, String comment) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.comment = comment;
    }

}
