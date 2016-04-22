package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(new SimpleDateFormat("YYYYMM_HHmmss_SSS").format(new Date()));
		Thread.sleep(1000);
		System.out.println(new SimpleDateFormat("YYYYMM_HHmmss_SSS").format(new Date()));
		
				System.out.println("output:"+new xmlDef().xmlDefoutput[0][2].toString());
				
				HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
				hmap.put(2, 5000);
				System.out.println(hmap.get(2));
				hmap.put(2, 10);
				System.out.println(hmap.get(2));
				

	}

}
