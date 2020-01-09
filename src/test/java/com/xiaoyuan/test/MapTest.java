package com.xiaoyuan.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapTest {
	 // TODO code application logic here 
	public static void main(String[]args){
     List<String> l= new ArrayList<String>();
     l.add("1");
     l.add("2");
     l.add("3");
     l.add("4");
     int [] arr=new int[l.size()];
     for(int i=0;i<l.size();i++){
    	 arr[i]=Integer.parseInt(l.get(i));
     }
     for(Integer i:arr){
    	 System.out.println("rs=="+i);
     }
}  
  
private static void printMap(Map map){  
    System.out.println("===================mapStart==================");  
    Iterator it = map.entrySet().iterator();  
    while(it.hasNext()){  
        Map.Entry entry = (Map.Entry) it.next();  
        System.out.println(entry.getKey() + ":" + entry.getValue());  
    }  
    System.out.println("===================mapEnd==================");  
}   

public static Map sortMap(Map oldMap) {  
    ArrayList<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(oldMap.entrySet());  
    Collections.sort(list, new Comparator<Map.Entry<String, String>>() {  

        @Override  
        public int compare(Entry<java.lang.String, String> arg0,  
                Entry<java.lang.String, String> arg1) {  
        	
            return Integer.parseInt(arg0.getValue()) - Integer.parseInt(arg1.getValue());  
        }  
    });  
    Map newMap = new LinkedHashMap();  
    for (int i = 0; i < list.size(); i++) {  
        newMap.put(list.get(i).getKey(), list.get(i).getValue());  
    }  
    return newMap;  
}  
}  