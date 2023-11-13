package com.example.hadoop.CaloriesPerGram;

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


public class RunnerClassCpg {
    public static void run(String name, String protein, String sugars){

        Configuration conf = new Configuration();
        JobConf job = new JobConf(conf, App.class);

		job.set("name", name);
        job.set("protein", protein);
        job.set("sugars", sugars);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(MapperClassCpg.class);
		job.setReducerClass(ReducerClassCpg.class);

		// Path input = new Path("/Users/I590201/Projects/Uni/GolemiDanni/stu2001321033/src/main/resources/cereal.csv");
		Path input = new Path ("hdfs://127.0.0.1:9000/cereal.csv");
		// Path output = new Path("output");
		Path output = new Path("hdfs://127.0.0.1:9000/calories-per-gram/result");

		FileInputFormat.setInputPaths(job, input);
		FileOutputFormat.setOutputPath(job, output);

        try {
			FileSystem fs = FileSystem.get(URI.create("hdfs://127.0.0.1:9000/calories-per-gram"), conf);

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
