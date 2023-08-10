package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class result extends AppCompatActivity {

    List<Record> recordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String total_amount = intent.getStringExtra("TOTAL_A");
        String total_person = intent.getStringExtra("TOTAL_P");
        recordList = (List<Record>) intent.getSerializableExtra("RESULT");

        TextView resultTitle = findViewById(R.id.tv_resultTitle);
        LinearLayout result = findViewById(R.id.result);
        resultTitle.setText("Total Amount = RM" + total_amount
                + "\nNumber of Person = " + total_person);
        try{
            String i = String.valueOf(recordList.size());
        }catch(Exception e) {
            Toast.makeText(result.this,"??", Toast.LENGTH_LONG).show();
        }
        if (recordList != null && !recordList.isEmpty()) {
            for (int i = 0; i < recordList.size(); i++) {
                TextView tv = new TextView(this);
                tv.setText(recordList.get(i).toString());
                if (recordList.get(i).getBreakdown() == 4) {
                    if (recordList.get(i).getResult() != 0) {
                        tv.setTextColor(Color.RED);
                    }
                }
                result.addView(tv);
            }
        } else {
            Toast.makeText(this, "No records to display.", Toast.LENGTH_LONG).show();
        }

        Button btn_share = findViewById(R.id.share1);
        Button btn_home = findViewById(R.id.home1);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                if(recordList.get(recordList.size() - 1).getBreakdown() == 0) {
                    intent.putExtra(Intent.EXTRA_TEXT, getEqualResult(total_amount,total_person,recordList));
                }
                else{
                    intent.putExtra(Intent.EXTRA_TEXT,getCustomResult(total_amount,total_person,recordList));
                }
                intent.setType("text/plain");

                if(intent.resolveActivity((getPackageManager()))!= null){
                    startActivity(intent);
                }
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(result.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private String getEqualResult(String total_amount, String total_person, List<Record> recordLists){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total amount : " + total_amount
                + "\nNumber of person : " + total_person
                + "\nAmount per person : " + recordList.get(recordList.size() - 1).getNumber());
        return stringBuilder.toString();
    }

    private String getCustomResult(String total_amount, String total_person, List<Record> personList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total amount : " + total_amount
                + "\nNumber of person : " + total_person
                + "\nDetails per person:\n");
        for (int i = 0; i < recordList.size(); i++) {
            String temp = recordList.get(i).toString();
            stringBuilder.append(temp).append("\n\n"); // Separate items with newlines
        }
        return stringBuilder.toString();
    }
}