package com.example.vuthiphuong_android43_btcuoikhoa.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuthiphuong_android43_btcuoikhoa.Product.Message;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.adapter.AdapterMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    EditText etMessage;
    Button btnSend;
    private RecyclerView rcvMessage;
    AdapterMessage adapterMessage;
    List<Message> messageList;
    View view;
    public static ChatFragment newInstance() {

        Bundle args = new Bundle();

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        etMessage = view.findViewById(R.id.et_message);
        btnSend = view.findViewById(R.id.btn_send);
        rcvMessage = view.findViewById(R.id.rcv_message);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvMessage.setLayoutManager(linearLayoutManager);

        messageList = new ArrayList<>();
        adapterMessage = new AdapterMessage();
        adapterMessage.setData(messageList);

        rcvMessage.setAdapter(adapterMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        etMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKeyboard();
            }
        });
        return view;
    }
    private void sendMessage(){
        String strMessage = etMessage.getText().toString().trim();
        if(TextUtils.isEmpty(strMessage)){
            return;
        }
        messageList.add(new Message(strMessage));
        adapterMessage.notifyDataSetChanged();
        rcvMessage.scrollToPosition(messageList.size()-1);

        etMessage.setText("");
    }

    private void checkKeyboard(){
        final View activityRootView = view.findViewById(R.id.activityRoot);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);

                int heightDiff = activityRootView.getRootView().getHeight() - r.height();
                if(heightDiff > 0.25*activityRootView.getRootView().getHeight()){
                    if(messageList.size() > 0){
                        rcvMessage.scrollToPosition(messageList.size() - 1);
                        activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }
}