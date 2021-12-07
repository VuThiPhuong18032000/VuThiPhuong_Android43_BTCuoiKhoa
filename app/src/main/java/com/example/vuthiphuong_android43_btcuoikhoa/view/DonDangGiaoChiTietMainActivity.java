package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDaMua;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDangGiao;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.GioHang;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterThanhToan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DonDangGiaoChiTietMainActivity extends AppCompatActivity {
    AdapterThanhToan adapterThanhToan;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DonDangGiao donDangGiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_dang_giao_chi_tiet_main);
        TextView tvHoTen = findViewById(R.id.tvHoTen);
        TextView tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        TextView tvDiaChi = findViewById(R.id.tvDiaChi);
        TextView tvHinhThucThanhToan = findViewById(R.id.tvHinhThucThanhToan);
        TextView tvThoiGian = findViewById(R.id.tvThoiGian);
        TextView tvTongTien = findViewById(R.id.tvTongTien);
        ListView lvProduct = findViewById(R.id.lvProduct);
        Button btnBackDonDangGiaoChiTiet = findViewById(R.id.btnBackDonDangGiaoChiTiet);
        Button btnXacNhanDonHang = findViewById(R.id.btnXacNhanDonHang);

        Bundle bundle = getIntent().getExtras();
        donDangGiao = (DonDangGiao) bundle.get("object_donDangGiao");

        if(donDangGiao == null){
            return;
        }

        tvHoTen.setText(donDangGiao.getDiaChiNhanHang().getHoTen());
        tvSoDienThoai.setText(donDangGiao.getDiaChiNhanHang().getSoDienThoai());
        tvDiaChi.setText(donDangGiao.getDiaChiNhanHang().getDiaChi());

        adapterThanhToan = new AdapterThanhToan(getBaseContext(), donDangGiao.getGioHangList());
        lvProduct.setAdapter(adapterThanhToan);

        tvHinhThucThanhToan.setText(donDangGiao.getHinhThucThanhToan());
        tvThoiGian.setText(donDangGiao.getThoiGian());
        tvTongTien.setText(donDangGiao.getTongTien() + " đ");

        btnBackDonDangGiaoChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();
                if(user != null){
                    Toast.makeText(getApplication(), "Xác nhận hoàn thành đơn hàng thành công", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplication(), "Vui lòng đăng nhập để mua hàng", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplication(), LoginActivity.class);
//                        startActivity(intent);
                }
            }
        });
    }
}