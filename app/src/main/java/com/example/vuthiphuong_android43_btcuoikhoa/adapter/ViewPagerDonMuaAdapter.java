package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.vuthiphuong_android43_btcuoikhoa.fragment.DonDaMuaFragment;
import com.example.vuthiphuong_android43_btcuoikhoa.fragment.DonDangGiaoFragment;

public class ViewPagerDonMuaAdapter extends FragmentStatePagerAdapter {
    public ViewPagerDonMuaAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DonDangGiaoFragment();
            case 1:
                return new DonDaMuaFragment();
            default:
                return new DonDangGiaoFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Đơn đang giao";
                break;
            case 1:
                title = "Đơn đã mua";
                break;
        }
        return title;
    }
}
