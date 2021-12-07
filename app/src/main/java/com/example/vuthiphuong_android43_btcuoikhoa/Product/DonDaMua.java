package com.example.vuthiphuong_android43_btcuoikhoa.Product;

import java.io.Serializable;
import java.util.List;

public class DonDaMua implements Serializable {
    DonDangGiao  donDangGiao;
    String thoiGianHoanThanh;
    public DonDaMua() {
    }

    public DonDaMua(DonDangGiao donDangGiao, String thoiGianHoanThanh) {
        this.donDangGiao = donDangGiao;
        this.thoiGianHoanThanh = thoiGianHoanThanh;
    }

    public DonDangGiao getDonDangGiao() {
        return donDangGiao;
    }

    public void setDonDangGiao(DonDangGiao donDangGiao) {
        this.donDangGiao = donDangGiao;
    }

    public String getThoiGianHoanThanh() {
        return thoiGianHoanThanh;
    }

    public void setThoiGianHoanThanh(String thoiGianHoanThanh) {
        this.thoiGianHoanThanh = thoiGianHoanThanh;
    }
}
