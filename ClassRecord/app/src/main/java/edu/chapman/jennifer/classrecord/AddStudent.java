package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static edu.chapman.jennifer.classrecord.listClass.sendClassNum;
import static edu.chapman.jennifer.classrecord.listClass.sendName;

public class AddStudent extends Activity {

    Button addStudent;
    EditText firstName;
    EditText lastName;
    EditText studNum;

    ArrayList<String> firstNameArray = new ArrayList<String>();
    public static final String sendStudents = "students";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addstudent);

        addStudent = findViewById(R.id.bnAddStudent);
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        studNum = findViewById(R.id.etStudentNum);

        Intent intent = getIntent();
        final String name = intent.getStringExtra(sendName);
        final String num = intent.getStringExtra(sendClassNum);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showClassActivity = new Intent(AddStudent.this, ShowClass.class);
                showClassActivity.putExtra(sendName, name);
                showClassActivity.putExtra(sendClassNum, num);
                showClassActivity.putStringArrayListExtra("students", (ArrayList<String>) firstNameArray);
                //showClassActivity.putExtra(sendStudents, students);

                startActivity(showClassActivity);
            }
        });


    }
}
