package com.example.clienttest;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {

	/**
	 * @param args
	 */
	Context context;
	String address;
	public MyLocationListener(Context con){
		context = con;
	}
	
	//The location is only changed by parameters given in locationManager.requestLocationUpdates()

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		
		String longitude = "Longitude: " +location.getLongitude();  
				      
		String latitude = "Latitude: " +location.getLatitude();
		
		
		
		/*----------to get City-Name from coordinates ------------- */
	      String cityName=null;
	      String streetName=null;
	      String fulladd = null;
	      
	      //Sets up the geocoder
	      Geocoder gcd = new Geocoder(context, 
	   Locale.getDefault());
	      
	      
	      List<Address>  addresses;

	      //Gets the current address
	      try {  

	      addresses = gcd.getFromLocation(location.getLatitude(), location
	   .getLongitude(), 1);  
	      if (addresses.size() > 0)
	    	  
	         System.out.println(addresses.get(0).getLocality());  
	         cityName=addresses.get(0).getLocality();
	         streetName= addresses.get(0).getThoroughfare();
	         fulladd=cityName + " " + streetName;
	         
	         
	        } catch (IOException e) {            
	        e.printStackTrace();  
	      }
	   
	      Toast.makeText(context,"Location changed : " + cityName + " " +fulladd,
				   Toast.LENGTH_LONG).show();
	      String s = "Longitude" + longitude + "Latitude" + latitude;
	      System.out.println("Location " + s);
	      address = fulladd + " " + s;
	      
	   
				     
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	
	public String getAddress(){
		return address;
	}

}
