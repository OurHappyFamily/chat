package com.example.chat_program.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chat_program.R;
import com.example.chat_program.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一个消息列表页
 */

public class MessageFragment extends Fragment {
    private ListView listView;
    private List<String> list= new ArrayList<String>();
    private View view;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaa");


        MessageAdapter adapter=new MessageAdapter(getActivity(),list);
        listView.setAdapter(adapter);



    }
}
