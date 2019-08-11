package com.example.swiftscan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import java.util.logging.Logger;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // Programmatically initialize the scanner view
        mScannerView = new ZXingScannerView(this);
        // Set the scanner view as the content view
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        //mScannerView.resumeCameraPreview(this);
        System.out.println("HANDLEEEEEEEEEEEEEEEEE");
        DBHelper db = DBHelper.getInstance(getApplicationContext());
        Drink ifExist = db.getProduct(rawResult.getText());

        Intent intent;
        if (!(ifExist == null)) {
            intent = new Intent(BarcodeActivity.this, ProductActivity.class);
            intent.putExtra("KEY_CODE", rawResult.getText());
            intent.putExtra("TYPE_CODE", rawResult.getBarcodeFormat().toString());
        } else {
            intent = new Intent(BarcodeActivity.this, ScanActivity.class);
            Toast.makeText(BarcodeActivity.this, "Prodcut with code " +
                    rawResult.getText() + " is not found in database", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(BarcodeActivity.this, rawResult.getText(), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}