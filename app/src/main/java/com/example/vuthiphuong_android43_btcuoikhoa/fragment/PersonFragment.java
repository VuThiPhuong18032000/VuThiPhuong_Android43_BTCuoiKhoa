package com.example.vuthiphuong_android43_btcuoikhoa.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.login.LoginActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.login.SignupActivity;
//import com.example.vuthiphuong_android43_btcuoikhoa.view.DiaChiNhanHangActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DiaChiNhanHangActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DonDangGiaoActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DonHangMuaActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DonMuaActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.MapsActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.ThongTinCaNhanActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.TrungTamTroGiupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PersonFragment extends Fragment {
    Button btnDangNhap, btnDangKy, btnDangXuat, btnThongTinCaNhan, btnDiaChiNhanHang, btnDonDangGiao, btnDonHangDaMua, btnDonMua, btnTroGiup;
    ImageView imgAvatar;
    TextView tvName, tvEmail;
    LinearLayout llDaDangNhap, llChuaDangNhap;
   // ThongTinCaNhanActivity thongTinCaNhanActivity = new ThongTinCaNhanActivity();
    public static PersonFragment newInstance() {

        Bundle args = new Bundle();

        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        btnDangKy = view.findViewById(R.id.btnDangKy);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        llDaDangNhap = view.findViewById(R.id.llDaDangNhap);
        llChuaDangNhap = view.findViewById(R.id.llChuaDangNhap);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        btnThongTinCaNhan = view.findViewById(R.id.btnTTCaNhan);
        btnDiaChiNhanHang = view.findViewById(R.id.btnDiaChi);
//        btnDonDangGiao = view.findViewById(R.id.btnDonDangGiao);
//        btnDonHangDaMua = view.findViewById(R.id.btnDonHangDaMua);
        btnDonMua = view.findViewById(R.id.btnDonMua);
        btnTroGiup = view.findViewById(R.id.btnTroGiup);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
        showUserInformation();
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangXuat();
            }
        });
        thongTinCaNhan();
        diaChiNhanHang();
//        donHangDangGiao();
//        donHangDaMua();
        donMua();
        trungTamTroGiup();
        return view;
    }

    public void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            llChuaDangNhap.setVisibility(View.VISIBLE);
            llDaDangNhap.setVisibility(View.GONE);
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if(name == null){
            tvName.setVisibility(View.GONE);
        }
        else {
            tvName.setVisibility(View.VISIBLE);
            tvName.setText(name);
        }
        llDaDangNhap.setVisibility(View.VISIBLE);
        llChuaDangNhap.setVisibility(View.GONE);
        tvEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar_default).into(imgAvatar);
    }
    private void dangXuat(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    private void thongTinCaNhan(){
        btnThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThongTinCaNhanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void diaChiNhanHang(){
        btnDiaChiNhanHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaChiNhanHangActivity.class);
                startActivity(intent);
            }
        });
    }
//    private void donHangDangGiao(){
//        btnDonDangGiao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DonDangGiaoActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    private void donHangDaMua(){
//        btnDonHangDaMua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DonMuaActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    private void donMua(){
        btnDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonHangMuaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void trungTamTroGiup(){
        btnTroGiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TrungTamTroGiupActivity.class);
                startActivity(intent);
            }
        });
    }
}
