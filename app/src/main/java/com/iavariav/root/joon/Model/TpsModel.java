package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class TpsModel{

	@SerializedName("lng")
	private String lng;

	@SerializedName("nama_tps")
	private String namaTps;

	@SerializedName("lat")
	private String lat;

	public void setLng(String lng){
		this.lng = lng;
	}

	public String getLng(){
		return lng;
	}

	public void setNamaTps(String namaTps){
		this.namaTps = namaTps;
	}

	public String getNamaTps(){
		return namaTps;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}
}