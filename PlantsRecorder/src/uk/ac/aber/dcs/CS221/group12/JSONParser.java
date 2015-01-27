package uk.ac.aber.dcs.CS221.group12;

/**
 * This is class is used to get JSON from the php pages on the URL specified
 * It is called from other activities and used to interact with the database.
 * @author James
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;




public class JSONParser {
	
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	
	//constructor
	public JSONParser() {
 }
	
	
	/**
	 * @param url -- url to corresponding PHP page eg. create_species.php
	 * @param method -- GET or POST
	 * @param params -- Name Value Pair eg name: James
	 * makes a HttpRequest and returns a JSON Object
	 * @return
	 */
	public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {
		
		try {
		// check for request method
		if(method.equalsIgnoreCase("POST")){
			//default HTTP Client
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			
			
		} else if(method.equalsIgnoreCase("GET")){
			//request method is GET
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String paramString = URLEncodedUtils.format(params, "utf-8");
			url += "?" + paramString;
			HttpGet httpGet = new HttpGet(url);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
		}
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		is.close();
		json = sb.toString();
	} catch (Exception e) {
		Log.e("Buffer error", "Error converting result " + e.toString());
	}
	
	// try to parse string to JSON object
	try {
		jObj = new JSONObject(json);
	} catch (JSONException e) {
		Log.e("JSON Parser", "Error parsing data " + e.toString());
	}
	
	// return JSON String
	return jObj;
	}

}
