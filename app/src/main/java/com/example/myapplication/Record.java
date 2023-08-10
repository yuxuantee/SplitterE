package com.example.myapplication;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Record implements Serializable {
    private double number = 0;
    private double result = 0;
    private String name;
    private int breakdown = 0; // 1 = "Equal"; 2 = "Percentage; 3 = "Ratio"; 4 = "Amount"

    public Record(String name, double number, double result, int breakdown) {
        this.number = number;
        this.result = result;
        this.name = name;
        this.breakdown = breakdown;
    }

    public Record (String name, double number, int breakdown) {
        this.number = number;
        this.name = name;
        this.breakdown = breakdown;
    }

    public Record(double result, int breakdown) {
        this.result = result;
        this.breakdown = breakdown;
    }

    public double getResult() {
        return result;
    }

    public String getName() {
        return name;
    }
    public int getBreakdown() {
        return breakdown;
    }

    public double getNumber() {
        return number;
    }

    public void setResult(double result) {
        this.result = result;
    }


    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String dcm = decimalFormat.format(result);
        if(breakdown == 1){
            return "\nAmount = RM" + dcm+ " per person\n";
        }else if (breakdown == 2){
            return "\nName = " + name +
                    "\nPercentage = " + number + "%"
                    + "\nAmount = RM" + dcm+ "\n";
        }else if (breakdown == 3){
            return "\nName = " + name +
                    "\nRatio = " + number
                    + "\nAmount = RM" + dcm + "\n";
        }else{
            return "\nName = " + name +
                    "\nAmount = RM" + number
                    + "\nDifference = RM" + dcm + "\n";
        }
    }
}
