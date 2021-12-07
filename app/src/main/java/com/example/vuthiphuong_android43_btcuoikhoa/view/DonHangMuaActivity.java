package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.ViewPagerDonMuaAdapter;
import com.google.android.material.tabs.TabLayout;

public class DonHangMuaActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    ViewPager viewPager;
    Button btnBackDonMua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_mua);

        btnBackDonMua = findViewById(R.id.btnBackDonMua);
        tabLayout = findViewById(R.id.tab_layout_donMua);
        viewPager = findViewById(R.id.view_pagerDonMua);

        btnBackDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();;
            }
        });

        ViewPagerDonMuaAdapter viewPagerDonMuaAdapter = new ViewPagerDonMuaAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerDonMuaAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}