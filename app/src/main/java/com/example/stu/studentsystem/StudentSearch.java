package com.example.stu.studentsystem;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class StudentSearch extends AppCompatActivity implements View.OnClickListener {
    EditText nameText;
    LinearLayout layout;
    Button button,reButton,returnButton;
    ListView listView;
    StudentAdapter adapter;
    private SimpleCursorAdapter findadapter;

    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        nameText = (EditText) findViewById(R.id.et_srarch);
        layout=(LinearLayout) findViewById(R.id.linersearch);
        button = (Button) findViewById(R.id.bn_sure);
        reButton = (Button) findViewById(R.id.bn_return);
        listView = (ListView) findViewById(R.id.searchListView);
        returnButton = (Button) findViewById(R.id.return_id);
        adapter = new StudentAdapter(new StudentDBHelper(this));


        reButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
        button.setOnClickListener(this);

    }

    public void onClick(View v){
        if (v == button) {
            reButton.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            nameText.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            String name = nameText.getText().toString();
            cursor = adapter.findStudent(name);
            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "没有所查学员信息！", Toast.LENGTH_SHORT).show();
            } else
                //如果有所查询的信息，则将查询结果显示出来
                findadapter = new SimpleCursorAdapter(this, R.layout.student_find_list_item,
                        cursor, new String[] {
                        Table.StudentColumns.ID,
                        Table.StudentColumns.NAME,
                        Table.StudentColumns.GRADE,
                        Table.StudentColumns.SEX,
                        Table.StudentColumns.PROFESSION,
                        Table.StudentColumns.SCORE
                },
                        new int[] {
                                R.id.tv_stu_id,
                                R.id.tv_stu_name,
                                R.id.tv_stu_grade,
                                R.id.tv_stu_sex,
                                R.id.tv_stu_profession,
                                R.id.tv_stu_score
                        });
            listView.setAdapter(findadapter);
        }else if(v==reButton|v==returnButton){
            finish();
        }
    }
}


