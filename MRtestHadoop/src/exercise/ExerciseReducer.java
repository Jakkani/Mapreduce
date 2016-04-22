package exercise;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ExerciseReducer extends Reducer<Text,Text,Text,Text> 
{
    public void reduce(Text key,Iterable<Text> value,Context context) throws IOException,InterruptedException
    {
        Map<Integer,Integer> mymap=new HashMap<Integer,Integer>();
        StringBuilder sb=new StringBuilder();
        int myval=0;
        int count=0;
        for(Text s:value)
        {
            String comma_values=s.toString();
            System.out.println("comma_values=="+comma_values);
            String[] comma_values_arr=comma_values.split(",");
            count=count+1;
            for(int i=0;i<comma_values_arr.length;i++)
            {
                if(mymap.get(i)==null){
                mymap.put(i,Integer.parseInt(comma_values_arr[i]));
                System.out.println("mymap input i vlaues"+ i);
                System.out.println("mymap input vlaues"+Integer.parseInt(comma_values_arr[i]));
                }else
                {
                 myval=mymap.get(i)+Integer.parseInt(comma_values_arr[i]);
                 System.out.println("Get values"+mymap.get(i)+"current values"+Integer.parseInt(comma_values_arr[i]));
                 mymap.put(i,myval);
                }
            }
        }
        for(Integer finalval:mymap.values())
        {

            sb.append(finalval.toString());
            sb.append("\t");
        }
        System.out.println("sbvalues is "+sb.append(count).toString());
        context.write(key,new Text(sb.toString().replaceAll("\t$","")));        
    }
}