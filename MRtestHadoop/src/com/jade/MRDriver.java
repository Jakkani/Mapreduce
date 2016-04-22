package com.jade;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MRDriver extends Configured implements Tool {


public int run(String[] args) throws Exception {
	 Configuration conf= new Configuration();
	    FileSystem fs = FileSystem.get(conf);
	    int retrun=1;
	    //estimate reducers
	    Job job = new Job(conf,"exportxmltocsvfile");
	    job.setJarByClass(MRDriver.class);
	    //job.setJobName("WholeFile");
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    job.setInputFormatClass(WholeFileInputFormat.class);
		/*
		 * Using MultipleOutputs creates zero-sized default output Ex:
		 * part-r-00000. To prevent this use LazyOutputFormat
		 */
	    //job.setOutputFormatClass(TextOutputFormat.class);
	    LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
		MultipleOutputs.addNamedOutput(job,"text", TextOutputFormat.class,Text.class, Text.class);
	    job.setMapperClass(WholefileMapper.class);
	    job.setNumReduceTasks(0);

	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    Path output=new Path(args[1]);
	    try {
	        fs.delete(output, true);
	    } catch (IOException e) {
	        System.out.println("Failed to delete temporary path");
	    }
	    FileOutputFormat.setOutputPath(job, output);

	    boolean ret=job.waitForCompletion(true);
	    if(!ret){
	    	
	        throw new Exception("Job Failed");
	    }else {
			retrun=0;	
			}
			
	    
	    return retrun;
		
}

public static void main(String[] args) throws Exception  {
    int exitCode = ToolRunner.run(new MRDriver(), args);
    System.exit(exitCode);
}

}
