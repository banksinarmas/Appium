package framework;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.JSONObject;

public class TravisCheckout {

	private static final String TRAVIS_TOKEN = "wB4vl41Od8tSDvPSgpHgDQ";
	public static String getLastBuildVersion() throws IOException {

		//JSONObject for mobile-app in branch master
		JSONObject mobileapp_json = curl("/repo/2410737/branch/master");

		String build_link=mobileapp_json.getJSONObject("last_build").optString("@href");

		String android_job_link=curl(build_link).getJSONArray("jobs").getJSONObject(1).optString("@href");

		if(curl(android_job_link).optString("state").equals("started")) {

			System.out.print("Waiting Travis Android build to finish");
			while(curl(android_job_link).optString("finished_at").equals("")) {
				System.out.print(".");
			}
			System.out.println(curl(android_job_link).optString("finished_at"));

		}
		return mobileapp_json.getJSONObject("last_build").optString("number");

	}

	public static JSONObject curl(String endPoint) throws IOException {		

		return new JSONObject(
				given()
				.baseUri("https://api.travis-ci.com")
				.header("Travis-API-Version", "3")
				.header("Authorization", "token "+TRAVIS_TOKEN)
				.when()
				.get(endPoint)
				.body()
				.asString()
				);

	}

}
