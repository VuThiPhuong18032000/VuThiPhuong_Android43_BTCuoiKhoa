package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.view.ItemMenu;

import java.util.List;

public class AdapterMenu extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<ItemMenu> list;

    public AdapterMenu(Context context, int layout, List<ItemMenu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView tvItem;
        ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvItem = (TextView)convertView.findViewById(R.id.tvItem);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tvItem.setText(list.get(position).itemName);
        return convertView;
    }
}
