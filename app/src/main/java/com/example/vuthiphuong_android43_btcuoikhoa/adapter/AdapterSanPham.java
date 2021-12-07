package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.view.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHoder> {
    Context context;
    List<SanPham> sanPhamList;

    public AdapterSanPham(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_sanpham, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        if(sanPham == null){
            return;
        }
//        Picasso.with(context).load(sanPham.getAnh()).into(holder.imgAnh);
        Glide.with(context).load(sanPham.getAnh()).into(holder.imgAnh);
        holder.tvTen.setText(sanPham.getTenSP());
        holder.tvGia.setText(sanPham.getGia()+" đ");

        holder.layout_itemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToChiTietSP(sanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(sanPhamList != null){
            return sanPhamList.size();
        }
        return 0;
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        CardView layout_itemSP;
        TextView tvTen, tvGia;
        ImageView imgAnh;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            layout_itemSP = itemView.findViewById(R.id.layout_itemSP);
            imgAnh = itemView.findViewById(R.id.imgAnh);
            tvTen = itemView.findViewById(R.id.tvTenSP);
            tvGia = itemView.findViewById(R.id.tvGia);
        }
    }

    private void onClickGoToChiTietSP(SanPham samPham){
        Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_sanPham", samPham);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


//    Context context;
//    List<SanPham> sanPhamList;
//
//    public AdapterSanPham(Context context, List<SanPham> sanPhamList) {
//        this.context = context;
//        this.sanPhamList = sanPhamList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolderSanPham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.item_sanpham, parent, false);
//        return new ViewHolderSanPham(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolderSanPham holder, int position) {
//        SanPham sanPham = sanPhamList.get(position);
//        if(sanPham == null){
//            return;
//        }
//        Picasso.with(context).load(sanPham.getAnh()).into(holder.imgAnh);
//        holder.tvTen.setText(sanPham.getTenSP());
//        holder.tvGia.setText(sanPham.getGia()+" đ");
//
//        holder.layout_itemSP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickGoToChiTietSP(sanPham);
//            }
//        });
//    }
//
//    private void onClickGoToChiTietSP(SanPham samPham){
//        Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("object_sanPham", samPham);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
//    }
//
//    @Override
//    public int getItemCount() {
//        if(sanPhamList != null){
//            return sanPhamList.size();
//        }
//        return 0;
//    }
//
//    public class ViewHolderSanPham extends RecyclerView.ViewHolder{
//        CardView layout_itemSP;
//        TextView tvTen, tvGia;
//        ImageView imgAnh;
//        public ViewHolderSanPham(@NonNull View itemView) {
//            super(itemView);
//            layout_itemSP = itemView.findViewById(R.id.layout_itemSP);
//            imgAnh = itemView.findViewById(R.id.imgAnh);
//            tvTen = itemView.findViewById(R.id.tvTenSP);
//            tvGia = itemView.findViewById(R.id.tvGia);
//        }
//    }
}
