package com.example.afinal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class CardOperations {


    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

    public CardOperations(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        Cursor cursor = database.query(CardContract.CardsEntry.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Card card = new Card(cursor.getLong(cursor.getColumnIndex(CardContract.CardsEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(CardContract.CardsEntry.COLUMN_FRONT_TEXT)),
                        cursor.getString(cursor.getColumnIndex(CardContract.CardsEntry.COLUMN_BACK_TEXT)));
                cards.add(card);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cards;
    }

    public long addCard(String frontText, String backText) {
        ContentValues values = new ContentValues();
        values.put(CardContract.CardsEntry.COLUMN_FRONT_TEXT, frontText);
        values.put(CardContract.CardsEntry.COLUMN_BACK_TEXT, backText);
        return database.insert(CardContract.CardsEntry.TABLE_NAME, null, values);
    }

    public int deleteCard(long id) {
        return database.delete(CardContract.CardsEntry.TABLE_NAME, CardContract.CardsEntry._ID + "=?", new String[]{String.valueOf(id)});
    }
}