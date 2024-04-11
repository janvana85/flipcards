package com.example.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    public static final String THEME_PREFS = "ThemePrefs";
    public static final String SELECTED_THEME_KEY = "SelectedTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySelectedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Load selected theme from SharedPreferences and set the corresponding RadioButton checked
        SharedPreferences prefs = getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE);
        int selectedTheme = prefs.getInt(SELECTED_THEME_KEY, R.id.lightThemeRadioButton);
        RadioButton radioButton = findViewById(selectedTheme);
        radioButton.setChecked(true);

        RadioGroup themeRadioGroup = findViewById(R.id.themeRadioGroup);
        themeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Store selected theme in SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(SELECTED_THEME_KEY, checkedId);
                editor.apply();

                // Restart the activity to apply the new theme
                recreate();
            }
        });
    }

    private void applySelectedTheme() {
        SharedPreferences prefs = getSharedPreferences(SettingsActivity.THEME_PREFS, Context.MODE_PRIVATE);
        int selectedTheme = prefs.getInt(SettingsActivity.SELECTED_THEME_KEY, R.id.lightThemeRadioButton);

        if (selectedTheme == R.id.lightThemeRadioButton){
            setTheme(R.style.LightTheme);

        }else if(selectedTheme == R.id.darkThemeRadioButton){
            setTheme(R.style.DarkTheme);
        }
    }
}
