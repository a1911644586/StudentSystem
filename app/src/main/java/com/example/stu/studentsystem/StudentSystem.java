package com.example.stu.studentsystem;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class StudentSystem extends ListActivity implements View.OnClickListener ,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private Button addButton,searchButton;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private Student student;
    private Boolean isDeleteList = false;
    private Cursor cursor;

    private List<Long> list;
    private  StudentAdapter adapter;
    private ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_system);
        addButton = findViewById(R.id.btn_add);
        searchButton = findViewById(R.id.btn_query);
        relativeLayout = findViewById(R.id.RelativeLayout);
        list = new ArrayList<Long>();
        student = new Student();
        listView = getListView();
        adapter = new StudentAdapter(new StudentDBHelper(this));
        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);


    }
    @Override
    protected void onStart() {
        super.onStart();
        load();
    }
    public void onClick(View v){
        if(v==addButton){
            startActivity(new Intent(StudentSystem.this,AddStudent.class));
            load();
        }else if(v == searchButton){
            startActivity(new Intent(StudentSystem.this,StudentSearch.class));
            load();
        }
    }

    public void load(){
        StudentDBHelper studentDBHelper = new StudentDBHelper(
                StudentSystem.this);
        SQLiteDatabase database = studentDBHelper.getWritableDatabase();
        cursor = database.query(Table.STUDENT_TABLE, null, null, null,
                null, null,null);
        startManagingCursor(cursor);
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.student_list_item,
                cursor, new String[] {
                Table.StudentColumns.ID,
                Table.StudentColumns.NAME,
                Table.StudentColumns.GRADE,
                Table.StudentColumns.PROFESSION,
                Table.StudentColumns.SCORE
        },
                new int[] {
                        R.id.tv_stu_id, R.id.tv_stu_name, R.id.tv_stu_grade,
                        R.id.tv_stu_profession, R.id.tv_stu_score
                });
        listView.setAdapter(simpleCursorAdapter);
        listView.setTag(simpleCursorAdapter);
    }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        student =  adapter.getStudentView(view,id);
        Intent intent = new Intent(this,updataActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("id",student.getId());
        bundle.putString("name",student.getName());
        bundle.putString("grade",student.getGrade());
        bundle.putString("sex",student.getSex());
        bundle.putString("profession",student.getProfession());
        bundle.putString("score",student.getScore());
        intent.putExtra("student",bundle);
        startActivity(intent);
        intent.setClass(this,updataActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        deleteStudent(id);
    }

    private void deleteStudent(final long ID) {
        // 利用对话框的形式删除数据
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("学员信息删除")
                .setMessage("确定删除所选记录?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int raws = adapter.deleteStudentById(ID);
                        isDeleteList = !isDeleteList;
                        load();
                        if (raws > 0) {
                            Toast.makeText(StudentSystem.this, "删除成功!",
                                    Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(StudentSystem.this, "删除失败!",
                                    Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
