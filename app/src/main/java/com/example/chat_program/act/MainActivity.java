package com.example.chat_program.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.chat_program.R;

public class MainActivity extends AppCompatActivity {
private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dianjitiaozhuan();
        shuimian();
    }

    private void dianjitiaozhuan() {
        textView = (TextView) findViewById(R.id.text_jinru);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void shuimian() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    init();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }).start();
    }


        private void init() {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

//        EMClient.getInstance().login("cy","123456", new EMCallBack() {//回调
//            @Override
//            public void onSuccess() {
//                EMClient.getInstance().groupManager().loadAllGroups();
//                EMClient.getInstance().chatManager().loadAllConversations();
//                Log.d("main", "登录聊天服务器成功！");
//            }
//
//            @Override
//            public void onProgress(int progress, String status) {
//
//            }
//
//            @Override
//            public void onError(int code, String message) {
//                Log.d("main", "登录聊天服务器失败！");
//            }
//        });



}
