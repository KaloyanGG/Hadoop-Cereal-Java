package com.example.hadoop.Sales;

import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapreduce.v2.app.webapp.App;

public class RunnerClass {

	public static void run(String inp) {
		Configuration conf = new Configuration();

		JobConf job = new JobConf(conf, App.class);

		job.set("country", inp);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		job.setMapperClass(MapperClass.class);
		job.setReducerClass(ReducerClass.class);

		Path input = new Path("/Users/I590201/Projects/Uni/GolemiDanni/stu2001321033/src/main/resources/SalesJan2009.csv");
		// Path output = new Path("hdfs://127.0.0.1:9000/uni_output/sales_result");
		Path output = new Path("output");

		FileInputFormat.setInputPaths(job, input);
		FileOutputFormat.setOutputPath(job, output);

		try {
			// FileSystem fs = FileSystem.get(URI.create("hdfs://127.0.0.1:9000"), conf);
			FileSystem fs = FileSystem.get(URI.create("/Users/I590201/Projects/Uni/GolemiDanni/stu2001321033"), conf);

			if (fs.exists(output))
				fs.delete(output, true);

			// throws here
			RunningJob task = JobClient.runJob(job);

			System.out.println("Is successful: " + task.isSuccessful());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
