package com.example.qrcodescanwithsqlitecrud;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.squareup.picasso.Picasso;

import java.util.Locale;


public class EditData extends AppCompatDialogFragment {
    // listeners for changing data in the list
    DataListener dataListener;
    PassData passDataListener;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // creating the dialog layout
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.edit_data,null);

        // receiving the existing data
        Bundle bundle = dataListener.ReceiveData();

        // populating the fields
        EditText quantity = view.findViewById(R.id.Quantity);
        EditText barcode = view.findViewById(R.id.barcode);
        EditText name = view.findViewById(R.id.name);
        EditText price = view.findViewById(R.id.price);
        ImageView image = view.findViewById(R.id.image);

        barcode.setText(String.format(Locale.getDefault(),"%d",bundle.getInt("barcode")));
        name.setText(bundle.getString("name"));
        price.setText(String.format(Locale.getDefault(),"%.2f",bundle.getDouble("price")));

        // setting up the image in the image view via internet using Picasso
        if (!bundle.getString("URL").isEmpty()) {
            Picasso.with(view.getContext()).load(bundle.getString("URL")).into(image);
        }

        quantity.setText(String.format(Locale.getDefault(),"%d",bundle.getInt("quantity")));

        // setting up the view
        builder.setView(view)
                .setTitle("Edit")
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .setPositiveButton("OK", (dialog, which) -> {
                    // when OK is clicked then the data will be updated

                    Bundle data = new Bundle();
                    data.putInt("position",bundle.getInt("position"));
                    data.putInt("barcode",Integer.parseInt(barcode.getText().toString()));
                    data.putString("name",name.getText().toString());
                    data.putDouble("price",Double.parseDouble(price.getText().toString()));
                    data.putInt("quantity",Integer.parseInt(quantity.getText().toString()));
                    data.putString("URL", bundle.getString("URL"));

                    //sending back the updated data
                    passDataListener.sendData(data);
                });
        return builder.create();
    }

    //for setting up the listeners
    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }
    public void setPassDataListener(PassData passData) {
        this.passDataListener = passData;
    }

    //interfaces for the listeners
    public interface DataListener {
        Bundle ReceiveData();
    }
    public interface PassData {
        void sendData(Bundle bundle);
    }
}
