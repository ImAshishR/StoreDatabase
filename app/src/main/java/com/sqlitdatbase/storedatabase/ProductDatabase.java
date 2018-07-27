package com.sqlitdatbase.storedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase extends SQLiteOpenHelper {
    final public static int DB_VERSION=1;
    final public static String DB_NAME="MyDatabase";
    final public static String TABLE_NAME="Product";
    final public static String PRODUCT_ID="Product_Id";
    final public static String PRODUCT_NAME="Product_Name";
    final public static String PRODUCT_QUANTITY="Product_Quantity";
    final public static String PRODUCT_PRICE="Product_Price";


    public ProductDatabase(Context context) {
        super(context,DB_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" ("+
        PRODUCT_ID+" number primary key ,"+
        PRODUCT_NAME+" text ,"+
        PRODUCT_QUANTITY+" number ,"+
        PRODUCT_PRICE+" number"+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public long addProduct(Product record){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues productValues=new ContentValues();
        productValues.put(PRODUCT_ID,record.getProductId());
        productValues.put(PRODUCT_NAME,record.getProductName());
        productValues.put(PRODUCT_QUANTITY,record.getProductQuantity());
        productValues.put(PRODUCT_PRICE,record.getProductPrice());
        long rowid=db.insert(TABLE_NAME,null,productValues);
        db.close();
        return rowid;
    }

    public ArrayList<Product> getAllProduct(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
        ArrayList<Product> products=new ArrayList<Product>();
        if(cursor.moveToFirst()){
            do{
                Product record=new Product(cursor.getInt(0),
                        cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
                products.add(record);
            }while (cursor.moveToNext());
        }
        return products;
    }

    public Product findProduct(int productId){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,PRODUCT_ID+"=?",new String[]{String.valueOf(productId)},
                null,null,null);
        Product product=null;
        if(cursor.moveToFirst())
            product=new Product(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),
                    cursor.getInt(3));
        return product;
    }


    public void sellProduct(int productId,int sellQty){
        SQLiteDatabase db=getWritableDatabase();
        int currentQty=findProduct(productId).getProductQuantity();
        currentQty-=sellQty;
        ContentValues contentValues=new ContentValues();
        contentValues.put(PRODUCT_QUANTITY,currentQty);
        db.update(TABLE_NAME,contentValues,PRODUCT_ID+"=?",new String[]{String.valueOf(productId)});
        db.close();
    }

    public void delete(int productId){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME,PRODUCT_ID+"=?",new String[]{String.valueOf(productId)});
        db.close();
    }

    public void addQuantity(int productId,int addQty){
        SQLiteDatabase db=getWritableDatabase();
        int currentQty=findProduct(productId).getProductQuantity();
        currentQty+=addQty;
        ContentValues contentValues=new ContentValues();
        contentValues.put(PRODUCT_QUANTITY,currentQty);
        db.update(TABLE_NAME,contentValues,PRODUCT_ID+"=?",new String[]{String.valueOf(productId)});
        db.close();
    }


}
