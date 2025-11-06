package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.calculator.core.CalculatorEngine;

public class MainActivity extends AppCompatActivity {

    TextView txtDisplay;
    CalculatorEngine engine = new CalculatorEngine();
    String currentInput = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDisplay = findViewById(R.id.txtDisplay);

        GridLayout grid = findViewById(R.id.gridButtons);

        for (int i = 0; i < grid.getChildCount(); i++) {
            Button b = (Button) grid.getChildAt(i);
            b.setOnClickListener(btnClick);
        }
    }

    View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String text = b.getText().toString();

            switch (text) {

                case "C":
                    engine.reset();
                    currentInput = "0";
                    txtDisplay.setText("0");
                    break;

                case "⌫":
                    if (currentInput.length() > 1)
                        currentInput = currentInput.substring(0, currentInput.length() - 1);
                    else
                        currentInput = "0";
                    txtDisplay.setText(currentInput);
                    break;

                case "±":
                    currentInput = engine.plusMinus(currentInput);
                    txtDisplay.setText(currentInput);
                    break;

                case "√":
                    currentInput = engine.sqrt(currentInput);
                    txtDisplay.setText(currentInput);
                    break;

                case "+":
                case "-":
                case "×":
                case "÷":
                case "=":
                    currentInput = engine.process(currentInput, text);
                    txtDisplay.setText(currentInput);
                    break;

                case ".":
                    if (!currentInput.contains(".")) currentInput += ".";
                    txtDisplay.setText(currentInput);
                    break;

                default: // numbers
                    if (currentInput.equals("0") || engine.shouldClearNext()) {
                        currentInput = text;
                        engine.clearNextDone();
                    } else {
                        currentInput += text;
                    }
                    txtDisplay.setText(currentInput);

            }
        }
    };
}
