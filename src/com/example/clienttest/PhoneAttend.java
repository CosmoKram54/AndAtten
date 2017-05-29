package com.example.clienttest;

import android.os.Parcel;
import android.os.Parcelable;

public class PhoneAttend implements Parcelable {

	/**
	 * @param args
	 */
	private String location;
	private String phnum;
	
	public PhoneAttend(String loc, String num){
		location = loc;
		phnum = num;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhnum() {
		return phnum;
	}

	public void setPhnum(String phnum) {
		this.phnum = phnum;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
