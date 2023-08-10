package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class equal extends AppCompatActivity {

    List<Record> recordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equal);

        EditText totalAmount = findViewById(R.id.editTotalAmount);
        EditText numOfPerson = findViewById(R.id.editNumofPerson);

        Button btn_calculate = findViewById(R.id.calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double total = Double.parseDouble(totalAmount.getText().toString());
                    int person = Integer.parseInt(numOfPerson.getText().toString());
                    double cal = total/person;
                    totalAmount.setText("");
                    numOfPerson.setText("");
                    recordList.add(new Record(cal,1));
                    Intent intent = new Intent(getApplicationContext(), result.class);
                    intent.putExtra("TOTAL_A", String.format("%.2f",total));
                    intent.putExtra("TOTAL_P", String.valueOf(person));
                    intent.putExtra("RESULT", (Serializable) recordList);
                    startActivity(intent);
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter total amount and number of person",Toast.LENGTH_SHORT).show();
                }





            }
        });



    }
}