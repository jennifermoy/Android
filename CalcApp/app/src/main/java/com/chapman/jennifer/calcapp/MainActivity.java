package com.chapman.jennifer.calcapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chapman.jennifer.calcapp.databinding.MainActivityBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    private Double val1 = Double.NaN;
    private Double val2;

    private DecimalFormat decimalFormat;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        binding.bnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.bn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.bn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.bn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.bn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.bn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.bn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.bn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.bnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                binding.textView.setText(decimalFormat.format(val1) + "+");
                binding.editText.setText(null);
            }
        });

        binding.bnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                binding.textView.setText(decimalFormat.format(val1) + "-");
                binding.editText.setText(null);
            }
        });

        binding.bnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLY;
                binding.textView.setText(decimalFormat.format(val1) + "*");
                binding.editText.setText(null);
            }
        });

        binding.bnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                binding.textView.setText(decimalFormat.format(val1) + "/");
                binding.editText.setText(null);
            }
        });

        binding.bnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.textView.setText(binding.textView.getText().toString() +
                        decimalFormat.format(val2) + " = " + decimalFormat.format(val1));
                val1 = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.bnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
                }
                else {
                    val1 = Double.NaN;
                    val2 = Double.NaN;
                    binding.editText.setText("");
                    binding.textView.setText("");
                }
            }
        });

        binding.bnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val1 = Double.NaN;
                val2 = Double.NaN;
                binding.editText.setText("");
                binding.textView.setText("");
            }
        });

    }

    private void computeCalculation() {
        if(!Double.isNaN(val1)){
            val2 = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if(CURRENT_ACTION == ADDITION) {
                val1 = this.val1 + val2;
            }
            else if(CURRENT_ACTION == SUBTRACTION) {
                val1 = this.val1 - val2;
            }
            else if(CURRENT_ACTION == MULTIPLY) {
                val1 = this.val1 * val2;
            }
            else if(CURRENT_ACTION == DIVISION) {
                val1 = this.val1 / val2;
            }
        }

        else {
            try{
                val1 = Double.parseDouble(binding.editText.getText().toString());
            }
            catch (Exception e) {

            }
        }
    }
}
