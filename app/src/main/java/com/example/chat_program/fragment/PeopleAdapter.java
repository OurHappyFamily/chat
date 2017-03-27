package com.example.chat_program.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chat_program.R;

import java.util.List;

/**
 * Created by 阳 on 2017/3/27.
 */

public class PeopleAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public PeopleAdapter(Context context, List<String> list) {
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
      convertView =View.inflate(context, R.layout.item_people,null);
  }
        TextView tixtview_people = (TextView) convertView.findViewById(R.id.tixtview_people);
                 tixtview_people.setText(list.get(position));

        return convertView;
    }
}
