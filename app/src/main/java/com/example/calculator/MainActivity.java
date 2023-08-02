package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private String currentInput = "";
    private double result = 0;
    private String operator = "";
    private boolean clearInput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
    }

    public void onNumberClick(View view) {
        if (clearInput) {
            currentInput = "";
            clearInput = false;
        }

        Button button = (Button) view;
        currentInput += button.getText().toString();
        textResult.setText(currentInput);
    }

    public void onOperatorClick(View view) {
        if (currentInput.isEmpty()) {
            return;
        }

        Button button = (Button) view;
        String newOperator = button.getText().toString();

        if (!operator.isEmpty()) {
            performCalculation();
            DecimalFormat decimalFormat = new DecimalFormat("#.##########");
            String formattedResult = decimalFormat.format(result);
            textResult.setText(formattedResult);
        } else {
            result = Double.parseDouble(currentInput);
        }

        operator = newOperator;
        clearInput = true;
    }

    public void onEqualClick(View view) {
        if (operator.isEmpty()) {
            return;
        }

        if (!currentInput.isEmpty()) {
            performCalculation();
            DecimalFormat decimalFormat = new DecimalFormat("#.##########");
            String formattedResult = decimalFormat.format(result);
            textResult.setText(formattedResult);

            currentInput = formattedResult;
            operator = "";
        }
    }

    public void onDecimalClick(View view) {
        if (clearInput) {
            currentInput = "0.";
            clearInput = false;
        } else if (!currentInput.contains(".")) {
            currentInput += ".";
        }
        textResult.setText(currentInput);
    }

    private void performCalculation() {
        double secondNumber = Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                result += secondNumber;
                break;
            case "-":
                result -= secondNumber;
                break;
            case "*":
                result *= secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result /= secondNumber;
                } else {
                    textResult.setText("Error");
                    result = 0;
                    operator = "";
                    currentInput = "";
                    clearInput = true;
                    return;
                }
                break;
        }

        currentInput = "";
    }
}
