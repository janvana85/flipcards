package com.example.afinal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

public class Card {
    private long id;
    private String frontText;
    private String backText;

    public Card(long id, String frontText, String backText) {
        this.id = id;
        this.frontText = frontText;
        this.backText = backText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        if (id != 0) {
            values.put(DatabaseHelper.COLUMN_ID, id);
        }
        values.put(DatabaseHelper.COLUMN_FRONT_TEXT, frontText);
        values.put(DatabaseHelper.COLUMN_BACK_TEXT, backText);
        return values;
    }

    public static Card fromCursor(Cursor cursor) {
        @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
        @SuppressLint("Range") String frontText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FRONT_TEXT));
        @SuppressLint("Range") String backText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BACK_TEXT));
        return new Card(id, frontText, backText);
    }
}