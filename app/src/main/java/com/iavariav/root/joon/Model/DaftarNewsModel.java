package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class DaftarNewsModel{

	@SerializedName("jenis_berita")
	private String jenisBerita;

	public String getJenisBerita(){
		return jenisBerita;
	}
}