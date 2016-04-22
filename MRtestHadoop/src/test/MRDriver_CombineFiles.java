package test;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;




public class MRDriver_CombineFiles extends Configured implements Tool{
	
	  @SuppressWarnings("deprecation")
	@Override
	  public int run(String[] args) throws Exception {
		  Configuration conf= new Configuration();
		    FileSystem fs = FileSystem.get(conf);
		    int retrun=1;
		    //estimate reducers
		    Job job = new Job(conf,"exportxmltocsvfile");
		    job.setJarByClass(MRDriver_CombineFiles.class);
		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    Path output=new Path(args[1]);
		    job.setMapOutputKeyClass(Text.class);
		    job.setMapOutputValueClass(Text.class);
		    FileOutputFormat.setOutputPath(job, output);
		    job.setInputFormatClass(WholeFileInputFormat.class);
		    job.setOutputFormatClass(TextOutputFormat.class);
		    



		    job.setMapperClass(SequenceFileMapper.class);

		    return job.waitForCompletion(true) ? 0 : 1;
	  }
	  
	  public static void main(String[] args) throws Exception {
	    int exitCode = ToolRunner.run(new MRDriver_CombineFiles(), args);
	    System.exit(exitCode);
	  }

}
