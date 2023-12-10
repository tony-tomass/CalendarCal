package com.example.calendarapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EventContentProvider extends ContentProvider {

    public static final String DB_NAME = "EVENT_LIST";
    public static final String TBL_NAME = "Events";
    public static final String COL1_NAME = "Name";
    public static final String COL2_NAME = "Date";
    public static final String COL3_NAME = "Time";
    public static final String AUTHORITY = "com.calendarapp.eventcontentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + DB_NAME);
    public static final String CREATE_URI_QUERY = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +
            "( _id INTEGER PRIMARY KEY, " +
            COL1_NAME + " TEXT, " +
            COL2_NAME + " TEXT, " +
            COL3_NAME + " TEXT )"
            ;

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

    public EventContentProvider() {
        //Empty constructor
    }

    @Override
    public boolean onCreate() {
        SQLHelper = new MainDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return SQLHelper.getWritableDatabase().query(TBL_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = SQLHelper.getWritableDatabase().insert(TBL_NAME, null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return SQLHelper.getWritableDatabase().delete(TBL_NAME, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return SQLHelper.getWritableDatabase().update(TBL_NAME, values, selection, selectionArgs);
    }
}
