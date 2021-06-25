package ru.otus.spring.kreidun.models;

public class OrderItem {

    private final String itemName;

    public OrderItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
