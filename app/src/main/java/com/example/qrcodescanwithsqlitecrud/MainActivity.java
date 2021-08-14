package com.example.qrcodescanwithsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.initiateScan();
            }

            protected void onActivityResult(int result, int requestCode, int resultCode, Intent data) {

                MainActivity.super.onActivityResult(requestCode, resultCode, data);
            }
        });
        productList = new ArrayList<>();

        for (int i=0;i<10;i++) {
            productList.add(new Product(i+1, "ABC",100,"https://i.imgur.com/5zpAsGl.jpg",12.5));
        }


        recyclerView.setAdapter(new ProductAdapter(productList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(recyclerView);
    }
}