package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class PartaiModel{

	@SerializedName("date")
	private String date;

	@SerializedName("ketum")
	private String ketum;

	@SerializedName("foto_partai")
	private String fotoPartai;

	@SerializedName("tanggal_hardcopy")
	private String tanggalHardcopy;

	@SerializedName("sekjen")
	private String sekjen;

	@SerializedName("singkatan")
	private String singkatan;

	@SerializedName("notaris_tanggal_pendirian")
	private String notarisTanggalPendirian;

	@SerializedName("kemkumham_tanggal")
	private String kemkumhamTanggal;

	@SerializedName("notaris_nama")
	private String notarisNama;

	@SerializedName("bendum")
	private String bendum;

	@SerializedName("bank_no")
	private String bankNo;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("kemkumham_no")
	private String kemkumhamNo;

	@SerializedName("notelp")
	private int notelp;

	@SerializedName("tanggal_cd")
	private String tanggalCd;

	@SerializedName("nama")
	private String nama;

	@SerializedName("notaris_no")
	private String notarisNo;

	@SerializedName("fax")
	private int fax;

	@SerializedName("bank_nama")
	private String bankNama;

	@SerializedName("bank_pemilik")
	private String bankPemilik;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
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

	public void setTanggalHardcopy(String tanggalHardcopy){
		this.tanggalHardcopy = tanggalHardcopy;
	}

	public String getTanggalHardcopy(){
		return tanggalHardcopy;
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

	public void setNotarisTanggalPendirian(String notarisTanggalPendirian){
		this.notarisTanggalPendirian = notarisTanggalPendirian;
	}

	public String getNotarisTanggalPendirian(){
		return notarisTanggalPendirian;
	}

	public void setKemkumhamTanggal(String kemkumhamTanggal){
		this.kemkumhamTanggal = kemkumhamTanggal;
	}

	public String getKemkumhamTanggal(){
		return kemkumhamTanggal;
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

	public void setKemkumhamNo(String kemkumhamNo){
		this.kemkumhamNo = kemkumhamNo;
	}

	public String getKemkumhamNo(){
		return kemkumhamNo;
	}

	public void setNotelp(int notelp){
		this.notelp = notelp;
	}

	public int getNotelp(){
		return notelp;
	}

	public void setTanggalCd(String tanggalCd){
		this.tanggalCd = tanggalCd;
	}

	public String getTanggalCd(){
		return tanggalCd;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNotarisNo(String notarisNo){
		this.notarisNo = notarisNo;
	}

	public String getNotarisNo(){
		return notarisNo;
	}

	public void setFax(int fax){
		this.fax = fax;
	}

	public int getFax(){
		return fax;
	}

	public void setBankNama(String bankNama){
		this.bankNama = bankNama;
	}

	public String getBankNama(){
		return bankNama;
	}

	public void setBankPemilik(String bankPemilik){
		this.bankPemilik = bankPemilik;
	}

	public String getBankPemilik(){
		return bankPemilik;
	}
}