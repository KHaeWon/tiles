package test.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//		//1. JSONParser 만들기
//		JSONParser parser = new JSONParser();
//		
//		//2. 파싱하기
//		JSONObject root = (JSONObject) parser.parse(new FileReader("src/main/java/test/json/myjson2.json"));
//		JSONObject root1 = (JSONObject) parser.parse(new FileReader("src/main/java/test/json/myjson2.json"));
//		JSONObject root2 = (JSONObject) parser.parse(new FileReader("src/main/java/test/json/myjson2.json"));
//		JSONObject root3 = (JSONObject) parser.parse(new FileReader("src/main/java/test/json/myjson2.json"));
//		
//		//3. JSONObject에서 데이터 읽기
//		String name = (String) root.get("name");
//		String age = (String) root.get("age");
//		System.out.println("name:"+name);
//		System.out.println("age:"+age);
//		
//		String month1 = (String) root1.get("month");
//		String subject1 = (String) root1.get("subject");
//		String ms1 = (String)root.get(root1);
//		
//		String month2 = (String) root2.get("month");
//		String subject2 = (String) root2.get("subject");
//		String ms2 = (String)root.get(root2);
//		
//		String zip = (String) root3.get("zip");
//		String addr1 = (String) root3.get("addr1");
//		String addr = (String) root.get(root3);
//		
//		//4. JSONArray에서 데이터 읽기
//		JSONArray subjectlist = new JSONArray();
//		for(int i=0; i<subjectlist.size(); i++) {
//			String subject = (String) subjectlist.get(i);
//			System.out.println("subject:"+subject);
//		}
//		JSONArray historylist = new JSONArray();
//		for(int i=0; i<historylist.size(); i++) {
//			String history = (String) historylist.get(i);
//			System.out.println("history:"+history);
//		}

/*
 * .json파일을 JSONArray나 JSONObject로 변환
 * JSON파서 - JSONParser(json-simple.jar라이브러리내에서) 
 */
public class JSONParser_Exam {
	public static void main(String[] args) throws Exception {
		// 1. JSONParser를 생성
		JSONParser parser = new JSONParser();
		// 2. json파싱하기
		JSONObject root = (JSONObject) parser.parse(new FileReader("src/main/java/test/json/myjson2.json"));

		// 3. JSONObject에서 데이터 읽기
		String name = (String) root.get("name");
		String age = (String) root.get("age");
		System.out.println("name:" + name);
		System.out.println("age:" + age);

		// 4. JSONArray에서 데이터읽기
		JSONArray subjectlist = (JSONArray) root.get("subject");

		for (int i = 0; i < subjectlist.size(); i++) {
			String subject = (String) subjectlist.get(i);
			System.out.println("Subject:" + subject);
		}

		// 5. addr
		JSONObject addr = (JSONObject) root.get("addr");
		String zip = (String) addr.get("zip");
		String addr1 = (String) addr.get("addr1");
		System.out.println("zip:" + zip);
		System.out.println("addr:" + addr1);

		// 6. history
		JSONArray historylist = (JSONArray) root.get("history");
		for (int i = 0; i < historylist.size(); i++) {
			JSONObject history = (JSONObject) historylist.get(i);
			System.out.println("subject:" + history.get("subject"));
			System.out.println("month:" + history.get("month"));
		}

	}
}
