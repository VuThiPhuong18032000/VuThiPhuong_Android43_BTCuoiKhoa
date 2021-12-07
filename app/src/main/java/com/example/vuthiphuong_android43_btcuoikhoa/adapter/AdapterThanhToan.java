package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.GioHang;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.view.ChiTietSanPhamActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.GioHangActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThanhToan extends BaseAdapter {
    Context context;
    List<GioHang> gioHangList;
    TextView tvSoLuong, imgAnhGH, tvTenSPGH, tvGiaGH, tvMauGH, tvSizeGH;

    public AdapterThanhToan(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_thanhtoan, parent, false);
        ImageView imgAnhGH = view.findViewById(R.id.imgAnhGH);
        TextView tvTenSPGH = view.findViewById(R.id.tvTenSPGH);
        TextView tvGiaGH = view.findViewById(R.id.tvGiaGH);

        tvSoLuong = view.findViewById(R.id.tvSoLuong);
        TextView tvMauGH = view.findViewById(R.id.tvMauGH);
        TextView tvSizeGH = view.findViewById(R.id.tvSizeGH);

        GioHang gioHang = gioHangList.get(position);

        Picasso.with(context).load(gioHang.getAnhGH()).into(imgAnhGH);
//        Glide.with(context).load(gioHang.getAnhGH()).into(imgAnhGH);
        tvTenSPGH.setText(gioHang.getTenGH());
        tvGiaGH.setText(gioHang.getGiaGH() + " đ");
        tvMauGH.setText(gioHang.getMauGH());
        tvSizeGH.setText(gioHang.getSizeGH());
        tvSoLuong.setText(gioHang.getSoLuongGH() + "");

        return view;
    }

    private void onClickGoToChiTietSP(SanPham samPham){
        Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_sanPham", samPham);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
