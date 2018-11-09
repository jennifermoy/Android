package com.example.jennifer.widgets_inclass;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;


public class WidgetActivity extends Activity {

    private Switch swtch;
    private Button btn;

    private static String TAG = "Widget2Activity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);

        Log.d(TAG, "onCreate()");

        //swtch = new Switch(this);
        btn = new Button(this);

        //setContentView(swtch);
        //setContentView(btn);

        //swtch.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View v) {
                //Log.d(TAG, "Switch Clicked");
            //}
       // });

        btn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Log.d(TAG, "Button Clicked");
            }
        });
    }
}
