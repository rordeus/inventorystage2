package com.example.android.inventoryappstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryappstage1.data.ContractInventory.InventoryEntry;

/**
 * Created by Chris on 07.12.2017.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            InventoryEntry.TABLE_NAME + " (" +
            InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
            InventoryEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, " +
            InventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, " +
            InventoryEntry.COLUMN_PRODUCT_IMAGE + " BLOB, " +
            InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, " +
            InventoryEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL, " +
            InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + InventoryEntry.TABLE_NAME;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}


