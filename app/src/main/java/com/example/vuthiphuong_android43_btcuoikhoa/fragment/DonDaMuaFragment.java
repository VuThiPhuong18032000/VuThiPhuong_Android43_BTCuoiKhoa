package com.example.vuthiphuong_android43_btcuoikhoa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterDonDaMua;

public class DonDaMuaFragment extends Fragment {
    ListView lvDonMua;
    AdapterDonDaMua adapterDonDaMua;
    public DonDaMuaFragment() {
    }

    public static DonDaMuaFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DonDaMuaFragment fragment = new DonDaMuaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_da_mua, container, false);
        lvDonMua = view.findViewById(R.id.lvDonMua);

        adapterDonDaMua =new AdapterDonDaMua(getContext(), MainActivity.donDaMuaArrayList);
        lvDonMua.setAdapter(adapterDonDaMua);
        return view;
    }
}
