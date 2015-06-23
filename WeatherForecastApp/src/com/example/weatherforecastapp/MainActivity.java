package com.example.weatherforecastapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tvOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvOutput = (TextView) findViewById(R.id.tvOutput);

		new WeatherParsingTask().execute();
	}

	public void updateWeather(final String weatherMain,
			final String weatherDes, final String weathericon) {

		new Thread() {
			public void run() {
				HttpClient myClient = new DefaultHttpClient();

				
				HttpPost post = new HttpPost(
						"http://10.128.148.51/weather_php/insert.php");// PC IP Address with file path
				try {
					// Save data in database with namevaluepair
					List<NameValuePair> myArgs = new ArrayList<NameValuePair>();
					myArgs.add(new BasicNameValuePair("main", weatherMain));
					myArgs.add(new BasicNameValuePair("description", weatherDes));
					myArgs.add(new BasicNameValuePair("icon", weathericon));
					post.setEntity(new UrlEncodedFormEntity(myArgs));
					HttpResponse myResponse = myClient.execute(post);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(myResponse.getEntity()
									.getContent()));
					String line = "";
					while ((line = br.readLine()) != null) {
						Log.e("a and b", line);

					}
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("PRO", e.getMessage());
				}
			}
		}.start();

	}

	private class WeatherParsingTask extends
			AsyncTask<Void, Void, List<Weather>> {

		@Override
		protected List<Weather> doInBackground(Void... params) {

			return WeatherParser
					.getWeathers("http://api.openweathermap.org/data/2.5/weather?q=London");
		}

		@Override
		protected void onPostExecute(List<Weather> result) {

			super.onPostExecute(result);

			if (result != null) {

				String message = "";

				for (Weather weather : result) {
					message += weather.toString() + "\n\n";
				}

				tvOutput.setText(message);
				String weathers[] = message.split("\n");
				for (int i = 0; i < weathers.length; i++) {
					Log.e("VAL " + i, weathers[i]);
				}
				// send the message to database which
				updateWeather(weathers[0], weathers[1], weathers[2]);
			}
		}

	}
}
