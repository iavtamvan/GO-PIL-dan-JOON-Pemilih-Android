package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class ResultVoteModel{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("nama_calon")
	private String namaCalon;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("jumlah_suara")
	private String jumlahSuara;

	@SerializedName("nama_wakli_calon")
	private String namaWakliCalon;

	private String foto_berdua;


	public String getFoto_berdua() {
		return foto_berdua;
	}

	public void setFoto_berdua(String foto_berdua) {
		this.foto_berdua = foto_berdua;
	}

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setNamaCalon(String namaCalon){
		this.namaCalon = namaCalon;
	}

	public String getNamaCalon(){
		return namaCalon;
	}

	public void setJabatan(String jabatan){
		this.jabatan = jabatan;
	}

	public String getJabatan(){
		return jabatan;
	}

	public void setJumlahSuara(String jumlahSuara){
		this.jumlahSuara = jumlahSuara;
	}

	public String getJumlahSuara(){
		return jumlahSuara;
	}

	public void setNamaWakliCalon(String namaWakliCalon){
		this.namaWakliCalon = namaWakliCalon;
	}

	public String getNamaWakliCalon(){
		return namaWakliCalon;
	}
}