package com.example.project5_pizzaapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildYourOwnActivity extends AppCompatActivity {

    private Spinner spinnerSize;
    private ListView listViewToppings;
    private Button btnAddToOrder;
    private Button btnBack;

    private String style;
    private ArrayList<Topping> selectedToppings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own);

        spinnerSize = findViewById(R.id.spinnerSize);
        listViewToppings = findViewById(R.id.listViewToppings);
        btnAddToOrder = findViewById(R.id.btnAddToOrder);
        btnBack = findViewById(R.id.btnBack);

        selectedToppings = new ArrayList<>();

        style = getIntent().getStringExtra("style");

        setupSizeSpinner();
        setupToppingList();

        btnAddToOrder.setOnClickListener(view -> addPizzaToOrder());
        btnBack.setOnClickListener(view -> finish());
    }

    private void setupSizeSpinner() {
        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Size.values()
        );

        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(sizeAdapter);
    }

    private void setupToppingList() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.values()));

        ArrayAdapter<Topping> toppingAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                toppings
        );

        listViewToppings.setAdapter(toppingAdapter);
        listViewToppings.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listViewToppings.setOnItemClickListener((parent, view, position, id) -> {
            Topping topping = toppings.get(position);

            if (selectedToppings.contains(topping)) {
                selectedToppings.remove(topping);
            } else {
                if (selectedToppings.size() >= 5) {
                    listViewToppings.setItemChecked(position, false);
                    Toast.makeText(this, "Maximum 5 toppings allowed", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectedToppings.add(topping);
            }
        });
    }

    private void addPizzaToOrder() {
        BuildYourOwn pizza = new BuildYourOwn();

        if ("Chicago".equals(style)) {
            pizza.setCrust(Crust.ChicagoBYO);
        } else {
            pizza.setCrust(Crust.NYBYO);
        }

        pizza.setSize((Size) spinnerSize.getSelectedItem());

        for (Topping topping : selectedToppings) {
            pizza.addTopping(topping);
        }
        new AlertDialog.Builder(this)
                .setTitle("Confirm Pizza")
                .setMessage("Are you sure you want to add this pizza to your order?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    OrderManager.getInstance().getCurrentOrder().addPizza(pizza);
                    Toast.makeText(this, "Build Your Own added", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}
