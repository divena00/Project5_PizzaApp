package com.example.project5_pizzaapp;

/**
 * interface that handles pizza types
 * @author Divena Deshmukh
 * @author Ishani Rajeshirke
 */
public interface PizzaFactory {
    /**
     * creates Deluxe pizza
     * @return Pizza
     */
    Pizza createDeluxe();

    /**
     * creates Meatzza pizza
     * @return .Pizza
     */
    Pizza createMeatzza();

    /**
     * creates BBQ Chicken pizza
     * @return Pizza
     */
    Pizza createBBQChicken();

    /**
     * creates Build Your Own pizza
     * @return Pizza
     */
    Pizza createBuildYourOwn();
}
