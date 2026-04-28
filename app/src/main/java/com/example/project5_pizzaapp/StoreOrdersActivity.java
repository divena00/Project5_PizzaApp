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
    /**
     * StoreOrdersActivity displays all placed orders in the store.
     * Users can:
     * - View order details using a Spinner (dropdown list)
     * - Cancel an existing order using an AlertDialog
     * - Navigate back to the previous screen
     * @author Divena Deshmukh
     * @author Ishani Rajeshrike
     */

    /**
     *  Dropdown list of order numbers
     */
    private Spinner spinnerOrders;
    /**
     *   Displays selected order details
     */
    private TextView textOrderDetails;
    /**
     *   Buttons for canceling and navigating back
     */
    private Button btnCancelOrder,btnBack;
    /**
     *   Adapter for Spinner
     */
    private ArrayAdapter<Integer> spinnerAdapter;
    /**
     *   List of order numbers
     */
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
        /**
         * Listener for Spinner selection.
         * When user selects an order number, display its details.
         */
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
    /**
     * Loads all store order numbers into the Spinner.
     * Uses ArrayAdapter to bind data to the dropdown UI.
     */
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
    /**
     * Displays a confirmation dialog to cancel the selected order.
     * Removes the order if user confirms.
     */
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