package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class LoginModel{

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("agama")
	private String agama;

	@SerializedName("rule")
	private String rule;

	@SerializedName("wilayah")
	private String wilayah;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("token")
	private String token;

	@SerializedName("petugas")
	private String petugas;

	@SerializedName("nik")
	private String nik;

	@SerializedName("password")
	private String password;

	@SerializedName("tempat_lahir")
	private String tempatLahir;

	@SerializedName("foto")
	private String foto;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("nohp")
	private String nohp;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("status")
	private String status;

	private String idhp;
	private String status_voted;
	private String digital_signature;
	private String status_aplikasi;
	private String foto_muka;
	private String status_login;

	public String getStatus_login() {
		return status_login;
	}

	public String getFoto_muka() {
		return foto_muka;
	}

	public String getIdhp() {
		return idhp;
	}

	public String getStatus_voted() {
		return status_voted;
	}

	public String getDigital_signature() {
		return digital_signature;
	}

	public String getStatus_aplikasi() {
		return status_aplikasi;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setAgama(String agama){
		this.agama = agama;
	}

	public String getAgama(){
		return agama;
	}

	public void setRule(String rule){
		this.rule = rule;
	}

	public String getRule(){
		return rule;
	}

	public void setWilayah(String wilayah){
		this.wilayah = wilayah;
	}

	public String getWilayah(){
		return wilayah;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setPetugas(String petugas){
		this.petugas = petugas;
	}

	public String getPetugas(){
		return petugas;
	}

	public void setNik(String nik){
		this.nik = nik;
	}

	public String getNik(){
		return nik;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setTempatLahir(String tempatLahir){
		this.tempatLahir = tempatLahir;
	}

	public String getTempatLahir(){
		return tempatLahir;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
	}

	public void setNohp(String nohp){
		this.nohp = nohp;
	}

	public String getNohp(){
		return nohp;
	}

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}