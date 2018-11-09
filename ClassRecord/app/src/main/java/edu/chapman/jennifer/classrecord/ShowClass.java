package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static edu.chapman.jennifer.classrecord.listClass.sendClassNum;
import static edu.chapman.jennifer.classrecord.listClass.sendPosition;
//import static edu.chapman.jennifer.classrecord.listClass.sendName;


public class ShowClass extends Activity {

    static String TAG = "ShowClass";

    Button bnEdit;
    Button listClass;
    TextView txtName;
    TextView txtNum;

    ListView lvStudents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.showclass);

        Log.i(TAG, "ShowClass");

        bnEdit = findViewById(R.id.bnEdit);
        listClass = findViewById(R.id.bnListClass);
        txtName = findViewById(R.id.txtDisplayName);
        txtNum = findViewById(R.id.txtDisplayClassNum);

        lvStudents = findViewById(R.id.lvStudents);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("sendName");
        final String num = intent.getStringExtra(sendClassNum);
        final int pos = intent.getIntExtra("sendPosition", 0);

        txtName.setText(name);
        txtNum.setText(num);

        Log.i(TAG, name);
        Log.i(TAG, num);
        Log.i(TAG, String.valueOf(pos));

        //if key does not exist in hashmap, create new key and value
        if(MySingleton.getInstance().classList.get(name) == null)
        {
            MySingleton.getInstance().classList.put(name, new ArrayList<String>());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MySingleton.getInstance().classList.get(name));
        lvStudents.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        bnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editClassActivity = new Intent(ShowClass.this, EditClass.class);
                editClassActivity.putExtra("sendName", name);
                editClassActivity.putExtra(sendClassNum, num);
                editClassActivity.putExtra(sendPosition, pos);

                startActivity(editClassActivity);
            }
        });

        listClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listClassActivity = new Intent(ShowClass.this, listClass.class);
                listClassActivity.putExtra("sendName", name);
                listClassActivity.putExtra("sendClassNum", num);

                Log.i(TAG, String.valueOf(pos));

                startActivity(listClassActivity);
            }
        });

    }
}
