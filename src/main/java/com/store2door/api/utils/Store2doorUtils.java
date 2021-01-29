package com.store2door.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Store2doorUtils {
	@Value("${app.server.key}")
	private String serverKey;
	@Value("${app.google.url}")
	private String googleUrl;
	
	public void sendPushNotification(String title, String message, String deviceToken) throws Exception {
		try {
			String SERVER_KEY = serverKey;
			String DEVICE_TOKEN = deviceToken;

			String pushMessage = "{\"data\":{\"title\":\"" + title + "\",\"message\":\"" + message + "\"},\"to\":\""
					+ DEVICE_TOKEN + "\"}";
			// Create connection to send FCM Message request.
			URL url = new URL(googleUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// Send FCM message content.
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(pushMessage.getBytes());
			conn.getResponseCode();
			conn.getResponseMessage();
		} catch (Exception e) {
			throw e;
		}
	}

	public static JSONObject getAddressFromUrl(String pincode) throws Exception{
		JSONObject jsonObject=null;
		try {
			InputStream is = new URL(Constants.PINCODEAPIURL.getValue()+pincode).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = (JSONObject) new JSONParser().parse(jsonText);
			JSONArray jsonArray=(JSONArray)json.get("PostOffice");
			if(jsonArray!=null) {
				jsonObject=(JSONObject) jsonArray.get(0);
			}
		}catch (Exception e) {
			throw e;
		}
		return jsonObject;
	}
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
