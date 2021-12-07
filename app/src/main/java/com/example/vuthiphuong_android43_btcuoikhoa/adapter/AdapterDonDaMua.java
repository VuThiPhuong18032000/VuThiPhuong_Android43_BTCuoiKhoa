package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDaMua;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDangGiao;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DonDangGiaoChiTietMainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DonMuaChiTietActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDonDaMua extends BaseAdapter {
    Context context;
    List<DonDaMua> donDaMuaList;
    TextView tvSoLuong;
    TextView tvThanhTien;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public AdapterDonDaMua(Context context, List<DonDaMua> donDaMuaList) {
        this.context = context;
        this.donDaMuaList = donDaMuaList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return donDaMuaList.size();
    }

    @Override
    public Object getItem(int position) {
        return donDaMuaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_donmua, parent, false);
        ImageView imgAnh = view.findViewById(R.id.imgAnh);
        TextView tvTenSP = view.findViewById(R.id.tvTenSP);
        TextView tvGia = view.findViewById(R.id.tvGia);
        tvSoLuong = view.findViewById(R.id.tvSoLuong);
        TextView tvMau = view.findViewById(R.id.tvMau);
        TextView tvSize = view.findViewById(R.id.tvSize);
        TextView tvSoSP = view.findViewById(R.id.tvSoSP);
        tvThanhTien = view.findViewById(R.id.tvThanhTien);
        LinearLayout layout_itemDonDaMua = view.findViewById(R.id.layout_itemDonMua);

        DonDaMua donDaMua = donDaMuaList.get(position);
        Picasso.with(context).load(donDaMua.getDonDangGiao().getGioHangList().get(0).getAnhGH()).into(imgAnh);
//        Glide.with(context).load(gioHang.getAnhGH()).into(imgAnhGH);
        tvTenSP.setText(donDaMua.getDonDangGiao().getGioHangList().get(0).getTenGH());
        tvGia.setText(donDaMua.getDonDangGiao().getGioHangList().get(0).getGiaGH() + " đ");
        tvMau.setText(donDaMua.getDonDangGiao().getGioHangList().get(0).getMauGH());
        tvSize.setText(donDaMua.getDonDangGiao().getGioHangList().get(0).getSizeGH());
        tvSoSP.setText(donDaMua.getDonDangGiao().getGioHangList().size() + " sản phẩm");
        tvSoLuong.setText(donDaMua.getDonDangGiao().getGioHangList().get(0).getSoLuongGH() + "");
        tvThanhTien.setText("Thành tiền: " + donDaMua.getDonDangGiao().getTongTien() + " đ");

        layout_itemDonDaMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDonDaMua(donDaMua);
            }
        });

        return view;
    }
    private void onClickGoToDonDaMua(DonDaMua donDaMua){
        Intent intent = new Intent(context, DonMuaChiTietActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_donDaMua", donDaMua);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
