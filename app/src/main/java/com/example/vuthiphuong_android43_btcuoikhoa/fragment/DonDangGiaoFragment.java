package com.example.vuthiphuong_android43_btcuoikhoa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterDangGiao;

public class DonDangGiaoFragment extends Fragment {
    ListView lvDonDangGiao;
    AdapterDangGiao adapterDangGiao;
    public DonDangGiaoFragment() {
    }

    public static DonDangGiaoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DonDangGiaoFragment fragment = new DonDangGiaoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_dang_giao, container, false);
        lvDonDangGiao = view.findViewById(R.id.lvDonDangGiao);

        adapterDangGiao =new AdapterDangGiao(getContext(), MainActivity.donDangGiaoArrayList);
        lvDonDangGiao.setAdapter(adapterDangGiao);
        return view;
    }
}
