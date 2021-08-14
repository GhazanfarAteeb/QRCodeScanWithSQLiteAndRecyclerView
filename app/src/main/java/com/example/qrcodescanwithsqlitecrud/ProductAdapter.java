package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private int position;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }
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

        Picasso.with(holder.itemView.getContext()).load(product.getProductImageURL()).into(image);
        barcode.setText(Integer.toString(product.getProductID()));
        name.setText(product.getProductName());
        quantity.setText("1");

        price.setText(Double.toString(product.getProductPrice()));

        setPosition(holder.getPosition());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
