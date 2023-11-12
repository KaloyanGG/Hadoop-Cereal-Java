package com.example;

import com.example.hadoop.CaloriesPerGram.MapperClassCpg;
import com.example.hadoop.CaloriesPerGram.RunnerClassCpg;
import com.example.hadoop.ListBreakfasts.RunnerClassLB;
import com.example.hadoop.Sales.RunnerClass;

public class Main {
    public static void main(String[] args) {
        //Starting SalesApp:
        // Main.startSales();
        // Main.startCaloriesPerGram();
        // Main.startListBreakfasts();
        new UI();
    }

    public static void startSales() {
        System.out.println("Starting application...");
        RunnerClass.run("b");
        
    }

    public static void startCaloriesPerGram() {
        RunnerClassCpg.run("Bran", "3", "7");
    }

    public static void startListBreakfasts(){
        RunnerClassLB.run("cor", "2", "7", "120");
    }

}
