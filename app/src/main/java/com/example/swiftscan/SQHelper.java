package com.example.swiftscan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQHelper extends SQLiteOpenHelper {
    private String data_table = "PRODUCTS";
    private String saved_table = "SAVED";
    private static final int DATABASE_VERSION = 2;
    private String[] pre_data = new String[]{
            "INSERT INTO PRODUCTS (NAME, BRAND, PRICE, DESCRIPTION, ABV, COUNTRY, TYPE, CATEGORY, CODE) VALUES('Zatecky Hus','Brewery','5000','Best Czezh beer','y','Czezch','Beer','1','001');",
            "INSERT INTO PRODUCTS (NAME, BRAND, PRICE, DESCRIPTION, ABV, COUNTRY, TYPE, CATEGORY, CODE) VALUES('Hennesy HO','Hennesy','9000','Best cognac','y','USA','Cognac','2','002');",
            "INSERT INTO PRODUCTS (NAME, BRAND, PRICE, DESCRIPTION, ABV, COUNTRY, TYPE, CATEGORY, CODE) VALUES('Budweiser','Brewery','2000','Best USA beer','y','Czezch','Beer','1','003');",
            "INSERT INTO PRODUCTS (NAME, BRAND, PRICE, DESCRIPTION, ABV, COUNTRY, TYPE, CATEGORY, CODE) VALUES('Shampan','d.u.Shampan','2500','Best wedding drink','y','France','Wine','3','004');",
            "INSERT INTO PRODUCTS (NAME, BRAND, PRICE, DESCRIPTION, ABV, COUNTRY, TYPE, CATEGORY, CODE) VALUES('Horilka','Hetman','4000','Best Ukraine drink','n','Ukraine','Spiritus','4','005');",
            "INSERT INTO PRODUCTS (NAME, BRAND, PRICE, DESCRIPTION, ABV, COUNTRY, TYPE, CATEGORY, CODE) VALUES('Palenka','Hospodar','3500','Best Hungary drink','n','Hungary','Spiritus','4','006');"};

    public SQHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PRODUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT," +
                "BRAND TEXT, PRICE TEXT, DESCRIPTION TEXT,ABV TEXT, COUNTRY TEXT, TYPE TEXT, " +
                "CATEGORY TEXT, CODE TEXT); ");
        db.execSQL("CREATE TABLE SAVED (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME text,CODE TEXT); ");
        for (String s : pre_data) {
            db.execSQL(s);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + data_table);
        db.execSQL("DROP TABLE IF EXISTS " + saved_table);
        onCreate(db);
    }
}
