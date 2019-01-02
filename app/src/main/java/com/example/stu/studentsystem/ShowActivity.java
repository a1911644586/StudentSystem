package com.example.stu.studentsystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra(Table.STUDENT_TABLE);
        ((TextView)findViewById(R.id.tv_info_id)).setText(student.getId()+"");
        ((TextView)findViewById(R.id.tv_info_name)).setText(student.getName());
        ((TextView)findViewById(R.id.tv_info_sex)).setText(student.getSex());
        ((TextView)findViewById(R.id.tv_info_grade)).setText(student.getGrade()+"");
        ((TextView)findViewById(R.id.tv_info_profession)).setText(student.getProfession());
        ((TextView)findViewById(R.id.tv_info_score)).setText(student.getScore());
    }
    public void goBack(View view) {
        finish();
    }

}
