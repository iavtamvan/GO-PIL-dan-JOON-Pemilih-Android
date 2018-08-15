package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class AnggaranPribadiModel {

	@SerializedName("no")
	private int no;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("nama_calon")
	private String namaCalon;

	@SerializedName("nilai")
	private String nilai;

	@SerializedName("jenis_harta")
	private String jenisHarta;

	@SerializedName("wilayah")
	private String wilayah;

	private String jenisAnggaran;

	private String foto;

	public String getJenisAnggaran() {
		return jenisAnggaran;
	}

	public String getFoto() {
		return foto;
	}

	public void setNo(int no){
		this.no = no;
	}

	public int getNo(){
		return no;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setNamaCalon(String namaCalon){
		this.namaCalon = namaCalon;
	}

	public String getNamaCalon(){
		return namaCalon;
	}

	public void setNilai(String nilai){
		this.nilai = nilai;
	}

	public String getNilai(){
		return nilai;
	}

	public void setJenisHarta(String jenisHarta){
		this.jenisHarta = jenisHarta;
	}

	public String getJenisHarta(){
		return jenisHarta;
	}

	public void setWilayah(String wilayah){
		this.wilayah = wilayah;
	}

	public String getWilayah(){
		return wilayah;
	}
}