package com.abhi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.IOUtils;


/**
 * Utility class for all webservice request and response
 * 
 */

/**
 * Created by Abhishek on 10-09-2015.
 */
public class ServerRequest {
	
	public static final int SERVER_CONNECTION_TIMEOUT = 400000;
	

	public String getServerResponse(String strUrl) throws IOException {
		
		System.out.println("Url:::"+strUrl);
		String result = "";
		InputStream inputStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);
			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setConnectTimeout(SERVER_CONNECTION_TIMEOUT);
			// Connecting to url
			urlConnection.connect();
			// Reading data from url
			inputStream = urlConnection.getInputStream();

			if (inputStream != null) {
				result = convertInputStreamToString(inputStream);

			}
			else
				result = "Error occured!";

			System.out.println("Server Response : " + result);
		} catch (java.net.SocketTimeoutException e) {
			Log.d("CONNECTION TIMEOUT",
					e.toString());
			return null;
		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());

		} finally {
			// inputStream.close();
			urlConnection.disconnect();
		}

		return result;

	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {

      /*  String json ;
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        json = writer.toString();
        return json;*/

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		StringBuilder result = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null)
			result.append(line);

		inputStream.close();
		return result.toString();


	}

	public static String loadJSONFromAsset(Context pContext, String filename) {
		String json = null;
		try {

			InputStream is = pContext.getAssets().open(filename);

			int size = is.available();

			byte[] buffer = new byte[size];

			is.read(buffer);

			is.close();

			json = new String(buffer, "UTF-8");


		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;

	}


	
}
