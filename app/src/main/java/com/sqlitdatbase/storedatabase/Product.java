package com.sqlitdatbase.storedatabase;

public class Product {
    public  int productId,productQuantity,productPrice;
    public  String productName;


    Product(int productId,String productName,int productQuantity,int productPrice){
        this.productId=productId;
        this.productName=productName;
        this.productQuantity=productQuantity;
        this.productPrice=productPrice;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product Id :\t"+getProductId()+"\nProduct Name :\t"+getProductName()+"\nProduct Quantity :\t"+getProductQuantity()+"\nProduct Price :\t"+getProductPrice()+" /-";
    }
}
