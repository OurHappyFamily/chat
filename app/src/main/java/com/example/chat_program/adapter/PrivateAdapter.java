package com.example.chat_program.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chat_program.R;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 张泽雅文 on 2017/4/5.
 */

public class PrivateAdapter extends BaseAdapter {
    private Context context;
    private List<EMMessage> list;
    private static final String MYUSER="000000";
    private String myUserName;

    public PrivateAdapter(Context context, List<EMMessage> list,String myUserName) {
        this.context = context;
        this.list = list;
        this.myUserName = myUserName;
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
        ViewHolder viewHolder;
        if (convertView == null) {

            viewHolder=new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_message_list, parent, false);
            //初始化所有控件

            viewHolder.setViews(convertView);
            //设置标签
            convertView.setTag(viewHolder);








        } else {
            //设置之前写好的对象
            viewHolder= (ViewHolder) convertView.getTag();








        }
        //获取消息类型
        EMMessage msg = list.get(position);
        EMMessage.Type typeMsg = msg.getType();
        viewHolder.timeLay.setVisibility(View.VISIBLE);
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd HH:mm");
        viewHolder.time.setText(dateFormat.format(msg.getMsgTime()));
        if (MYUSER.equals(msg.getFrom())){
            viewHolder.rightLay.setVisibility(View.VISIBLE);
            viewHolder.leftLay.setVisibility(View.GONE);
            viewHolder.rightname.setText("我");
        } else {
            viewHolder.rightLay.setVisibility(View.GONE);
            viewHolder.leftLay.setVisibility(View.VISIBLE);
            viewHolder.leftname.setText(msg.getUserName());
        }
        switch (typeMsg) {
            case TXT:
                //文本消息 直接显示消息内容
                EMTextMessageBody body = (EMTextMessageBody) msg.getBody();
                viewHolder.leftContent.setText(body.getMessage());
                viewHolder.leftContent.setVisibility(View.VISIBLE);
                viewHolder.rightImg.setVisibility(View.GONE);
                viewHolder.leftImg.setVisibility(View.VISIBLE);
                viewHolder.LeftImgcontext.setVisibility(View.GONE);
                break;
            case IMAGE:
                viewHolder.leftContent.setVisibility(View.GONE);
                viewHolder.rightContent.setVisibility(View.GONE);
                viewHolder.leftImg.setVisibility(View.VISIBLE);
                viewHolder.LeftImgcontext.setVisibility(View.VISIBLE);
                //图片消息类型
                EMImageMessageBody emImg = (EMImageMessageBody) msg.getBody();
                //判断是否是自己发送的
                //如果是 使用本地路径url
                //如果不是  使用缩略图路径url

                //因 没有存 自己的用户名所以暂用对方用户名来判断
                if (myUserName.equals(msg.getTo())) {
                    //显示图片
                    Glide.with(context)
                            .load(emImg.getLocalUrl())
                            .override(300, 200)
                            .into(viewHolder.LeftImgcontext);

                } else {
                    //显示图片
                    Glide.with(context)
                            .load(emImg.getThumbnailUrl())
                            .override(300, 200)
                            .into(viewHolder.LeftImgcontext);
                }
                break;
        }
//        setViewContent(viewHolder, (EMMessage) getItem(position));



        return convertView;

    }

//    private void setViewContent(ViewHolder viewHolder,EMMessage emMessage){
//
//        viewHolder.timeLay.setVisibility(View.VISIBLE);
//        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd HH:mm");
//        viewHolder.time.setText(dateFormat.format(emMessage.getMsgTime()));
//
//        if (MYUSER.equals(emMessage.getFrom())){
//            viewHolder.rightLay.setVisibility(View.VISIBLE);
//            viewHolder.leftLay.setVisibility(View.GONE);
//            viewHolder.rightname.setText("我");
//            EMTextMessageBody txt= (EMTextMessageBody) emMessage.getBody();
//            viewHolder.rightContent.setText(txt.getMessage());
//        }else {
//            viewHolder.rightLay.setVisibility(View.GONE);
//            viewHolder.leftLay.setVisibility(View.VISIBLE);
//            viewHolder.leftname.setText(emMessage.getUserName());
//            EMTextMessageBody txt= (EMTextMessageBody) emMessage.getBody();
//            viewHolder.leftContent.setText(txt.getMessage());
//        }
//
//    }







    class ViewHolder {
        private LinearLayout timeLay, leftLay, rightLay;
        private TextView time, leftname, leftContent, rightname, rightContent;
        ImageView leftImg, rightImg,LeftImgcontext;

        void setViews(View view) {
            leftContent = (TextView) view.findViewById(R.id.item_msg_left_content);
            leftname = (TextView) view.findViewById(R.id.item_msg_left_name);
            leftImg = (ImageView) view.findViewById(R.id.item_msg_left_image);
            leftLay = (LinearLayout) view.findViewById(R.id.item_msg_left_lay);
            rightContent = (TextView) view.findViewById(R.id.item_msg_right_content);
            rightname = (TextView) view.findViewById(R.id.item_msg_right_name);
            rightImg = (ImageView) view.findViewById(R.id.item_msg_right_image);
            rightLay = (LinearLayout) view.findViewById(R.id.item_msg_right_lay);
            time = (TextView) view.findViewById(R.id.item_msg_time_textview);
            timeLay = (LinearLayout) view.findViewById(R.id.item_msg_time_lay);
LeftImgcontext= (ImageView) view.findViewById(R.id.item_msg_left_content_image);
        }

    }

}
