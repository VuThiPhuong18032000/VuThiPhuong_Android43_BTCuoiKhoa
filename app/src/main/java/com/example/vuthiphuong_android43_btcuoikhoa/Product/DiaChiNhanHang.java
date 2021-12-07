package com.example.vuthiphuong_android43_btcuoikhoa.Product;

import java.io.Serializable;

public class DiaChiNhanHang implements Serializable {
    String hoTen;
    String soDienThoai;
    String diaChi;

    public DiaChiNhanHang(String hoTen, String soDienThoai, String diaChi) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public DiaChiNhanHang() {
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
