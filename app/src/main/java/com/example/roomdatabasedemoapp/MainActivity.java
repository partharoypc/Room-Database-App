package com.example.roomdatabasedemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabasedemoapp.Room.UserDao;
import com.example.roomdatabasedemoapp.Room.Users;
import com.example.roomdatabasedemoapp.Room.UsersDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener{

    EditText username,userEmail;
    Button btnSubmit;
    RecyclerView rv;

    private UsersDatabase usersDatabase;
    private UserDao userDao;
    private UserAdapter  userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.name);
        userEmail = findViewById(R.id.email);
        btnSubmit = findViewById(R.id.btn);
        rv = findViewById(R.id.user_rv);


        userAdapter = new UserAdapter(this,this);
        rv.setAdapter(userAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        usersDatabase = UsersDatabase.getInstance(this);
        userDao = usersDatabase.getDao();



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString();
                String email = userEmail.getText().toString();

                Users users = new Users(0,name,email);
                userAdapter.addUser(users);
                userDao.insert(users);
                username.setText("");
                userEmail.setText("");

                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fetchData(){
        userAdapter.clearData();
        List<Users> usersList = userDao.getAllUsers();
        for (int i=0; i<usersList.size();i++){
            Users users = usersList.get(i);
            userAdapter.addUser(users);
        }

    }

    @Override
    public void onUpdate(Users users) {
        Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
        intent.putExtra("model",users);
        startActivity(intent);

    }

    @Override
    public void onDelete(int id, int pos) {
        userDao.delete(id);
        userAdapter.removeUser(pos);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}