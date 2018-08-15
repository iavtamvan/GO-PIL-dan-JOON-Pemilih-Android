package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class NewsModel{

	@SerializedName("artikel")
	private String artikel;

	@SerializedName("gambarLink")
	private String gambarLink;

	@SerializedName("createdby")
	private String createdby;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("judul")
	private String judul;

	private String jenis_berita;

	public String getJenis_berita() {
		return jenis_berita;
	}

	public void setArtikel(String artikel){
		this.artikel = artikel;
	}

	public String getArtikel(){
		return artikel;
	}

	public void setGambarLink(String gambarLink){
		this.gambarLink = gambarLink;
	}

	public String getGambarLink(){
		return gambarLink;
	}

	public void setCreatedby(String createdby){
		this.createdby = createdby;
	}

	public String getCreatedby(){
		return createdby;
	}

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}
}