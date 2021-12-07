package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDaMua;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDangGiao;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterThanhToan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonMuaChiTietActivity extends AppCompatActivity {
    AdapterThanhToan adapterThanhToan;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DonDaMua donDaMua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_mua_chi_tiet);
        TextView tvHoTen = findViewById(R.id.tvHoTen);
        TextView tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        TextView tvDiaChi = findViewById(R.id.tvDiaChi);
        TextView tvHinhThucThanhToan = findViewById(R.id.tvHinhThucThanhToan);
        TextView tvThoiGian = findViewById(R.id.tvThoiGian);
        TextView tvThoiGianHoanThanh = findViewById(R.id.tvThoiGianHoanThanh);
        TextView tvTongTien = findViewById(R.id.tvTongTien);
        ListView lvProduct = findViewById(R.id.lvProduct);
        Button btnBackDonMuaChiTiet = findViewById(R.id.btnBackDonMuaChiTiet);
        Button btnMuaLai = findViewById(R.id.btnMuaLai);

        Bundle bundle = getIntent().getExtras();
        donDaMua = (DonDaMua) bundle.get("object_donDaMua");

        if(donDaMua == null){
            return;
        }

        tvHoTen.setText(donDaMua.getDonDangGiao().getDiaChiNhanHang().getHoTen());
        tvSoDienThoai.setText(donDaMua.getDonDangGiao().getDiaChiNhanHang().getSoDienThoai());
        tvDiaChi.setText(donDaMua.getDonDangGiao().getDiaChiNhanHang().getDiaChi());

        adapterThanhToan = new AdapterThanhToan(getBaseContext(), donDaMua.getDonDangGiao().getGioHangList());
        lvProduct.setAdapter(adapterThanhToan);

        tvHinhThucThanhToan.setText(donDaMua.getDonDangGiao().getHinhThucThanhToan());
        tvThoiGian.setText(donDaMua.getDonDangGiao().getThoiGian());
        tvThoiGianHoanThanh.setText(donDaMua.getThoiGianHoanThanh());
        tvTongTien.setText(donDaMua.getDonDangGiao().getTongTien() + " đ");

        btnBackDonMuaChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}