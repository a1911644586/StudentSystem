package com.example.stu.studentsystem.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabaseHelper extends SQLiteOpenHelper {
    private static myDatabaseHelper instance;
    public static final String CREATE_ADMIN = "create table admin(id integer primary key autoincrement, name text,password text)";//创建管理员表



    private myDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADMIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==1){
            db.execSQL("alter table student add  column ranking integer");
        }
    }

    public static myDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new myDatabaseHelper(context, "StudentManagement.db", null, 2);
        }
        return instance;

    }


}
