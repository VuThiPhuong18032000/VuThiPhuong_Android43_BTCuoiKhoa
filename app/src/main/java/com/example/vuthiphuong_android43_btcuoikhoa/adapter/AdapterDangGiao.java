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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDaMua;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDangGiao;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DonDangGiaoChiTietMainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterDangGiao extends BaseAdapter {
    Context context;
    List<DonDangGiao> donDangGiaoList;
    TextView tvSoLuong;
    TextView tvThanhTien;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DonDangGiao donDangGiao;
    Button btnXacNhanDonHang;

    public AdapterDangGiao(Context context, List<DonDangGiao> donDangGiaoList) {
        this.context = context;
        this.donDangGiaoList = donDangGiaoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return donDangGiaoList.size();
    }

    @Override
    public Object getItem(int position) {
        return donDangGiaoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_dondanggiao, parent, false);
        ImageView imgAnh = view.findViewById(R.id.imgAnh);
        TextView tvTenSP = view.findViewById(R.id.tvTenSP);
        TextView tvGia = view.findViewById(R.id.tvGia);
        tvSoLuong = view.findViewById(R.id.tvSoLuong);
        TextView tvMau = view.findViewById(R.id.tvMau);
        TextView tvSize = view.findViewById(R.id.tvSize);
        TextView tvSoSP = view.findViewById(R.id.tvSoSP);
        tvThanhTien = view.findViewById(R.id.tvThanhTien);
        LinearLayout layout_itemDonDangGiao = view.findViewById(R.id.layout_itemDonDangGiao);
        btnXacNhanDonHang = view.findViewById(R.id.btnXacNhanDonHang);

        donDangGiao = donDangGiaoList.get(position);
        Picasso.with(context).load(donDangGiao.getGioHangList().get(0).getAnhGH()).into(imgAnh);
//        Glide.with(context).load(gioHang.getAnhGH()).into(imgAnhGH);
        tvTenSP.setText(donDangGiao.getGioHangList().get(0).getTenGH());
        tvGia.setText(donDangGiao.getGioHangList().get(0).getGiaGH() + " đ");
        tvMau.setText(donDangGiao.getGioHangList().get(0).getMauGH());
        tvSize.setText(donDangGiao.getGioHangList().get(0).getSizeGH());
        tvSoSP.setText(donDangGiao.getGioHangList().size() + " sản phẩm");
        tvSoLuong.setText(donDangGiao.getGioHangList().get(0).getSoLuongGH() + "");
        tvThanhTien.setText("Thành tiền: " + donDangGiao.getTongTien() + " đ");

        xacNhanDonHang();
        layout_itemDonDangGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDonDangGiao(donDangGiao);
            }
        });

        return view;
    }

    private void onClickGoToDonDangGiao(DonDangGiao donDangGiao){
        Intent intent = new Intent(context, DonDangGiaoChiTietMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_donDangGiao", donDangGiao);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private void xacNhanDonHang(){
        btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();
                if(user != null){
                    Toast.makeText(context, "Xác nhận hoàn thành đơn hàng thành công", Toast.LENGTH_LONG).show();
                    if(MainActivity.donDangGiaoArrayList.size()>0){
//                        String thoiGianDatHang = tvThoiGian.getText().toString();
//                        for(int i=0; i<MainActivity.donDangGiaoArrayList.size(); i++){
//                            if(MainActivity.donDangGiaoArrayList.get(i).getThoiGian().equals(thoiGianDatHang)){
                        MainActivity.donDangGiaoArrayList.remove(donDangGiao);
                        databaseReference.child("DonDangGiao").child(user.getUid() + "").child(donDangGiao.getThoiGian()).removeValue();

                        DateFormat df = new SimpleDateFormat("dd-MM-yyy 'at' HH:mm");
                        String thoiGianHoanThanh = df.format(Calendar.getInstance().getTime());

                        MainActivity.donDaMuaArrayList.add(new DonDaMua(donDangGiao, thoiGianHoanThanh));
                        DonDaMua donDaMua = new DonDaMua(donDangGiao, thoiGianHoanThanh);
                        databaseReference.child("DonDaMua").child(user.getUid() + "").child(donDaMua.getThoiGianHoanThanh()).setValue(donDaMua);
//                            }
//                        }
                    }
                }
                else {
//                    Toast.makeText(context, "Vui lòng đăng nhập để mua hàng", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplication(), LoginActivity.class);
//                        startActivity(intent);
                }
            }
        });
    }

}
