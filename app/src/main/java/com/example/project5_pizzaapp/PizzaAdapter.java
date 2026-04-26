package com.example.project5_pizzaapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {
    private ArrayList<PizzaItem> pizzaList;
    private OnPizzaClickListener listener;

    public interface OnPizzaClickListener {
        void onPizzaClick(PizzaItem item);
    }

    public PizzaAdapter(ArrayList<PizzaItem> pizzaList, OnPizzaClickListener listener) {
        this.pizzaList = pizzaList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pizzaImage);
            textView = itemView.findViewById(R.id.pizzaName);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pizza, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PizzaItem item = pizzaList.get(position);

        holder.textView.setText(item.getName());
        holder.imageView.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> listener.onPizzaClick(item));
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

}
