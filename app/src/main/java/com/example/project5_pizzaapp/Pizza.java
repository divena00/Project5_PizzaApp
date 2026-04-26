package com.example.project5_pizzaapp;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Abstract class representing a pizza.
 * Contains common properties such as toppings, crust, and size,
 * and defines shared behavior for all pizza types.
 * @author Divena Deshmukh
 * @author Ishani Rajeshirke
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Abstract method to calculate the price of the pizza.
     * @return price of the pizza
     */
    public abstract double price();

    /**
     * Pizza constructor initializes default values.
     */
    Pizza() {
        toppings = new ArrayList<>();
        size = Size.small;
        crust = null;
    }
    /**
     * Infers the pizza style based on its crust.
     * @return "Chicago Style" or "New York Style"
     */
    private String getStyle() {
        if (crust != null && crust.toString().toLowerCase().contains("chicago")) {
            return "Chicago Style";
        } else {
            return "New York Style";
        }
    }
    /**
     * Builds a formatted string of toppings.
     * @return comma-separated toppings
     */
    private String toStringToppings() {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < toppings.size(); i++) {
            x.append(toppings.get(i));
            if (i < toppings.size() - 1) {
                x.append(", ");
            }
        }
        return x.toString();
    }
    /**
     * Returns a string representation of the pizza.
     * Includes style, crust, toppings, size, and price.
     */
    @Override
    public String toString() {
        return "(" + getStyle() + "-" + Crust.crustInfo(crust) + ")" +
                toStringToppings() + " " +
                size.toString().toUpperCase(Locale.ROOT) + " " +
                price();
    }
    /**
     * Adds a topping to the pizza.
     * Limits Build Your Own pizzas to 5 toppings.
     */
    public boolean addTopping(Topping topping) {
        if (topping == null || toppings.contains(topping)) {
            return false;
        }
        if (this instanceof BuildYourOwn && toppings.size() >= 5) {
            return false;
        }
        toppings.add(topping);
        return true;
    }
    /**
     * Removes a topping from the pizza.
     */
    public boolean removeTopping(Topping topping) {
        return toppings.remove(topping);
    }
    /**
     * Gets number of toppings.
     */
    public int sizeToppings() {
        return toppings.size();
    }
    /**
     * Gets pizza size.
     */
    public Size getSize() {
        return size;
    }
    /**
     * Sets pizza size.
     */
    public void setSize(Size size) {
        this.size = size;
    }
    /**
     * Sets crust type.
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }
    /**
     * Gets crust type.
     */
    public Crust getCrust() {
        return crust;
    }
    /**
     * Gets toppings list.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }
}
