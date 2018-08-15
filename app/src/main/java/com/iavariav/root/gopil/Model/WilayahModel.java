package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class WilayahModel{

	@SerializedName("wilayah_name")
	private String wilayahName;

	public void setWilayahName(String wilayahName){
		this.wilayahName = wilayahName;
	}

	public String getWilayahName(){
		return wilayahName;
	}
}