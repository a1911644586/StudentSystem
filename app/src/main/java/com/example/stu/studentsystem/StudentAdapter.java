package com.example.stu.studentsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

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
        values.put(Table.StudentColumns.AGE, s.getAge());
        values.put(Table.StudentColumns.SEX, s.getSex());
        values.put(Table.StudentColumns.PROFESSION, s.getProfession());
        values.put(Table.StudentColumns.SCORE, s.getScore());
        values.put(Table.StudentColumns.TRAIN_DATE, s.getTrainDate());
        values.put(Table.StudentColumns.MODIFY_TIME, s.getModifyDateTime());
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
        values.put(Table.StudentColumns.AGE, s.getAge());
        values.put(Table.StudentColumns.SEX, s.getSex());
        values.put(Table.StudentColumns.PROFESSION, s.getProfession());
        values.put(Table.StudentColumns.SCORE, s.getScore());
        values.put(Table.StudentColumns.TRAIN_DATE, s.getTrainDate());
        values.put(Table.StudentColumns.MODIFY_TIME, s.getModifyDateTime());
        return dbHelper.getWritableDatabase().update(Table.STUDENT_TABLE, values,
                Table.StudentColumns.ID + "=?", new String[] { s.getId() + "" });
    }
    public Cursor findStudent(String name){
        Cursor cursor = dbHelper.getWritableDatabase().query(Table.STUDENT_TABLE,  null, "name like ?",
                new String[] { "%" + name + "%" }, null, null, null,null);
        return cursor;
    }

    public void closeDB() {
        dbHelper.close();
    }

    public Student getStudentFromView(View view, long id) {
        TextView nameView = (TextView) view.findViewById(R.id.tv_stu_name);
        TextView ageView = (TextView) view.findViewById(R.id.tv_stu_age);
        TextView sexView = (TextView) view.findViewById(R.id.tv_stu_sex);
        TextView likeView = (TextView) view.findViewById(R.id.tv_stu_profession);
        TextView phoneView = (TextView) view.findViewById(R.id.tv_stu_score);
        TextView dataView = (TextView) view.findViewById(R.id.tv_stu_traindate);
        String name = nameView.getText().toString();
        int age = Integer.parseInt(ageView.getText().toString());
        String sex = sexView.getText().toString();
        String profession = likeView.getText().toString();
        String score = phoneView.getText().toString();
        String data = dataView.getText().toString();
        Student student = new Student(id, name, age, sex, profession, score, data,null);
        return student;
    }

}
