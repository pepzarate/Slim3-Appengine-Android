package com.example.tutorialandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button obtener = (Button) findViewById(R.id.btn_obtener);
		Button tweet = (Button) findViewById(R.id.bto_tweet);
		final EditText text = (EditText) findViewById(R.id.editTextTweet);
		// tweet = (EditText) findViewById(R.id.edt_tweet);

		obtener.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				try {
					String pojoTweet = getAdesoft("getHola", "", 1);

					Toast.makeText(getApplicationContext(), pojoTweet,
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "" + e,
							Toast.LENGTH_SHORT).show();
				}

			}

		});

		tweet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String pojoTweet = getAdesoft("twitter/tweet", "?content="   + text.getText().toString().replaceAll(" ", "%20")  , 1);

					Toast.makeText(getApplicationContext(), pojoTweet,
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "" + e,
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	public String getAdesoft(String wsRest, String params, int formato) {

		String url = "http://newprojectslim.appspot.com/" + wsRest + params;

		HttpClient client = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);

		try {

			HttpResponse response = client.execute(httpGet);

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				InputStream stream = entity.getContent();

				String responseString;
				if (formato == 0) {
					responseString = convertStreamToString2(stream);
				} else {
					responseString = convertStreamToString(stream);
				}

				// DataWrapper wrap = new DataWrapper();

				// wrap = wrap.fromJson(responseString);

				return responseString;

			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return null;

	}

	private static String convertStreamToString2(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String sb = "";
		// StringBuilder sb = new StringBuilder();

		String line = null;

		try {

			while ((line = reader.readLine()) != null) {

				sb = line;
				// sb.append(line + "\n");

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		return sb;

	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();

		String line = null;

		try {

			while ((line = reader.readLine()) != null) {

				sb.append(line + "\n");

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		return sb.toString();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
