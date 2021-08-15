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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.Edit {
    RecyclerView recyclerView;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        recyclerView = findViewById(R.id.recycler_view);

        productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(i,"ABC",1,"https://i.ibb.co/z5QV8tm/download.png",12.5));
        }
        recyclerView.setAdapter(new ProductAdapter(productList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(recyclerView);
    }

    public void scanButton(View v) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult resultIntent = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (resultIntent != null) {
            if (resultIntent.getContents()!=null) {
                System.out.println(resultIntent.getContents());
            }
            else {
                System.out.println("Content is null");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void editData(int position) {
        Product product = productList.get(position);

        EditData editData = new EditData();
        EditData.DataListener dataListener = new EditData.DataListener() {

            @Override
            public Bundle ReceiveData() {
                Bundle bundle = new Bundle();

                bundle.putInt("barcode",product.getProductID());
                bundle.putString("name",product.getProductName());
                bundle.putDouble("price",product.getProductPrice());
                bundle.putString("URL",product.getProductImageURL());
                return bundle;
            }
        };
        editData.setDataListener(dataListener);
        editData.show(getSupportFragmentManager(),"Edit");
    }
}