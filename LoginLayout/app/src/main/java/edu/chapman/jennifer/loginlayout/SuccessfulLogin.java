package edu.chapman.jennifer.loginlayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SuccessfulLogin extends Activity {

    static String TAG = "SuccessfulLogin";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen2);
        Log.i(TAG, "onCreate");

        TextView txtName = findViewById(R.id.txtName);
        Button bnClose = findViewById(R.id.bnClose);
        Button bnSignOut = findViewById(R.id.bnSignOut);

        Intent i = getIntent();

        String name = i.getStringExtra("name");

        txtName.setText(name);

        Toast.makeText(SuccessfulLogin.this, "Successful Login", Toast.LENGTH_LONG).show();

        bnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater mInflater;
                mInflater = LayoutInflater.from(SuccessfulLogin.this);

                final View newView = mInflater.inflate(R.layout.dialog, null);
                Button ok = newView.findViewById(R.id.bnOk);
                Button cancel = newView.findViewById(R.id.bnCancel);

                final AlertDialog confirm = new AlertDialog.Builder(SuccessfulLogin.this)
                        .setTitle("Confirm Sign Out")
                        .setMessage("Are you sure you want to sign out?").setView(newView)
                        .show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mainActivity = new Intent(SuccessfulLogin.this, MainActivity.class);

                        startActivity(mainActivity);
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        finish();
    }
}
