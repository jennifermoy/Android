package edu.chapman.jennifer.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

public class AddRestaurant extends AppCompatActivity implements Serializable{

    Button insert;
    Button clear;
    EditText restName;
    EditText restPhone;
    EditText restWebsite;
    RatingBar restRating;
    Spinner restCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrestaurant);

        insert = findViewById(R.id.bnInsert);
        clear = findViewById(R.id.bnClear);
        restName = findViewById(R.id.etRestName);
        restPhone = findViewById(R.id.etRestPhone);
        restWebsite = findViewById(R.id.etRestWebsite);
        restRating = findViewById(R.id.rbRestRating);
        restCategory = findViewById(R.id.spinRestCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoriesArray, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restCategory.setAdapter(adapter);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listRest = new Intent(AddRestaurant.this, MainActivity.class);
                Intent viewRest = new Intent(AddRestaurant.this, ViewRestaurant.class);

                String name = restName.getText().toString();
                String phone = restPhone.getText().toString();
                String website = restWebsite.getText().toString();
                float rating = restRating.getRating();
                String category = restCategory.getSelectedItem().toString();

                Restaurant rest = new Restaurant(name, phone, website, rating, category);

                MySingleton.getInstance().restaurantList.put(rest.getName(), new Restaurant(rest.getName(), rest.getPhone(),
                        rest.getWebsite(), rest.getRating(), rest.getCategory()));
                MySingleton.getInstance().restList.add(MySingleton.getInstance().restaurantList.size()-1, rest.toString());

                listRest.putExtra("restObj", rest);
                viewRest.putExtra("restObj", rest);

                if (rating == 5) {
                    Intent fiveBroadcast = new Intent();
                    fiveBroadcast.setAction("edu.chapman.jennifer.broadcast");
                    sendBroadcast(fiveBroadcast);
                }

                startActivity(listRest);

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restName.setText("");
                restPhone.setText("");
                restWebsite.setText("");
                restRating.setRating(0.0f);
                restCategory.setSelection(0);
            }
        });

    }
}
