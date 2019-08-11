package com.example.swiftscan;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView textView=findViewById(R.id.textViewIName);
        DBHelper dbHelper=DBHelper.getInstance(getApplicationContext());
        textView.setText(dbHelper.getName());
    }

    public void toProfile(View view) {
        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void toSave(View view) {
        Intent intent = new Intent(ProfileActivity.this, SavedActivity.class);
        startActivity(intent);
    }

    public void toScan(View view) {
        Intent intent = new Intent(ProfileActivity.this, ScanActivity.class);
        startActivity(intent);
    }
}
