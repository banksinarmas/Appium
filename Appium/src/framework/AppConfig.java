package framework;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class AppConfig {


	private static final String APP_CONFIG_URL="http://simigi.banksinarmas.com/PersonalBanking/rest/v3/action/app-config";
	
	public static HashMap<String, String> getConfig()  {
		JSONObject jsonResult=null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(APP_CONFIG_URL);
			httpget.addHeader("Content-Type", "application/json");

			CloseableHttpResponse response = httpclient.execute(httpget);
			jsonResult=new JSONObject(EntityUtils.toString(response.getEntity()));

		} catch (Exception e) {
			
		}
		
		JSONArray tokenConfig = new JSONArray(jsonResult.get("tokenConfig").toString());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("OP_TIME_SKN", jsonResult.get("opTimeSKN").toString());
		map.put("CUT_OFF_TIME_SKN", jsonResult.get("cutOffTimeSkn").toString());
		map.put("OP_TIME_RTGS", jsonResult.get("opTimeRTGS").toString());
		map.put("CUT_OFF_TIME_RTGS", jsonResult.get("cutOffTimeRtgs").toString());
		map.put("EASY_PIN_MAX_AMOUNT", tokenConfig.getJSONObject(0).optString("max_amount"));
		map.put("OTP_PIN_MAX_AMOUNT", tokenConfig.getJSONObject(1).optString("max_amount"));
		return map;
		
	}
	
}
