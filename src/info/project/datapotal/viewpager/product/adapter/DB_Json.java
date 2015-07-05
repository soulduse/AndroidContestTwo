package info.project.datapotal.viewpager.product.adapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;



public class DB_Json {
	
	public static JSONObject getJURL(String url) {
		InputStream is = null;
		String result ="";
		JSONObject jArray = null;
		
		try {
			HttpClient httpc = new DefaultHttpClient();
			HttpGet httpg = new HttpGet(url);
			HttpResponse httpr = httpc.execute(httpg);
			HttpEntity httpe = httpr.getEntity();
			is = httpe.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		
		try {
			BufferedReader breader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = breader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result" + e.toString());
		}
		
		try {
			jArray = new JSONObject(result);
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data" + e.toString());
		}
		return jArray;
	}
	
}