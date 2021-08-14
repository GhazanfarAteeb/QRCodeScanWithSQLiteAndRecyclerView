package com.example.qrcodescanwithsqlitecrud;

public class Product {
    private int productID;
    private String productName;
    private int productQuantity;
    private String productImageURL;
    private double productPrice;

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

    public void setProductID(int productID) {
        this.productID = productID;
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

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
