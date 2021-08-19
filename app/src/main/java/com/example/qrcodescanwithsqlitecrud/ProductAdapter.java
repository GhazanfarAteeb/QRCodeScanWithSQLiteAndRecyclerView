package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private int position;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.list_item_view,parent,false);
        return new ViewHolder(productView);
    }

    public void setPosition(int position) {
        this.position = position;
    }
    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        TextView barcode = holder.barcode;
        TextView name = holder.productName;
        TextView quantity = holder.productQuantity;
        TextView price = holder.productPrice;
        ImageView image = holder.productImage;

        if (!product.getProductImageURL().isEmpty()) {
            Picasso.with(holder.itemView.getContext()).load(product.getProductImageURL()).into(image);
        }

        barcode.setText(String.format(Locale.getDefault(),"%d", product.getProductID()));
        name.setText(product.getProductName());
        quantity.setText(String.format(Locale.getDefault(),"%d", product.getProductQuantity()));
        double totalPrice = product.getProductPrice() * product.getProductQuantity();
        price.setText(String.format(Locale.getDefault(),"%.2f", totalPrice));

        setPosition(holder.getPosition());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView barcode;
        public TextView productName;
        public TextView productQuantity;
        public ImageView productImage;
        public TextView productPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            barcode = itemView.findViewById(R.id.barcode);
            productName = itemView.findViewById(R.id.name);
            productQuantity = itemView.findViewById(R.id.quantity);
            productImage = itemView.findViewById(R.id.image);
            productPrice = itemView.findViewById(R.id.price);

            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(v -> {
                Edit listener;
                try {
                    listener = (Edit) v.getContext();
                    listener.editData(position);
                } catch (ClassCastException ex) {
                    throw new ClassCastException(v.getContext().toString());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem edit = menu.add(0, R.id.edit, 0, "Edit");
            MenuItem delete = menu.add(0, R.id.delete, 1, "Delete");
            edit.setOnMenuItemClickListener(item -> {
                Edit listener;
                try {
                    listener = (Edit) v.getContext();
                    listener.editData(position);
                } catch (ClassCastException ex) {
                    throw new ClassCastException(v.getContext().toString());
                }
                return true;
            });
            delete.setOnMenuItemClickListener(item -> {
                Delete listener;
                try {
                    listener = (Delete) v.getContext();
                    listener.deleteData(position);
                } catch (ClassCastException ex) {
                    throw new ClassCastException(v.getContext().toString());
                }

                return true;
            });
        }
    }

    public interface Delete {
        void deleteData(int position);
    }

    public interface Edit{
        void editData(int position);
    }
}
