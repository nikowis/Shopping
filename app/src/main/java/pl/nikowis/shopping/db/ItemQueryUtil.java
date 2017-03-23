package pl.nikowis.shopping.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pl.nikowis.shopping.ShoppingItem;

/**
 * Created by Nikodem on 3/21/2017.
 */

public class ItemQueryUtil {

    private SQLiteDatabase database;

    public ItemQueryUtil(SQLiteDatabase database) {
        this.database = database;
    }

    private static String[] projection = {
            ItemEntry._ID,
            ItemEntry.COLUMN_NAME_TITLE,
            ItemEntry.COLUMN_NAME_DESCRIPTION,
            ItemEntry.COLUMN_NAME_IMAGE
    };

    public List<ShoppingItem> getAllItems() {
        List<ShoppingItem> items = new ArrayList<>();
        Cursor cursor = database.query(
                ItemEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ItemEntry.COLUMN_NAME_TITLE));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(ItemEntry.COLUMN_NAME_DESCRIPTION));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(ItemEntry.COLUMN_NAME_IMAGE));
            items.add(new ShoppingItem(title, desc, image));
        }

        return items;
    }

    public void addNewItem(ShoppingItem item) {
        ContentValues values = new ContentValues();
        values.put(ItemEntry.COLUMN_NAME_TITLE, item.getTitle());
        values.put(ItemEntry.COLUMN_NAME_DESCRIPTION, item.getDescription());
        values.put(ItemEntry.COLUMN_NAME_IMAGE, item.getImage());
        item.setId(database.insert(ItemEntry.TABLE_NAME, null, values));
    }


    public void saveItem(ShoppingItem item) {
        ContentValues values = new ContentValues();
        values.put(ItemEntry.COLUMN_NAME_TITLE, item.getTitle());
        values.put(ItemEntry.COLUMN_NAME_DESCRIPTION, item.getDescription());
        values.put(ItemEntry.COLUMN_NAME_IMAGE, item.getImage());

        String selection = ItemEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(item.getId())};

        database.update(
                ItemEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void deleteItem(ShoppingItem shoppingItem) {
        String selection = ItemEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(shoppingItem.getId())};
        database.delete(ItemEntry.TABLE_NAME, selection, selectionArgs);
    }
}
