package com;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

class Xml2Csv {

	public static void main(String args[]) throws Exception {
	    File stylesheet = new File("/home/jadeglobal/Projects/testHadoop/src/com/style.xsl");
	    File xmlSource = new File("/home/jadeglobal/Downloads/data.xml");

	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document document = builder.parse(xmlSource);

	    StreamSource stylesource = new StreamSource(stylesheet);
	    Transformer transformer = TransformerFactory.newInstance()
			  .newTransformer(stylesource);
	    Source source = new DOMSource(document);
	    //System.out.println(transformer.transform(source, new StreamResult()));
	   // Result outputTarget = new StreamResult(new File("/home/jadeglobal/Downloads/howto.csv"));
	    //transformer.transform(source, outputTarget);
	    System.out.println("Done.");
	  //create a StringWriter for the output
	    StringWriter outWriter = new StringWriter();
	    StreamResult result = new StreamResult( outWriter );
	
	    transformer.transform( source, result );  
	    StringBuffer sb = outWriter.getBuffer(); 
	    String finalstring = sb.toString();
	    System.out.println("Text:"+finalstring);
	    
	    
	  }
}