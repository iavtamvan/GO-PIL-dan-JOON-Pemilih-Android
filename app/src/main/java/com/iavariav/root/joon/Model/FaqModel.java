package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class FaqModel{

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("jawaban")
	private String jawaban;

	@SerializedName("pertanyaan")
	private String pertanyaan;

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
	}

	public void setJawaban(String jawaban){
		this.jawaban = jawaban;
	}

	public String getJawaban(){
		return jawaban;
	}

	public void setPertanyaan(String pertanyaan){
		this.pertanyaan = pertanyaan;
	}

	public String getPertanyaan(){
		return pertanyaan;
	}
}