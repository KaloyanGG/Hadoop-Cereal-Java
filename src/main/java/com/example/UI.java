package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.hadoop.CaloriesPerGram.RunnerClassCpg;
import com.example.hadoop.ListBreakfasts.RunnerClassLB;

public class UI extends JFrame {

    JPanel panel = new JPanel();
    String[] results = { "Calories per gram", "List of breakfasts" };
    JComboBox<String> resultDropdown = new JComboBox<>(results);
    JTextField breakfastSearchField = new JTextField();
    JTextField proteinContentField = new JTextField();
    JTextField sugarContentField = new JTextField();
    JTextField maxCaloriesField = new JTextField();

    public UI() {
        setTitle("Hadoop UI");
        setSize(500, 400);

        panel.setLayout(new GridLayout(6, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        maxCaloriesField.setBackground(Color.GRAY);
        maxCaloriesField.setEnabled(false);

        resultDropdown.addActionListener(ev -> {
            if (resultDropdown.getSelectedItem().equals("Calories per gram")) {
                maxCaloriesField.setEnabled(false);
                maxCaloriesField.setBackground(Color.GRAY);
                maxCaloriesField.setText("");
            } else {
                maxCaloriesField.setEnabled(true);
                maxCaloriesField.setBackground(Color.WHITE);
            }
        });

        // Adding components to the panel
        panel.add(new JLabel("Result type:"));
        panel.add(resultDropdown);
        panel.add(new JLabel("Search breakfast:"));
        panel.add(breakfastSearchField);
        panel.add(new JLabel("Min protein content:"));
        panel.add(proteinContentField);
        panel.add(new JLabel("Max sugar content:"));
        panel.add(sugarContentField);
        panel.add(new JLabel("Max calories:"));
        panel.add(maxCaloriesField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        panel.add(new JLabel()); 
        panel.add(searchButton);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void search() {

        System.out.println("Searching...");
        String resultType = (String) resultDropdown.getSelectedItem();
        String breakfast = breakfastSearchField.getText();
        String proteinContent = proteinContentField.getText();
        String sugarContent = sugarContentField.getText();
        String caloriesContent = maxCaloriesField.getText();

        switch (resultType) {
            case "Calories per gram":
                RunnerClassCpg.run(breakfast, proteinContent, sugarContent);
                break;
            case "List of breakfasts":
                RunnerClassLB.run(breakfast, proteinContent, sugarContent, caloriesContent);
                break;
            default:
                System.out.println("...bad choice...");
                break;
        }

    }

}
