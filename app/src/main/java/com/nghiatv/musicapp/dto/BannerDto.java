package com.nghiatv.musicapp.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BannerDto implements Serializable {

    @SerializedName("IdQuangCao")
    @Expose
    private String id;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhanh;
    @SerializedName("NoiDung")
    @Expose
    private String noidung;
    @SerializedName("IdBaiHat")
    @Expose
    private String idbaihat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenbaihat;

    public BannerDto(String id, String hinhanh, String noidung, String idbaihat, String tenbaihat, String hinhbaihat) {
        this.id = id;
        this.hinhanh = hinhanh;
        this.noidung = noidung;
        this.idbaihat = idbaihat;
        this.tenbaihat = tenbaihat;
        this.hinhbaihat = hinhbaihat;
    }

    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhbaihat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getIdbaihat() {
        return idbaihat;
    }

    public void setIdbaihat(String idbaihat) {
        this.idbaihat = idbaihat;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public String getHinhbaihat() {
        return hinhbaihat;
    }

    public void setHinhbaihat(String hinhbaihat) {
        this.hinhbaihat = hinhbaihat;
    }

}