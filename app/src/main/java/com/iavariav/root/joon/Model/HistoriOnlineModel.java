package com.iavariav.root.joon.Model;

import com.google.gson.annotations.SerializedName;

public class HistoriOnlineModel {

	@SerializedName("nik")
	private String nik;

	@SerializedName("foto")
	private String foto;

	@SerializedName("status_vote")
	private String statusVote;

	@SerializedName("nama_user")
	private String namaUser;

	@SerializedName("petugas")
	private String petugas;

	public void setNik(String nik){
		this.nik = nik;
	}

	public String getNik(){
		return nik;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setStatusVote(String statusVote){
		this.statusVote = statusVote;
	}

	public String getStatusVote(){
		return statusVote;
	}

	public void setNamaUser(String namaUser){
		this.namaUser = namaUser;
	}

	public String getNamaUser(){
		return namaUser;
	}

	public void setPetugas(String petugas){
		this.petugas = petugas;
	}

	public String getPetugas(){
		return petugas;
	}
}