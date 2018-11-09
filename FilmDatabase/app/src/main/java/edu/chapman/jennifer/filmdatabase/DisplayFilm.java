package edu.chapman.jennifer.filmdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayFilm extends AppCompatActivity {

    static String TAG = "DisplayFilm";

    private FilmDbAdapter dbHelper;

    Button stopPlaying, deleteFilm;
    TextView name, date, file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayfilm);

        dbHelper = new FilmDbAdapter(this);
        dbHelper.open();

        stopPlaying = findViewById(R.id.bnStopPlaying);
        deleteFilm = findViewById(R.id.bnDeleteFilm);
        TextView name = findViewById(R.id.txtDisplayName);
        TextView date = findViewById(R.id.txtDisplayReleaseDate);
        TextView file = findViewById(R.id.txtDisplayFileName);

        Intent intent = getIntent();
        String filmName = intent.getStringExtra("sendFilmName");

        Cursor cursor = dbHelper.fetchFilmByName(MySingleton.getInstance().curr_FilmName);
        cursor.moveToFirst();

        name.setText(cursor.getString(2));
        date.setText(cursor.getString(3));
        file.setText(cursor.getString(4));

        stopPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), LocalService.class));
            }
        });

        deleteFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), LocalService.class));

                dbHelper.delete(MySingleton.getInstance().curr_FilmName);
            }
        });
    }
}
