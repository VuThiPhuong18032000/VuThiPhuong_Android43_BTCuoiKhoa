package com.example.vuthiphuong_android43_btcuoikhoa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuthiphuong_android43_btcuoikhoa.Product.Message;
import com.example.vuthiphuong_android43_btcuoikhoa.R;

import java.util.List;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.MessageViewHolder>{
    private List<Message> messageList;

    public void setData(List<Message> list){
        this.messageList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        if(message == null){
            return;
        }
        holder.tvMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        if(messageList != null){
            return messageList.size();
        }
        return 0;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMessage;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMessage = itemView.findViewById(R.id.tv_message);
        }
    }
}
