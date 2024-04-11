package com.example.afinal;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddNewCard extends AppCompatActivity {

    private EditText editTextFront;
    private EditText editTextBack;
    private Button buttonAddCard;

    private ListView listView;
    private CardAdapter adapter;
    private List<Card> cardList;
    private DatabaseHelper dbHelper;
    private CardOperations cardOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySelectedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);

        // Initialize database helper and card operations
        dbHelper = new DatabaseHelper(this);
        cardOperations = new CardOperations(dbHelper);

        editTextFront = findViewById(R.id.front_text);
        editTextBack = findViewById(R.id.back_text);
        buttonAddCard = findViewById(R.id.insert_card_button);

        listView = findViewById(R.id.listViewCards);
        cardList = new ArrayList<>();
        adapter = new CardAdapter(this, cardList);
        listView.setAdapter(adapter);

        // Populate the ListView with data from the SQLite database
        loadCardsFromDatabase();

        // Set up click listener for the Add Card button
        buttonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the front and back text from the EditText views
                String frontText = editTextFront.getText().toString();
                String backText = editTextBack.getText().toString();

                // Call the addCard method from the CardOperations class
                cardOperations.open();
                cardOperations.addCard(frontText, backText);
                cardOperations.close();

                // Load cards from database again after adding a new card
                loadCardsFromDatabase();

                // Clear the EditText fields
                editTextFront.setText("");
                editTextBack.setText("");
            }
        });
    }

    private void loadCardsFromDatabase() {
        cardList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_CARDS,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            cardList.add(Card.fromCursor(cursor));
        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();
    }


    private void applySelectedTheme() {
        SharedPreferences prefs = getSharedPreferences(SettingsActivity.THEME_PREFS, Context.MODE_PRIVATE);
        int selectedTheme = prefs.getInt(SettingsActivity.SELECTED_THEME_KEY, R.id.lightThemeRadioButton);

        if (selectedTheme == R.id.lightThemeRadioButton) {
            setTheme(R.style.LightTheme);
        } else if (selectedTheme == R.id.darkThemeRadioButton) {
            setTheme(R.style.DarkTheme);
        }
    }
}
