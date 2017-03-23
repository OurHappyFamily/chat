package com.example.chat_program.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chat_program.MainActivity;
import com.example.chat_program.R;
import com.example.chat_program.RegisterActivity;

/**
 * Created by 张泽雅文 on 2017/3/23.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText edit_username,edit_password;
    private Button button_login,button_zhuce;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        edit_username= (EditText) findViewById(R.id.edit_username);
        edit_password= (EditText) findViewById(R.id.edit_password);
        button_login= (Button) findViewById(R.id.button_login);
        button_zhuce= (Button) findViewById(R.id.button_zhuce);
        button_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edit_username.getText().toString();
                String password=edit_password.getText().toString();
                if(username.equals("147")&&password.equals("123")){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
