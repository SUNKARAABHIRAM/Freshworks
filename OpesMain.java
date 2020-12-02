import org.json.simple.JSONObject;

public class OpesMain {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//Creation of object without any any file path mentioned
		Ops ops=new Ops(null);
		JSONObject book1=new JSONObject();
		book1.put("Author", "ABC");
		book1.put("YEAR", 2005);
		book1.put("Month", "January");
		
		JSONObject book2=new JSONObject();
		book2.put("Author", "BCD");
		book2.put("YEAR", 2006);
		book2.put("Month", "February");
		
		JSONObject book3=new JSONObject();
		book3.put("Author", "CDE");
		book3.put("YEAR", 2007);
		book3.put("Month", "March");
		
		ops.create("Book1",book1);
		ops.create("Book2",book2);
		ops.create("Book3",book3);
		ops.create("Book3",book3);
		
        ops.read("Book2");
        ops.read("Book4");
        
        ops.delete("Book2");
        
        ops.read("Book2");
        
        ops.delete("Book4");
		
	}

}
