package com.example.swiftscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        db = DBHelper.getInstance(getApplicationContext());
        Drink drink = db.getProduct(getIntent().getStringExtra("KEY_CODE"));
        TextView name = findViewById(R.id.textViewName);
        name.setText(drink.getName());
        TextView brand = findViewById(R.id.textViewBrand);
        brand.setText(drink.getBrand());
        TextView price = findViewById(R.id.textViewPrice);
        price.setText(drink.getPrice());
        TextView description = findViewById(R.id.textViewDescription);
        description.setText(drink.getDescription());
        TextView type = findViewById(R.id.textView3);
        type.setText(drink.getType());
        TextView country = findViewById(R.id.textView5);
        country.setText(drink.getCountry());
        TextView category = findViewById(R.id.textView6);
        category.setText(drink.getCategory());
        TextView abv = findViewById(R.id.textViewPriceDA);
        abv.setText(drink.getABV());
        int u = getIntent().getIntExtra("TYPE", 0);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        if (u == 1) {
            fab.hide();
        } else {
            fab.show();
        }
    }

    public void saveProduct(View view) {
        db.saveProduct(getIntent().getStringExtra("KEY_CODE"));
        Intent intent = new Intent(ProductActivity.this, SavedActivity.class);
        startActivity(intent);
    }

    public void toProfile(View view) {
        Intent intent = new Intent(ProductActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void toSave(View view) {
        Intent intent = new Intent(ProductActivity.this, SavedActivity.class);
        startActivity(intent);
    }

    public void toScan(View view) {
        Intent intent = new Intent(ProductActivity.this, ScanActivity.class);
        startActivity(intent);
    }
}
