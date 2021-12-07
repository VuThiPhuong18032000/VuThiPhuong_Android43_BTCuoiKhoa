package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.TroGiup;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterDonDaMua;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterTroGiup;

import java.util.ArrayList;
import java.util.List;

public class TrungTamTroGiupActivity extends AppCompatActivity {
    TroGiup troGiup1, troGiup2, troGiup3, troGiup4, troGiup5, troGiup6, troGiup7, troGiup8, troGiup9;
    List<TroGiup> troGiupList;
    ListView lvTroGiup;
    AdapterTroGiup adapterTroGiup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trung_tam_tro_giup);
        Button btnBackTrungTamTroGiup = findViewById(R.id.btnBackTrungTamTroGiup);
        lvTroGiup = findViewById(R.id.lvTroGiup);
        troGiupList = new ArrayList<>();

        troGiup1 = new TroGiup("Cập nhật về ảnh hưởng của dịch covid với việc giao/nhận hàng?");
        troGiup2 = new TroGiup("Tại sao tài khoản của tôi bị khoá?");
        troGiup3 = new TroGiup("Làm sao để mua hàng của shop?");
        troGiup4 = new TroGiup("Các câu hỏi về đánh giá sản phẩm?");
        troGiup5 = new TroGiup("Làm sao để kiểm tra đơn hàng đã giao cho mình chưa?");
        troGiup6 = new TroGiup("Tại sao tôi không nhận được đơn hàng?");
        troGiup7 = new TroGiup("Cập nhật về ảnh hưởng của dịch covid với việc giao/nhận hàng?");
        troGiup8 = new TroGiup("Cập nhật về ảnh hưởng của dịch covid với việc giao/nhận hàng?");
        troGiup9 = new TroGiup("Cập nhật về ảnh hưởng của dịch covid với việc giao/nhận hàng?");

        troGiupList.add(troGiup1);
        troGiupList.add(troGiup2);
        troGiupList.add(troGiup3);
        troGiupList.add(troGiup4);
        troGiupList.add(troGiup5);
        troGiupList.add(troGiup6);
        troGiupList.add(troGiup7);
        troGiupList.add(troGiup8);
        troGiupList.add(troGiup9);

        adapterTroGiup =new AdapterTroGiup(getBaseContext(), troGiupList);
        lvTroGiup.setAdapter(adapterTroGiup);

        btnBackTrungTamTroGiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}