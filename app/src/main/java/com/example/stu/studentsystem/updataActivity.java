package com.example.stu.studentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;

public class updataActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_reset,btn_updata;
    private EditText et_name,et_grade,et_profession,et_score,et_stu_id;
    private StudentAdapter adapter;
    private Long stu_id;
    private RadioGroup radioGroup;
    private RadioButton rdt1,rdt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        et_stu_id = findViewById(R.id.et_stu_id);
        et_name=findViewById(R.id.et_name);
        et_grade = findViewById(R.id.et_grade);
        et_profession=findViewById(R.id.et_profession);
        et_score=findViewById(R.id.et_score);
        radioGroup = findViewById(R.id.rdg_sex);
        rdt1 = findViewById(R.id.rdb_male);
        rdt2=findViewById(R.id.rdb_female);
        adapter = new StudentAdapter(new StudentDBHelper(this));
        btn_updata=findViewById(R.id.btn_updata);
        btn_updata.setOnClickListener(this);
        btn_reset=findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);
        init();
    }
    public void init(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("student");
        stu_id = bundle.getLong("id");
        et_stu_id.setText(stu_id+"");
        et_name.setText(bundle.getString("name"));
        et_grade.setText(bundle.getString("grade"));
        String sex = bundle.getString("sex");
       /* if (sex.trim().equals("男")) {
            rdt1.setChecked(true);
        } else if (sex.trim().equals("女")) {
            rdt2.setChecked(true);
        }*/
        et_profession.setText(bundle.getString("profession"));
        et_score.setText(bundle.getString("score"));
    }
    private void updata(Student student) {

        String name=et_name.getText().toString().trim();
        String grade=et_grade.getText().toString().trim();
        String score=et_score.getText().toString().trim();
        String profession=et_profession.getText().toString().trim();
        String sex = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        if (sex.equals("男")) {
            rdt1.setChecked(true);
        } else if (sex.equals("女")) {
            rdt2.setChecked(true);
        }
        et_score.setText(score);
        et_name.setText(name);
        et_grade.setText(grade);
        et_profession.setText(profession);
    }

    //重置添加界面
    public void clearData(){
        et_name.setText("");
        et_grade.setText("");
        et_profession.setText("");
        et_score.setText("");
        rdt1.setChecked(false);
        rdt2.setChecked(false);
        radioGroup.clearCheck();
    }
    public void onClick(View v) {
        if (v == btn_updata) {
            checkInput();
            Student s = getStudent();
            updata(s);
            int raw = adapter.updateStudent(s);
            adapter.closeDB();
            if (raw >0) {
                Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "更新失败", Toast.LENGTH_SHORT).show();
            }
        }else if (v==btn_reset){
            clearData();
        }
    }



    private boolean checkInput(){
        String name = et_name.getText().toString().trim();
        String grade = et_grade.getText().toString().trim();
        String profession = et_profession.getText().toString().trim();
        String score = et_score.getText().toString().trim();
        int id = radioGroup.getCheckedRadioButtonId();
        String message = null;
        View focusView = null;
        if (name.trim().equals("")){
            message = "请输入姓名! ";
            focusView = et_name;
        }else if (grade.trim().equals("")){
            message = "请输入年级! ";
            focusView = et_grade;
        }else if (id == -1){
            message = "请选择性别! ";
        }else if (profession.trim().equals("")){
            message = "请输入科目！ ";
            focusView = et_profession;
        }else if (score.trim().equals("")){
            message = "请输入成绩！ ";
            focusView = et_score;
        }
        if (message != null){
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            if (focusView != null){
                //获取焦点输入框
                focusView.requestFocus();
            }
            return false;
        }
        return true;

    }
    //获取输入的学生数据
    private Student getStudent() {
        long id = Integer.parseInt(et_stu_id.getText().toString());
        String name = et_name.getText().toString();
        String grade =et_grade.getText().toString();
        String sex = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        String profession = et_profession.getText().toString().trim();
        String score= et_score.getText().toString();
        Student student=new Student(name, grade, profession, score);
        student.setId(id);
        return student;
    }

}
