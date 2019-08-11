package com.example.swiftscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SighnInActivity extends AppCompatActivity {

    private static final String TAG = SighnInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighn_in);
    }

    public void sighInOnClick(View view) {
        final EditText mailEditText = findViewById(R.id.editTextEMail_signin);
        final EditText passEditText = findViewById(R.id.editTextPass_signin);
        final EditText fNameEditText = findViewById(R.id.editTextName_signin);
        final EditText lNameEditText = findViewById(R.id.editTextSurname_signin);
        boolean check = mailEditText.getText().toString().isEmpty()
                | passEditText.getText().toString().isEmpty()
                | fNameEditText.getText().toString().isEmpty()
                | lNameEditText.getText().toString().isEmpty();
        if (!check) {
            Log.d(TAG, "createUserWithEmail:success");
            DBHelper db = DBHelper.getInstance(getApplicationContext());
            db.setName(mailEditText.getText().toString());
            Intent intent = new Intent(SighnInActivity.this, ScanActivity.class);
            startActivity(intent);
        } else {
            Log.w(TAG, "createUserWithEmail:failure");
            Toast.makeText(SighnInActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
            passEditText.setText("");
            passEditText.setText("");
            fNameEditText.setText("");
            lNameEditText.setText("");
        }
    }
}
