package com.example.chat_program.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chat_program.R;
import com.example.chat_program.adapter.PeopleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 第二页联系人页
 */

public class PeopleFragment extends Fragment {
    private View view;
    private ListView listView;
    private List<String> list = new ArrayList<String>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    //初始化listview
    private void init() {
        listView = (ListView) view.findViewById(R.id.listview_people);
        list.add("第一行");
        list.add("第二行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        list.add("第一行");
        PeopleAdapter adapter = new PeopleAdapter(getActivity(), list);

        listView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_people, container, false);
        return view;
    }
}
