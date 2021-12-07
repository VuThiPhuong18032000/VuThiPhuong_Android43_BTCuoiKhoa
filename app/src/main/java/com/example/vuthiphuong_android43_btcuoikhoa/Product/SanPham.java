package com.example.vuthiphuong_android43_btcuoikhoa.Product;

import java.io.Serializable;

public class SanPham implements Serializable {
    String maSP;
    String maLoai;
    String anh;
    String tenSP;
    int gia;
    String moTaSP;
    String cachBaoQuan;


    public SanPham(String maSP, String maLoai, String anh, String tenSP, int gia, String moTaSP, String cachBaoQuan) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.anh = anh;
        this.tenSP = tenSP;
        this.gia = gia;
        this.moTaSP = moTaSP;
        this.cachBaoQuan = cachBaoQuan;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getCachBaoQuan() {
        return cachBaoQuan;
    }

    public void setCachBaoQuan(String cachBaoQuan) {
        this.cachBaoQuan = cachBaoQuan;
    }
}
