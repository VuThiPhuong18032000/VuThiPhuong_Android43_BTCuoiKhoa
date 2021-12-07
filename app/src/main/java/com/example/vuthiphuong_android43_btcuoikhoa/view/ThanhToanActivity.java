package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDangGiao;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterThanhToan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThanhToanActivity extends AppCompatActivity {
    ListView lvThanhToan;
    AdapterThanhToan adapterThanhToan;
    static TextView tvTongTien;
    public static TextView tvHoTen, tvSoDienThoai, tvDiaChi;
    Button btnThanhToan;
    TextView tvHinhThucThanhToan;
    static int tongTien;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        lvThanhToan = findViewById(R.id.lvProduct);
        Button btnBackTT = findViewById(R.id.btnBackTT);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        tvTongTien = findViewById(R.id.tvTongTien);
        tvHoTen = findViewById(R.id.tvHoTen);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvHinhThucThanhToan = findViewById(R.id.tvHinhThucThanhToan);

        if(MainActivity.muaNgayArrayList.size()>0){
            adapterThanhToan =new AdapterThanhToan(getBaseContext(), MainActivity.muaNgayArrayList);
            lvThanhToan.setAdapter(adapterThanhToan);
            tongTienThanhToanMuaNgay();
        }
        else {
            adapterThanhToan =new AdapterThanhToan(getBaseContext(), MainActivity.gioHangArrayList);
            lvThanhToan.setAdapter(adapterThanhToan);
            tongTienThanhToan();
        }
//        adapterThanhToan =new AdapterThanhToan(getBaseContext(), MainActivity.gioHangArrayList);
//        lvThanhToan.setAdapter(adapterThanhToan);
        btnBackTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
                onBackPressed();
                MainActivity.muaNgayArrayList.clear();
            }
        });
        showDiaChiNhanHang();
//        tongTienThanhToan();
        thanhToan();
    }
    public static void tongTienThanhToan(){
        tongTien = 20000;
        for(int i=0; i<MainActivity.gioHangArrayList.size(); i++){
            tongTien += (MainActivity.gioHangArrayList.get(i).getGiaGH()*MainActivity.gioHangArrayList.get(i).getSoLuongGH());
        }
        tvTongTien.setText(tongTien + "đ");
    }

    public static void tongTienThanhToanMuaNgay(){
        tongTien = 20000;
        for(int i=0; i<MainActivity.muaNgayArrayList.size(); i++){
            tongTien += (MainActivity.muaNgayArrayList.get(i).getGiaGH()*MainActivity.muaNgayArrayList.get(i).getSoLuongGH());
        }
        tvTongTien.setText(tongTien + "đ");
    }

    public void showDiaChiNhanHang(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        if(MainActivity.diaChiNhanHang != null){
            String hoTen = MainActivity.diaChiNhanHang.getHoTen();
            String soDienThoai = MainActivity.diaChiNhanHang.getSoDienThoai();
            String diaChi = MainActivity.diaChiNhanHang.getDiaChi();

            tvHoTen.setText(hoTen);
            tvSoDienThoai.setText(soDienThoai);
            tvDiaChi.setText(diaChi);
        }
        else {
            Toast.makeText(getApplication(), "Vui lòng nhập địa chỉ để tiếp tục thanh toán", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), DiaChiNhanHangActivity.class);
            startActivity(intent);
        }
    }

    public void thanhToan(){
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();
                if(user != null) {
//                    if(tvHoTen.getText()=="" || tvSoDienThoai.getText()=="" || tvDiaChi.getText()==""){
//                        Toast.makeText(getApplication(), "Vui lòng nhập đầy đủ thông tin nhận hàng", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getBaseContext(), DiaChiNhanHangActivity.class);
//                        startActivity(intent);
//                    }
                    if(MainActivity.muaNgayArrayList.size()>0){
                        Toast.makeText(getApplication(), "Thanh toán thành công", Toast.LENGTH_LONG).show();
                        DateFormat df = new SimpleDateFormat("dd-MM-yyy 'at' HH:mm");
                        String thoiGian = df.format(Calendar.getInstance().getTime());

                        DonDangGiao donDangGiao = new DonDangGiao(MainActivity.muaNgayArrayList, MainActivity.diaChiNhanHang, tvHinhThucThanhToan.getText().toString(), thoiGian, tongTien);
                        databaseReference.child("DonDangGiao").child(user.getUid() + "").child(donDangGiao.getThoiGian()).setValue(donDangGiao);
                        Intent intent = new Intent(getBaseContext(), DonHangMuaActivity.class);
                        startActivity(intent);
                        MainActivity.muaNgayArrayList.clear();
                    }
                    else {
                        Toast.makeText(getApplication(), "Thanh toán thành công", Toast.LENGTH_LONG).show();
                        DateFormat df = new SimpleDateFormat("dd-MM-yyy 'at' HH:mm");
                        String thoiGian = df.format(Calendar.getInstance().getTime());

                        DonDangGiao donDangGiao = new DonDangGiao(MainActivity.gioHangArrayList, MainActivity.diaChiNhanHang, tvHinhThucThanhToan.getText().toString(), thoiGian, tongTien);
                        databaseReference.child("DonDangGiao").child(user.getUid() + "").child(donDangGiao.getThoiGian()).setValue(donDangGiao);
                        Intent intent = new Intent(getBaseContext(), DonHangMuaActivity.class);
                        startActivity(intent);
                        MainActivity.gioHangArrayList.clear();
                        databaseReference.child("GioHang").child(user.getUid()).removeValue();
                    }
                }
                else {
//                    Toast.makeText(getApplication(), "Vui lòng đăng nhập để mua hàng", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}