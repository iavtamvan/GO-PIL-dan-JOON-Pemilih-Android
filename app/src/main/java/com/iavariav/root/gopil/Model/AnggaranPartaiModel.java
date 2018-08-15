package com.iavariav.root.gopil.Model;

import com.google.gson.annotations.SerializedName;

public class AnggaranPartaiModel{

	@SerializedName("periode1")
	private String periode1;

	@SerializedName("periode3")
	private String periode3;

	@SerializedName("total")
	private String total;

	@SerializedName("periode2")
	private String periode2;

	@SerializedName("nama_partai")
	private String namaPartai;

	@SerializedName("periode5")
	private String periode5;

	@SerializedName("periode4")
	private String periode4;

	public void setPeriode1(String periode1){
		this.periode1 = periode1;
	}

	public String getPeriode1(){
		return periode1;
	}

	public void setPeriode3(String periode3){
		this.periode3 = periode3;
	}

	public String getPeriode3(){
		return periode3;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setPeriode2(String periode2){
		this.periode2 = periode2;
	}

	public String getPeriode2(){
		return periode2;
	}

	public void setNamaPartai(String namaPartai){
		this.namaPartai = namaPartai;
	}

	public String getNamaPartai(){
		return namaPartai;
	}

	public void setPeriode5(String periode5){
		this.periode5 = periode5;
	}

	public String getPeriode5(){
		return periode5;
	}

	public void setPeriode4(String periode4){
		this.periode4 = periode4;
	}

	public String getPeriode4(){
		return periode4;
	}
}