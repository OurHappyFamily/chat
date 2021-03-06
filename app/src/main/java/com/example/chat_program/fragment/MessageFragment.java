package com.example.chat_program.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat_program.R;
import com.example.chat_program.act.MainActivity;
import com.example.chat_program.act.MessageActivity;
import com.example.chat_program.act.PrivateMessageActivity;
import com.example.chat_program.adapter.MessageAdapter;
import com.example.chat_program.callback.ListItemClick;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一个消息列表页
 */

public class MessageFragment extends Fragment implements AdapterView.OnItemLongClickListener, View.OnClickListener, EMCallBack, ListItemClick {
    private EditText editText, editText1;
    private TextView sendbtn, exitbtn;
    private ListView listView;



    private List<EMConversation> list = new ArrayList<EMConversation>();
    private View view;

    private SwipeRefreshLayout swipeRefreshLayout;
    private MessageAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intView();
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    private void intView() {

        editText = (EditText) view.findViewById(R.id.edit_username);
        editText1 = (EditText) view.findViewById(R.id.edit_content);
        sendbtn = (TextView) view.findViewById(R.id.button11);
        exitbtn = (TextView) view.findViewById(R.id.button22);
        listView = (ListView) view.findViewById(R.id.listview_message);
        sendbtn.setOnClickListener(this);
        exitbtn.setOnClickListener(this);


    }

    private void init() {
<<<<<<< HEAD
=======
<<<<<<< HEAD
        initData();
//注册方法
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        //没数据时 显示指定textview
//        listView.setEmptyView(textView);
        adapter = new MessageAdapter(getActivity(), list);
        adapter.setListItemClick(this);
=======
<<<<<<< HEAD
        listView = (ListView) view.findViewById(R.id.listview_message);
        textView = (TextView) view.findViewById(R.id.textview);
        listView.setEmptyView(textView);
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
>>>>>>> 0e06c0fe58d4b279df9c2beba242543cc12253f7


//注册方法
        EMClient.getInstance().chatManager().addMessageListener(msgListener);



        //没数据时 显示指定textview
//        listView.setEmptyView(textView);
        adapter = new MessageAdapter(getActivity(), list);
<<<<<<< HEAD
=======
>>>>>>> 6749a750c5e5e02938da83ebddd2bda39f0973bd
>>>>>>> 20775ea014d177dd0c013791a308f2a9db8d98b8
>>>>>>> 0e06c0fe58d4b279df9c2beba242543cc12253f7
        listView.setAdapter(adapter);
        //刷新控件初始化
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.shuaxin);
        //改变刷新图标颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        //下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
//关闭 设置flaes
<<<<<<< HEAD
                        list.clear();
                        swipeRefreshLayout.setRefreshing(false);
=======
                list.clear();
                swipeRefreshLayout.setRefreshing(false);

                //获取所有会话的数据源
                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                //遍历Mao集合里边所有的value
                for (EMConversation emConversation : conversations.values()) {
                    list.add(emConversation);

                }
>>>>>>> 0e06c0fe58d4b279df9c2beba242543cc12253f7

                        //获取所有会话的数据源
                        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                        //遍历Mao集合里边所有的value
                        for (EMConversation emConversation : conversations.values()) {
                            list.add(emConversation);

                        }

                        //刷新listview
                        adapter.notifyDataSetChanged();
                    }
                });


    }


    EMMessageListener msgListener = new EMMessageListener() {

        //收到消息
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            Log.e("onMessageReceived", list.size() + "");
            list.addAll(list);
            adapter.notifyDataSetChanged();

        }

        //收到透传消息
        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        //收到已读回执
        @Override
        public void onMessageReadAckReceived(List<EMMessage> list) {

        }

        //收到已送达回执
        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> list) {

        }

        //消息状态变动
        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button22://退出按钮
                //退出环信服务器 再次启动程序 会 重新登录
                EMClient.getInstance().logout(false);
                break;
            case R.id.button11://发送按钮
                //调用发送文本消息方法
                sendTxtMsg();

                break;
        }
    }

    private void sendTxtMsg() {

        //创建一条文本信息，第一参数为消息文本内容，第二为对方用户或群聊的id
        EMMessage massage = EMMessage.createTxtSendMessage(editText.getText().toString(), editText1.getText().toString());
        //TODO  设置聊天类型
        //设置消息状态回调
        massage.setMessageStatusCallback(this);
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(massage);


    }

    @Override
    public void onSuccess() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                initData();
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onError(int i, String s) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getActivity(), "发送失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onProgress(int i, String s) {

    }

    private void initData() {
        list.clear();
        //获取所有会话
        Map<String, EMConversation> conversations = EMClient
                .getInstance()
                .chatManager()
                .getAllConversations();

        for (EMConversation emc : conversations.values()) {
            list.add(emc);
        }
        sort();

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                int t;
                long a = list.get(i)
                        .getLastMessage()
                        .getMsgTime();
                long b = list.get(j)
                        .getLastMessage()
                        .getMsgTime();
                if (a < b) {
                    a = a - b;
                    b = a - b;
                    b = b + a;

                }
            }

        }
    }

    private void sort() {
        //给list集合排序的规则   接口
        Comparator comp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {

                EMConversation p1 = (EMConversation) o1;
                EMConversation p2 = (EMConversation) o2;

                if (p1.getLastMessage().getMsgTime() < p2.getLastMessage().getMsgTime())
                    return 1;
                else if (p1.getLastMessage().getMsgTime() == p2.getLastMessage().getMsgTime())
                    return 0;
                else if (p1.getLastMessage().getMsgTime() > p2.getLastMessage().getMsgTime())
                    return -1;

                return 0;

            }
        };

        //排序的方法
        Collections.sort(list, comp);
    }





    @Override
<<<<<<< HEAD
    public void onClick(int id) {
        ((MessageActivity) getActivity()).intent2Message(list.get(id).getUserName());
    }

    @Override
    public void onLongClick() {

    }

    @Override
    public void deleteItem(int id) {

=======
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PrivateMessageActivity.class);
        //获取点击的item内容数据
        EMConversation emc = (EMConversation) adapter.getItem(position);
        if (emc.getType() == EMConversation.EMConversationType.GroupChat) {

            intent.putExtra("groupId", emc.getUserName());
        } else {
//把需要传递到下个页面的数据put到intent里
            intent.putExtra("username", emc.getUserName());
        }
        startActivity(intent);
<<<<<<< HEAD
=======
>>>>>>> 6749a750c5e5e02938da83ebddd2bda39f0973bd
>>>>>>> 20775ea014d177dd0c013791a308f2a9db8d98b8
>>>>>>> 0e06c0fe58d4b279df9c2beba242543cc12253f7
    }
    public void setChatText(HashMap<String, String> textMap) {
        adapter.setTextMap(textMap);
}}
