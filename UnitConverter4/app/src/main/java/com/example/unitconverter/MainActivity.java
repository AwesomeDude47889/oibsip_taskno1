package com.example.unitconverter;

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

    Spinner spinnerCategory;
    Spinner spinnerFrom;
    Spinner spinnerTo;

    EditText etValue;
    Button btnConvert;
    TextView tvResult;

    String[] categories = {
            "Weight",
            "Distance"
    };

    String[] weightUnits = {
            "Grams",
            "Kilograms",
            "Pounds",
            "Ounces"
    };

    String[] distanceUnits = {
            "Millimeters",
            "Centimeters",
            "Meters",
            "Kilometers",
            "Inches",
            "Feet",
            "Miles"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);

        etValue = findViewById(R.id.etValue);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        categories);

        categoryAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(categoryAdapter);

        updateUnits();

        spinnerCategory.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        updateUnits();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etValue.getText().toString();
                if (input.isEmpty()) {
                    tvResult.setText("Please enter a value");
                    return;
                }

                double value = Double.parseDouble(input);
                String category = spinnerCategory.getSelectedItem().toString();
                String fromUnit = spinnerFrom.getSelectedItem().toString();
                String toUnit = spinnerTo.getSelectedItem().toString();
                double result;

                if (category.equals("Weight")) {
                    result = convertWeight(value, fromUnit, toUnit);
                }
                else {
                    result = convertDistance(value, fromUnit, toUnit);
                }

                tvResult.setText("Result: " + result);
            }
        });
    }

    private void updateUnits() {
        String category;

        if (spinnerCategory.getSelectedItem() == null) {
            category = "Weight";
        }
        else {
            category = spinnerCategory.getSelectedItem().toString();
        }

        String[] units;

        if (category.equals("Weight")) {
            units = weightUnits;
        }
        else {
            units = distanceUnits;
        }

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(unitAdapter);
        spinnerTo.setAdapter(unitAdapter);
    }

    private double convertWeight(double value, String fromUnit, String toUnit) {

        double grams;

        if (fromUnit.equals("Grams")) {
            grams = value;
        }
        else if (fromUnit.equals("Kilograms")) {
            grams = value * 1000;
        }
        else if (fromUnit.equals("Pounds")) {
            grams = value * 453.592;
        }
        else {
            grams = value * 28.3495;
        }

        if (toUnit.equals("Grams")) {
            return grams;
        }
        else if (toUnit.equals("Kilograms")) {
            return grams / 1000;
        }
        else if (toUnit.equals("Pounds")) {
            return grams / 453.592;
        }
        else {
            return grams / 28.3495;
        }
    }

    private double convertDistance(double value, String fromUnit, String toUnit) {

        double meters;

        if (fromUnit.equals("Millimeters")) {
            meters = value / 1000;
        }
        else if (fromUnit.equals("Centimeters")) {
            meters = value / 100;
        }
        else if (fromUnit.equals("Meters")) {
            meters = value;
        }
        else if (fromUnit.equals("Kilometers")) {
            meters = value * 1000;
        }
        else if (fromUnit.equals("Inches")) {
            meters = value * 0.0254;
        }
        else if (fromUnit.equals("Feet")) {
            meters = value * 0.3048;
        }
        else {
            meters = value * 1609.34;
        }

        if (toUnit.equals("Millimeters")) {
            return meters * 1000;
        }
        else if (toUnit.equals("Centimeters")) {
            return meters * 100;
        }
        else if (toUnit.equals("Meters")) {
            return meters;
        }
        else if (toUnit.equals("Kilometers")) {
            return meters / 1000;
        }
        else if (toUnit.equals("Inches")) {
            return meters / 0.0254;
        }
        else if (toUnit.equals("Feet")) {
            return meters / 0.3048;
        }
        else {
            return meters / 1609.34;
        }
    }
}