package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static edu.chapman.jennifer.classrecord.AddStudent.sendStudents;
import static edu.chapman.jennifer.classrecord.listClass.sendClassNum;
import static edu.chapman.jennifer.classrecord.listClass.sendName;

public class ShowClass extends Activity {

    Button bnEdit;
    Button listClass;
    TextView txtName;
    TextView txtNum;

    ListView studentsList;

    ArrayList<String> studentArray = new ArrayList<>();
    ArrayList<String> firstNameArray = new ArrayList<String>();
    public static final String sendStudents = "students";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.showclass);

        bnEdit = findViewById(R.id.bnEdit);
        listClass = findViewById(R.id.bnListClass);
        txtName = findViewById(R.id.txtDisplayName);
        txtNum = findViewById(R.id.txtDisplayClassNum);

        studentsList = findViewById(R.id.lvStudents);

        Intent intent = getIntent();
        final String name = intent.getStringExtra(sendName);
        final String num = intent.getStringExtra(sendClassNum);
        //final String students = intent.getStringExtra(sendStudents);

        txtName.setText(name);
        txtNum.setText(num);

        firstNameArray = getIntent().getStringArrayListExtra("students");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentArray);
        //studentArray = getIntent().getStringArrayListExtra("students");
        studentsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        bnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editClassActivity = new Intent(ShowClass.this, EditClass.class);
                editClassActivity.putExtra(sendName, name);
                editClassActivity.putExtra(sendClassNum, num);
                //editClassActivity.putExtra(sendStudents, students);
                startActivity(editClassActivity);
            }
        });

        listClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listClassActivity = new Intent(ShowClass.this, listClass.class);
                listClassActivity.putExtra(sendName, name);
                listClassActivity.putExtra(sendClassNum, num);
                startActivity(listClassActivity);
            }
        });

    }
}
