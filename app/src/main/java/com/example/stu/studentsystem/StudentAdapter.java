package com.example.stu.studentsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StudentAdapter {
    private StudentDBHelper dbHelper;
    private Cursor cursor;
    public StudentAdapter(StudentDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    // 添加一个Student对象数据到数据库表
    public long addStudent(Student s) {
        ContentValues values = new ContentValues();
        values.put(Table.StudentColumns.NAME, s.getName());
        values.put(Table.StudentColumns.GRADE, s.getGrade());
        values.put(Table.StudentColumns.SEX, s.getSex());
        values.put(Table.StudentColumns.PROFESSION, s.getProfession());
        values.put(Table.StudentColumns.SCORE, s.getScore());
        return dbHelper.getWritableDatabase().insert(Table.STUDENT_TABLE, null, values);
    }

    // 删除一个id所对应的数据库表student的记录
    public int deleteStudentById(long id) {
        return dbHelper.getWritableDatabase().delete(Table.STUDENT_TABLE,
                Table.StudentColumns.ID + "=?", new String[] { id + "" });
    }

    // 更新一个id所对应数据库表student的记录
    public int updateStudent(Student s) {
        ContentValues values = new ContentValues();
        values.put(Table.StudentColumns.NAME, s.getName());
        values.put(Table.StudentColumns.GRADE, s.getGrade());
        values.put(Table.StudentColumns.SEX, "男");
        values.put(Table.StudentColumns.PROFESSION, s.getProfession());
        values.put(Table.StudentColumns.SCORE, s.getScore());
        return dbHelper.getWritableDatabase().update(Table.STUDENT_TABLE, values,
                Table.StudentColumns.ID + "=?", new String[] { s.getId()+"" });
    }
    public Cursor findStudent(String name){
        Cursor cursor = dbHelper.getWritableDatabase().query(Table.STUDENT_TABLE,  null, "name like ?",
                new String[] { "%" + name + "%" }, null, null, null,null);
        return cursor;
    }

    public void closeDB() {
        dbHelper.close();
    }

    public Student getStudentView(View view, long id) {
        TextView nameView = (TextView) view.findViewById(R.id.tv_stu_name);
        TextView gradeView = (TextView) view.findViewById(R.id.tv_stu_grade);
        TextView sexView = (TextView) view.findViewById(R.id.tv_stu_sex);
        TextView professionView = (TextView) view.findViewById(R.id.tv_stu_profession);
        TextView scoreView = (TextView) view.findViewById(R.id.tv_stu_score);
        String name = nameView.getText().toString();
        String grade =gradeView.getText().toString();
        //String sex = sexView.getText().toString();
        String profession = professionView.getText().toString();
        String score = scoreView.getText().toString();
        Student student = new Student(id, name, grade,profession, score);
        return student;
    }

}
