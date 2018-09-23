package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static edu.chapman.jennifer.classrecord.AddStudent.sendStudents;
import static edu.chapman.jennifer.classrecord.listClass.sendClassNum;
import static edu.chapman.jennifer.classrecord.listClass.sendName;

public class EditClass extends Activity{

    EditText className;
    EditText classNum;
    Button addStudent;
    Button save;
    Button delete;
    ListView studentList;

    ArrayList<String> students = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editclass);

        className = findViewById(R.id.etClassName);
        classNum = findViewById(R.id.etClassNum);
        addStudent = findViewById(R.id.bnAddStudent);
        save = findViewById(R.id.bnSave);
        delete = findViewById(R.id.bnDelete);
        studentList = findViewById(R.id.lvStudents);

        //retrieving class name and number
        Intent intent = getIntent();
        final String name = intent.getStringExtra(sendName);
        final String num = intent.getStringExtra(sendClassNum);

        className.setText(name);
        classNum.setText(num);

        /*students = getIntent().getStringArrayListExtra("students");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
        studentList.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addStudentActivity = new Intent(EditClass.this, AddStudent.class);
                addStudentActivity.putExtra(sendName, name);
                addStudentActivity.putExtra(sendClassNum, num);
                startActivity(addStudentActivity);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showClassActivity = new Intent(EditClass.this, ShowClass.class);
                String newName = className.getText().toString();
                String newNum = classNum.getText().toString();

                showClassActivity.putExtra(sendName, newName);
                showClassActivity.putExtra(sendClassNum, newNum);

                startActivity(showClassActivity);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listClassActivity = new Intent(EditClass.this, listClass.class);


                startActivity(listClassActivity);
            }
        });
    }
}
