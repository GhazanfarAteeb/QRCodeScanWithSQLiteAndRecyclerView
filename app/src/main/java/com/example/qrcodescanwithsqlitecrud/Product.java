package com.example.qrcodescanwithsqlitecrud;

public class Product {
    final private int productID;
    final private String productName;
    private int productQuantity;
    final private String productImageURL;
    final private double productPrice;

    public Product(int productID, String productName, int productQuantity, String productImageURL, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productImageURL = productImageURL;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
