package edu.chapman.jennifer.filmdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FilmList extends AppCompatActivity {

    Button addFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmlist);

        addFilm = findViewById(R.id.bnAddFilm);

        addFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFilm = new Intent(FilmList.this, AddFilm.class);
                startActivity(addFilm);
            }
        });
    }
}
