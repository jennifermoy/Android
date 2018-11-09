package edu.chapman.jennifer.loginlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.AsyncTask;

public class AsyncTaskActivity extends Activity {

    protected InitTask initTask;

    static String TAG = "AsyncTaskActivity";

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.asynctask);

        initTask = new InitTask();
        progressBar = findViewById(R.id.progressBar);
        initTask.execute(this);
    }

    protected class InitTask extends android.os.AsyncTask<Context, Integer, String>
    {

        protected boolean cryptoCheck()
        {
            for(int i = 0; i <= 100; ++i)
            {
                try {
                    Thread.sleep(15);
                    publishProgress(i);
                }
                catch(Exception e){

                }
            }

            return true;
        }

        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask", "onPreExecute");

            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected String doInBackground (Context... params)
        {
            cryptoCheck();

            return "COMPLETE";
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            Log.i("AsyncTask", "onProgressUpdate(): " + String.valueOf(values[0]));
            progressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Log.i("AsyncTask", "onPostExecute(): " + result);
            progressBar.setVisibility(View.INVISIBLE);

            Intent nextScreen = new Intent(AsyncTaskActivity.this, SuccessfulLogin.class);

            Intent i = getIntent();
            String name = i.getStringExtra("name");
            nextScreen.putExtra("name", name);

            startActivity(nextScreen);

            finish();

        }

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
