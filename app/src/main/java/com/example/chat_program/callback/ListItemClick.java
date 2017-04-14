package com.example.chat_program.callback;

/**
 * Created by yinm_pc on 2017/4/1.
 */

public interface ListItemClick {
    void onClick(int id);

    void onLongClick();

    void deleteItem(int id);
}
