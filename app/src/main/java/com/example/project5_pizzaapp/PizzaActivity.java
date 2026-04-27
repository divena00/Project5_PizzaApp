package com.example.project5_pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

/**
 * Displays available pizzas using RecyclerView.
 * Allows user to add pizzas to the current order.
 */
public class PizzaActivity extends AppCompatActivity implements PizzaAdapter.OnPizzaClickListener {

    private RecyclerView recyclerView;
    private PizzaAdapter adapter;
    private ArrayList<PizzaItem> pizzaList;
    private Button btnBack; // Back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        // Back button
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerViewPizza);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

            switch (item.getName()) {

                // NY Style
                case "NY Deluxe":
                    pizza = new NYPizza().createDeluxe();
                    break;

                case "NY BBQ Chicken":
                    pizza = new NYPizza().createBBQChicken();
                    break;

                case "NY Meatzza":
                    pizza = new NYPizza().createMeatzza();
                    break;

                case "NY Build Your Own":
                    Intent nyIntent = new Intent(this, BuildYourOwnActivity.class);
                    nyIntent.putExtra("style", "NY");
                    startActivity(nyIntent);
                    return;

                // Chicago Style
                case "Chicago Deluxe":
                    pizza = new ChicagoPizza().createDeluxe();
                    break;

                case "Chicago BBQ Chicken":
                    pizza = new ChicagoPizza().createBBQChicken();
                    break;

                case "Chicago Meatzza":
                    pizza = new ChicagoPizza().createMeatzza();
                    break;

                case "Chicago Build Your Own":
                    Intent chicagoIntent = new Intent(this, BuildYourOwnActivity.class);
                    chicagoIntent.putExtra("style", "Chicago");
                    startActivity(chicagoIntent);
                    return;

                default:
                    Toast.makeText(this, "Unknown pizza", Toast.LENGTH_SHORT).show();
                    return;
            }

            String[] sizes = {"Small", "Medium", "Large"};
            final int[] selectedSizeIndex = {0}; // default selection

            new AlertDialog.Builder(this)
                    .setTitle("Select Size for " + item.getName())
                    .setSingleChoiceItems(sizes, 0, (dialog, which) -> {
                        selectedSizeIndex[0] = which;
                    })
                    .setPositiveButton("Add to Order", (dialog, which) -> {

                        // Set size based on selection
                        switch (selectedSizeIndex[0]) {
                            case 0:
                                pizza.setSize(Size.small);
                                break;
                            case 1:
                                pizza.setSize(Size.medium);
                                break;
                            case 2:
                                pizza.setSize(Size.large);
                                break;
                        }

                        OrderManager.getInstance()
                                .getCurrentOrder()
                                .addPizza(pizza);

                        Toast.makeText(this,
                                item.getName() + " (" + sizes[selectedSizeIndex[0]] + ") added!",
                                Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        } catch (Exception e) {
            Toast.makeText(this, "Error adding pizza", Toast.LENGTH_SHORT).show();
        }
    }
}