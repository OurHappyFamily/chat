package com.example.chat_program.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chat_program.R;
import com.example.chat_program.act.PrivateMessageActivity;
import com.example.chat_program.callback.ListItemClick;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.adapter.message.EMATextMessageBody;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.name;
import static com.hyphenate.chat.a.b.a.c;

/**
 * 消息列表适配器
 */

public class MessageAdapter extends BaseAdapter {
    private LinearLayout linearLayout;
    private HashMap<String, String> textMap = new HashMap<>();
    private Context context;
    private List<EMConversation> list = new ArrayList();

<<<<<<< HEAD



    public MessageAdapter(FragmentActivity activity, List<EMConversation> list) {
        this.context = activity;
=======
    public MessageAdapter(Context context, List<String> list) {
=======
    private List <EMConversation>list= new ArrayList();
    private ListItemClick listItemClick;
    public MessageAdapter(Context context, List <EMConversation>list) {
>>>>>>> 6749a750c5e5e02938da83ebddd2bda39f0973bd
        this.context = context;
>>>>>>> 0e06c0fe58d4b279df9c2beba242543cc12253f7
        this.list = list;
    }

    public void setListItemClick(ListItemClick listItemClick) {
        this.listItemClick = listItemClick;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_message, null);
        }
//            TextView textView= (TextView) convertView.findViewById(R.id.text);
//            EMConversation bianliang = list.get(position);
//            EMTextMessageBody body = (EMTextMessageBody) bianliang.getLastMessage().getBody();
//            textView.setText(body.getMessage());
<<<<<<< HEAD
        linearLayout = (LinearLayout) convertView.findViewById(R.id.la);
        TextView name = (TextView) convertView.findViewById(R.id.textview1);
        final TextView content = (TextView) convertView.findViewById(R.id.textview2);
        TextView time = (TextView) convertView.findViewById(R.id.chat_list_time);
        TextView unread = (TextView) convertView.findViewById(R.id.chat_list_unread);
        EMConversation msg = (EMConversation) getItem(position);
        String username = msg.getUserName();
        EMMessage latMessage = msg.getLastMessage();
        EMMessage.Type type = latMessage.getType();
        switch (type) {
            case TXT:
                EMTextMessageBody textMessageBody = (EMTextMessageBody) latMessage.getBody();
                content.setText(textMessageBody.getMessage());
                break;

        }

        name.setText(username);
        time.setText(getLastMsgTime(msg) + "");


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PrivateMessageActivity.class);
                //获取点击的item内容数据
                EMConversation emc = (EMConversation) list.get(position);
                if (emc.getType() == EMConversation.EMConversationType.GroupChat) {

                    intent.putExtra("groupId", emc.getUserName());
                } else {
//把需要传递到下个页面的数据put到intent里
                    intent.putExtra("username", emc.getUserName());
=======
linearLayout= (LinearLayout) convertView.findViewById(R.id.la);
            TextView name = (TextView) convertView.findViewById(R.id.textview1);
            final TextView content = (TextView) convertView.findViewById(R.id.textview2);
            TextView time = (TextView) convertView.findViewById(R.id.chat_list_time);
            TextView unread = (TextView) convertView.findViewById(R.id.chat_list_unread);
            EMConversation msg=(EMConversation)getItem(position);
            String username = msg.getUserName();
            EMMessage latMessage=msg.getLastMessage();
        if (!TextUtils.isEmpty(textMap.get(msg.getUserName()))) {
            content.setText("[草稿] " + textMap.get(msg.getUserName()));
        } else {
            EMMessage.Type type = latMessage.getType();
            switch (type) {
                case TXT:
                    EMTextMessageBody textMessageBody = (EMTextMessageBody) latMessage.getBody();
                    content.setText(textMessageBody.getMessage());
                    break;

            }
        }
            name.setText(username);
            time.setText(getLastMsgTime(msg) + "");


            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, PrivateMessageActivity.class);
//                    //获取点击的item内容数据
//                    EMConversation emc= (EMConversation) list.get(position);
//                    if (emc.getType()==EMConversation.EMConversationType.GroupChat){
//
//                        intent.putExtra("groupId",emc.getUserName());
//                    }else {
////把需要传递到下个页面的数据put到intent里
//                        intent.putExtra("username",emc.getUserName());
//                    }
//                    context.startActivity(intent);
//
                    if (listItemClick != null) {
                        listItemClick.onClick(position);
                    }
                }
            });

            final View finalConvertView = convertView;
            convertView.findViewById(R.id.item_menu_del).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                    //关闭侧滑删除控件
                    ((SwipeMenuLayout) finalConvertView).quickClose();

>>>>>>> 0e06c0fe58d4b279df9c2beba242543cc12253f7
                }
                context.startActivity(intent);
            }
        });

        final View finalConvertView = convertView;
        convertView.findViewById(R.id.item_menu_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                //关闭侧滑删除控件
                ((SwipeMenuLayout) finalConvertView).quickClose();

            }
        });


        return convertView;
    }

    private String getLastMsgTime(EMConversation msg) {
        //绘画最后一条消息的时间
        long t = msg.getLastMessage().getMsgTime();
        //当前时间和最后一条消息的时间差
        long notT = new Date().getTime() - t;
//        return   m2M(notT) > 60 ? (m2H(m2M(notT))>24?H2d(m2H(m2M(notT))):m2H(m2M(notT))): m2M(notT);
//把时间差的单位重毫秒转成分钟
        int m = m2M(notT);
//判断是否大于60分钟，大于则换成小时
        if (m > 60) {
            //判断换成小时后是否大于24小时
            if (m2H(m) > 24) {
                return H2d(m2H(m)) + "天前";
            }
            return m2H(m) + "小时前";


        } else {
            //判断是否大于1分钟
            if (m > 1)

                return m + "分钟前";
            else
                return "刚刚";


        }
    }

    //毫秒转分钟的方法
    private int m2M(long time) {


        return (int) (time / 1000 / 60);
    }

    private int m2H(long time) {


        return (int) (time / 60);
    }

    private int H2d(long time) {


        return (int) (time / 24);
    }
    public  void setTextMap(HashMap<String, String> textMap) {
        this.textMap = textMap;
        notifyDataSetChanged();
    }
}
