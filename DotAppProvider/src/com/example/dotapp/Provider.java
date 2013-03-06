package com.example.dotapp;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.provider.BaseColumns;

public class Provider extends ContentProvider {
    private OpenHelper dbHelper;
    private SQLiteDatabase database;
    public static final String AUTHORITY = "com.example.dotapp.contentprovider";
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int DOTSS = 1001;
    private static final int DOTS_ID = 1002;
    public static final String DOTS_PATH = "DOTSs";
    public static final Uri DOTS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + DOTS_PATH);
    public static final String DOTS_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/DOTSs";
    public static final String DOTS_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/DOTS";
    static {
        sURIMatcher.addURI(AUTHORITY, DOTS_PATH, DOTSS);
        sURIMatcher.addURI(AUTHORITY, DOTS_PATH + "/#", DOTS_ID);
    }



    @Override
    public boolean onCreate() {
        dbHelper = new OpenHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public String getType(Uri uri) {
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
        case DOTSS:
            return DOTS_CONTENT_TYPE;
        case DOTS_ID:
            return DOTS_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        long id = 0;
        switch (uriType) {
        case DOTSS:
            id = database.insert(DotTable.TABLE_NAME, null, values);
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.parse("content://" + AUTHORITY + "/" + DOTS_PATH + "/" + id);
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = sURIMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (uriType) {
        case DOTSS:
            queryBuilder.setTables(DotTable.TABLE_NAME);
            break;
        case DOTS_ID:
            queryBuilder.setTables(DotTable.TABLE_NAME);
            queryBuilder.appendWhere(BaseColumns._ID + "=" + uri.getLastPathSegment());
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);        
        }
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        int rowsDeleted = 0;
        switch (uriType) {
        case DOTSS:
            rowsDeleted = database.delete(DotTable.TABLE_NAME, selection, selectionArgs);
            break;
        case DOTS_ID:
            String DOTSId = uri.getLastPathSegment();
            if (TextUtils.isEmpty(selection)) {
                rowsDeleted = database.delete(DotTable.TABLE_NAME, BaseColumns._ID + "=" + DOTSId, null);
            } else {
                rowsDeleted = database.delete(DotTable.TABLE_NAME, BaseColumns._ID + "=" + DOTSId + " AND " + selection, selectionArgs);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);      
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        int rowsUpdated = 0;
        switch (uriType) {
        case DOTSS:
            rowsUpdated = database.update(DotTable.TABLE_NAME, values, selection, selectionArgs);
            break;
        case DOTS_ID:
            String DOTSId = uri.getLastPathSegment();
            if (TextUtils.isEmpty(selection)) {
                rowsUpdated = database.update(DotTable.TABLE_NAME, values, BaseColumns._ID + "=" + DOTSId, null);
            } else {
                rowsUpdated = database.update(DotTable.TABLE_NAME, values, BaseColumns._ID + "=" + DOTSId + " AND " + selection, selectionArgs);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }


}