package com.example.chat_program.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.example.chat_program.R;
import com.example.chat_program.act.LoginActivity;
import com.example.chat_program.act.MainActivity;
import com.hyphenate.chat.EMClient;

/**
 * 第三页设置页
 */

public class SettingFragment extends Fragment {
    private Button button_tuichu;
    private TextView fragment_set_set1, fragment_set_set2, fragment_set_set3, fragment_set_set4, fragment_set_set5;
    private View view;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        dianji(view);
        return view;
    }

    /**
     * 设置各个选项的点击事件
     *
     * @param v
     */
    public void dianji(View v) {
        //初始化TextView控件
        fragment_set_set1 = (TextView) view.findViewById(R.id.fragment_set_set1);
        fragment_set_set2 = (TextView) view.findViewById(R.id.fragment_set_set2);
        fragment_set_set3 = (TextView) view.findViewById(R.id.fragment_set_set3);
        fragment_set_set4 = (TextView) view.findViewById(R.id.fragment_set_set4);
        fragment_set_set5 = (TextView) view.findViewById(R.id.fragment_set_set5);
        button_tuichu= (Button) view.findViewById(R.id.button_tuichu);
        button_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(getActivity())
                       .setMessage("确定退出吗")
                       .setTitle("退出登录")
                       .setNegativeButton("取消", null)
                       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Intent intent = new Intent(getActivity(), LoginActivity.class);
                               startActivity(intent);
                           }
                       }).show();
            }
        });
        //给控件添加点击事件
        fragment_set_set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击弹出提示框
                new AlertDialog.Builder(getActivity())
                        .setTitle("设置信息")
                        .setView(R.layout.alerdialog)//提示框所要显示的布局文件
                        .setNegativeButton("取消", null)
                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
        fragment_set_set2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("消息通知")
                        .setView(R.layout.alerdialog1)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
        fragment_set_set3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("聊天")
                        .setView(R.layout.alerdialog2)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
        fragment_set_set4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("隐私")
                        .setView(R.layout.alerdialog3)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        fragment_set_set5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("通用")
                        .setView(R.layout.alerdialog4)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });


    }
}
