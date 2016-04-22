package test;

import test.xmlDef;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class xmlParserMapper extends Mapper<LongWritable, Text, Text, NullWritable>  {
	
	 @Override
	 public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	   String currLine = value.toString();
	   try {
	    XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new ByteArrayInputStream(currLine.getBytes()));
	 
	    String currentElement = "";
	    int col = 0;
	    
	    // initialize all xml value to blank string
	    for (String xmlTag : xmlDef.xmlDefoutput[0]) {
	     xmlDef.xmlDefoutput[1][col] = "";
	     col++;
	    }
	    
	    
	    // read xml tags and store values in xmlDef
	    while (reader.hasNext()) {
	     int code = reader.next();
	     switch (code) {
	     case START_ELEMENT:
	      currentElement = reader.getLocalName();
	      break;
	     case CHARACTERS:
	      col = 0;
	      for (String xmlTag : xmlDef.xmlDefoutput[0]) {
	       if (currentElement.equalsIgnoreCase(xmlTag)) {
	        xmlDef.xmlDefoutput[2][col] += reader.getText().trim(); 
	       }
	       col++;
	      }
	     }
	    }
	    
	    
	    // writing values to mapper output file
	    // can remove this context.write
	    context.write(new Text(xmlDef.xmlDefoutput[2][0]+"#"+xmlDef.xmlDefoutput[2][1]+"#"+xmlDef.xmlDefoutput[2][2]+"#"+xmlDef.xmlDefoutput[2][3]+"#"+xmlDef.xmlDefoutput[2][4]),NullWritable.get());
	    
	   
	  
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	 }

}
