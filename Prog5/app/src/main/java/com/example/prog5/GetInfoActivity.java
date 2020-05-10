package com.example.prog5;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


/**
 * @author Jake Ippolito
 * @author Krushn Gor
 * Set the diplay objectives for second screen
 */
public class GetInfoActivity extends AppCompatActivity {
    private Button Back;
    private ImageView imageBmi;
    private TextView TextInfo;

    /**
     * @param SavedInstanceState sets a Bundle object
     * the method will calculate the BMI based on user input and set advice screen accordingly
     * it will save the calculated and regarding data in the local variables
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_get_info);
        setTitle(R.string.title_info);
        double bmi = getIntent().getDoubleExtra("BMI", 0);
        Back = findViewById(R.id.Back);
        imageBmi = findViewById(R.id.imageBmi);
        TextInfo = findViewById(R.id.textInfo);

        if (bmi < 18.5) {
            imageBmi.setImageResource(R.mipmap.under_weight);
            TextInfo.append(" underweight.");
            TextInfo.setBackgroundColor(getColor(R.color.underWeight));
        }
        if (bmi >= 18.5 && bmi < 25) {
            imageBmi.setImageResource(R.mipmap.normal);
            TextInfo.append(" normal.");
            TextInfo.setBackgroundColor(getColor(R.color.normalWeight));
        }
        if (bmi >= 25 && bmi < 30) {
            imageBmi.setImageResource(R.mipmap.over_weight);
            TextInfo.append(" overweight.");
            TextInfo.setBackgroundColor(getColor(R.color.overWeight));
        }
        if (bmi >= 30 && bmi < 35) {
            imageBmi.setImageResource(R.mipmap.obese);
            TextInfo.append(" obese.");
            TextInfo.setBackgroundColor(getColor(R.color.obese));
        }
        if (bmi > 35) {
            imageBmi.setImageResource(R.mipmap.extra_obese);
            TextInfo.append(" extremely obese.");
            TextInfo.setBackgroundColor(getColor(R.color.extraObese));
        }
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
