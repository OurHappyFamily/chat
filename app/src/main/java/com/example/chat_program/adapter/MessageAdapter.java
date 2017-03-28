package com.example.chat_program.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chat_program.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息列表适配器
 */

public class MessageAdapter extends BaseAdapter {
    private Context context;
    private List <String>list= new ArrayList();

    public MessageAdapter(Context context, List <String>list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=View.inflate(context,R.layout.item_message,null);
            TextView textView= (TextView) convertView.findViewById(R.id.text);
            textView.setText(list.get(position));
            textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

        return convertView;
    }
}
