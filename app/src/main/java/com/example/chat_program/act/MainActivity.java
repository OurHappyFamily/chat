package com.example.chat_program.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.chat_program.R;
import com.hyphenate.chat.EMClient;

import java.util.Date;

/**
 * 开屏页
 */
public class MainActivity extends AppCompatActivity {
    //点击跳转      倒计时跳转
    private TextView textView, tv1;
    //设置常量  用途 代表时间
    private static final String SLEEP_TIME_KEY = "time";
    //设置常量  用途 代表秒数
    private static final long SLEEP_TIME = 1000;

    Handler handler = new Handler() {
//        接受并处理接收到的消息
        public void handleMessage(Message msg) {
            String a = msg.getData().getString(SLEEP_TIME_KEY);
            tv1.setText(a + "s");
        }
    };

    private void init() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //判断之前是否登陆过
                if (EMClient.getInstance().isLoggedInBefore()) {
                    //拿到当前时间
                    long StartTime = new Date().getTime();
                    //耗时操作 加载数据
                    EMClient.getInstance().chatManager().loadAllConversations();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    //拿到执行到的当前时间减去开始时间   得到加载所耗时间
                    long time = new Date().getTime() - StartTime;
                    //登陆过就跳转到主页（SDK已经实现自动登录）
                    try {

                        //延时（4000-time）秒跳转到主页
                        Thread.sleep(4000 - time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    startActivity(intent);
                } else {
                    //没登录过跳转到登录页面

                    try {
                        //延时4秒跳转到登录页面
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //跳转到登录页面
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

                finish();
            }
        }).start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dianjitiaozhuan();
init();
        djs();
    }
        //点击textview跳转到登录页
    private void dianjitiaozhuan() {
        textView = (TextView) findViewById(R.id.text_jinru);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EMClient.getInstance().isLoggedInBefore()) {
                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
        //倒计时的方法
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
        //  开屏页 休眠方法
//    private void shuimian() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(4000);
//                    init();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        }


        //从开平页跳转到登陆页
//    private void init() {
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);
//    }

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
