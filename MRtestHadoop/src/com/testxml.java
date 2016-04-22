package com;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class testxml {
	//public HashMap<String, HashMap<String, String>> parsehashmap; 
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
	    // TODO Auto-generated method stub
		HashMap<String, HashMap<String, String>> parsehashmap= new HashMap<String, HashMap<String,String>>(); 
	    		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	    		DocumentBuilder builder = factory.newDocumentBuilder();
	            File f= new File("/home/jadeglobal/Downloads/students.xml");
	            Document doc=builder.parse(f);
	            doc.getDocumentElement().normalize();
	            System.out.println("test2");
	            Element root=doc.getDocumentElement();
	            System.out.println(root.getNodeName());
	            NodeList nList = doc.getElementsByTagName("questions");
	            System.out.println("============================");
	            parseXML(root,root);
	            //visitChildNodes(nList);
	        	}
	       /* private static void visitChildNodes(NodeList nList)
	        {
	           for (int temp = 0; temp < nList.getLength(); temp++)
	           {
	              Node node = nList.item(temp);
	              if (node.getNodeValue().length() > 0){
	              System.out.println("Node Name = " + node.getNodeName() + "; Complete Value = " +node.getNodeValue());
	              }
	              //if (node.getNodeType() == Node.ELEMENT_NODE)
	              //{
	                //System.out.println("Node Name = " + node.getNodeName() + "; Complete Value = " + node.getTextContent());
	                 //Check all attributes
	                 if (node.hasAttributes()) {
	                    // get attributes names and values
	                    NamedNodeMap nodeMap = node.getAttributes();
	                    for (int i = 0; i < nodeMap.getLength(); i++)
	                    {
	                        Node tempNode = nodeMap.item(i);
	                        System.out.println("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
	                    }
	                 }    
	                  if (node.hasChildNodes()) {
	                       //We got more childs; Let's visit them as well
	                       visitChildNodes(node.getChildNodes());
	                  }
	                //}
	              //}
	           }
	        }*/
	        
	        public static void parseXML(Node node, Node parent)
	        {
	        	//HashMap<String, String> hm = new HashMap<String, String>();
	        	if(node.hasAttributes()){
	        		NamedNodeMap nodeMap = node.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++)
                    {
                        Node tempNode = nodeMap.item(i);
                        System.out.print("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
                       // System.out.println("\n");
                    }
	        		
	        	}
	           if (node.hasChildNodes())
	           {
	              //System.out.println(node.getNodeName());
	              NodeList childrens = node.getChildNodes();
	              for (int i = 0; i < childrens.getLength(); i++)
	              {
	                    parseXML(childrens.item(i), node);           
	              }//for
	           }//fi:root_childrens
	           else {
	              String nodeValue = node.getNodeValue().trim();
	              if (nodeValue.length() > 0){
	                  System.out.print(parent.getNodeName() + "::::" + nodeValue);
	                  //hm.put(parent.getNodeName(), nodeValue);
	                  //System.out.println("\n");
	              }

	           }
	        }
}
