package edu.chapman.jennifer.restaurantapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class ViewRestaurant extends AppCompatActivity implements Serializable {

    TextView phoneShow;
    TextView websiteShow;
    TextView nameShow;
    RatingBar ratingShow;
    TextView categoryShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewrestaurant);

        phoneShow = findViewById(R.id.txtPhoneView);
        websiteShow = findViewById(R.id.txtWebsiteView);
        nameShow = findViewById(R.id.txtNameView);
        ratingShow = findViewById(R.id.ratingBar);
        categoryShow = findViewById(R.id.txtCategoryView);

        Intent intent = getIntent();
        final Restaurant restCurr = (Restaurant) intent.getSerializableExtra("restObj");
        final Restaurant loadedRest = (Restaurant) intent.getSerializableExtra("loadObj");
        final Boolean loaded = intent.getBooleanExtra("sendLoaded", false);

        try {
            if (loaded == true) {
                nameShow.setText(loadedRest.getName());
                phoneShow.setText(loadedRest.getPhone());
                websiteShow.setText(loadedRest.getWebsite());
                ratingShow.setRating(loadedRest.getRating());
                categoryShow.setText(loadedRest.getCategory());
            }
            else {
                nameShow.setText(restCurr.getName());
                phoneShow.setText(restCurr.getPhone());
                websiteShow.setText(restCurr.getWebsite());
                ratingShow.setRating(restCurr.getRating());
                categoryShow.setText(restCurr.getCategory());
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Can't Load Restaurant Info from Loaded List into View Restaurant",
                    Toast.LENGTH_LONG).show();
        }

        //implicit intents for phone number and website
        phoneShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:" + restCurr.getPhone());
                Intent phoneNum = new Intent(Intent.ACTION_DIAL, number);

                startActivity(phoneNum);

                finish();
            }
        });

        websiteShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //website needs to include "http://"
                Uri webpage = Uri.parse(restCurr.getWebsite());
                Intent webpageIntent = new Intent(Intent.ACTION_VIEW, webpage);

                startActivity(webpageIntent);

                finish();
            }
        });

    }


}
