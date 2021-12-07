package com.example.vuthiphuong_android43_btcuoikhoa.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterSanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.view.GioHangActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.MapsActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.TimKiemActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView rcvSPNoiBat, rcvSPBanChay, rcvQABeTrai, rcvQABeGai, rcvQASoSinh;
    AdapterSanPham adapterSanPhamNoiBat, adapterSanPhamBanChay, adapterQuanAoBeTrai, adapterQuanAoBeGai, adapterQuanAoSoSinh;
    public static AutoCompleteTextView tvTimKiem;
    Button btnTimKiem;
    LinearLayout layout_DiaChiShop;
    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcvSPNoiBat = view.findViewById(R.id.rcvSPNoiBat);
        rcvSPBanChay = view.findViewById(R.id.rcvSPBanChay);
        rcvQABeTrai = view.findViewById(R.id.rcvQABeTrai);
        rcvQABeGai = view.findViewById(R.id.rcvQABeGai);
        rcvQASoSinh = view.findViewById(R.id.rcvQASoSinh);
        tvTimKiem = view.findViewById(R.id.tvTimKiem);
        btnTimKiem = view.findViewById(R.id.btnTimKiem);
        layout_DiaChiShop = view.findViewById(R.id.layout_DiaChiShop);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        rcvSPNoiBat.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        rcvSPBanChay.setLayoutManager(layoutManager2);
        RecyclerView.LayoutManager layoutManager3 = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        rcvQABeTrai.setLayoutManager(layoutManager3);
        RecyclerView.LayoutManager layoutManager4 = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        rcvQABeGai.setLayoutManager(layoutManager4);
        RecyclerView.LayoutManager layoutManager5 = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        rcvQASoSinh.setLayoutManager(layoutManager5);

        new DataGetPrduct().execute("https://demo7852048.mockable.io/SanPham");
        TimKiem();
        DiaChiShop();

        return view;
    }
    class  DataGetPrduct extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
                //binding.tvView.setText(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                SanPham samPham;
                List<String> arr = new ArrayList<>();
                List<SanPham>  samPhamNoiBatList= new ArrayList<>();
                List<SanPham>  samPhamBanChayList= new ArrayList<>();
                List<SanPham>  quanAoBeTraiList= new ArrayList<>();
                List<SanPham>  quanAoBeGaiList= new ArrayList<>();
                List<SanPham>  quanAoSoSinhList= new ArrayList<>();
                JSONArray array = new JSONArray(aVoid);
                for(int i=0; i<array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    String maSP = jsonObject.getString("maSP");
                    String maLoai = jsonObject.getString("maLoai");
                    String anh = jsonObject.getString("anh");
                    String tenSP = jsonObject.getString("tenSP");
                    int gia = jsonObject.getInt("gia");
                    String moTaSP = jsonObject.getString("moTaSP");
                    String cachBaoQuan = jsonObject.getString("cachBaoQuan");
                    arr.add(tenSP);
                    samPham = new SanPham(maSP, maLoai, anh, tenSP, gia, moTaSP, cachBaoQuan);
                    if(maLoai.equals("Loai01")){
                        samPhamNoiBatList.add(samPham);
                    }
                    if(maLoai.equals("Loai02")){
                        samPhamBanChayList.add(samPham);
                    }
                    if(maLoai.equals("Loai03")){
                        quanAoBeTraiList.add(samPham);
                    }
                    if(maLoai.equals("Loai04")){
                        quanAoBeGaiList.add(samPham);
                    }
                    if(maLoai.equals("Loai05")){
                        quanAoSoSinhList.add(samPham);
                    }

                }
                adapterSanPhamNoiBat = new AdapterSanPham(getContext(), samPhamNoiBatList);
                rcvSPNoiBat.setAdapter(adapterSanPhamNoiBat);
                adapterSanPhamBanChay =new AdapterSanPham(getContext(), samPhamBanChayList);
                rcvSPBanChay.setAdapter(adapterSanPhamBanChay);
                adapterQuanAoBeTrai =new AdapterSanPham(getContext(), quanAoBeTraiList);
                rcvQABeTrai.setAdapter(adapterQuanAoBeTrai);
                adapterQuanAoBeGai =new AdapterSanPham(getContext(), quanAoBeGaiList);
                rcvQABeGai.setAdapter(adapterQuanAoBeGai);
                adapterQuanAoSoSinh =new AdapterSanPham(getContext(), quanAoSoSinhList);
                rcvQASoSinh.setAdapter(adapterQuanAoSoSinh);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, arr);
                tvTimKiem.setAdapter(adapter);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void TimKiem(){
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fragmentHome, TimKiemFragment.newInstance()).commit();
                Intent intent = new Intent(getContext(), TimKiemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void DiaChiShop(){
        layout_DiaChiShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
//    public static Editable tvTimKiem(){
//        return tvTimKiem.getText();
//    }
    public static String tvTimKiem(){
        return tvTimKiem.getText().toString().trim();
    }

}
