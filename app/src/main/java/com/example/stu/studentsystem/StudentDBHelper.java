package com.example.stu.studentsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StudentDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "student_manager.db";
    public static final int VERSION = 1;    //构造方法
    public StudentDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    public StudentDBHelper(Context context) {
        this(context, DB_NAME, null, VERSION);     }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "
                + Table.STUDENT_TABLE                 + "(_id Integer primary key AUTOINCREMENT,"
                + "name char,grade integer, sex char, profession char, score char)");     }
    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}