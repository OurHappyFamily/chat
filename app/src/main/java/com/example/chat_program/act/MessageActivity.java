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
 * Created by 张泽雅文 on 2017/3/23.
 */

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_message,button_people,button_set;
    private ViewPager viewPager;
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

    private void init2() {
        button_message= (Button) findViewById(R.id.button_message);
        button_people= (Button) findViewById(R.id.button_people);
        button_set= (Button) findViewById(R.id.button_set);
        button_message.setOnClickListener(this);
        button_people.setOnClickListener(this);
        button_set.setOnClickListener(this);
    }

    private void addTolist() {
        MessageFragment mf=new MessageFragment();
        PeopleFragment pf=new PeopleFragment();
        SettingFragment sf=new SettingFragment();
        list.add(mf);
        list.add(pf);
        list.add(sf);
    }

    private void init() {
        fm=getSupportFragmentManager();
        viewPager= (ViewPager) findViewById(R.id.viewpaper);
        FragmentPagerAdapter fp=new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        viewPager.setAdapter(fp);
        viewPager.setCurrentItem(0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_message:
                viewPager.setCurrentItem(0);
                break;
            case R.id.button_people:
                viewPager.setCurrentItem(1);
                break;
            case R.id.button_set:
                viewPager.setCurrentItem(2);
                break;


        }
    }
}
