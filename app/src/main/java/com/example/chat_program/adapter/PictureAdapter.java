package com.example.chat_program.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chat_program.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 张泽雅文 on 2017/4/13.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> list;
    private HashSet<String> checklist=new HashSet<>();
    public PictureAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;

    }
    class MyViewHolder extends RecyclerView.ViewHolder {
private ImageView imageView;
        private CheckBox checkBox;

        public MyViewHolder(View itemView) {

            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imagee);
checkBox= (CheckBox) itemView.findViewById(R.id.checkboxx);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder m = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_picture, parent, false));
        return m;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String path=list.get(position);
        Glide.with(context).load(path).override(200,300)    .into(holder.imageView);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checklist.add(path);
                }else {
                    checklist.remove(path);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public HashSet<String> getCheckList() {
        return checklist;
    }
}
