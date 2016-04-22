package com.jade;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WholefileMapper  extends Mapper<NullWritable,Text,NullWritable,Text>{
	private Text filenamekey;
	private MultipleOutputs<NullWritable, Text> multipleOutputs;
	public File stylesheet ;
	public File xmlSource;
	public String outputfilename;
	public void map(NullWritable key,Text value,Context context) throws IOException,InterruptedException
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			//Document document = builder.parse(value.toString());
			
			Document document = builder.parse(new InputSource(new ByteArrayInputStream(value.toString().getBytes("utf-8"))));
		    
			StreamSource stylesource = new StreamSource(stylesheet);
		    Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
		    Source source = new DOMSource(document);
		    StringWriter outWriter = new StringWriter();
		    StreamResult result = new StreamResult( outWriter );
		
		    transformer.transform( source, result );  
		    StringBuffer sb = outWriter.getBuffer(); 
		    String finalstring = sb.toString();
		    
		    //transformer.transform(source, outputTarget);
		    multipleOutputs.write(NullWritable.get(), new Text(finalstring), outputfilename);
		    
		} catch (ParserConfigurationException | SAXException | TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		//context.write(filenamekey,value);
		//multipleOutputs.write(filenamekey, value, generateFileName(filenamekey,new Text(new SimpleDateFormat("YYYYMMdd_HHmmss_SSS").format(new Date()).toString() )));
	}
	
	public void setup(Context context) throws IOException
	{
		InputSplit split=context.getInputSplit();
		Path path=((FileSplit)split).getPath();
		filenamekey=new Text(path.toString());
		multipleOutputs = new MultipleOutputs<NullWritable, Text>(context);
		outputfilename = generateFileName(filenamekey,new Text(new SimpleDateFormat("YYYYMMdd_HHmmss_SSS").format(new Date()).toString() ));
		stylesheet= new File("/home/jadeglobal/Projects/testHadoop/src/com/jade/style.xsl");
		//xmlSource = new File("path.toString()");
	}
	
	String generateFileName(Text filename, Text time_value){
		
		String fname = new File(filename.toString()).getName();
		System.out.println("filename"+fname + "_" + time_value.toString());
		return fname + "_" + time_value.toString();		
		
	}
	
	@Override
	public void cleanup(final Context context) throws IOException, InterruptedException{
		multipleOutputs.close();
	}

}
