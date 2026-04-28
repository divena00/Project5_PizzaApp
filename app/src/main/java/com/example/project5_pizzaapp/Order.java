package com.example.project5_pizzaapp;

import java.util.ArrayList;

/**
 * Represents a customer's order.
 * @author Divena Deshmukh
 * @author Ishani Rajeshirke
 */
public class Order {
    private int number; // order number
    private ArrayList<Pizza> pizzas;
    private static int nextNumber = 1;

    /**
     * Constructs a new Order with a unique number.
     */
    public Order() {
        this.number = nextNumber++;
        this.pizzas = new ArrayList<>();
    }
    /**
     * get number method
     */
    public int getNumber() {
        return number;
    }
    /**
     * get pizza method
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
    /**
     * pizza add method
     */
    public boolean addPizza(Pizza pizza) {
        if (pizza == null) return false;
        pizzas.add(pizza);
        return true;
    }
    /**
     * pizza remove method
     */
    public boolean removePizza(Pizza pizza) {
        return pizzas.remove(pizza);
    }
    /**
     * calculates subtotal
     */
    public double getSubtotal() {
        double total = 0;
        for (Pizza p : pizzas) {
            total += p.price();
        }
        return total;
    }
    /**
     * get tax method  method
     */
    public double getTax() {
        return getSubtotal() * 0.06625;
    }
    /**
     * get total  method
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }
    /**
     * toString method
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Pizza p : pizzas) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}