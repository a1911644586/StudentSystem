package com.example.stu.studentsystem.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.stu.studentsystem.R;
import com.example.stu.studentsystem.login.*;
import com.example.stu.studentsystem.StudentSystem;

public class admin_activity extends AppCompatActivity {
    private Button enter;//进入学生信息系统// 按钮
    private TextView forceOffline;//强制下线

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin_activity);

        enter = (Button) findViewById(R.id.admin_activity_enter);
        forceOffline=findViewById(R.id.admin_activity_forceOffline);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_activity.this, StudentSystem.class);
                startActivity(intent);

            }
        });



        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.he.example.OfflineBradcast");
                sendBroadcast(intent);
            }
        });


    }
}
