package com.example.project5_pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main screen of the app.
 * Allows navigation to other activities.
 */
public class MainActivity extends AppCompatActivity {

    private Button btnOrderPizza;
    private Button btnCurrentOrder;
    private Button btnStoreOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI elements
        btnOrderPizza = findViewById(R.id.btnOrderPizza);
        btnCurrentOrder = findViewById(R.id.btnCurrentOrder);
        btnStoreOrders = findViewById(R.id.btnStoreOrders);

        // Go to Pizza Ordering Screen
        btnOrderPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, PizzaActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error opening Pizza Menu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Go to Current Order Screen
        btnCurrentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error opening Current Order", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Go to Store Orders Screen
        btnStoreOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error opening Store Orders", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}