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
public class StudentSystem extends ListActivity implements View.OnClickListener ,AdapterView.OnItemClickListener {
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
        linearLayout = findViewById(R.id.showLiner);
        student = new Student();
        relativeLayout = findViewById(R.id.RelativeLayout);
        list = new ArrayList<Long>();

        listView = getListView();
        adapter = new StudentAdapter(new StudentDBHelper(this));
        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
//        listView.setOnItemClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnCreateContextMenuListener(this);

        //final List<Student> list = new ArrayList<>();

        //listView = findViewById(R.id.lv);
       /* listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(StudentSystem.this).inflate(R.layout.student_list_item,parent,false);
                TextView tv = convertView.findViewById(R.id.tv_stu_id);
                tv.setText(list.get(position).g);
                return null;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
       // registerForContextMenu(getListView());*/
    }
    public void onClick(View v){
        if(v==addButton){
            startActivity(new Intent(StudentSystem.this,AddStudent.class));
            load();
        }else if(v == searchButton){
            startActivity(new Intent(StudentSystem.this,StudentSearch.class));
            load();
        }else if (v== selectButton){

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        load();

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this); //getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    //@Override
    /*public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        student = (Student) listView.getTag();
        final long studentid = student.getId();

        Intent intent = new Intent();
        switch (item_id) {

            // 删除
            case R.id.delete:
                deleteStudent(studentid);
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
    }*/





    private void deleteStudent(final long Id) {
        // 利用对话框的形式删除数据
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("学员信息删除")
                .setMessage("确定删除所选记录?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int raws = adapter.deleteStudentById(Id);
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



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        deleteStudent(id);

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
}
