package com.example.swiftscan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SavedActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        recyclerView = (RecyclerView) findViewById(R.id.drinksList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        DBHelper db = DBHelper.getInstance(getApplicationContext());
        // specify an adapter (see also next example)
        mAdapter = new DrinkAdapter(db.getSaved(), new DrinkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Drink item) {
                Intent intent = new Intent(SavedActivity.this, ProductActivity.class);
                intent.putExtra("KEY_CODE", item.getCode());
                intent.putExtra("TYPE", 1);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void toProfile(View view) {
        Intent intent = new Intent(SavedActivity.this, ProfileActivity.class);
        startActivity(intent);
    }


    public void toScan(View view) {
        Intent intent = new Intent(SavedActivity.this, ScanActivity.class);
        startActivity(intent);
    }
}
