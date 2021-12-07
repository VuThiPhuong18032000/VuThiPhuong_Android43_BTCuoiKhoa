package com.example.vuthiphuong_android43_btcuoikhoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DiaChiNhanHang;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDaMua;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDangGiao;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.GioHang;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterMenu;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.NavigateAdapter;
import com.example.vuthiphuong_android43_btcuoikhoa.databinding.ActivityMainBinding;
import com.example.vuthiphuong_android43_btcuoikhoa.view.GioHangActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.ItemMenu;
import com.example.vuthiphuong_android43_btcuoikhoa.view.QuanAoBeGaiActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.QuanAoBeTraiActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.QuanAoSoSinhActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.SanPhamBanChayActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.view.SanPhamNoiBatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<ItemMenu> arrayList;
    public static ArrayList<GioHang> gioHangArrayList;
    public static ArrayList<GioHang> muaNgayArrayList;
    AdapterMenu adapterMenu;
    public  static DiaChiNhanHang diaChiNhanHang;
    public static ArrayList<DonDangGiao> donDangGiaoArrayList;
    public static ArrayList<DonDaMua> donDaMuaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavigateAdapter navigateAdapter=new NavigateAdapter(this);
        binding.vpNavigate.setAdapter(navigateAdapter);

        binding.vpNavigate.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        binding.bottomNavigation.getMenu().findItem(R.id.menu_Home).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigation.getMenu().findItem(R.id.menu_Chat).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigation.getMenu().findItem(R.id.menu_Person).setChecked(true);
                        break;
                }
            }
        });

        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_Home:
                        binding.vpNavigate.setCurrentItem(0);
                        break;
                    case R.id.menu_Chat:
                        binding.vpNavigate.setCurrentItem(1);
                        break;
                    case R.id.menu_Person:
                        binding.vpNavigate.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        binding.lvLoaiSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getBaseContext(), SanPhamNoiBatActivity.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(getBaseContext(), SanPhamBanChayActivity.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(getBaseContext(), QuanAoBeTraiActivity.class);
                    startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(getBaseContext(), QuanAoBeGaiActivity.class);
                    startActivity(intent);
                }
                if(position == 4){
                    Intent intent = new Intent(getBaseContext(), QuanAoSoSinhActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GioHangActivity.class);
                startActivity(intent);
//                onBackPressed();
            }
        });


        if(gioHangArrayList == null){
            gioHangArrayList = new ArrayList<>();
        }

        if(muaNgayArrayList == null){
            muaNgayArrayList = new ArrayList<>();
        }

        if(donDangGiaoArrayList == null){
            donDangGiaoArrayList = new ArrayList<>();
        }

        if(donDaMuaArrayList == null){
            donDaMuaArrayList = new ArrayList<>();
        }
        ActionBar();
        ActionMenu();
        showUserInformation();
        getGioHang();
        getDiaChiNhanHang();
        getDonDangGiao();
        getDonDaMua();
    }

    private void ActionMenu() {
        arrayList=new ArrayList<>();
        arrayList.add(new ItemMenu("SẢN PHẨM NỔI BẬT"));
        arrayList.add(new ItemMenu("SẢN PHẨM BÁN CHẠY"));
        arrayList.add(new ItemMenu("QUẦN ÁO BÉ TRAI"));
        arrayList.add(new ItemMenu("QUẦN ÁO BÉ GÁI"));
        arrayList.add(new ItemMenu("QUẦN ÁO SƠ SINH"));
        adapterMenu = new AdapterMenu(this, R.layout.item_menu, arrayList);
        binding.lvLoaiSanPham.setAdapter(adapterMenu);
    }

    private void ActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if(name == null){
            binding.tvName.setVisibility(View.GONE);
        }
        else {
            binding.tvName.setVisibility(View.VISIBLE);
            binding.tvName.setText(name);
        }
        binding.tvEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar_default).into(binding.imgAvatar);
    }

    private List<GioHang> getGioHang(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("GioHang").child(user.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    gioHangArrayList.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                        gioHangArrayList.add(gioHang);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getApplicationContext(), "Không tải được dữ liệu" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            gioHangArrayList.clear();
        }
        return gioHangArrayList;
    }

    private DiaChiNhanHang getDiaChiNhanHang(){
//        DiaChiNhanHang diaChiNhanHang = new DiaChiNhanHang();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("DiaChiNhanHang").child(user.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    diaChiNhanHang = null;
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        GioHang gioHang = dataSnapshot.getValue(GioHang.class);
//                        gioHangArrayList.add(gioHang);
                        diaChiNhanHang = snapshot.getValue(DiaChiNhanHang.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getApplicationContext(), "Không tải được dữ liệu" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            diaChiNhanHang=null;
        }
        return diaChiNhanHang;
    }

    private List<DonDangGiao> getDonDangGiao(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("DonDangGiao").child(user.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    donDangGiaoArrayList.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DonDangGiao donDangGiao = dataSnapshot.getValue(DonDangGiao.class);
                        donDangGiaoArrayList.add(donDangGiao);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getApplicationContext(), "Không tải được dữ liệu" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            donDangGiaoArrayList.clear();
        }
        return donDangGiaoArrayList;
    }

    private List<DonDaMua> getDonDaMua(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("DonDaMua").child(user.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    donDaMuaArrayList.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DonDaMua donDaMua = dataSnapshot.getValue(DonDaMua.class);
                        donDaMuaArrayList.add(donDaMua);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getApplicationContext(), "Không tải được dữ liệu" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            donDaMuaArrayList.clear();
        }
        return donDaMuaArrayList;
    }


    //    private void getFragment(Fragment fragment){
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragmentMain, fragment).commit();
//    }


}