package exercise;

import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class Exercisemapper extends Mapper<LongWritable,Text,Text,Text>
{
    public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException
    {
        String orig_val=value.toString();
        String[] orig_val1=orig_val.split(",");
        String state_val=orig_val1[0];
        String other_counts=orig_val1[2]+","+orig_val1[3]+","+orig_val1[4];
        context.write(new Text(state_val),new Text(other_counts));
    }


}