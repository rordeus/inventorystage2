/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.inventoryappstage1;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryappstage1.data.ContractInventory.InventoryEntry;

public class InventoryCursorAdapter extends CursorAdapter {

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView quantityTextView = view.findViewById(R.id.quantity);
        TextView priceTextView = view.findViewById(R.id.price);
        ImageView shoping = view.findViewById(R.id.shopCart);

        // Find the columns of item attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
        final int id = cursor.getInt(cursor.getColumnIndex(InventoryEntry._ID));
        // Read the item attributes from the Cursor for the current item
        final String itemName = cursor.getString(nameColumnIndex);
        final int itemQuantity = cursor.getInt(quantityColumnIndex);
        int itemPrice = cursor.getInt(priceColumnIndex);

        // Update the TextViews with the attributes for the current item
        nameTextView.setText(itemName);
        quantityTextView.setText(Integer.toString(itemQuantity));
        priceTextView.setText(Integer.toString(itemPrice));
        shoping.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (itemQuantity > 0) {
                    int newQuantity = itemQuantity - 1;
                    Uri quantitytUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);
                    ContentValues values = new ContentValues();
                    values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, newQuantity);
                    context.getContentResolver().update(quantitytUri, values, null, null);
                } else {
                    Toast.makeText(context, "Out of stock!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

