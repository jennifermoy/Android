package edu.chapman.jennifer.filmdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class AddFilm extends AppCompatActivity{

    private SQLiteDatabase mDb;

    private FilmDbAdapter dbHelper;

    Button insert;
    EditText filmName, releaseDate, fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfilm);

        insert = findViewById(R.id.bnInsert);
        filmName = findViewById(R.id.etFilmName);
        releaseDate = findViewById(R.id.etReleaseDate);
        fileName = findViewById(R.id.etFileName);

        dbHelper = new FilmDbAdapter(this);
        dbHelper.open();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = filmName.getText().toString();
                String date = releaseDate.getText().toString();
                String file = fileName.getText().toString();

                Log.i("AddFilm", name + " " + date + " " + file);

                dbHelper.createFilm(name, date, file);
                dbHelper.close();

                Intent intent = new Intent(AddFilm.this, AndroidListViewCursorAdapter.class);
                startActivity(intent);
            }
        });
    }
}
