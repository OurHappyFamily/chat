package com.example.chat_program.fragment;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chat_program.R;
import com.example.chat_program.act.MessageActivity;
import com.example.chat_program.act.PrivateMessageActivity;
import com.example.chat_program.adapter.PictureAdapter;
import com.hyphenate.util.FileUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 张泽雅文 on 2017/4/11.
 */

public class PictureFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView listView;
    private ArrayList <String>list=new ArrayList();
    private Button button;
    private PictureAdapter pictureAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = com.example.chat_program.utils.FileUtils.getAllImg(getActivity());
         pictureAdapter=new PictureAdapter(getActivity(),list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        listView= (RecyclerView) view.findViewById(R.id.recyclerview);
        listView.setLayoutManager(llm);
        listView.setAdapter(pictureAdapter);
        button= (Button) view.findViewById(R.id.button_send);
        button.setOnClickListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_picture, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //发送图片按钮 点击事件
            case R.id.button_send:
                //获取选中的所有图片途径
                HashSet<String> checkList = pictureAdapter.getCheckList();
                //获取容器activity对象
                PrivateMessageActivity ma = (PrivateMessageActivity) getActivity();

                for (String str :
                        checkList) {
                    Log.e("checkList", str);
                    //调用activity中的发送图片方法
                    ma.sendImage(str,false);
                }
                break;
        }
    }
}
