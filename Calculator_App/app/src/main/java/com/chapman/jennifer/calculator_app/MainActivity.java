package com.chapman.jennifer.calculator_app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chapman.jennifer.calculator_app.databinding.MainActivityBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    private Double val1 = Double.NaN;
    private Double val2;

    private DecimalFormat decimalFormat;

    private static final char add = '+';
    private static final char subtract = '-';
    private static final char multiply = '*';
    private static final char divide = '/';

    private char curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        decimalFormat = new DecimalFormat("#.##########");
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        //setting each button's functionality
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
                curr = add;
                binding.textView.setText(decimalFormat.format(val1) + "+");
                binding.editText.setText(null);
            }
        });

        binding.bnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                curr = subtract;
                binding.textView.setText(decimalFormat.format(val1) + "-");
                binding.editText.setText(null);
            }
        });

        binding.bnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                curr = multiply;
                binding.textView.setText(decimalFormat.format(val1) + "*");
                binding.editText.setText(null);
            }
        });

        binding.bnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                curr = divide;
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

                //setting the history textview
                binding.textView2.setText(binding.textView.getText().toString());

                //resetting the value and current operation
                val1 = Double.NaN;
                curr = '0';
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
                    val1 = null;
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

            if(curr == add) {
                val1 = this.val1 + val2;
            }
            else if(curr == subtract) {
                val1 = this.val1 - val2;
            }
            else if(curr == multiply) {
                val1 = this.val1 * val2;
            }
            else if(curr == divide) {
                val1 = this.val1 / val2;
            }
        }

        else {
            try{
                val1 = Double.parseDouble(binding.editText.getText().toString());
            }
            catch (Exception e) {
                val1 = Double.NaN;
                val2 = Double.NaN;
                binding.editText.setText("");
                binding.textView.setText("");
                binding.textView2.setText("");
            }
        }
    }
}
