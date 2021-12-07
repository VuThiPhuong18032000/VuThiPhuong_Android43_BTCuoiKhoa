package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterDonDaMua;

public class DonMuaActivity extends AppCompatActivity {
    Button btnBackDonMua;
    ListView lvDonMua;
    AdapterDonDaMua adapterDonDaMua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_mua);
        btnBackDonMua = findViewById(R.id.btnBackDonMua);
        lvDonMua = findViewById(R.id.lvDonMua);

        adapterDonDaMua =new AdapterDonDaMua(getBaseContext(), MainActivity.donDaMuaArrayList);
        lvDonMua.setAdapter(adapterDonDaMua);

        btnBackDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}