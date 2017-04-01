package com.example.chat_program.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chat_program.R;
import com.example.chat_program.adapter.MessageAdapter;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 第一个消息列表页
 */

public class MessageFragment extends Fragment {
    private ListView listView;
    private List<EMConversation> list= new ArrayList<EMConversation>();
    private View view;
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MessageAdapter adapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //注册方法
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        //获取所有会话的数据源
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        //遍历Mao集合里边所有的value
        for(EMConversation emConversation : conversations.values()){
            list.add(emConversation);
        }
        init();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_message,container,false);
        return view;
    }



    private void init() {
        listView= (ListView) view.findViewById(R.id.listview_message);
        textView = (TextView) view.findViewById(R.id.textview);
        //没数据时 显示指定textview
        listView.setEmptyView(textView);
        adapter=new MessageAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        //刷新控件初始化
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.shuaxin);
        //改变刷新图标颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        //下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭 设置flaes
                swipeRefreshLayout.setRefreshing(false);
                //刷新listview
                adapter.notifyDataSetChanged();
            }
        });

    }



    EMMessageListener msgListener = new EMMessageListener() {

        //收到消息
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            Log.e("onMessageReceived",list.size()+"");
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
}
