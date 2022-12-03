package com.example.roomdatabasedemoapp.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class},version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    public abstract UserDao getDao();

    public static UsersDatabase INSTANCE;

    public static UsersDatabase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,UsersDatabase.class,"UserDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
