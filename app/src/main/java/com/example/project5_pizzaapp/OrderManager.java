package com.example.project5_pizzaapp;
/**
 * OrderManager is a Singleton class that manages application-wide order data.
 * It ensures there is only one instance used across all Activities.
 *Singleton class
 * @author Divena Deshmukh
 * @author Ishani Rajeshirke
 */
public class OrderManager {
    private static OrderManager instance;
    private Order currentOrder;
    private StoreOrder storeOrders;
    /**
     * Private constructor prevents direct instantiation.
     * Initializes current order and store orders list.
     */
    private OrderManager() {
        currentOrder = new Order();
        storeOrders = new StoreOrder();
    }
    /**
     * Returns the single instance of OrderManager.
     * Creates the instance if it does not already exist.
     *
     * @return OrderManager instance
     */

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }
    /**
     * Returns the current order being worked on.
     *
     * @return current Order object
     */

    public Order getCurrentOrder() {
        return currentOrder;
    }
    /**
     * Returns the collection of all store orders.
     *
     * @return StoreOrder object
     */
    public StoreOrder getStoreOrders() {
        return storeOrders;
    }
    /**
     * Resets the current order after it has been placed.
     * Creates a new empty Order object for the next transaction.
     */
    public void resetOrder() {
        currentOrder = new Order();
    }
}