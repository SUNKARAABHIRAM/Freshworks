import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
public class Ops {
	String filePath;
	
	//creating file path where data should be stored
	Ops(String filePath){
		
		//If the user doesnt give any specific location for storing of data
	if(filePath==null)
		this.filePath="E:\\assignment.json";
	
		else this.filePath=filePath;	//If the file path is mentioned
	}
	public void create(String key,JSONObject object) throws Exception{
        
		// Checking key length
		if(key.length()<=32) {
			
			//Checking value size
        	 if(object.toJSONString().length()<=16*1024) {
        	 
        		 
             JSONObject obj=new JSONObject();
             /*creation of JSONObject to store in a file(key,value),
        	 where key is a string and value is another JSONObject*/
             obj.put(key, object);
        	
        	 File f=new File(filePath);
        	 
        	 //Checking whether file exists or not.If not,will create a file with mentiond filepath
        	 
        	 if(!f.exists())
        		 f.createNewFile();
        	 
        	 //Checking file size before inserting data.filesize<=1GB
        	 if(f.length()<=1024*1024*1024) {
        	 FileReader reader=new FileReader(filePath);
        	 JSONArray array=new JSONArray();
        	 
        	/*If there exists any data in that file,that data will be taken to a JSONArray and 
        	 will add the JSONObject received to that JSONArray and store it in that file 
        	 */
        	 
     		if(f.length()!=0) {
        	 JSONParser parser=new JSONParser();
     		 array=(JSONArray)parser.parse(reader);}
     		int i=0;
     		
     		//Checking whether any data already present in the file with that key.If presents,data will not be added.  
     		for(i=0;i<array.size();i++) {
     		JSONObject ob=(JSONObject)array.get(i);
     			if(ob.containsKey(key)) {
     				
     				/*if data with the given key is already present,then data will not be added and
     				this message will be displayed*/
     				System.out.println("An object is there with that key");
     				break;
     				}
     		}
     		
     		//if i is equal to array size then no such data already present in the file
     		if(i==array.size()) {
     		array.add(obj);
     		
     		//inserting data to the file
        	Files.write(Paths.get(filePath),array.toJSONString().getBytes());}
     		}
        	 
        	 //if file size exceeds the limit,data will not be added and this message is displayed
        	 else System.out.println("File size exceeds limit,no data can be inserted");
     		}
        	 
        	 //if value size >16KB
        	 else System.out.println("Value length exceeds limit");
         }
		
		//if key size>32 chars
         else System.out.println("Key length exceeds limit");
	}
	public void read(String key) throws Exception{
		FileReader reader=new FileReader(filePath);
		JSONParser parser=new JSONParser();
		
		//Accessing data in the file
		JSONArray array=(JSONArray)parser.parse(reader);
		int i=0;
		for(i=0;i<array.size();i++) {
		JSONObject obj=(JSONObject)array.get(i);
		
            //Checking whether the Object with the given key is present		
			if(obj.containsKey(key)) {
				
				//Value of the given key is printed
		System.out.println(obj.get(key));
		break;
		}
		}
		
		//If the entire file is read and can't access object with the given key 
		if(i==array.size())
		System.out.println("No such data present in the file");
	}
	public void delete(String key) throws Exception{
		FileReader reader=new FileReader(filePath);
		JSONParser parser=new JSONParser();
		
		//Accessing data in the file
		JSONArray array=(JSONArray)parser.parse(reader);
		int i=0;
		for(i=0;i<array.size();i++) {
		JSONObject obj=(JSONObject)array.get(i);
		
		//Checking whether the Object with the given key is present	
			if(obj.containsKey(key)) {
				
		//Data of the given key is removed from the JSONArray if the key is found		
		array.remove(i);
		break;
		}
		}
		
		//If the entire file is read and can't access object with the given key
		if(i==array.size())
			System.out.println("No such element is there in the file to delete");
		else {
			
			//Rewriting JSONArray data into the file
			Files.write(Paths.get(filePath),array.toJSONString().getBytes());
		}
	}
}
