package com.example.stu.studentsystem.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.stu.studentsystem.R;

public class MainActivity extends AppCompatActivity{
    private long exit_time;//用于实现按两次back退出
    private Button admin;
    private Button student;
    private myDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        admin = (Button) findViewById(R.id.main_activity_admin);
        dbHelper = myDatabaseHelper.getInstance(this);
        dbHelper.getWritableDatabase();

        //跳转到管理员登录界面
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, admin_login_activity.class);
                startActivity(intent);
            }
        });
    }
}
