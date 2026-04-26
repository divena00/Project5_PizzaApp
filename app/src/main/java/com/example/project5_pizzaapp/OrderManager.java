package com.example.project5_pizzaapp;
public class OrderManager {
    private static OrderManager instance;
    private Order currentOrder;
    private StoreOrder storeOrders;

    private OrderManager() {
        currentOrder = new Order();
        storeOrders = new StoreOrder();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public StoreOrder getStoreOrders() {
        return storeOrders;
    }

    public void resetOrder() {
        currentOrder = new Order();
    }
}