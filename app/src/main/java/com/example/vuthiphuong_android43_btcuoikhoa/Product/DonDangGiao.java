package com.example.vuthiphuong_android43_btcuoikhoa.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DonDangGiao implements Serializable {
    List<GioHang> gioHangList;
    DiaChiNhanHang diaChiNhanHang;
    String hinhThucThanhToan;
    String thoiGian;
    int tongTien;
    public DonDangGiao() {
    }

    public DonDangGiao(List<GioHang> gioHangList, DiaChiNhanHang diaChiNhanHang, String hinhThucThanhToan, String thoiGian, int tongTien) {
        this.gioHangList = gioHangList;
        this.diaChiNhanHang = diaChiNhanHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
    }

    public List<GioHang> getGioHangList() {
        return gioHangList;
    }

    public void setGioHangList(List<GioHang> gioHangList) {
        this.gioHangList = gioHangList;
    }

    public DiaChiNhanHang getDiaChiNhanHang() {
        return diaChiNhanHang;
    }

    public void setDiaChiNhanHang(DiaChiNhanHang diaChiNhanHang) {
        this.diaChiNhanHang = diaChiNhanHang;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
