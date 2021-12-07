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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.GioHang;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.view.ChiTietSanPhamActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.GioHangActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterGioHang extends BaseAdapter {
    Context context;
    List<GioHang> gioHangList;
    Button btnGiamSL, btnTangSL;
    TextView tvSoLuong;
    int index;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    GioHang gioHang;
//    int soLuongGH=ChiTietSanPhamActivity.soLuong;

    public AdapterGioHang(Context context, List<GioHang> gioHangList) {
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
        View view = layoutInflater.inflate(R.layout.item_giohang, parent, false);
        ImageView imgAnhGH = view.findViewById(R.id.imgAnhGH);
        TextView tvTenSPGH = view.findViewById(R.id.tvTenSPGH);
        TextView tvGiaGH = view.findViewById(R.id.tvGiaGH);
        btnGiamSL = view.findViewById(R.id.btnGiamSL);
        btnTangSL = view.findViewById(R.id.btnTangSL);
        tvSoLuong = view.findViewById(R.id.tvSoLuong);
        TextView tvMauGH = view.findViewById(R.id.tvMauGH);
        TextView tvSizeGH = view.findViewById(R.id.tvSizeGH);
        index=position;

        gioHang = gioHangList.get(position);
        Picasso.with(context).load(gioHang.getAnhGH()).into(imgAnhGH);
//        Glide.with(context).load(gioHang.getAnhGH()).into(imgAnhGH);
        tvTenSPGH.setText(gioHang.getTenGH());
        tvGiaGH.setText(gioHang.getGiaGH() + "");
        tvMauGH.setText(gioHang.getMauGH());
        tvSizeGH.setText(gioHang.getSizeGH());
        tvSoLuong.setText(gioHang.getSoLuongGH() + "");

        SoLuongGH();

        return view;
    }
    private void SoLuongGH(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        btnTangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongGH = Integer.parseInt(tvSoLuong.getText().toString())+1;
                tvSoLuong.setText(soLuongGH + "");
                MainActivity.gioHangArrayList.get(index).setSoLuongGH(soLuongGH);
                databaseReference.child("GioHang").child(user.getUid()).child(gioHang.getId()).child("soLuongGH").setValue(soLuongGH);
                GioHangActivity.tongTienGioHang();
            }
        });

        btnGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tvSoLuong.getText().toString()) > 1){
                    int soLuongGH = Integer.parseInt(tvSoLuong.getText().toString())-1;
                    tvSoLuong.setText(soLuongGH + "");
                    MainActivity.gioHangArrayList.get(index).setSoLuongGH(soLuongGH);
                    databaseReference.child("GioHang").child(user.getUid()).child(gioHang.getId()).child("soLuongGH").setValue(soLuongGH);
                    GioHangActivity.tongTienGioHang();
                }
                else {
                    gioHangList.remove(index);
                    databaseReference.child("GioHang").child(user.getUid()).child(gioHang.getId()).removeValue();
                    notifyDataSetChanged();
                    GioHangActivity.tongTienGioHang();
                }
            }
        });
    }
}
