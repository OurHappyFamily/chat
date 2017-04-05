package com.example.chat_program.act;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.chat_program.R;
import com.example.chat_program.adapter.PrivateAdapter;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张泽雅文 on 2017/4/5.
 */
public class PrivateMessageActivity extends AppCompatActivity implements View.OnClickListener, EMCallBack {
    private ListView listView;
    private EditText editText;
    private Button button;
    private String groupId;
    private String username;
    private List<EMMessage> messages;
    private PrivateAdapter padapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_privatemessage);
        super.onCreate(savedInstanceState);
        username=getIntent().getStringExtra("username");
        groupId = getIntent().getStringExtra("groupId");
        initview();
        init();
    }


    private void initData(){
        if (TextUtils.isEmpty(groupId)) {
            EMConversation conversation = EMClient
                    .getInstance()
                    .chatManager()
                    .getConversation(username);
            //獲取此会话的所有消息
            messages = conversation
                    .getAllMessages();

        } else {
            EMConversation conversation = EMClient
                    .getInstance().chatManager()
                    .getConversation(groupId);
            if (conversation != null) {
                //獲取此会话的所有消息
                messages = conversation
                        .getAllMessages();

            } else {
                messages = new ArrayList<EMMessage>();
            }

        }

    }


    private void initview(){
        listView= (ListView) findViewById(R.id.private_message_listview);
        editText= (EditText) findViewById(R.id.private_message_edittext);

        button= (Button) findViewById(R.id.private_message_button);

        listView.setSelection(listView.getBottom());
        button.setOnClickListener(this);

    }

    private void init() {
        initData();
        padapter=new PrivateAdapter(this,messages);
        listView.setAdapter(padapter);
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();


            sendTt(str);
    }

    private void sendTt(String str) {
        EMMessage message;
        if (TextUtils.isEmpty(username)) {
            //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id
            message = EMMessage.createTxtSendMessage(str, groupId);
        } else {
            message = EMMessage.createTxtSendMessage(str, username);
        }
        if (TextUtils.isEmpty(username)) {
            message.setChatType(EMMessage.ChatType.GroupChat);
        }
        //TODO 設置聊天類型
        //設置消息状态回调
        message.setMessageStatusCallback(this);
        //調用發送消息
        EMClient.getInstance().chatManager().sendMessage(message);
        addMsg2List(message);
    }
    private  void addMsg2List(EMMessage message){
        messages.add(message);

        padapter.notifyDataSetChanged();
        listView.setSelection(listView.getBottom());
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int i, String s) {

    }

    @Override
    public void onProgress(int i, String s) {

    }
}

