package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class TataTertibModel{

	@SerializedName("no")
	private String no;

	@SerializedName("artikel")
	private String artikel;

	@SerializedName("category")
	private String category;

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

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}