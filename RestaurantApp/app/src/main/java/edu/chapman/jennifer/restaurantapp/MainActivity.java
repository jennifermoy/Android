package edu.chapman.jennifer.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements Serializable{

    static String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public Boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listrestaurants);

        recyclerView = findViewById(R.id.rvRestaurants);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RecyclerView.Adapter<RestaurantViewHolder>() {

            @Override
            public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(
                        android.R.layout.simple_list_item_1, parent, false);
                RestaurantViewHolder vh = new RestaurantViewHolder(v);

                return vh;
            }

                @Override
                public void onBindViewHolder(RestaurantViewHolder vh, int position) {
                TextView tv = (TextView) vh.itemView;
                tv.setText(MySingleton.getInstance().restList.get(position));
                tv.setCompoundDrawablePadding(30);
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_food_icon_round, 0, 0, 0);
            }

            @Override
            public int getItemCount() {
                return MySingleton.getInstance().restaurantList.size();
            }
        });
    }

    private class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RestaurantViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Toast.makeText(getApplicationContext(), "View: " + ((TextView) v).getText(), Toast.LENGTH_LONG).show();

            Intent viewRest = new Intent(MainActivity.this, ViewRestaurant.class);
            final Restaurant getRest = (Restaurant) getIntent().getSerializableExtra("restObj");
            final Restaurant restCurr = MySingleton.getInstance().restaurantList.get(getRest.getName());
            viewRest.putExtra("restObj", restCurr);

            startActivity(viewRest);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.add:
            Toast.makeText(getApplicationContext(), "Add Restaurant", Toast.LENGTH_SHORT).show();

            Intent addRest = new Intent(MainActivity.this, AddRestaurant.class);
            startActivity(addRest);

            return(true);

        case R.id.clear:
            Toast.makeText(getApplicationContext(), "Cleared List", Toast.LENGTH_SHORT).show();

            MySingleton.getInstance().restaurantList.clear();
            MySingleton.getInstance().restList.clear();

            finish();
            startActivity(getIntent());

            return(true);

        case R.id.load:
            Toast.makeText(getApplicationContext(), "Load List", Toast.LENGTH_SHORT).show();

            Log.i(TAG, readFile(this));

            finish();
            startActivity(getIntent());

            loaded = true;
            Intent intent = new Intent(MainActivity.this, ViewRestaurant.class);
            intent.putExtra("sendLoaded", loaded);

            return(true);

        case R.id.preferences:
            Toast.makeText(getApplicationContext(), "Preferences", Toast.LENGTH_SHORT).show();

            Intent pref = new Intent(MainActivity.this, Preferences.class);
            startActivity(pref);

            return(true);

        case R.id.sortAlphabetically:
            Toast.makeText(getApplicationContext(), "Sort List Alphabetically", Toast.LENGTH_SHORT).show();

            Collections.sort(MySingleton.getInstance().restList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });

            finish();
            startActivity(getIntent());

    }
        return(super.onOptionsItemSelected(item));
    }

    public String readFile(Context ctx) {
        InputStream input = ctx.getResources().openRawResource(R.raw.samplelist);

        InputStreamReader inputReader = new InputStreamReader(input);
        BufferedReader bufferReader = new BufferedReader(inputReader);
        String line;
        StringBuilder text = new StringBuilder();

        int lineNum = 0;

        String name = "";
        String phone = "";
        String website = "";
        float rating = 0.0f;
        String category = "";

        try{
            while((line = bufferReader.readLine()) != null) {
                if(lineNum == 0){
                    name = line;
                    lineNum++;
                }
                else if (lineNum == 1)
                {
                    phone = line;
                    lineNum++;
                }
                else if (lineNum == 2)
                {
                    website = line;
                    lineNum++;
                }
                else if (lineNum == 3)
                {
                    rating = Float.valueOf(line);
                    lineNum++;
                }
                else if (lineNum == 4)
                {
                    category = line;
                    lineNum++;
                }

                text.append(line);
                text.append('\n');
            }
        }
        catch(IOException e) {
            return null;
        }

        Restaurant loadRest = new Restaurant(name, phone, website, rating, category);

        MySingleton.getInstance().restaurantList.put("loadRest", loadRest);
        MySingleton.getInstance().restList.add(0, loadRest.toString());

        Intent intent = new Intent(MainActivity.this, ViewRestaurant.class);
        intent.putExtra("loadObj", loadRest);

        return text.toString();
    }
}

