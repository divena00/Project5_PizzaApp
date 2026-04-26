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
    public int getNumber() {
        return number;
    }
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
    public boolean addPizza(Pizza pizza) {
        if (pizza == null) return false;
        pizzas.add(pizza);
        return true;
    }
    public boolean removePizza(Pizza pizza) {
        return pizzas.remove(pizza);
    }
    public double getSubtotal() {
        double total = 0;
        for (Pizza p : pizzas) {
            total += p.price();
        }
        return total;
    }
    public double getTax() {
        return getSubtotal() * 0.06625;
    }
    public double getTotal() {
        return getSubtotal() + getTax();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Pizza p : pizzas) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}