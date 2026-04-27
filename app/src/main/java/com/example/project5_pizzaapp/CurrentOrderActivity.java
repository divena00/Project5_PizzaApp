package com.example.project5_pizzaapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView listViewCurrentOrder;
    private TextView textSubtotal, textTax, textTotal;
    private Button btnPlaceOrder, btnBack,btnRemovePizza;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> pizzaStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        listViewCurrentOrder = findViewById(R.id.listViewCurrentOrder);
        textSubtotal = findViewById(R.id.textSubtotal);
        textTax = findViewById(R.id.textTax);
        textTotal = findViewById(R.id.textTotal);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnBack = findViewById(R.id.btnBack);
        btnRemovePizza = findViewById(R.id.btnRemovePizza);
        listViewCurrentOrder.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        loadCurrentOrder();

        listViewCurrentOrder.setOnItemClickListener((parent, view, position, id) ->
                showRemoveDialog(position));

        btnPlaceOrder.setOnClickListener(view -> placeOrder());
        btnBack.setOnClickListener(v -> finish());
        btnRemovePizza.setOnClickListener(v -> {
            int position = listViewCurrentOrder.getCheckedItemPosition();

            if (position == ListView.INVALID_POSITION) {
                Toast.makeText(this, "Select a pizza to remove", Toast.LENGTH_SHORT).show();
                return;
            }

            Pizza pizza = OrderManager.getInstance()
                    .getCurrentOrder()
                    .getPizzas()
                    .get(position);

            OrderManager.getInstance().getCurrentOrder().removePizza(pizza);

            loadCurrentOrder();

            Toast.makeText(this, "Pizza removed", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadCurrentOrder() {
        pizzaStrings = new ArrayList<>();

        for (Pizza pizza : OrderManager.getInstance().getCurrentOrder().getPizzas()) {
            pizzaStrings.add(pizza.toString());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, pizzaStrings);
        listViewCurrentOrder.setAdapter(adapter);

        updateTotals();
    }

    private void updateTotals() {
        Order order = OrderManager.getInstance().getCurrentOrder();

        textSubtotal.setText(String.format("Subtotal: $%.2f", order.getSubtotal()));
        textTax.setText(String.format("Tax: $%.2f", order.getTax()));
        textTotal.setText(String.format("Total: $%.2f", order.getTotal()));
    }

    private void showRemoveDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Remove Pizza")
                .setMessage("Remove this pizza from the current order?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Pizza pizza = OrderManager.getInstance()
                            .getCurrentOrder()
                            .getPizzas()
                            .get(position);

                    OrderManager.getInstance().getCurrentOrder().removePizza(pizza);
                    loadCurrentOrder();

                    Toast.makeText(this, "Pizza removed", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void placeOrder() {
        Order currentOrder = OrderManager.getInstance().getCurrentOrder();

        if (currentOrder.getPizzas().isEmpty()) {
            Toast.makeText(this, "Current order is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        OrderManager.getInstance().getStoreOrders().addOrder(currentOrder);
        OrderManager.getInstance().resetOrder();

        Toast.makeText(this, "Order placed", Toast.LENGTH_SHORT).show();

        loadCurrentOrder();
    }

}