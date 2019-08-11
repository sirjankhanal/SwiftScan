package com.example.swiftscan;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.MyViewHolder> {
    private List<Drink> mDataset;


    public DrinkAdapter(List<Drink> myDataset, OnItemClickListener listener) {
        this.mDataset = myDataset;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewNameDA;
        public TextView textViewBrandDA;
        public TextView textViewPriceDA;
        ImageView imgView;

        public MyViewHolder(View v) {
            super(v);
            textViewNameDA = v.findViewById(R.id.textViewNameDA);
            textViewBrandDA = v.findViewById(R.id.textViewBrandDA);
            textViewPriceDA = v.findViewById(R.id.textViewPriceDA);
            imgView = v.findViewById(R.id.imageView15);
        }

        public void bind(final Drink item, final OnItemClickListener listener) {
            textViewNameDA.setText(item.getName());
            textViewBrandDA.setText(item.getBrand());
            textViewPriceDA.setText(item.getPrice());
            imgView.setImageResource(R.drawable.ic_local_drink_black_24dp);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public DrinkAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(mDataset.get(position), listener);

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Drink item);
    }

    private OnItemClickListener listener;
}