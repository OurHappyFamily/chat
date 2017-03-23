package com.example.chat_program;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.chat_program.act.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView, tv1;
    private static final String SLEEP_TIME_KEY = "time";
    private static final long SLEEP_TIME = 1000;

    Handler handler = new Handler() {
//        接受并处理接收到的消息
        public void handleMessage(Message msg) {
            String a = msg.getData().getString(SLEEP_TIME_KEY);
            tv1.setText(a + "s");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dianjitiaozhuan();
        shuimian();
        djs();
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

    private void djs() {
        tv1 = (TextView) findViewById(R.id.text_djs1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 3; x > -1; x--) {
                    String a = String.valueOf(x);
//编辑需要发送的消息
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString(SLEEP_TIME_KEY, a);
                    msg.setData(data);
//给handler发送消息
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }

        }).start();


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
                } finally {
//                    init();

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
