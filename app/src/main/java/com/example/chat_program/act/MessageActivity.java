package com.example.chat_program.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.chat_program.R;
import com.example.chat_program.fragment.MessageFragment;
import com.example.chat_program.fragment.PeopleFragment;
import com.example.chat_program.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息列表
 */

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    //         消息按钮    联系人按钮    设置按钮
    private Button button_message,button_people,button_set;

    //  滑动控件
    private ViewPager viewPager;
    // fragment管理器
    FragmentManager fm;
    private List<Fragment> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        addTolist();
        init();
        init2();
    }
    //初始化控件  设置点击事件
    private void init2() {
        button_message= (Button) findViewById(R.id.button_message);
        button_people= (Button) findViewById(R.id.button_people);
        button_set= (Button) findViewById(R.id.button_set);
        button_message.setOnClickListener(this);
        button_people.setOnClickListener(this);
        button_set.setOnClickListener(this);
    }
    //实例化fragment并添加到list集合里
    private void addTolist() {
        //实例化   消息    列表界面
        MessageFragment mf=new MessageFragment();
        //实例化   联系人   列表界面
        PeopleFragment pf=new PeopleFragment();
        //实例化   设置    列表界面
        SettingFragment sf=new SettingFragment();
        list.add(mf);
        list.add(pf);
        list.add(sf);
    }
    //给viewPager添加内容
    private void init() {
        //开启事物
        fm=getSupportFragmentManager();
        //初始化viewPager
        viewPager= (ViewPager) findViewById(R.id.viewpaper);
        //给viewPager设置适配器
        FragmentPagerAdapter fp=new FragmentPagerAdapter(fm) {
            //获取item内容
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
            //获取item个数
            @Override
            public int getCount() {
                return list.size();
            }
        };
        //设置适配器
        viewPager.setAdapter(fp);
        //设置当前页面
        viewPager.setCurrentItem(0);
        //限定预加载的个数
        viewPager.setOffscreenPageLimit(3);
    }




// 判断点击事件

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 点击  进入 消息页
            case R.id.button_message:
                viewPager.setCurrentItem(0);
                break;
            //点击进入联系人页
            case R.id.button_people:
                viewPager.setCurrentItem(1);
                break;
            //点击进入设置页
            case R.id.button_set:
                viewPager.setCurrentItem(2);
                break;


        }
    }
}
