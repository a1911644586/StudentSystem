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
        values.put(Table.StudentColumns.SEX, s.getSex());
        values.put(Table.StudentColumns.PROFESSION, s.getProfession());
        values.put(Table.StudentColumns.SCORE, s.getScore());
        return dbHelper.getWritableDatabase().update(Table.STUDENT_TABLE, values,
                Table.StudentColumns.ID + "=?", new String[] { s.getId() + "" });
    }
    public Cursor findStudent(String name){
        Cursor cursor = dbHelper.getWritableDatabase().query(Table.STUDENT_TABLE,  null, "name like ?",
                new String[] { "%" + name + "%" }, null, null, null,null);
        return cursor;
    }

    // 查询所有的记录
    public List<Map<String,Object>> getAllStudents() {
        //modify_time desc
        List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
        Cursor cursor = dbHelper.getWritableDatabase().query(Table.STUDENT_TABLE, null, null, null,
                null, null, null);
        while(cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>(8);
            long id = cursor.getInt(cursor.getColumnIndex(Table.StudentColumns.ID));
            map.put(Table.StudentColumns.ID, id);
            String name = cursor.getString(cursor.getColumnIndex(Table.StudentColumns.NAME));
            map.put(Table.StudentColumns.NAME, name);
            String grade = cursor.getString(cursor.getColumnIndex(Table.StudentColumns.GRADE));
            map.put(Table.StudentColumns.GRADE, grade);
            String sex = cursor.getString(cursor.getColumnIndex(Table.StudentColumns.SEX));
            map.put(Table.StudentColumns.SEX, sex);
            String profession = cursor.getString(cursor.getColumnIndex(Table.StudentColumns.PROFESSION));
            map.put(Table.StudentColumns.PROFESSION, profession);
            String score = cursor.getString(cursor.getColumnIndex(Table.StudentColumns.SCORE));
            map.put(Table.StudentColumns.SCORE, score);
        }
        return data;
    }

    public void closeDB() {
        dbHelper.close();
    }

    public Student getStudent(View view, long id) {
        TextView nameView =  view.findViewById(R.id.tv_stu_name);
        TextView gradeView =  view.findViewById(R.id.tv_stu_grade);
        TextView sexView =  view.findViewById(R.id.tv_stu_sex);
        TextView professionView =  view.findViewById(R.id.tv_stu_profession);
        TextView scoreView =  view.findViewById(R.id.tv_stu_score);
        String name = nameView.getText().toString();
        String grade =gradeView.getText().toString();
        String sex = sexView.getText().toString();
        String profession = professionView.getText().toString();
        String score = scoreView.getText().toString();
        Student student = new Student(id, name, grade, sex, profession, score);
        return student;
    }

}
