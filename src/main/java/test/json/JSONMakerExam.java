package test.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONMakerExam {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		JSONObject jobj = new JSONObject(); //myjson
		jobj.put("name", "김서연");
		jobj.put("age", "25");
		
		JSONArray jarr = new JSONArray();
		jarr.add("자바");
		jarr.add("하둡");
		jarr.add("시큐어코딩");
		jobj.put("subject", jarr);
		
		//addr
		JSONObject jobj1 = new JSONObject(); //addrobj
		jobj1.put("zip","111-222");
		jobj1.put("addr1","인천시");
		jobj.put("addr",jobj1);
		
		//history
		JSONArray jarr2 = new JSONArray(); //historylist
		JSONObject jobj2 = new JSONObject();
		jobj2.put("subject","java");
		jobj2.put("month","11");
		
		JSONObject jobj3 = new JSONObject();
		jobj3.put("subject","servlet");
		jobj3.put("month","12");
		
		jarr2.add(jobj2); //history1
		jarr2.add(jobj3); //history2
		
		jobj.put("history", jarr2);
		
		System.out.println(jobj.toJSONString());
		
		FileWriter fw = new FileWriter("src/main/java/test/json/myjson2.json");
		fw.write(jobj.toJSONString());
		fw.flush();
		fw.close();
	}

}
