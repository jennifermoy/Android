package edu.chapman.jennifer.restaurantapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Preferences extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
    }

}