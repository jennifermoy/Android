package edu.chapman.jennifer.filmdatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Controller extends Activity{

    private static String TAG = "Controller";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.filmlist);
    }

    private View.OnClickListener mStartListener = new View.OnClickListener() {
        public void onClick(View v) {
            startService(new Intent(Controller.this, LocalService.class));
        }
    };
}
