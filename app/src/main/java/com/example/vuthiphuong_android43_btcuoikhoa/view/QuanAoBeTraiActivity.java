package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuanAoBeTraiActivity extends AppCompatActivity {
    RecyclerView rcvLoaiQABeTrai;
    AdapterSanPham adapterLoaiQABeTrai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ao_be_trai);
        rcvLoaiQABeTrai = findViewById(R.id.rcvLoaiQABeTrai);
        Button btnBackQABT = findViewById(R.id.btnBackQABT);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        rcvLoaiQABeTrai.setLayoutManager(layoutManager);

        new DataGetPrduct().execute("https://demo7852048.mockable.io/SanPham");

        btnBackQABT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });
    }

    class  DataGetPrduct extends AsyncTask<String, Void, String> {

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
                SanPham sanPham;
                List<SanPham> quanAoBeTraiList= new ArrayList<>();
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
                    sanPham = new SanPham(maSP, maLoai, anh, tenSP, gia, moTaSP, cachBaoQuan);
//                    samPhamNoiBatList.add(samPham);
                    if(maLoai.equals("Loai03")){
                        quanAoBeTraiList.add(sanPham);
                    }

                }
                adapterLoaiQABeTrai = new AdapterSanPham(getBaseContext(), quanAoBeTraiList);
                rcvLoaiQABeTrai.setAdapter(adapterLoaiQABeTrai);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            //binding.tvView.setText(result);
        }
    }
}