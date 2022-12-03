package com.example.roomdatabasedemoapp;

import com.example.roomdatabasedemoapp.Room.Users;

public interface AdapterListener {
    void onUpdate(Users users);
    void onDelete(int id,int pos);
}
