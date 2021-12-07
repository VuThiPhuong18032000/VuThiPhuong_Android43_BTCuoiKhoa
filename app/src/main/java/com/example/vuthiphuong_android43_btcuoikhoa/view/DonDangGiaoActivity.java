package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterDangGiao;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterGioHang;
import com.squareup.picasso.Picasso;

public class DonDangGiaoActivity extends AppCompatActivity {
    ListView lvDonDangGiao;
    AdapterDangGiao adapterDangGiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_dang_giao);
        lvDonDangGiao = findViewById(R.id.lvDonDangGiao);
        Button btnBackDonDangGiao = findViewById(R.id.btnBackDonDangGiao);

        adapterDangGiao =new AdapterDangGiao(getBaseContext(), MainActivity.donDangGiaoArrayList);
        lvDonDangGiao.setAdapter(adapterDangGiao);


        btnBackDonDangGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}