package com.example.hadoop.CaloriesPerGram;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MapperClassCpg extends MapReduceBase
        implements Mapper<LongWritable, Text, Text, Text> {

    String name;
    String protein;
    String sugars;

    @Override
    public void configure(JobConf job) {
        name = job.get("name", "");
        protein = job.get("protein", "");
        sugars = job.get("sugars", "");
    }

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
            throws IOException {

        String[] columns = value.toString().split(";");
        try { 
            String nameCSV = columns[0].toLowerCase();
            double proteinCSV = Double.parseDouble(columns[4]);
            double sugarsCSV = Double.parseDouble(columns[9]);

            // If weight means 1 kilo:
            String caloriesPerGram = String.valueOf(Double.parseDouble(columns[3]) / 1000);

            if ((name.isEmpty() || nameCSV.contains(name.toLowerCase())) &&
                    (protein.isEmpty() || proteinCSV >= Double.parseDouble(protein)) &&
                    (sugars.isEmpty() || sugarsCSV <= Double.parseDouble(sugars))) {

                Text outputKey = new Text(columns[0]);
                Text outputValue = new Text(caloriesPerGram);

                output.collect(outputKey, outputValue);

            }
        } catch (NumberFormatException ex) {
            System.err.println(value.toString());
        }

    }
}
