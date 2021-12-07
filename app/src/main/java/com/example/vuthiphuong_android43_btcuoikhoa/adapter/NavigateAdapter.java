package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vuthiphuong_android43_btcuoikhoa.fragment.ChatFragment;
import com.example.vuthiphuong_android43_btcuoikhoa.fragment.HomeFragment;
import com.example.vuthiphuong_android43_btcuoikhoa.fragment.PersonFragment;

public class NavigateAdapter extends FragmentStateAdapter {
    public NavigateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new ChatFragment();
            case 2:
                return new PersonFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
