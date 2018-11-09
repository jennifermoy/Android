package edu.chapman.jennifer.stockservice;

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

public class Controller extends Activity {
    private static String TAG = "Controller";

    public HashMap<String, Double> stocks = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.stocklayout);

        Button button = (Button)findViewById(R.id.bnGetStock);
        button.setOnClickListener(mStartListener);

        stocks.put("AMZN", 1755.25);
        stocks.put("AAPL", 216.36);
        stocks.put("BA", 367.47);
    }

    private View.OnClickListener mStartListener = new View.OnClickListener() {
        public void onClick(View v) {
        startService(new Intent(Controller.this, LocalService.class));

        EditText stockName = findViewById(R.id.etStockName);
        String userInput = stockName.getText().toString();
        TextView displayPrice = (TextView)findViewById(R.id.txtStockPrice);
        try {
            displayPrice.setText("$" + stocks.get(userInput).toString());
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Stock not in database", Toast.LENGTH_SHORT).show();
        }
        }
    };
}
