package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class AnggaranModel{

	@SerializedName("Termin_II")
	private String terminII;

	@SerializedName("Anggaran_Yang_Disetujui")
	private String anggaranYangDisetujui;

	@SerializedName("Termin_I")
	private String terminI;

	@SerializedName("wilayah")
	private String wilayah;

	@SerializedName("Termin_satu")
	private String terminSatu;

	@SerializedName("Termin_dua")
	private String terminDua;

	@SerializedName("Termin_tiga")
	private String termStringiga;

	@SerializedName("Termin_III")
	private String terminIII;

	@SerializedName("Nomor_NHPD")
	private String nomorNHPD;

	@SerializedName("Total")
	private String total;

	@SerializedName("Tanggal_NPHD")
	private String tanggalNPHD;

	@SerializedName("Usulan_Anggaran_NPHD_Pemilihan_Serentak_Tahun_2018")
	private String usulanAnggaranNPHDPemilihanSerentakTahun2018;

	@SerializedName("Nama_Satker")
	private String namaSatker;

	public void setTerminII(String terminII){
		this.terminII = terminII;
	}

	public String getTerminII(){
		return terminII;
	}

	public void setAnggaranYangDisetujui(String anggaranYangDisetujui){
		this.anggaranYangDisetujui = anggaranYangDisetujui;
	}

	public String getAnggaranYangDisetujui(){
		return anggaranYangDisetujui;
	}

	public void setTerminI(String terminI){
		this.terminI = terminI;
	}

	public String getTerminI(){
		return terminI;
	}

	public void setWilayah(String wilayah){
		this.wilayah = wilayah;
	}

	public String getWilayah(){
		return wilayah;
	}

	public void setTerminSatu(String terminSatu){
		this.terminSatu = terminSatu;
	}

	public String getTerminSatu(){
		return terminSatu;
	}

	public void setTerminDua(String terminDua){
		this.terminDua = terminDua;
	}

	public String getTerminDua(){
		return terminDua;
	}

	public void setTermStringiga(String termStringiga){
		this.termStringiga = termStringiga;
	}

	public String getTermStringiga(){
		return termStringiga;
	}

	public void setTerminIII(String terminIII){
		this.terminIII = terminIII;
	}

	public String getTerminIII(){
		return terminIII;
	}

	public void setNomorNHPD(String nomorNHPD){
		this.nomorNHPD = nomorNHPD;
	}

	public String getNomorNHPD(){
		return nomorNHPD;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setTanggalNPHD(String tanggalNPHD){
		this.tanggalNPHD = tanggalNPHD;
	}

	public String getTanggalNPHD(){
		return tanggalNPHD;
	}

	public void setUsulanAnggaranNPHDPemilihanSerentakTahun2018(String usulanAnggaranNPHDPemilihanSerentakTahun2018){
		this.usulanAnggaranNPHDPemilihanSerentakTahun2018 = usulanAnggaranNPHDPemilihanSerentakTahun2018;
	}

	public String getUsulanAnggaranNPHDPemilihanSerentakTahun2018(){
		return usulanAnggaranNPHDPemilihanSerentakTahun2018;
	}

	public void setNamaSatker(String namaSatker){
		this.namaSatker = namaSatker;
	}

	public String getNamaSatker(){
		return namaSatker;
	}
}