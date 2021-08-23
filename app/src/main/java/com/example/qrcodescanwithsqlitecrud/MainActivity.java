package com.example.qrcodescanwithsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ProductAdapter.Edit,ProductAdapter.Delete {
    RecyclerView recyclerView;
    List<Product> productList;
    SQLiteOpenHelper openHelper;
    TextView textView;
    Cursor cursor;

    // this function will calculate the total according to the items in the list
    private double calculateTotal() {
        double total = 0;
        for (Product p: productList) {
            total += (p.getProductPrice() * p.getProductQuantity());
        }
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new ProductDatabaseHelper(this);

        // setting permissions for the app
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        recyclerView = findViewById(R.id.recycler_view);

        productList = new ArrayList<>();

        recyclerView.setAdapter(new ProductAdapter(productList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // setting basic total to the textView
        textView = findViewById(R.id.total);
        textView.setText(String.format(Locale.getDefault(),"Total: Rs. %.2f",calculateTotal()));

        registerForContextMenu(recyclerView);
    }

    // this will help in scanning the product barcode/QR Code
    public void scanButton(View v) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult resultIntent = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //The scanned barcode/QR Code must contains the information of the product
        if (resultIntent != null) {
            // if the barcode/QR Code scanned provide empty string or there is only
            // space the this loop will not work
            if (!resultIntent.getContents().isEmpty()&& !resultIntent.getContents().equals(" ")) {
                // SQL query
                cursor = openHelper.getReadableDatabase().rawQuery(
                        "SELECT * FROM "+ ProductDatabaseHelper.TABLE_NAME
                        +" WHERE " +
                        ProductDatabaseHelper.COL_1+"="+Integer.parseInt(resultIntent.getContents()),
                        null);

                // if the product is in the database
                if(cursor.getCount()!= 0) {

                        while(cursor.moveToNext()) {
                            int index = -1;

                            //to check whether the product already exists or not in the product list
                            for (Product p: productList){
                                if (p.getProductID()==cursor.getInt(0)) {
                                    index = productList.indexOf(p);
                                    break;
                                }
                            }
                            //if the product does not exists
                            if (index == -1) {
                                //adding new product to the list
                                productList.add(
                                        new Product(
                                                cursor.getInt(0),
                                                cursor.getString(1),
                                                1,
                                                cursor.getString(4),
                                                cursor.getDouble(3)
                                        )
                                );
                            } else { // if the product already exists then there will be increment in the quantity of the product
                                productList.get(index).setProductQuantity(productList.get(index).getProductQuantity() + 1);
                                textView.setText(String.format(Locale.getDefault(),"Total: Rs. %.2f",calculateTotal()));
                            }
                        }


                    textView.setText(String.format(Locale.getDefault(),"Total: Rs. %.2f",calculateTotal()));

                    recyclerView.setAdapter(new ProductAdapter(productList));
                }
            }
            else {
                System.out.println("Content is null");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    // for editing the existing data
    @Override
    public void editData(int position) {
        // finding the product whether the selected product exists
        Product product = productList.get(position);

        EditData editData = new EditData();

        // populating the fields of editData with the existing data by passing the data as a bundle
        EditData.DataListener dataListener = () -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",position);
            bundle.putInt("barcode",product.getProductID());
            bundle.putString("name",product.getProductName());
            bundle.putDouble("price",product.getProductPrice());
            bundle.putString("URL",product.getProductImageURL());
            bundle.putInt("quantity",product.getProductQuantity());
            return bundle;
        };
        //setting up the listener
        editData.setDataListener(dataListener);
        //showing the listener
        editData.show(getSupportFragmentManager(),"Edit");

        // getting the updated data through the listener to update the existing data
        EditData.PassData getUpdatedDataListener = (bundle) -> {
            Product product1 = productList.get(bundle.getInt("position"));
            product1.setProductQuantity(bundle.getInt("quantity"));
            productList.set(bundle.getInt("position"), product1);

            textView.setText(String.format(Locale.getDefault(),"Total: Rs. %.2f",calculateTotal()));
            recyclerView.setAdapter(new ProductAdapter(productList));
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        };
        editData.setPassDataListener(getUpdatedDataListener);
    }

    //to delete the existing data
    @Override
    public void deleteData(int position) {
        productList.remove(position);
        textView.setText(String.format(Locale.getDefault(),"Total: Rs. %.2f",calculateTotal()));
        recyclerView.setAdapter(new ProductAdapter(productList));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}