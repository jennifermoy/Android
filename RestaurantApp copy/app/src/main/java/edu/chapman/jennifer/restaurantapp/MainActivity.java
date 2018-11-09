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

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable{

    static String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    /*private class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RestaurantViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Toast.makeText(getApplicationContext(), "you clicked: " + ((TextView) v).getText(), Toast.LENGTH_LONG).show();

            Intent viewRest = new Intent(MainActivity.this, ViewRestaurant.class);
            viewRest.putExtra("viewRestName", ((TextView) v).getText());
            viewRest.putExtra("restObj", getIntent().getSerializableExtra("restObj"));

            startActivity(viewRest);
        }
    }*/

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        private String[] mDataset;

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView mTextView;
            public MyViewHolder(TextView v){
                super(v);
                mTextView = v;
            }
        }

        //@Override
        public void onClick(View v){
            Toast.makeText(getApplicationContext(), "you clicked: " + ((TextView) v).getText(), Toast.LENGTH_LONG).show();

            Intent viewRest = new Intent(MainActivity.this, ViewRestaurant.class);
            viewRest.putExtra("viewRestName", ((TextView) v).getText());
            viewRest.putExtra("restObj", getIntent().getSerializableExtra("restObj"));

            startActivity(viewRest);
        }

        public MyAdapter(String[] mDataset){
            mDataset = mDataset;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.listrestaurants, parent, false);
            MyViewHolder vh = new MyViewHolder(v);

            return vh;
        }

        public void onBindViewHolder(MyViewHolder holder, int position){
            holder.mTextView.setText(mDataset[position]);
        }

        @Override
        public int getItemCount(){
            return mDataset.length;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listrestaurants);

        recyclerView = findViewById(R.id.rvRestaurants);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        final Restaurant restCurr = (Restaurant) getIntent().getSerializableExtra("restObj");

        /*recyclerView.setAdapter(new RecyclerView.Adapter<RestaurantViewHolder>() {

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
                tv.setText(MySingleton.getInstance().restaurantList.get(restCurr.getName()));
                tv.setCompoundDrawablePadding(34);
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_food_icon_round, 0, 0, 0);
            }

            @Override
            public int getItemCount() {
                return MySingleton.getInstance().restaurantList.size();
            }
        });*/
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
            Toast.makeText(getApplicationContext(), "Add Restaurant", Toast.LENGTH_SHORT).show();

            Intent addRest = new Intent(MainActivity.this, AddRestaurant.class);
            startActivity(addRest);

            return(true);

        case R.id.clear:
            Toast.makeText(getApplicationContext(), "Cleared List", Toast.LENGTH_SHORT).show();

            MySingleton.getInstance().restaurantList.clear();

            finish();
            startActivity(getIntent());

            return(true);

        case R.id.load:
            Toast.makeText(getApplicationContext(), "Load List", Toast.LENGTH_SHORT).show();

            Log.i(TAG, readFile(this));

            return(true);

        case R.id.preferences:
            Toast.makeText(getApplicationContext(), "Preferences", Toast.LENGTH_SHORT).show();

            Intent pref = new Intent(MainActivity.this, Preferences.class);
            startActivity(pref);

            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }

    public static String readFile(Context ctx) {
        InputStream input = ctx.getResources().openRawResource(R.raw.samplelist);

        InputStreamReader inputReader = new InputStreamReader(input);
        BufferedReader bufferReader = new BufferedReader(inputReader);
        String line;
        StringBuilder text = new StringBuilder();

        try{
            while((line = bufferReader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        }
        catch(IOException e) {
            return null;
        }
        return text.toString();
    }
}

