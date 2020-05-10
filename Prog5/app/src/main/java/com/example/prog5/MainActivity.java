package com.example.prog5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Jake Ippolito
 * @author Krushn Gor
 * Set the diplay objectives for first screen
 */
public class MainActivity extends AppCompatActivity {

    protected double bmi;
    private final double BMI_IMPERIAL_CONSTANT = 703;
    private EditText Height;
    private EditText Weight;
    private RadioButton Imperial;
    private RadioButton Metric;
    private TextView BMI;
    private Button Calc;
    private Button GetInfo;
    private ConstraintLayout bmiContainer;

    /**
     * Override method from AppCompatActivity class!
     * the method will calculate the BMI based on user input and get you a link to the advice screen
     * it will save the calculated and regarding data in the local variables
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Height = findViewById(R.id.Height);
        Weight = findViewById(R.id.Weight);
        Imperial = findViewById(R.id.radioImperial);
        Metric = findViewById(R.id.radioMetric);
        BMI = findViewById(R.id.bmi);
        Calc = findViewById(R.id.Calculate);
        GetInfo = findViewById(R.id.GetInfo);
        bmiContainer = findViewById(R.id.bmiContainer);

        Calc.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View V) {
                Double w, h;
                String weight = Weight.getText().toString();
                String height = Height.getText().toString();

                if(Weight.getText().toString().length() > 0 &&
                        Height.getText().toString().length() > 0) {
                    w = Double.parseDouble(weight);
                    h = Double.parseDouble(height);

                    if(h <= 0 || w <= 0) {
                        Toast.makeText(getApplicationContext(), "Invalid Height or Weight",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (Imperial.isChecked())
                        bmi = (w * BMI_IMPERIAL_CONSTANT / (h * h));
                    if (Metric.isChecked())
                        bmi = (w / (h * h));

                    int truncate = (int) (bmi * 1000);
                    bmi = truncate;
                    bmi = bmi / 1000;
                    bmi = (bmi + bmi % 0.01);
                    truncate = (int) (bmi * 100);
                    bmi = (double) truncate;
                    bmi = bmi / 100;

                    if (bmi < 18.5) {
                        bmiContainer.setBackgroundColor(getColor(R.color.underWeight));
                    }
                    if (bmi >= 18.5 && bmi < 25) {
                        bmiContainer.setBackgroundColor(getColor(R.color.normalWeight));
                    }
                    if (bmi >= 25 && bmi < 30) {
                        bmiContainer.setBackgroundColor(getColor(R.color.overWeight));
                    }
                    if (bmi >= 30 && bmi < 35) {
                        bmiContainer.setBackgroundColor(getColor(R.color.obese));
                    }
                    if (bmi > 35) {
                        bmiContainer.setBackgroundColor(getColor(R.color.extraObese));
                    }

                    BMI.setText(Double.toString(bmi));
                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid Height or Weight",
                            Toast.LENGTH_SHORT).show();
            }
        });

        Imperial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Height.setHint(getString(R.string.text_imperial_height));
                Weight.setHint(getString(R.string.text_imperial_weight));
            }
        });

        Metric.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Height.setHint(getString(R.string.text_metric_height));
                Weight.setHint(getString(R.string.text_metric_weight));
            }
        });

        GetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){

                if(Weight.getText().toString().length() == 0 ||
                        Height.getText().toString().length() == 0)
                    Toast.makeText(getApplicationContext(),"Invalid Height or Weight",
                            Toast.LENGTH_SHORT).show();
                else if (bmi == 0)
                    Toast.makeText(getApplicationContext(),"Calculate Your BMI First",
                            Toast.LENGTH_SHORT).show();
                else {
                    Intent a = new Intent(MainActivity.this, GetInfoActivity.class);
                    a.putExtra("BMI", bmi);
                    startActivity(a);
                }
            }
        });


    }

}
