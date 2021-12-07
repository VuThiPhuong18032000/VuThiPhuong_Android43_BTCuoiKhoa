//package com.example.vuthiphuong_android43_btcuoikhoa.fragment;
//
//import android.graphics.Rect;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewTreeObserver;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.vuthiphuong_android43_btcuoikhoa.Product.SanPham;
//import com.example.vuthiphuong_android43_btcuoikhoa.R;
//import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterSanPham;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TimKiemFragment extends Fragment {
//    RecyclerView rcvTimKiem;
//    AdapterSanPham adapterTimKiem;
//    String str;
//    Editable data;
//    public static TimKiemFragment newInstance() {
//
//        Bundle args = new Bundle();
//
//        TimKiemFragment fragment = new TimKiemFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_timkiem, container, false);
//        rcvTimKiem = view.findViewById(R.id.rcvTimKiem);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
//        rcvTimKiem.setLayoutManager(layoutManager);
//        new DataGetPrduct().execute("http://demo7852048.mockable.io/SanPham");
//        data = HomeFragment.tvTimKiem();
//
//
//        return view;
//    }
//
//    class  DataGetPrduct extends AsyncTask<String, Void, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            StringBuilder content = new StringBuilder();
//            try {
//                URL url = new URL(strings[0]);
//                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                String line = "";
//                while ((line = bufferedReader.readLine()) != null){
//                    content.append(line);
//                }
//                bufferedReader.close();
//                //binding.tvView.setText(result);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return content.toString();
//        }
//
//        @Override
//        protected void onPostExecute(String aVoid) {
//            super.onPostExecute(aVoid);
//            try {
//                str = aVoid;
//                SanPham sanPham;
//                List<SanPham> timKiemList= new ArrayList<>();
//                JSONArray array = new JSONArray(aVoid);
//                for(int i=0; i<array.length(); i++) {
//                    JSONObject jsonObject = array.getJSONObject(i);
//                    String maSP = jsonObject.getString("maSP");
//                    String maLoai = jsonObject.getString("maLoai");
//                    String anh = jsonObject.getString("anh");
//                    String tenSP = jsonObject.getString("tenSP");
//                    int gia = jsonObject.getInt("gia");
//                    String moTaSP = jsonObject.getString("moTaSP");
//                    String cachBaoQuan = jsonObject.getString("cachBaoQuan");
//                    sanPham = new SanPham(maSP, maLoai, anh, tenSP, gia, moTaSP, cachBaoQuan);
//
//                    String search = data.toString().toLowerCase();
//                    if(tenSP.toLowerCase().contains(data)){
//                        timKiemList.add(sanPham);
//                    }
//
//                }
//                adapterTimKiem = new AdapterSanPham(getContext(), timKiemList);
//                rcvTimKiem.setAdapter(adapterTimKiem);
//            }
//            catch (JSONException e) {
//                e.printStackTrace();
//            }
//            //binding.tvView.setText(result);
//        }
//    }
//}