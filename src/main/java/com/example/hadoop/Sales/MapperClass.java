package com.example.hadoop.Sales;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MapperClass extends MapReduceBase
	implements Mapper<LongWritable, Text, Text, DoubleWritable>{

	String country;
	
	@Override
	public void configure(JobConf job) {
		country = job.get("country", "");
	}

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter)
			throws IOException {
		//3 4 8
		String[] columns = value.toString().split(",");
		
		if(country.isEmpty() || columns[7].toLowerCase().contains(country)) {
			try {
				String currentCountry = columns[7];
				String payment = columns[3];
				double sum = Double.parseDouble(columns[2]);
				
				Text outputKey = new Text(currentCountry + " - " 
						+ payment + " : ");
				
				output.collect(outputKey, new DoubleWritable(sum));		
				
			}catch(NumberFormatException ex) {
				System.err.println(value.toString());
			}
			
		}
	}

}

