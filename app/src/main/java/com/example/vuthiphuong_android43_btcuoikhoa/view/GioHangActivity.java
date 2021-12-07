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
import com.example.vuthiphuong_android43_btcuoikhoa.Product.GioHang;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterGioHang;

public class GioHangActivity extends AppCompatActivity {
    ListView lvGioHang;
    AdapterGioHang adapterGioHang;
    static TextView tvTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        lvGioHang = findViewById(R.id.lvGioHang);
        Button btnBackGH = findViewById(R.id.btnBackGH);
        Button btnThanhToanGioHang = findViewById(R.id.btnThanhToanGioHang);
        tvTongTien = findViewById(R.id.tvTongTien);

        adapterGioHang=new AdapterGioHang(getBaseContext(), MainActivity.gioHangArrayList);
        lvGioHang.setAdapter(adapterGioHang);
        tongTienGioHang();

        btnBackGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });

        btnThanhToanGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.gioHangArrayList.size() <= 0){
                    Toast.makeText(getApplication(), "Vui lòng thêm hàng vào giỏ hàng để tiến hành thanh toán", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getBaseContext(), ThanhToanActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public static void tongTienGioHang(){
        long tongTien = 0;
        for(int i=0; i<MainActivity.gioHangArrayList.size(); i++){
            tongTien += (MainActivity.gioHangArrayList.get(i).getGiaGH()*MainActivity.gioHangArrayList.get(i).getSoLuongGH());
        }
        tvTongTien.setText(tongTien + "đ");
    }
}