package com.example.afinal;

import android.provider.BaseColumns;

public class CardContract {

    public static final String DB_NAME = "cards.db";
    public static final int DB_VERSION = 1;

    public static class CardsEntry implements BaseColumns {
        public static final String TABLE_NAME = "cards";
        public static final String COLUMN_FRONT_TEXT = "front_text";
        public static final String COLUMN_BACK_TEXT = "back_text";
    }
}