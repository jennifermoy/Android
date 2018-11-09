package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class NewClass extends Activity {

    EditText name;
    EditText classNum;

    Button addClass;
    Button clrAll;

    static String TAG = "NewClass";

    public static final String sendName = "className";
    public static final String sendClassNum = "classNumber";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newclass);

        Log.i(TAG, "NewClass");

        name = findViewById(R.id.etClassName);
        classNum = findViewById(R.id.etClassNum);
        addClass = findViewById(R.id.bnAddClass);
        clrAll = findViewById(R.id.bnClearClass);

        addClass.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent listActivity = new Intent(NewClass.this, listClass.class);
                String className = name.getText().toString();
                String classNumber = classNum.getText().toString();

                MySingleton.getInstance().classNames.add(className);
                MySingleton.getInstance().classNumbers.add(classNumber);

                listActivity.putExtra("sendName", className);
                listActivity.putExtra("sendClassNum", classNumber);

                startActivity(listActivity);
            }
        });

        clrAll.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                name.setText("");
                classNum.setText("");
            }
        });
    }
}
