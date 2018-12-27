package com.example.stu.studentsystem;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentSystem extends ListActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private Button addButton,searchButton,selectButton,deleteButton,canleButton,selectAllButton;
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
        selectButton = findViewById(R.id.btn_select);
        selectAllButton = findViewById(R.id.btn_selectall);
        deleteButton = findViewById(R.id.btn_delete);
        canleButton = findViewById(R.id.btn_canel);
        linearLayout = findViewById(R.id.showLiner);
        relativeLayout = findViewById(R.id.RelativeLayout);
        list = new ArrayList<Long>();
        Student student = new Student();
        listView = getListView();

        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        canleButton.setOnClickListener(this);
        selectAllButton.setOnClickListener(this);

    }
    public void onClick(View v){
        if(v==addButton){
            startActivity(new Intent(StudentSystem.this,AddStudent.class));
        }else if(v == searchButton){
            startActivity(new Intent(StudentSystem.this,StudentSearch.class));
        /*}else if (v == selectButton){
            isDeleteList = !isDeleteList;

        }else if (v == deleteButton){
            if(list.size()>0){
                for (int i = 0 ; i<list.size(); i++){
                    long id = list.get(i);
                    Log.i("成功","删除的ID="+id);

                }
                adapter.closeDB();
            }
        }else if (v == canleButton){

        }
        else if (v == selectAllButton){
*/
        }
    }
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Student student = (Student) adapter.getStudentFromView(view, id);
        listView.setTag(student);
        registerForContextMenu(listView);
        return false;
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }


    public void load() {
        StudentDBHelper studentDBHelper = new StudentDBHelper(
                StudentSystem.this);
        SQLiteDatabase database = studentDBHelper.getWritableDatabase();
        cursor = database.query(Table.STUDENT_TABLE, null, null, null,
                null, null, Table.StudentColumns.MODIFY_TIME + " desc");
        startManagingCursor(cursor);
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.student_list_item,
                cursor, new String[] {
                    Table.StudentColumns.ID,
                    Table.StudentColumns.NAME,
                    Table.StudentColumns.AGE,
                    Table.StudentColumns.SEX,
                    Table.StudentColumns.PROFESSION,
                    Table.StudentColumns.SCORE,
                    Table.StudentColumns.TRAIN_DATE
                    },
                new int[] {
                R.id.tv_stu_id, R.id.tv_stu_name, R.id.tv_stu_age,
                R.id.tv_stu_sex, R.id.tv_stu_profession, R.id.tv_stu_score,
                R.id.tv_stu_traindate
                });
        listView.setAdapter(simpleCursorAdapter);
    }



}
