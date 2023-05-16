package test.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// json 형식의 데이터를 만들어보기
// {} : JSON 오브젝트 → JSONObject
// [] : JSON Array → JSONArray
public class JSONMakerTest {
// JSON은 시스템 간 데이터 교환 목적으로 만드는 것이라면 어느 언어에서라도 사용할 수 있다.
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
			JSONObject myjson = new JSONObject();
			myjson.put("name", "방탄소년단"); //json → name, value 넣기
			myjson.put("age", "30");
			System.out.println(myjson.toJSONString());
			
			JSONArray subjectlist = new JSONArray();
			subjectlist.add("자바");
			subjectlist.add("스프링");
			subjectlist.add("하둡");
			// 위에서 작성한 JSONArray를 JSONObject에 추가하기
			
			JSONArray commentlist = new JSONArray();
			
			JSONObject comment1 = new JSONObject();
			comment1.put("no", "1");
			comment1.put("content", "json 만들기");
			comment1.put("id", "bts1");
			
			JSONObject comment2 = new JSONObject();
			comment2.put("no", "2");
			comment2.put("content", "json 파일도 만들기");
			comment2.put("id", "jang");
			
			commentlist.add(comment1);
			commentlist.add(comment2);
			
			myjson.put("comment", commentlist);
			myjson.put("subject", subjectlist);
			System.out.println(myjson.toJSONString());
			
			//json 파일을 생성 - 파일 io
			FileWriter fw = new FileWriter("src/main/java/test/json/myjson.json");
			fw.write(myjson.toJSONString());
			fw.flush();
			fw.close();
	}

}
