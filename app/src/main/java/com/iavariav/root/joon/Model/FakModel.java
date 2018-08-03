package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class FakModel{

	@SerializedName("kemkumham_no")
	private String kemkumhamNo;

	@SerializedName("ketum")
	private String ketum;

	@SerializedName("foto_partai")
	private String fotoPartai;

	@SerializedName("nama")
	private String nama;

	@SerializedName("sekjen")
	private String sekjen;

	@SerializedName("singkatan")
	private String singkatan;

	@SerializedName("notaris_no")
	private String notarisNo;

	@SerializedName("notaris_nama")
	private String notarisNama;

	@SerializedName("bendum")
	private String bendum;

	@SerializedName("bank_no")
	private String bankNo;

	@SerializedName("alamat")
	private String alamat;

	public void setKemkumhamNo(String kemkumhamNo){
		this.kemkumhamNo = kemkumhamNo;
	}

	public String getKemkumhamNo(){
		return kemkumhamNo;
	}

	public void setKetum(String ketum){
		this.ketum = ketum;
	}

	public String getKetum(){
		return ketum;
	}

	public void setFotoPartai(String fotoPartai){
		this.fotoPartai = fotoPartai;
	}

	public String getFotoPartai(){
		return fotoPartai;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setSekjen(String sekjen){
		this.sekjen = sekjen;
	}

	public String getSekjen(){
		return sekjen;
	}

	public void setSingkatan(String singkatan){
		this.singkatan = singkatan;
	}

	public String getSingkatan(){
		return singkatan;
	}

	public void setNotarisNo(String notarisNo){
		this.notarisNo = notarisNo;
	}

	public String getNotarisNo(){
		return notarisNo;
	}

	public void setNotarisNama(String notarisNama){
		this.notarisNama = notarisNama;
	}

	public String getNotarisNama(){
		return notarisNama;
	}

	public void setBendum(String bendum){
		this.bendum = bendum;
	}

	public String getBendum(){
		return bendum;
	}

	public void setBankNo(String bankNo){
		this.bankNo = bankNo;
	}

	public String getBankNo(){
		return bankNo;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}