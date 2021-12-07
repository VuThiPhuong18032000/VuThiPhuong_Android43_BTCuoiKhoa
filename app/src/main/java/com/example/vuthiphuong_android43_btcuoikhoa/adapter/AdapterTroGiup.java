package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vuthiphuong_android43_btcuoikhoa.Product.DonDaMua;
import com.example.vuthiphuong_android43_btcuoikhoa.Product.TroGiup;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.view.DonMuaChiTietActivity;

import java.util.List;

public class AdapterTroGiup extends BaseAdapter {
    Context context;
    List<TroGiup> troGiupList;

    public AdapterTroGiup(Context context, List<TroGiup> troGiupList) {
        this.context = context;
        this.troGiupList = troGiupList;
    }

    @Override
    public int getCount() {
        return troGiupList.size();
    }

    @Override
    public Object getItem(int position) {
        return troGiupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_trogiup, parent, false);
        TextView tvCauHoi = view.findViewById(R.id.tvCauHoi);
        LinearLayout layout_itemTroGiup = view.findViewById(R.id.layout_itemTroGiup);
        TroGiup troGiup = troGiupList.get(position);
        tvCauHoi.setText(troGiup.getCauHoi());
        layout_itemTroGiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCauHoi("https://help.shopify.com/vi/manual/online-sales-channels");
            }
        });
        return view;
    }

    private void onClickCauHoi(String s){
        Uri uri = Uri.parse(s);
        context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
