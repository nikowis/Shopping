package pl.nikowis.shopping.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nikodem on 3/21/2017.
 */

public class ShoppingDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "Shopping.db";

    private static final String SQL_CREATE_ITEMS =
            "CREATE TABLE " + ItemEntry.TABLE_NAME + " (" +
                    ItemEntry._ID + " INTEGER PRIMARY KEY," +
                    ItemEntry.COLUMN_NAME_TITLE + " TEXT," +
                    ItemEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ItemEntry.COLUMN_NAME_IMAGE + " INTEGER" +
                    " )";

    private static final String SQL_DELETE_ITEMS =
            "DROP TABLE IF EXISTS " + ItemEntry.TABLE_NAME;

    public ShoppingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ITEMS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ITEMS);
        onCreate(db);
    }

}
