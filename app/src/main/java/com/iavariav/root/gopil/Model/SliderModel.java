package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class SliderModel{

	@SerializedName("nama")
	private String nama;

	@SerializedName("link")
	private String link;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}
}