package com.example.roomdatabasedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabasedemoapp.Room.UserDao;
import com.example.roomdatabasedemoapp.Room.Users;
import com.example.roomdatabasedemoapp.Room.UsersDatabase;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameU,emailU;
    private Button update;
    private Users users;
    private UsersDatabase usersDatabase;
    private UserDao userDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameU = findViewById(R.id.update_name);
        emailU = findViewById(R.id.update_email);
        update = findViewById(R.id.btnUpdate);

        usersDatabase = UsersDatabase.getInstance(this);
        userDao = usersDatabase.getDao();

        users = (Users) getIntent().getSerializableExtra("model");

        nameU.setText(users.getName());
        emailU.setText(users.getEmail());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users userModel = new Users(users.getId(), nameU.getText().toString(),emailU.getText().toString());
                userDao.update(userModel);
                finish();

            }
        });
    }
}