package com.example.chat_program.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.chat_program.R;



public class CustomDialog extends ProgressDialog {
    //继承父类自带两个构造方法
    public CustomDialog(Context context) {
        super(context);
    }
    //继承父类自带构造方法
    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {

        setCancelable(true);

        setCanceledOnTouchOutside(true);
        //设置dialog布局
        setContentView(R.layout.load_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //得到宽和高
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置
        getWindow().setAttributes(params);
    }

}
