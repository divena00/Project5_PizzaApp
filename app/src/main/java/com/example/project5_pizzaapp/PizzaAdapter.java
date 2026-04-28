package com.example.project5_pizzaapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
/**
 * PizzaAdapter connects pizza data (PizzaItem objects) to the RecyclerView UI.
 * It is responsible for creating item views and binding data (name + image)
 * to each row in the list.
 * @author Divena Deshmukh
 * @author Ishani Rajeshirke
 */
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {
    private ArrayList<PizzaItem> pizzaList;
    private OnPizzaClickListener listener;
    /**
     * Interface used to handle clicks on pizza items.
     * Implemented in PizzaActivity to respond when a user selects a pizza.
     */
    public interface OnPizzaClickListener {
        void onPizzaClick(PizzaItem item);
    }
    /**
     * Constructor initializes adapter with pizza data and click listener.
     */
    public PizzaAdapter(ArrayList<PizzaItem> pizzaList, OnPizzaClickListener listener) {
        this.pizzaList = pizzaList;
        this.listener = listener;
    }
    /**
     * ViewHolder represents a single item (row) in the RecyclerView.
     * Holds references to UI components for each pizza item.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        /**
         * Called when RecyclerView needs a new ViewHolder.
         * Inflates the layout for a single pizza item.
         */
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pizzaImage);
            textView = itemView.findViewById(R.id.pizzaName);
        }
    }
    /**
     * Called when RecyclerView needs a new ViewHolder.
     * Inflates the layout for a single pizza item.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pizza, parent, false);
        return new ViewHolder(view);
    }
    /**
     * Binds data to a ViewHolder at a specific position.
     * Sets the pizza name and image, and handles click events.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PizzaItem item = pizzaList.get(position);

        holder.textView.setText(item.getName());
        holder.imageView.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> listener.onPizzaClick(item));
    }
    /**
     * Returns total number of items in the list.
     * RecyclerView uses this to determine how many items to display.
     */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

}
