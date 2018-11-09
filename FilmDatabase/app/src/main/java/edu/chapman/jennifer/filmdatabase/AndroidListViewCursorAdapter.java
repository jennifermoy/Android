package edu.chapman.jennifer.filmdatabase;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AndroidListViewCursorAdapter extends Activity {

    private FilmDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmlist);

        dbHelper = new FilmDbAdapter(this);
        dbHelper.open();

        //Generate ListView from SQLite Database
        displayListView();

        Button addFilm = findViewById(R.id.bnAddFilm);

        addFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFilmActivity = new Intent(AndroidListViewCursorAdapter.this, AddFilm.class);
                startActivity(addFilmActivity);
            }
        });
    }

    private void displayListView() {

        Cursor cursor = dbHelper.fetchAllFilms();

        // The desired columns to be bound
        String[] columns = new String[] {
                FilmDbAdapter.KEY_CODE,
                FilmDbAdapter.KEY_NAME,
                FilmDbAdapter.KEY_RELEASEDATE,
                FilmDbAdapter.KEY_FILENAME
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.txtFilmID,
                R.id.txtFilmName,
        };

        // create the adapter using the cursor pointing to the desired data 
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.listcell,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.lvFilmList);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                String filmName =
                        cursor.getString(cursor.getColumnIndexOrThrow("FilmName"));
                Toast.makeText(getApplicationContext(),
                        filmName, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AndroidListViewCursorAdapter.this, DisplayFilm.class);

                intent.putExtra("sendFilmName", filmName);
                Log.i("FILMLIST", filmName);

                MySingleton.getInstance().curr_FilmName = filmName;

                startService(new Intent(AndroidListViewCursorAdapter.this, LocalService.class));

            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbHelper.fetchFilmByName(constraint.toString());
            }
        });

    }
}
