package com.example.swiftscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
        TextView textView = findViewById(R.id.listOfScanned);
        List<Drink> list = dbHelper.getSaved();
        textView.setText("TOTAL SCANS: " + list.size());
    }

    public void startScan(View view) {
        Intent intent = new Intent(ScanActivity.this, BarcodeActivity.class);
        startActivity(intent);
    }

    public void toProfile(View view) {
        Intent intent = new Intent(ScanActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void toSave(View view) {
        Intent intent = new Intent(ScanActivity.this, SavedActivity.class);
        startActivity(intent);
    }
}
