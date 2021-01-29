package com.store2door.api.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class SendNotification {
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
}
