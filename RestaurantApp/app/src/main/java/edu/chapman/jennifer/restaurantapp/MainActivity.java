package edu.chapman.jennifer.restaurantapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> restaurantList = new ArrayList<>();

    private final String[] rList = new String[] {
            "Chipotle",
            "Taco Bell",
            "McDonalds"
    };

    private class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RestaurantViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Toast.makeText(getApplicationContext(), "you clicked: " + ((TextView) v).getText(), Toast.LENGTH_LONG).show();
        }
    }

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
                tv.setText(rList[position]);
                tv.setCompoundDrawablePadding(24);
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_test, 0, 0, 0);
            }

            @Override
            public int getItemCount() {
                return rList.length;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add:
            Toast.makeText(getApplicationContext(), "you clicked: add", Toast.LENGTH_LONG).show();
            return(true);
        case R.id.clear:
            Toast.makeText(getApplicationContext(), "you clicked: clear", Toast.LENGTH_LONG).show();
            return(true);
        case R.id.load:
            Toast.makeText(getApplicationContext(), "you clicked: load", Toast.LENGTH_LONG).show();
            return(true);
        case R.id.preferences:
            Toast.makeText(getApplicationContext(), "you clicked: preferences", Toast.LENGTH_LONG).show();
            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }
}

