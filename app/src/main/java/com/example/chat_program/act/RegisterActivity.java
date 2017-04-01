package com.example.chat_program.act;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chat_program.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * 注册页面
 */

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText register_username,register_password;
    private Button button_zhuce;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        register_username= (EditText) findViewById(R.id.register_et_number);
        register_password= (EditText) findViewById(R.id.register_et_password);
        button_zhuce= (Button) findViewById(R.id.register_btn);
        button_zhuce.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final String username=register_username.getText().toString();
        final String password=register_password.getText().toString();
        //判断为空账号为空
        if (TextUtils.isEmpty(username)
                || TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "帐号或密码不能为空！", Toast.LENGTH_SHORT).show();

        } else {
        //
            new AlertDialog.Builder(this)
                    .setMessage("确定提交吗")
                    .setTitle("提交")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        //环信注册账号和密码的方法
                                        EMClient.getInstance().createAccount(username, password);
                                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    } catch (HyphenateException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    }).show();

        }
    }
}
