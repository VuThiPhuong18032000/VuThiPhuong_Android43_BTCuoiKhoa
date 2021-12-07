package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.GioHang;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Button btnMau1, btnMau2, btnMau3, btnMau4, btnMau5, btnMau6;
    Button btnSize1,btnSize2,btnSize3;
    Button btnTang, btnGiam;
    TextView tvSoLuongSP;
    public static int soLuong=1;
    String idGH="";
    int giaGH=0;
    String mauGH="";
    String sizeGH="";
    String tenGH="";
    String anhGH="";
    String hoTen="";
    String soDienThoai="";
    String diaChi="";
    Button btnThemVaoGioHang, btnMuaNgay, btnGioHangCT;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

//    static float tongTien=0;
//    List<SanPham> samPhamList;
//    SanPham sanPham;
//    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_chitietsanpham);

        ImageView imgAnhCT = findViewById(R.id.imgAnhCT);
        TextView tvTenSPCT = findViewById(R.id.tvTenSPCT);
        TextView tvGiaCT = findViewById(R.id.tvGiaCT);
        TextView tvMoTaSPCT = findViewById(R.id.tvMoTaSPCT);
        TextView tvCachBaoQuanCT = findViewById(R.id.tvCachBaoQuanCT);
        Button btnBackCT = findViewById(R.id.btnBackCT);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        btnGioHangCT = findViewById(R.id.btnGioHangCT);
        btnMau1 = findViewById(R.id.btnMau1);
        btnMau2 = findViewById(R.id.btnMau2);
        btnMau3 = findViewById(R.id.btnMau3);
        btnMau4 = findViewById(R.id.btnMau4);
        btnMau5 = findViewById(R.id.btnMau5);
        btnMau6 = findViewById(R.id.btnMau6);

        btnSize1 = findViewById(R.id.btnSize1);
        btnSize2 = findViewById(R.id.btnSize2);
        btnSize3 = findViewById(R.id.btnSize3);

        btnTang = findViewById(R.id.btnTang);
        btnGiam = findViewById(R.id.btnGiam);
        tvSoLuongSP = findViewById(R.id.tvSoLuongSP);

        Bundle bundle = getIntent().getExtras();
        SanPham sanPham = (SanPham) bundle.get("object_sanPham");
        //Picasso.with(getApplication()).load(sanPham.getAnh()).into(imgAnhCT);
        Glide.with(getApplication()).load(sanPham.getAnh()).into(imgAnhCT);
        tvTenSPCT.setText(sanPham.getTenSP());
        tvGiaCT.setText(sanPham.getGia() + "đ");
        tvMoTaSPCT.setText(sanPham.getMoTaSP());
        tvCachBaoQuanCT.setText(sanPham.getCachBaoQuan());
        idGH = sanPham.getMaSP();
        giaGH = sanPham.getGia();
        tenGH = sanPham.getTenSP();
        anhGH = sanPham.getAnh();

        btnBackCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnGioHangCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GioHangActivity.class);
                startActivity(intent);
            }
        });

        SoLuong();
        chonMau();
        chonSize();
        onClickThemVaoGioHang();
        onClickMuaNgay();
    }

    private void chonMau(){
        btnMau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauGH="Xanh";
                Toast.makeText(getApplication(), "Xanh", Toast.LENGTH_LONG).show();
                return;
            }
        });
        btnMau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauGH="Đỏ";
                Toast.makeText(getApplication(), "Đỏ", Toast.LENGTH_LONG).show();
                return;
            }
        });
        btnMau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauGH="Vàng";
                Toast.makeText(getApplication(), "Vàng", Toast.LENGTH_LONG).show();
                return;
            }
        });
        btnMau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauGH="Hồng";
                Toast.makeText(getApplication(), "Hồng", Toast.LENGTH_LONG).show();
                return;
            }
        });
        btnMau5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauGH="Cam";
                Toast.makeText(getApplication(), "Cam", Toast.LENGTH_LONG).show();
                return;
            }
        });
        btnMau6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauGH="Trắng";
                Toast.makeText(getApplication(), "Trắng", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    private void chonSize(){
        btnSize1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeGH="5-7 kg";
                Toast.makeText(getApplication(), "5-7 kg", Toast.LENGTH_LONG).show();
                return;
            }
        });
        btnSize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeGH="7-10 kg";
                Toast.makeText(getApplication(), "7-10 kg", Toast.LENGTH_LONG).show();
                return;
            }
        });
        btnSize3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeGH="10-15 kg";
                Toast.makeText(getApplication(), "10-15 kg", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
    private void SoLuong(){
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                tvSoLuongSP.setText(soLuong+"");
                return;
            }
        });

        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soLuong>1){
                    soLuong--;
                    tvSoLuongSP.setText(soLuong+"");
                    return;
                }
            }
        });
    }

    private void onClickThemVaoGioHang(){
        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mauGH==""){
                    Toast.makeText(getApplication(), "Vui lòng chọn màu", Toast.LENGTH_LONG).show();
                }
                else if(sizeGH==""){
                    Toast.makeText(getApplication(), "Vui lòng chọn size", Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference();
                    if(user != null){
                        Toast.makeText(getApplication(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        if(MainActivity.gioHangArrayList.size()>0){
                            boolean exists = false;
                            int sl = Integer.parseInt(tvSoLuongSP.getText().toString());
                            for(int i=0; i<MainActivity.gioHangArrayList.size(); i++){
                                if(MainActivity.gioHangArrayList.get(i).getId().equals(idGH)){
                                    MainActivity.gioHangArrayList.get(i).setSoLuongGH(MainActivity.gioHangArrayList.get(i).getSoLuongGH() + sl);
                                    databaseReference.child("GioHang").child(user.getUid()).child(MainActivity.gioHangArrayList.get(i).getId()).setValue(MainActivity.gioHangArrayList.get(i));
                                    exists = true;
                                }
                            }
                            if(exists == false){
                                long tongGia = soLuong * giaGH;
                                MainActivity.gioHangArrayList.add(new GioHang(idGH, anhGH, tenGH, mauGH, sizeGH, giaGH, sl));
                                GioHang gioHang = new GioHang(idGH, anhGH, tenGH, mauGH, sizeGH, giaGH, Integer.parseInt(tvSoLuongSP.getText().toString()));
                                databaseReference.child("GioHang").child(user.getUid()+"").child(gioHang.getId()).setValue(gioHang);
                            }
                        }
                        else {
                            long tongGia = soLuong*giaGH;
                            MainActivity.gioHangArrayList.add(new GioHang(idGH, anhGH, tenGH, mauGH, sizeGH, giaGH, soLuong));
                            GioHang gioHang = new GioHang(idGH, anhGH, tenGH, mauGH, sizeGH, giaGH, Integer.parseInt(tvSoLuongSP.getText().toString()));
                            databaseReference.child("GioHang").child(user.getUid()+"").child(gioHang.getId()).setValue(gioHang);
                        }
                    }
                    else {
                        Toast.makeText(getApplication(), "Vui lòng đăng nhập để mua hàng", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplication(), LoginActivity.class);
//                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void onClickMuaNgay(){
        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mauGH==""){
                    Toast.makeText(getApplication(), "Vui lòng chọn màu", Toast.LENGTH_LONG).show();
                }
                else if(sizeGH==""){
                    Toast.makeText(getApplication(), "Vui lòng chọn size", Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference();
                    if(user != null){
//                        Toast.makeText(getApplication(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        if(MainActivity.muaNgayArrayList.size()>0){
                            boolean exists = false;
                            int sl = Integer.parseInt(tvSoLuongSP.getText().toString());
                            for(int i=0; i<MainActivity.muaNgayArrayList.size(); i++){
                                if(MainActivity.muaNgayArrayList.get(i).getId().equals(idGH)){
                                    MainActivity.muaNgayArrayList.get(i).setSoLuongGH(MainActivity.muaNgayArrayList.get(i).getSoLuongGH() + sl);
                                    exists = true;
                                }
                            }
                            if(exists == false){
                                long tongGia = soLuong * giaGH;
                                MainActivity.muaNgayArrayList.add(new GioHang(idGH, anhGH, tenGH, mauGH, sizeGH, giaGH, sl));
                            }
                        }
                        else {
                            long tongGia = soLuong*giaGH;
                            MainActivity.muaNgayArrayList.add(new GioHang(idGH, anhGH, tenGH, mauGH, sizeGH, giaGH, soLuong));
                        }
                    }
                    else {
                        Toast.makeText(getApplication(), "Vui lòng đăng nhập để mua hàng", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplication(), LoginActivity.class);
//                        startActivity(intent);
                    }
                    Intent intent = new Intent(getApplication(), ThanhToanActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
//    private void getFragment(Fragment fragment){
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragmentChiTietSP, fragment).commit();
//    }
