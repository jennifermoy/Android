package edu.chapman.jennifer.restaurantapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

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
        String nameKey = intent.getStringExtra("viewRestName");

        String name = intent.getStringExtra("sendRestName");
        final String phone = intent.getStringExtra("sendRestPhone");
        final String website = intent.getStringExtra("sendRestWebsite");
        float rating = intent.getFloatExtra("sendRestRating", 0.0f);
        String category = intent.getStringExtra("sendRestCategory");

        Restaurant restCurr = (Restaurant) intent.getSerializableExtra("restObj");

        nameShow.setText(restCurr.getName());
        phoneShow.setText(restCurr.getPhone());
        websiteShow.setText(restCurr.getWebsite());
        ratingShow.setRating(restCurr.getRating());
        categoryShow.setText(restCurr.getCategory());

        phoneShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:" + phone);
                Intent phoneNum = new Intent(Intent.ACTION_DIAL, number);

                startActivity(phoneNum);
            }
        });

        websiteShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //website needs to include "http://"
                Uri webpage = Uri.parse(website);
                Intent webpageIntent = new Intent(Intent.ACTION_VIEW, webpage);

                startActivity(webpageIntent);
            }
        });
    }


}
