package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class IklanMasyarkatModel{

	@SerializedName("link_video")
	private String linkVideo;

	@SerializedName("sumber")
	private String sumber;

	@SerializedName("judul")
	private String judul;

	@SerializedName("gambar")
	private String gambar;

	private String jenis_video;

	public String getJenis_video() {
		return jenis_video;
	}

	public void setLinkVideo(String linkVideo){
		this.linkVideo = linkVideo;
	}

	public String getLinkVideo(){
		return linkVideo;
	}

	public void setSumber(String sumber){
		this.sumber = sumber;
	}

	public String getSumber(){
		return sumber;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}
}