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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class StudentSystem extends ListActivity implements View.OnClickListener , AdapterView.OnItemLongClickListener {
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
        student = new Student();
        listView = getListView();

        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        canleButton.setOnClickListener(this);
        selectAllButton.setOnClickListener(this);
        //listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnCreateContextMenuListener(this);
        //registerForContextMenu(getListView());
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
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this); //getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        student = (Student) listView.getTag();
        //final long stu_id = student.getId();
        Intent intent = new Intent();
       // Log.i("id","id"+stu_id);
        switch (item_id) {
            case R.id.delete:
               // deleteStudent(stu_id);
                break;
            case R.id.look:
                // 查看学生信息
                intent.putExtra("student", student);
                intent.setClass(this, ShowActivity.class);
                this.startActivity(intent);
                break;
            case R.id.write:
                // 修改学生信息
                intent.putExtra("student", student);
                intent.setClass(this, AddStudent.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Student student = (Student) adapter.getStudent(view, id);
        listView.setTag(student);
        registerForContextMenu(listView);
        return false;
    }


   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!isDeleteList) {
            student = adapter.getStudentView(view, id);
            Intent intent = new Intent();
            intent.putExtra("student", student);
            intent.setClass(this, ShowActivity.class);
            this.startActivity(intent);
        } else {
            CheckBox box = (CheckBox) view.findViewById(R.id.cb_box);
            box.setChecked(!box.isChecked());
            list.add(id);
        }
    }*/


    private void deleteStudent(final long delete_id) {
        // 利用对话框的形式删除数据
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("学员信息删除")
                .setMessage("确定删除所选记录?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int raws = adapter.deleteStudentById(delete_id);
                        linearLayout.setVisibility(View.GONE);
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
