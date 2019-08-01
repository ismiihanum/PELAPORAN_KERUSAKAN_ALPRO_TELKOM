package com.ilh.alpro_telkom.model;
import com.google.gson.annotations.SerializedName;

public class PelaporModel{

	@SerializedName("id_pelapor")
	private String idPelapor;

	@SerializedName("id_user")
	private Object idUser;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("judul")
	private Object judul;

	@SerializedName("url_image")
	private String urlImage;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("status")
	private String status;

	public void setIdPelapor(String idPelapor){
		this.idPelapor = idPelapor;
	}

	public String getIdPelapor(){
		return idPelapor;
	}

	public void setIdUser(Object idUser){
		this.idUser = idUser;
	}

	public Object getIdUser(){
		return idUser;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setJudul(Object judul){
		this.judul = judul;
	}

	public Object getJudul(){
		return judul;
	}

	public void setUrlImage(String urlImage){
		this.urlImage = urlImage;
	}

	public String getUrlImage(){
		return urlImage;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}