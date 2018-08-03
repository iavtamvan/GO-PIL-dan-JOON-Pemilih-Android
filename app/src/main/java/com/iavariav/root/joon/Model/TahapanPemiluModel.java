package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class TahapanPemiluModel{

	@SerializedName("tgl_akhir")
	private String tglAkhir;

	@SerializedName("tahapan_persiapan")
	private String tahapanPersiapan;

	@SerializedName("tgl_awal")
	private String tglAwal;

	@SerializedName("wilayah")
	private String wilayah;

	public void setTglAkhir(String tglAkhir){
		this.tglAkhir = tglAkhir;
	}

	public String getTglAkhir(){
		return tglAkhir;
	}

	public void setTahapanPersiapan(String tahapanPersiapan){
		this.tahapanPersiapan = tahapanPersiapan;
	}

	public String getTahapanPersiapan(){
		return tahapanPersiapan;
	}

	public void setTglAwal(String tglAwal){
		this.tglAwal = tglAwal;
	}

	public String getTglAwal(){
		return tglAwal;
	}

	public void setWilayah(String wilayah){
		this.wilayah = wilayah;
	}

	public String getWilayah(){
		return wilayah;
	}
}