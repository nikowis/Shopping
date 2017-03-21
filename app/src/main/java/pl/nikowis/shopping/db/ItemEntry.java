package pl.nikowis.shopping.db;

import android.provider.BaseColumns;

/**
 * Created by Nikodem on 3/21/2017.
 */

public class ItemEntry implements BaseColumns {

    public static final String TABLE_NAME = "Item";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_IMAGE = "image";

}
