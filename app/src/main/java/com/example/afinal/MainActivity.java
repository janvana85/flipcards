package com.example.afinal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private CardPagerAdapter pagerAdapter;
    private List<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        applySelectedTheme();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Initialize ViewPager
        viewPager = findViewById(R.id.viewPager);

        // Initialize card list
        cardList = new ArrayList<>();

        loadCardsFromDatabase();

        // Initialize adapter
        pagerAdapter = new CardPagerAdapter(getSupportFragmentManager(), cardList);
        viewPager.setAdapter(pagerAdapter);

    }


    private void loadCardsFromDatabase() {
        cardList = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_CARDS,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            cardList.add(Card.fromCursor(cursor));
        }
        cursor.close();
        db.close();
    }

    public void applySelectedTheme() {
        SharedPreferences prefs = getSharedPreferences(SettingsActivity.THEME_PREFS, Context.MODE_PRIVATE);
        int selectedTheme = prefs.getInt(SettingsActivity.SELECTED_THEME_KEY, R.id.lightThemeRadioButton);

        if (selectedTheme == R.id.lightThemeRadioButton){
            setTheme(R.style.LightTheme);

        }else if(selectedTheme == R.id.darkThemeRadioButton){
            setTheme(R.style.DarkTheme);
        }
    }
    public void goToSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void goToAddNewCard(View view) {
        Intent intent = new Intent(this, AddNewCard.class);
        startActivity(intent);
    }
}