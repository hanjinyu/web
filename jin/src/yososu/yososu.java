package yososu;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class yososu {

	    public static void main(String[] args) throws IOException, ParseException {
	    	String apiUrl = "http://api.odcloud.kr/api/uws/v1/inventory";
			// 홈페이지에서 받은 키
			String serviceKey = "=WsZcrJH2%2FfSZRiZc%2Ba%2FOa8qZiMGD4RiYpOIZgqKrpR8kbdH6A1v9aRtPHYIG%2FEH73I%2BTiCL3Xge%2Bi3OXYUrxHw%3D%3D";
			String page = "1";
			String perPage = "10"; // 한 페이지 결과 수
			String lat = "60"; // 위도
			String lng = "120"; // 경도

			// 전날 23시 부터 153개의 데이터를 조회하면 오늘과 내일의 날씨를 알 수 있음
			yososu vl = new yososu();
	        StringBuilder urlBuilder = new StringBuilder("http://api.odcloud.kr/api/uws/v1/inventory");                               /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WsZcrJH2%2FfSZRiZc%2Ba%2FOa8qZiMGD4RiYpOIZgqKrpR8kbdH6A1v9aRtPHYIG%2FEH73I%2BTiCL3Xge%2Bi3OXYUrxHw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(page, "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(perPage, "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("addr","UTF-8") + "=" + URLEncoder.encode("addr", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("code","UTF-8") + "=" + URLEncoder.encode("code", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("name","UTF-8") + "=" + URLEncoder.encode("name", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("regDt","UTF-8") + "=" + URLEncoder.encode("reg", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8")); // 경도
			urlBuilder.append("&" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8")+"&"); // 위도
			urlBuilder.append("&" + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode("price", "UTF-8")); // 요소수가격 
	        System.out.println("TEST ::: " + urlBuilder.toString());
	        
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        String result = null;
			System.out.println("결과: " + result);
	        JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
			JSONObject parse_response = (JSONObject) jsonObj.get("response");
			JSONObject parse_body = (JSONObject) parse_response.get("body");// response 로 부터 body 찾아오기
			JSONObject parse_items = (JSONObject) parse_body.get("items");// body 로 부터 items 받아오기
			// items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다.
			JSONArray parse_item = (JSONArray) parse_items.get("item");
																		

	    }
	    }