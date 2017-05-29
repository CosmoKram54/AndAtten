package com.example.clienttest;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


//This app was design to get the phone location and phone number of the user
//and send a HTTPost of the information to a web server
//When the apps first opens, you will have to wait 5000ms(You can change it on line 61)
//Or the user moves 10 meters(Can change on line 61)
//You will also need to change the url on line 86


//If have any questions or know anyways to improve this app or 
//if you just want to tell me how bad this code looks
//Contact me at cosmokram54@gmail.com


public class MainActivity extends ActionBarActivity {

	private LocationManager locationManager;
	private LocationListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// My buttons
		
		Button sendbutton = (Button) findViewById(R.id.button1);
		Button disBut = (Button) findViewById(R.id.dataButton);

		// The intent need for sending data to the next
		// screen(GPSDisplayActivity)
		Intent i = new Intent(this, GPSDisplayActivity.class);

		// For every 5000 millisecond or for every time the user moves 10 meters
		// Away from current location while the app is on
		// Get the current gps location on phone
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		listener = new MyLocationListener(getBaseContext());
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				5000, 10, listener);

		// When the send button is clicked sends a post request with the phone
		// number and current location of user
		sendbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Gets user's telephone number
				TelephonyManager tMgr = (TelephonyManager) getBaseContext()
						.getSystemService(Context.TELEPHONY_SERVICE);
				String mPhoneNumber = tMgr.getLine1Number();

				// Gets the last address the phone read
				String myloc = ((MyLocationListener) listener).getAddress();

				PhoneAttend currentInfo = new PhoneAttend(myloc, mPhoneNumber);

				// Replace the first with the url of the web server that will
				// accept the post request
				MyRequest myreq = new MyRequest("http://httpbin.org/post",
						getBaseContext(), currentInfo);

				// Makes the request
				myreq.makeRequest();
			}
		});

		// Gets the last addressed after the location changed
		String myloc = ((MyLocationListener) listener).getAddress();

		// Sends the address to the Second Screen(GPSDisplayActivity)
		i.putExtra("loc", myloc);

		//When display settings is clicked, get the location and telephone that is going to be sent
		disBut.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getBaseContext(),
						GPSDisplayActivity.class);

				TelephonyManager tMgr = (TelephonyManager) getBaseContext()
						.getSystemService(Context.TELEPHONY_SERVICE);
				String mPhoneNumber = tMgr.getLine1Number();

				String myloc = ((MyLocationListener) listener).getAddress();

				i.putExtra("pn", mPhoneNumber);
				i.putExtra("loc", myloc);

				startActivity(i);
				

			}
		});

	}

	//Helps check gpsStatus of fun
	//Only used for testing
	private Boolean displayGpsStatus() {
		ContentResolver contentResolver = getBaseContext().getContentResolver();

		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
				contentResolver, LocationManager.GPS_PROVIDER);
		if (gpsStatus) {
			return true;

		} else {
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
