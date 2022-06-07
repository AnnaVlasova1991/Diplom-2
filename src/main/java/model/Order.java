package model;

import java.util.List;

public class Order {
    private List<Data> ingridients;
    private String id;
    private Owner owner;
    private String status;
    private String name;
    private String createdAt;
    private String updatedAt;
    private int number;
    private int price;

    public Order(List<Data> ingridients, String id, Owner owner, String status, String name, String createdAt, String updatedAt, int number, int price) {
        this.ingridients = ingridients;
        this.id = id;
        this.owner = owner;
        this.status = status;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.number = number;
        this.price = price;
    }

    public Order(int number) {
        this.number = number;
    }

    public List<Data> getIngridients() {
        return ingridients;
    }

    public void setIngridients(List<Data> ingridients) {
        this.ingridients = ingridients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
