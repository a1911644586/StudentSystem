package com.example.stu.studentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class AddStudent extends AppCompatActivity implements View.OnClickListener {
    private TextView id;
    private Button btn_reset,btn_save,btn_updata;
    private EditText et_name,et_grade,et_profession,et_score;
    private StudentAdapter adapter;
    private Long stu_id;
    Serializable serializable;
    private RadioGroup radioGroup;
    private RadioButton rdt1,rdt2;
    private boolean isAdd = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = findViewById(R.id.tv_stu_text_id);
        setContentView(R.layout.activity_add_student);
        et_name=findViewById(R.id.et_name);
        et_grade = findViewById(R.id.et_grade);
        et_profession=findViewById(R.id.et_profession);
        et_score=findViewById(R.id.et_score);
        radioGroup = findViewById(R.id.rdg_sex);
        rdt1 = findViewById(R.id.rdb_male);

        rdt2=findViewById(R.id.rdb_female);
        adapter = new StudentAdapter(new StudentDBHelper(this));
        btn_reset=findViewById(R.id.btn_reset);
        btn_save=findViewById(R.id.btn_save);
        btn_reset.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        isAdd();
    }

    public void onClick(View v){
        // isAdd=false;
        if (v == btn_save) {
            if (!checkInput()) {// 界面输入验证
                return;
            }
            Student student = getStudent();
            if (isAdd) {
                long id = adapter.addStudent(student);
                adapter.closeDB();
                if (id > 0) {
                    Toast.makeText(this, "保存成功， ID=" + id,Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "保存失败，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v == btn_reset) {
            clearData();
        }
    }
    //获取输入的学生数据
    private Student getStudent() {
        String name = et_name.getText().toString();
        String grade =et_grade.getText().toString();
        String sex = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        String profession = et_profession.getText().toString().trim();
        String score= et_score.getText().toString();
        Student student=new Student(name, grade, sex, profession, score);
        if (!isAdd) {
            student.setId(Integer.parseInt(id.getText().toString()));
            adapter.deleteStudentById(stu_id);
        }
        return student;
    }

    //添加数据
    public boolean isAdd(){
        Intent intent = getIntent();
        serializable = intent.getSerializableExtra(Table.STUDENT_TABLE);
        if (serializable == null){
            isAdd = true;
        }else{
            isAdd = false;
            Student student = (Student) serializable;
            updata(student);
        }
        return false;
    }
    //更新数据
    public void updata(Student student){

        stu_id = student.getId();
        String name = student.getName();
        String grade = student.getGrade();
        String profession = student.getProfession();
        String score = student.getScore();
        id.setText(stu_id + "");
        et_name.setText(name + "");
        et_grade.setText(grade + "");
        et_profession.setText(profession + "");
        et_score.setText(score+ "");
        setTitle("学员科目成绩更新");
        btn_save.setText("更新");

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

    //检查是否输入信息
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

}
