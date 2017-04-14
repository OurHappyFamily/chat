package com.example.chat_program.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chat_program.R;
import com.example.chat_program.adapter.PictureAdapter;
import com.example.chat_program.adapter.PrivateAdapter;
import com.example.chat_program.fragment.PictureFragment;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张泽雅文 on 2017/4/5.
 */
public class PrivateMessageActivity extends AppCompatActivity implements View.OnClickListener, EMCallBack, TextWatcher {
    private ListView listView;
    private EditText editText;
    private Button button;
    private String groupId;
    private String username;
    private List<EMMessage> messages;
    private PrivateAdapter padapter;
    private String text;
    private Button button_picture;
    PictureFragment pp;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_privatemessage);
        super.onCreate(savedInstanceState);
        username = getIntent().getStringExtra("userName");
        groupId = getIntent().getStringExtra("groupId");
        text = getIntent().getStringExtra("text");

        try {
            //设置返回键的图标
            // getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_bg1);
            //返回键
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //设置标题栏的名字
            getSupportActionBar().setTitle(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initview();
        init();
    }

    // actionBar里面的点击事件的方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("text", text);
                intent.putExtra("userName", username);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            case R.id.privatmessage_test:
                Toast.makeText(this, "啊哈", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    //设置标题栏右侧内容
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_message, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initData() {
        //判断是群聊还是单聊
        if (TextUtils.isEmpty(groupId)) {
            EMConversation conversation = EMClient
                    .getInstance()
                    .chatManager()
                    .getConversation(username);
            //获取此会话的所有消息
            messages = conversation
                    .getAllMessages();

        } else {
            EMConversation conversation = EMClient
                    .getInstance().chatManager()
                    .getConversation(groupId);
            if (conversation != null) {
                //获取此会话的所有消息
                messages = conversation
                        .getAllMessages();

            } else {
                messages = new ArrayList<EMMessage>();
            }

        }

    }


    private void initview() {
        listView = (ListView) findViewById(R.id.private_message_listview);
        editText = (EditText) findViewById(R.id.private_message_edittext);
        editText.addTextChangedListener(this);
        button_picture = (Button) findViewById(R.id.button_picture);
        button_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pp = new PictureFragment();

                if (pp.isAdded()) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();

                    fragmentTransaction.remove(pp);
                    fm.popBackStackImmediate("message_bottom_fragment", fm.POP_BACK_STACK_INCLUSIVE);
                    fragmentTransaction.commit();
                } else {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_picture, pp);
                    fragmentTransaction.addToBackStack("message_bottom_fragment");
                    fragmentTransaction.commit();
                }
            }
        });
        button = (Button) findViewById(R.id.private_message_button);
        if (!TextUtils.isEmpty(text)) {
            editText.setText(text);
            editText.setSelection(editText.getText().length());
        }
        listView.setSelection(listView.getBottom());
        button.setOnClickListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        text = editText.getText().toString();
    }

    private void init() {
        initData();
        padapter = new PrivateAdapter(this, messages,username);
        listView.setAdapter(padapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("text", text);
        intent.putExtra("username", username);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();
        text = "";

        sendTt(str);
        padapter.notifyDataSetChanged();


    }

    /**
     *
     * @param imgpath      图片路路径
     * @param isThumbnail  是否发送原图
     */
    public void sendImage(String imgpath, boolean isThumbnail) {
        EMMessage message = EMMessage
                .createImageSendMessage(imgpath, isThumbnail, username);
        message.setMessageStatusCallback(this);
        EMClient.getInstance().chatManager().sendMessage(message);
        addMsg2List(message);
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

    private void addMsg2List(EMMessage message) {
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

