package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class TataCaraModel{

	@SerializedName("no")
	private String no;

	@SerializedName("artikel")
	private String artikel;

	public void setNo(String no){
		this.no = no;
	}

	public String getNo(){
		return no;
	}

	public void setArtikel(String artikel){
		this.artikel = artikel;
	}

	public String getArtikel(){
		return artikel;
	}
}