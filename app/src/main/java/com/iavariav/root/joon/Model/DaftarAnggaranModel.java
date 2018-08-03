package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class DaftarAnggaranModel{

	@SerializedName("jenis_anggaran")
	private String jenisAnggaran;

	@SerializedName("detail")
	private String detail;

	public void setJenisAnggaran(String jenisAnggaran){
		this.jenisAnggaran = jenisAnggaran;
	}

	public String getJenisAnggaran(){
		return jenisAnggaran;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}
}