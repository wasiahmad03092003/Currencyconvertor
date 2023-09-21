package com.example.currencyconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText;
    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private Button convertButton;
    private TextView resultTextView;

    // Sample currency conversion rates (replace with actual rates)
    private double usdToEurRate = 0.85;
    private double usdToGbpRate = 0.73;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Populate spinner with currency options (you can add more currencies)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String amountStr = amountEditText.getText().toString();
        if (amountStr.isEmpty()) {
            resultTextView.setText("Please enter an amount.");
            return;
        }

        double amount = Double.parseDouble(amountStr);
        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        double convertedAmount = convert(amount, fromCurrency, toCurrency);
        String result = String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
        resultTextView.setText(result);
    }

    private double convert(double amount, String fromCurrency, String toCurrency) {
        // Replace with actual currency conversion logic using real exchange rates
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return amount * usdToEurRate;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            return amount * usdToGbpRate;
        } else {
            return amount; // Default to no conversion
        }
    }
}
