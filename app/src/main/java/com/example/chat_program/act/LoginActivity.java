package com.example.chat_program.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chat_program.R;


/**
 * 登陆页
 */

public class LoginActivity extends AppCompatActivity {
    // 输入姓名        输入密码
    private EditText edit_username, edit_password;
    //登录按钮         注册按钮
    private Button button_login, button_zhuce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
// 初始化控件   设置点击事件
    private void init() {
        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_password = (EditText) findViewById(R.id.edit_password);
        button_login = (Button) findViewById(R.id.button_login);
        button_zhuce = (Button) findViewById(R.id.button_zhuce);
        //点击按钮跳转到注册界面
        button_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //点击按钮如果登录成功跳转到主界面，如果失败，提示一下登录失败
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();

                if (username.equals("147") && password.equals("123")) {
                    Intent intent = new Intent(LoginActivity.this, MessageActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

}}