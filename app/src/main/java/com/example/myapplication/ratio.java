package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ratio extends AppCompatActivity {

    private LinearLayout linearLayout;
    List<Record> recordList = new ArrayList<Record>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratio);

        EditText et_ta = findViewById(R.id.editTotalAmount3);
        EditText et_np = findViewById(R.id.editNumofPerson3);
        Button btn_c = findViewById(R.id.btn_c2);

        Button btn_cal = findViewById(R.id.btn_cal2);
        linearLayout = findViewById(R.id.ll3);

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfLine;
                try {
                    linearLayout.removeAllViews();
                    numberOfLine = Integer.parseInt(et_np.getText().toString());
                    btn_cal.setVisibility(View.VISIBLE);
                    linearLayout.removeAllViews();
                    createLine(numberOfLine);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please enter number of person",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordList.clear();
                if(!empty()) {
                    double totalAmount = 0;
                    int numberOfLine = 0;
                    try {
                        totalAmount = Double.parseDouble(et_ta.getText().toString());
                        numberOfLine = Integer.parseInt(et_np.getText().toString());
                        // Calculate and display percentages
                        double t_ratio = 0;
                        try {
                            for (int i = 0; i < recordList.size(); i++) {
                                t_ratio += recordList.get(i).getNumber();
                            }
                            for (int i = 0; i < recordList.size(); i++) {
                                double result = totalAmount * ( recordList.get(i).getNumber()/ t_ratio);
                                recordList.get(i).setResult(result);

                            }
                            Intent intent = new Intent(getApplicationContext(), result.class);
                            intent.putExtra("TOTAL_A", String.valueOf(totalAmount));
                            intent.putExtra("TOTAL_P", String.valueOf(numberOfLine));
                            intent.putExtra("RESULT", (Serializable) recordList);
                            startActivity(intent);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter the ratio", Toast.LENGTH_LONG).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Please enter total amount and number of person.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void createLine(int numberOfLine) {

        for (int i = 0; i < numberOfLine; i++) {
            LinearLayout ll = new LinearLayout(this);
            EditText et = new EditText(this);
            et.setHint("Key Name");
            et.setTextColor(Color.BLACK);
            et.setTextSize(18.0f);

            EditText et2 = new EditText(this);
            et2.setHint("Key the ratio");
            et2.setTextColor(Color.BLACK);
            et2.setTextSize(18.0f);
            et2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            ll.addView(et,layoutParams());
            ll.addView(et2,layoutParams());
            linearLayout.addView(ll);
        }
    }

    private LinearLayout.LayoutParams layoutParams(){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1
        );
        return layoutParams;
    }
    private boolean empty(){
        //Get value from the et1 & et2
        // Iterate through the 'show' (LinearLayout)
        // 0 is the title
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childView = linearLayout.getChildAt(i);
            //Ensure the childView is LinearLayout
            if (childView instanceof LinearLayout) {
                LinearLayout ll= (LinearLayout) childView;
                //Ensure the linearLayout has total two child
                if (ll.getChildCount() == 2) {
                    //Get position on the first child of linearLayout
                    View et1View = ll.getChildAt(0);
                    //Ensure the et1View is EditText
                    if (et1View instanceof EditText) {
                        EditText et1 = (EditText) et1View;
                        String name = et1.getText().toString().trim();
                        //Get position on the first child of linearLayout
                        View et2View = ll.getChildAt(1);
                        //Ensure the et2View is EditText
                        if (et2View instanceof EditText) {
                            EditText et2 = (EditText) et2View;
                            String input = et2.getText().toString().trim();
                            if (!name.isEmpty() && !input.isEmpty()) {
                                try {
                                    double inputValue = Double.parseDouble(input);
                                    recordList.add(new Record(name, inputValue, 3));
                                }catch (NumberFormatException e){
                                    Toast.makeText(getApplicationContext(), "Please the value", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Please fill both name and value", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}