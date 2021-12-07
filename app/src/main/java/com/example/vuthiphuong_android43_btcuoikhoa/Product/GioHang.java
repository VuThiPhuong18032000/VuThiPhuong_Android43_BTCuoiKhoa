package com.example.vuthiphuong_android43_btcuoikhoa.Product;

import java.io.Serializable;

public class GioHang implements Serializable {
    String id;
    String anhGH;
    String tenGH;
    String mauGH;
    String sizeGH;
    int giaGH;
    int soLuongGH;
//    String hoTen;
//    String soDienThoai;
//    String diaChi;

    public GioHang() {
    }

//    public GioHang(String id, String anhGH, String tenGH, String mauGH, String sizeGH, int giaGH, int soLuongGH, String hoTen, String soDienThoai, String diaChi) {
//        this.id = id;
//        this.anhGH = anhGH;
//        this.tenGH = tenGH;
//        this.mauGH = mauGH;
//        this.sizeGH = sizeGH;
//        this.giaGH = giaGH;
//        this.soLuongGH = soLuongGH;
//        this.hoTen = hoTen;
//        this.soDienThoai = soDienThoai;
//        this.diaChi = diaChi;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAnhGH() {
//        return anhGH;
//    }
//
//    public void setAnhGH(String anhGH) {
//        this.anhGH = anhGH;
//    }
//
//    public String getTenGH() {
//        return tenGH;
//    }
//
//    public void setTenGH(String tenGH) {
//        this.tenGH = tenGH;
//    }
//
//    public String getMauGH() {
//        return mauGH;
//    }
//
//    public void setMauGH(String mauGH) {
//        this.mauGH = mauGH;
//    }
//
//    public String getSizeGH() {
//        return sizeGH;
//    }
//
//    public void setSizeGH(String sizeGH) {
//        this.sizeGH = sizeGH;
//    }
//
//    public int getGiaGH() {
//        return giaGH;
//    }
//
//    public void setGiaGH(int giaGH) {
//        this.giaGH = giaGH;
//    }
//
//    public int getSoLuongGH() {
//        return soLuongGH;
//    }
//
//    public void setSoLuongGH(int soLuongGH) {
//        this.soLuongGH = soLuongGH;
//    }
//
//    public String getHoTen() {
//        return hoTen;
//    }
//
//    public void setHoTen(String hoTen) {
//        this.hoTen = hoTen;
//    }
//
//    public String getSoDienThoai() {
//        return soDienThoai;
//    }
//
//    public void setSoDienThoai(String soDienThoai) {
//        this.soDienThoai = soDienThoai;
//    }
//
//    public String getDiaChi() {
//        return diaChi;
//    }
//
//    public void setDiaChi(String diaChi) {
//        this.diaChi = diaChi;
//    }

        public GioHang(String id, String anhGH, String tenGH, String mauGH, String sizeGH, int giaGH, int soLuongGH) {
        this.id = id;
        this.anhGH = anhGH;
        this.tenGH = tenGH;
        this.mauGH = mauGH;
        this.sizeGH = sizeGH;
        this.giaGH = giaGH;
        this.soLuongGH = soLuongGH;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnhGH() {
        return anhGH;
    }

    public void setAnhGH(String anhGH) {
        this.anhGH = anhGH;
    }

    public String getTenGH() {
        return tenGH;
    }

    public void setTenGH(String tenGH) {
        this.tenGH = tenGH;
    }

    public String getMauGH() {
        return mauGH;
    }

    public void setMauGH(String mauGH) {
        this.mauGH = mauGH;
    }

    public String getSizeGH() {
        return sizeGH;
    }

    public void setSizeGH(String sizeGH) {
        this.sizeGH = sizeGH;
    }

    public int getGiaGH() {
        return giaGH;
    }

    public void setGiaGH(int giaGH) {
        this.giaGH = giaGH;
    }

    public int getSoLuongGH() {
        return soLuongGH;
    }

    public void setSoLuongGH(int soLuongGH) {
        this.soLuongGH = soLuongGH;
    }

}
