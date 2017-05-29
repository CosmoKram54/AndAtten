package com.example.clienttest;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


//Creates the post request to a url given
public class MyRequest {

	String url;
	Context context;
	PhoneAttend info;
	
	public MyRequest(String url, Context context, PhoneAttend info ){
		
		this.url = url;
		this.context = context;
		this.info = info;
	};
	
	public void makeRequest(){
		
		// When the user presses the send Button, 
		// create a http post request at a given url.
		
		RequestQueue queue = Volley.newRequestQueue(context);
	

		//Sends a string to url;
		StringRequest stringRequest = new StringRequest(
				Request.Method.POST, url,
				new Response.Listener<String>() {

					// If successful connection, print a toast message
					// to phone with response
					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Toast.makeText(context,
								"Timed in Succesfully" + response,
								Toast.LENGTH_LONG).show();
						System.out.println(response);
						
						
					}
				},

				//Error Message
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						System.out.println("Error: "
								+ error.getLocalizedMessage() + " " + error.getMessage() + " " );
						error.printStackTrace();
					}

				}) {

			 //Data inside the post request;
			@Override
			protected Map<String, String> getParams() {

				Map<String, String> params = new HashMap<String, String>();
				//instead of data, you might to have call form to get the data from the json.
				params.put("parent", "YoYoMa!");
//				params.put("parent", info.getPhnum() + " " + info.getLocation());

				return params;
			}
			
		};
		queue.add(stringRequest);
		
	}
}
	
		
	
	

