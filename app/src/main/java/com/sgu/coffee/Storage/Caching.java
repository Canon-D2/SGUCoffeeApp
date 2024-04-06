package com.sgu.coffee.Storage;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.sgu.coffee.Model.Cart;
import com.sgu.coffee.Model.Category;
import com.sgu.coffee.Model.Order;
import com.sgu.coffee.Model.Order_Item;
import com.sgu.coffee.Model.Product;
import com.sgu.coffee.Model.ProductImage;
import com.sgu.coffee.Model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Caching extends SQLiteOpenHelper {
    private List new_products, best_sale_products, category, cart, category_prd;
    private static final String DATABASE_NAME = "Sgucoffee";
    private static final int DATABASE_VERSION = 1;

    public Caching(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        category = new ArrayList<Category>();
        new_products = new ArrayList<Product>();
        best_sale_products = new ArrayList<Product>();
        category_prd = new ArrayList<Product>();
        cart = new ArrayList<Cart>();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table USER (ID TEXT PRIMARY KEY, AVATAR TEXT, EMAIL TEXT, LOGIN_TYPE TEXT, PASSWORD TEXT, PHONE TEXT, ROLE TEXT, USER_NAME TEXT, ADDRESS TEXT)");
        sqLiteDatabase.execSQL("create table CATEGORY (ID INTEGER PRIMARY KEY AUTOINCREMENT, CATEGORY_NAME TEXT, CATEGORY_IMAGE TEXT)");
        sqLiteDatabase.execSQL("create table PRODUCT (ID INTEGER PRIMARY KEY AUTOINCREMENT, CREATE_AT TEXT, DESCRIPTION TEXT, ACTIVE INTEGER, SELLING INTEGER, PRICE INTEGER, PRODUCT_NAME TEXT, QUANTITY INTEGER, SOLD INTEGER, CATEGORY_ID INTEGER, FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY(ID))");
        sqLiteDatabase.execSQL("create table PRODUCT_IMAGE (ID INTEGER PRIMARY KEY AUTOINCREMENT, URL_IMAGE TEXT, PRODUCT_ID INTEGER, FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(ID))");
        sqLiteDatabase.execSQL("create table BILL (ID INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS TEXT, BOOKING_DATE TEXT, COUNTRY TEXT, EMAIL TEXT, FULLNAME TEXT, NOTE TEXT, PAYMENT TEXT, PHONE TEXT, STATUS TEXT, TOTAL INTEGER, USR_ID TEXT, FOREIGN KEY(USR_ID) REFERENCES USER(ID))");
        sqLiteDatabase.execSQL("create table CART (ID INTEGER PRIMARY KEY AUTOINCREMENT, COUNT INTEGER, PRODUCT_ID INTEGER, USER_ID TEXT ,FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(ID),FOREIGN KEY(USER_ID) REFERENCES USER(ID))");
        sqLiteDatabase.execSQL("create table BILL_DETAIL (ID INTEGER PRIMARY KEY AUTOINCREMENT, COUNT INTEGER, BILL_ID INTEGER, PRODUCT_ID INTERGER, FOREIGN KEY(BILL_ID) REFERENCES BILL(ID), FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", user.getId());
        values.put("AVATAR", user.getAvatar());
        values.put("EMAIL", user.getEmail());
        values.put("LOGIN_TYPE", user.getLogin_Type());
        values.put("PASSWORD", user.getPassword());
        values.put("PHONE", user.getPhone_Number());
        values.put("ROLE", user.getRole());
        values.put("USER_NAME", user.getUser_Name());
        values.put("ADDRESS", user.getAddress());
        db.insert("USER", null, values);
        db.close();
    }

    public void addCategory(Category type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", type.getId());
        values.put("CATEGORY_NAME", type.getCategory_Name());
        values.put("CATEGORY_IMAGE", type.getCategory_Image());
        db.insert("CATEGORY", null, values);
        db.close();
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", product.getId());
        values.put("CREATE_AT", String.valueOf(product.getCreated_At()));
        values.put("DESCRIPTION", product.getDescription());
        values.put("ACTIVE", product.getIs_Active());
        values.put("SELLING", product.getIs_Selling());
        values.put("PRICE", product.getPrice());
        values.put("PRODUCT_NAME", product.getProduct_Name());
        values.put("QUANTITY", product.getQuantity());
        values.put("SOLD", product.getSold());
        db.insert("PRODUCT", null, values);
        db.close();
    }

    public void addProduct_Image(List<ProductImage> imgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(ProductImage img : imgs){
            ContentValues values = new ContentValues();
            values.put("ID", img.getId());
            values.put("URL_IMAGE", img.getUrl_Image());
            values.put("PRODUCT_ID", Integer.parseInt(img.getPRD_Id()));
            db.insert("PRODUCT_IMAGE", null, values);
        }
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", user.getId());
        values.put("AVATAR", user.getAvatar());
        values.put("EMAIL", user.getEmail());
        values.put("LOGIN_TYPE", user.getLogin_Type());
        values.put("PASSWORD", user.getPassword());
        values.put("PHONE", user.getPhone_Number());
        values.put("ROLE", user.getRole());
        values.put("USER_NAME", user.getUser_Name());
        values.put("ADDRESS", user.getAddress());
        db.update("USER", values, "ID = ?", new String[]{user.getId()});
        db.close();
    }

    public void updateCategory(Category type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", type.getId());
        values.put("CATEGORY_NAME", type.getCategory_Name());
        values.put("CATEGORY_IMAGE", type.getCategory_Image());
        db.update("CATEGORY", values, "ID = ?", new String[]{String.valueOf(type.getId())});
        db.close();
    }

    public void updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", product.getId());
        values.put("CREATE_AT", String.valueOf(product.getCreated_At()));
        values.put("DESCRIPTION", product.getDescription());
        values.put("ACTIVE", product.getIs_Active());
        values.put("SELLING", product.getIs_Selling());
        values.put("PRICE", product.getPrice());
        values.put("PRODUCT_NAME", product.getProduct_Name());
        values.put("QUANTITY", product.getQuantity());
        values.put("SOLD", product.getSold());
        db.update("PRODUCT", values, "ID = ?", new String[]{String.valueOf(product.getId())});
        db.close();
    }

    public void updateProduct_Image(List<ProductImage> imgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(ProductImage img : imgs){
            ContentValues values = new ContentValues();
            values.put("ID", img.getId());
            values.put("URL_IMAGE", img.getUrl_Image());
            values.put("PRODUCT_ID", Integer.parseInt(img.getPRD_Id()));
            db.update("PRODUCT_IMAGE", values, "ID = ?", new String[]{String.valueOf(img.getId())});
        }
        db.close();
    }



    public boolean CheckCategoryData() {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        String Query = "Select * from Category ";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean CheckProductData() {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        String Query = "Select * from Product ";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public List<Category> getCategory() {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        String Query = "Select * from CATEGORY ";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String img = cursor.getString(2);
                category.add(new Category(id, name, img));
            }
            cursor.close();
        }
        return category;
    }

    public List<Product> getPrdCate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM PRODUCT";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    java.util.Date utildate = dateFormat.parse(cursor.getString(1));
                    Date create_at = new Date(utildate.getTime());
                    String description = cursor.getString(2);
                    int active = cursor.getInt(3);
                    int sell = cursor.getInt(4);
                    int price = cursor.getInt(5);
                    String pr_name = cursor.getString(6);
                    int quantity = cursor.getInt(7);
                    int sold = cursor.getInt(8);
                    List<ProductImage>prdimg_list = new ArrayList<>();
                    String selectImageQuery = "SELECT * FROM PRODUCT_IMAGE WHERE product_id = ?";
                    Cursor imageCursor = db.rawQuery(selectImageQuery, new String[]{String.valueOf(id)});
                    if (imageCursor.moveToFirst()) {
                        do {
                            int imgid = imageCursor.getInt(0);
                            String url = imageCursor.getString(1);
                            String prdid = imageCursor.getString(2);
                            prdimg_list.add(new ProductImage(imgid, url, prdid));
                        } while (imageCursor.moveToNext());
                    }
                    imageCursor.close();
                    category_prd.add(new Product(id, pr_name, description, sold, active, sell, create_at, price, quantity, prdimg_list));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }
        return category_prd;
    }

    public List<Product> getNewProducts() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM PRODUCT ORDER BY CREATE_AT DESC LIMIT 12";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    java.util.Date utildate = dateFormat.parse(cursor.getString(1));
                    Date create_at = new Date(utildate.getTime());
                    String description = cursor.getString(2);
                    int active = cursor.getInt(3);
                    int sell = cursor.getInt(4);
                    int price = cursor.getInt(5);
                    String pr_name = cursor.getString(6);
                    int quantity = cursor.getInt(7);
                    int sold = cursor.getInt(8);
                    List<ProductImage>prdimg_list = new ArrayList<>();
                    String selectImageQuery = "SELECT * FROM PRODUCT_IMAGE WHERE product_id = ?";
                    Cursor imageCursor = db.rawQuery(selectImageQuery, new String[]{String.valueOf(id)});
                    if (imageCursor.moveToFirst()) {
                        do {
                            int imgid = imageCursor.getInt(0);
                            String url = imageCursor.getString(1);
                            String prdid = imageCursor.getString(2);
                            prdimg_list.add(new ProductImage(imgid, url, prdid));
                        } while (imageCursor.moveToNext());
                    }
                    imageCursor.close();
                    new_products.add(new Product(id, pr_name, description, sold, active, sell, create_at, price, quantity, prdimg_list));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }
        return new_products;
    }

    public List<Product> getBestSaleProducts() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM Product ORDER BY SOLD DESC LIMIT 12 ";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    java.util.Date utildate = dateFormat.parse(cursor.getString(1));
                    Date create_at = new Date(utildate.getTime());
                    String description = cursor.getString(2);
                    int active = cursor.getInt(3);
                    int sell = cursor.getInt(4);
                    int price = cursor.getInt(5);
                    String pr_name = cursor.getString(6);
                    int quantity = cursor.getInt(7);
                    int sold = cursor.getInt(8);
                    List<ProductImage>prdimg_list = new ArrayList<>();
                    String selectImageQuery = "SELECT * FROM PRODUCT_IMAGE WHERE product_id = ?";
                    Cursor imageCursor = db.rawQuery(selectImageQuery, new String[]{String.valueOf(id)});
                    if (imageCursor.moveToFirst()) {
                        do {
                            int imgid = imageCursor.getInt(0);
                            String url = imageCursor.getString(1);
                            String prdid = imageCursor.getString(2);
                            prdimg_list.add(new ProductImage(imgid, url, prdid));
                        } while (imageCursor.moveToNext());
                    }
                    imageCursor.close();
                    best_sale_products.add(new Product(id, pr_name, description, sold, active, sell, create_at, price, quantity, prdimg_list));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }
        return best_sale_products;
    }
}
