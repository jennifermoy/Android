package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static edu.chapman.jennifer.classrecord.listClass.sendClassNum;
import static edu.chapman.jennifer.classrecord.listClass.sendPosition;

public class EditClass extends Activity{

    EditText className;
    EditText classNum;
    Button addStudent;
    Button save;
    Button delete;
    ListView studentList;

    static String TAG = "EditClass";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editclass);

        Log.i(TAG, "EditClass");

        className = findViewById(R.id.etClassName);
        classNum = findViewById(R.id.etClassNum);
        addStudent = findViewById(R.id.bnAddStudent);
        save = findViewById(R.id.bnSave);
        delete = findViewById(R.id.bnDelete);
        studentList = findViewById(R.id.lvStudents);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("sendName");
        final String num = intent.getStringExtra(sendClassNum);
        final int pos = intent.getIntExtra(sendPosition, 0);

        className.setText(name);
        classNum.setText(num);

        Log.i(TAG, String.valueOf(pos));

        if(MySingleton.getInstance().classList.get(name) == null)
        {
            MySingleton.getInstance().classList.put(name, new ArrayList<String>());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MySingleton.getInstance().classList.get(name));
        studentList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addStudentActivity = new Intent(EditClass.this, AddStudent.class);
                addStudentActivity.putExtra("sendName", name);
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

                showClassActivity.putExtra("sendName", newName);
                showClassActivity.putExtra(sendClassNum, newNum);
                showClassActivity.putExtra(sendPosition, pos);

                MySingleton.getInstance().classNames.set(pos, newName);
                MySingleton.getInstance().classNumbers.set(pos, newNum);

                //updating the key for the hashmap to correspond to the correct student list
                ArrayList<String> temp = MySingleton.getInstance().classList.get(name);
                MySingleton.getInstance().classList.remove(name);
                MySingleton.getInstance().classList.put(newName, temp);

                Log.i(TAG, "pos:");
                Log.i(TAG, String.valueOf(pos));

                startActivity(showClassActivity);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listClassActivity = new Intent(EditClass.this, listClass.class);

                MySingleton.getInstance().classNames.remove(pos);

                startActivity(listClassActivity);
            }
        });
    }
}
