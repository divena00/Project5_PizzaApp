package com.example.project5_pizzaapp;

public class PizzaItem {
    private String name;
    private int imageResId;

    public PizzaItem(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
