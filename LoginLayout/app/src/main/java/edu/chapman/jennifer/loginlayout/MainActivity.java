package edu.chapman.jennifer.loginlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;
import android.os.AsyncTask;

public class MainActivity extends Activity {

    Button btn;
    EditText username;
    EditText password;
    TextView error;

    static String TAG = "MainActivity";


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_linear);
        //setContentView(R.layout.layout_relative);

        username = findViewById(R.id.etUsername);
        btn = findViewById(R.id.bnLogin);
        password = (EditText)findViewById(R.id.etPassword);
        error = findViewById(R.id.txtError);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if(password.getText().toString().equals("Chapman")){
                    //finish();
                }
                else {
                    error.setText("Invalid Password");
                }
                */

                Intent asyncTask = new Intent(MainActivity.this, AsyncTaskActivity.class);
                //Intent loggedInScreen = new Intent(MainActivity.this, SuccessfulLogin.class);

                Log.i(TAG, "Before startActivity");

                asyncTask.putExtra("name", username.getText().toString());
                asyncTask.putExtra("password", password.getText().toString());

                startActivity(asyncTask);

                Log.i(TAG, "After startActivity");

                finish();

            }
        });
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        finish();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, "onResume");

    }
}
