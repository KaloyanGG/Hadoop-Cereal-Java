package com.example;

import com.example.hadoop.CaloriesPerGram.RunnerClassCpg;
import com.example.hadoop.ListBreakfasts.RunnerClassLB;

public class Main {
    public static void main(String[] args) {
        // Main.startCaloriesPerGram();
        // Main.startListBreakfasts();
        new UI();
    }
    public static void startCaloriesPerGram() {
        RunnerClassCpg.run("Bran", "3", "7");
    }

    public static void startListBreakfasts(){
        RunnerClassLB.run("cor", "2", "7", "120");
    }

}
