package com.example.practica67s21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SumaActivity extends AppCompatActivity {

    EditText txtnum1, txtnum2;
    Button btncalcula, btnregresa;
    TextView lblresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        txtnum1 = findViewById(R.id.txtnum1);
        txtnum2 = findViewById(R.id.txtnum2);
        lblresult = findViewById(R.id.lblresult);

        btncalcula= findViewById(R.id.btncalcular);
        btnregresa = findViewById(R.id.btnregresa);
        btncalcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.valueOf(txtnum1.getText().toString());
                int num2 = Integer.valueOf(txtnum2.getText().toString());

                lblresult.setText("La suma es: "+String.valueOf(num1+num2));
            }
        });
        btnregresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
