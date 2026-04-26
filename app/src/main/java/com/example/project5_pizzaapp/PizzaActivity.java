package com.example.project5_pizzaapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Displays available pizzas using RecyclerView.
 * Allows user to add pizzas to the current order.
 */
public class PizzaActivity extends AppCompatActivity implements PizzaAdapter.OnPizzaClickListener {

    private RecyclerView recyclerView;
    private PizzaAdapter adapter;
    private ArrayList<PizzaItem> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        recyclerView = findViewById(R.id.recyclerViewPizza);

        // Grid layout (2 columns looks nice)
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadPizzas();

        adapter = new PizzaAdapter(pizzaList, this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Loads pizza data into list
     */
    private void loadPizzas() {
        pizzaList = new ArrayList<>();

        // NY Style Pizzas
        pizzaList.add(new PizzaItem("NY Deluxe", R.drawable.ny_deluxe));
        pizzaList.add(new PizzaItem("NY BBQ Chicken", R.drawable.ny_bbq));
        pizzaList.add(new PizzaItem("NY Meatzza", R.drawable.ny_meatzza));
        pizzaList.add(new PizzaItem("NY Build Your Own", R.drawable.ny_byo));

        // Chicago Style Pizzas
        pizzaList.add(new PizzaItem("Chicago Deluxe", R.drawable.chicago_deluxe));
        pizzaList.add(new PizzaItem("Chicago BBQ Chicken", R.drawable.chicago_bbq));
        pizzaList.add(new PizzaItem("Chicago Meatzza", R.drawable.chicago_meatzza));
        pizzaList.add(new PizzaItem("Chicago Build Your Own", R.drawable.chicago_byo));
    }

    /**
     * Handles click on pizza item
     */
    @Override
    public void onPizzaClick(PizzaItem item) {
        try {
            Pizza pizza;

            // Create pizza based on selection
            switch (item.getName()) {
                case "Deluxe":
                    pizza = new Deluxe();
                    break;
                case "BBQ Chicken":
                    pizza = new BBQChicken();
                    break;

                case "Meatzza":
                    pizza = new Meatzza();
                    break;
                case "Build Your Own":
                    pizza = new BuildYourOwn();
                    break;
                default:
                    Toast.makeText(this, "Unknown pizza", Toast.LENGTH_SHORT).show();
                    return;
            }

            // Add to current order
            OrderManager.getInstance().getCurrentOrder().addPizza(pizza);

            Toast.makeText(this, item.getName() + " added!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error adding pizza", Toast.LENGTH_SHORT).show();
        }
    }
}