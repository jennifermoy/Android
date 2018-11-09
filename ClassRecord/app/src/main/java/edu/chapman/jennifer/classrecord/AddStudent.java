package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import static edu.chapman.jennifer.classrecord.listClass.sendClassNum;
import static edu.chapman.jennifer.classrecord.listClass.sendPosition;

public class AddStudent extends Activity {

    Button addStudent;
    EditText firstName;
    EditText lastName;
    EditText studNum;

    String fname;
    String lname;
    String studnum;

    static String TAG = "AddStudent";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addstudent);

        addStudent = findViewById(R.id.bnAddStudent);
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        studNum = findViewById(R.id.etStudentNum);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("sendName");
        final String num = intent.getStringExtra(sendClassNum);
        final int pos = intent.getIntExtra(sendPosition, 0);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showClassActivity = new Intent(AddStudent.this, ShowClass.class);

                fname = firstName.getText().toString();
                lname = lastName.getText().toString();
                studnum = studNum.getText().toString();

                showClassActivity.putExtra("sendName", name);
                showClassActivity.putExtra(sendClassNum, num);
                showClassActivity.putExtra(sendPosition, pos);

                Log.i(TAG, name);

                MySingleton.getInstance().classList.get(name).add(fname);

                startActivity(showClassActivity);
            }
        });


    }
}
