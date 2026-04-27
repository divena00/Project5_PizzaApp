package com.example.project5_pizzaapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner spinnerOrders;
    private TextView textOrderDetails;
    private Button btnCancelOrder,btnBack;
    private ArrayAdapter<Integer> spinnerAdapter;
    private ArrayList<Integer> orderNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        spinnerOrders = findViewById(R.id.spinnerOrders);
        textOrderDetails = findViewById(R.id.textOrderDetails);
        btnCancelOrder = findViewById(R.id.btnCancelOrder);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view -> {
            finish();
        });

        loadOrders();

        spinnerOrders.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent,
                                       android.view.View view,
                                       int position,
                                       long id) {

                Integer selectedOrderNumber = orderNumbers.get(position);
                Order selectedOrder = OrderManager.getInstance()
                        .getStoreOrders()
                        .search(String.valueOf(selectedOrderNumber));

                if (selectedOrder != null) {
                    textOrderDetails.setText(selectedOrder.toString());
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                textOrderDetails.setText("No order selected");
            }
        });

        btnCancelOrder.setOnClickListener(view -> showCancelDialog());
    }

    private void loadOrders() {
        orderNumbers = OrderManager.getInstance()
                .getStoreOrders()
                .getOrderNum();

        spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                orderNumbers
        );

        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerOrders.setAdapter(spinnerAdapter);

        if (orderNumbers.isEmpty()) {
            textOrderDetails.setText("No store orders available");
        }
    }

    private void showCancelDialog() {
        if (orderNumbers.isEmpty()) {
            Toast.makeText(this, "No orders to cancel", Toast.LENGTH_SHORT).show();
            return;
        }

        Integer selectedOrderNumber =
                (Integer) spinnerOrders.getSelectedItem();

        Order selectedOrder = OrderManager.getInstance()
                .getStoreOrders()
                .search(String.valueOf(selectedOrderNumber));

        if (selectedOrder == null) {
            Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Cancel Order")
                .setMessage("Cancel Order #" + selectedOrderNumber + "?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    OrderManager.getInstance()
                            .getStoreOrders()
                            .removeOrder(selectedOrder);

                    Toast.makeText(
                            this,
                            "Order cancelled",
                            Toast.LENGTH_SHORT
                    ).show();

                    loadOrders();
                })
                .setNegativeButton("No", null)
                .show();
    }
}