package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DiaChiNhanHang;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.GioHang;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.login.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DiaChiNhanHangActivity extends AppCompatActivity {
    public static EditText etHoten, etSoDienThoai, etDiaChi;
    Button btnUpdate, btnBackDCNH;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DiaChiNhanHang diaChiNhanHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_nhan_hang);
        etHoten = findViewById(R.id.etHoTen);
        etSoDienThoai = findViewById(R.id.etSoDienThoai);
        etDiaChi = findViewById(R.id.etDiaChi);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBackDCNH = findViewById(R.id.btnBackDCNH);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        //mainActivity = (MainActivity) getApplicationContext();
        btnBackDCNH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setUserInformation();
        initListener();
    }

    private void setUserInformation(){
//        diaChiNhanHang = new DiaChiNhanHang();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        if(MainActivity.diaChiNhanHang != null){
            etHoten.setText(MainActivity.diaChiNhanHang.getHoTen());
            etSoDienThoai.setText(MainActivity.diaChiNhanHang.getSoDienThoai());
            etDiaChi.setText(MainActivity.diaChiNhanHang.getDiaChi());
        }

    }

    private void initListener() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickUpdate();
            }
        });
    }


    private void onClickUpdate(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Toast.makeText(DiaChiNhanHangActivity.this, "Cập nhật thất bại",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(DiaChiNhanHangActivity.this, "Cập nhật thành công",
                    Toast.LENGTH_SHORT).show();
            String strHoTen = etHoten.getText().toString().trim();
            String strSoDienThoai = etSoDienThoai.getText().toString().trim();
            String strDiaChi = etDiaChi.getText().toString().trim();
            diaChiNhanHang = new DiaChiNhanHang(strHoTen, strSoDienThoai, strDiaChi);
            databaseReference.child("DiaChiNhanHang").child(user.getUid()+"").setValue(diaChiNhanHang);
        }
//        databaseReference.child("GioHang").child(user.getUid()).child("hoten").setValue(strHoTen);
//        databaseReference.child("GioHang").child(user.getUid()).child("soDienThoai").setValue(strSoDienThoai);
//        databaseReference.child("GioHang").child(user.getUid()).child("diaChi").setValue(strDiaChi);
    }

}