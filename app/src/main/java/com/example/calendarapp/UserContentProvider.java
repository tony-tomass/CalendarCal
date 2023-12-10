package com.example.calendarapp;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class UserContentProvider extends ContentProvider {

    public static final String DB_NAME = "UserDB";
    public static final String TABLE_NAME = "User";
    public static final String COL1_NAME = "Name";
    public static final String COL2_NAME = "Email";
    public static final String COL3_NAME = "Major";
    public static final String COL4_NAME = "Classification";
    public static final String AUTHORITY = "com.example.Usercontentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
    private static final String CREATE_URI_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(_id INTEGER PRIMARY KEY, " +
            COL1_NAME + " TEXT, " +
            COL2_NAME + " TEXT, " +
            COL3_NAME + " TEXT, " +
            COL4_NAME + " TEXT) ";

    protected static final class MainDBHelper extends SQLiteOpenHelper {

        public MainDBHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_URI_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //
        }
    }
    private MainDBHelper SQLHelper;
    public UserContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return SQLHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = SQLHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        SQLHelper = new MainDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return SQLHelper.getWritableDatabase().query(TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        return SQLHelper.getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
    }
}
