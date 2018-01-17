package com.example.dragnic.bpums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentExamsActivity extends AppCompatActivity {
    ArrayList<Exam> ispitiZaPrijavu = new ArrayList<>();
    ArrayList<Exam> ispitiZaOdjavu = new ArrayList<>();
    ArrayAdapter<Exam> naO = null;
    ArrayAdapter<Exam> naP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        ListView lvR = (ListView) findViewById(R.id.lvStudentExamsZaPrijavu);
        Button btn = (Button) findViewById(R.id.childButton);

        lvR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StudentExamsActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });



        DatabseHelper db = new DatabseHelper();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;



        try {
            String q1 = "SELECT role_id FROM users WHERE id= " + id;
            Log.d("Upit", q1);
            rs = db.execute(q1);
            Integer role = 0;
            if (rs.next()) {
                role = rs.getInt("role_id");
            }

            if (role == 2) {
                String q = "SELECT * FROM exams"; //+id;

                Log.d("Upit", q);
                rs1 = db.execute(q);
                //rs.beforeFirst();
                while (rs1.next()) {
                    Exam e = new Exam();
                    //Log.d("Poruka je", rs.getString("title"));
                    e.setId(rs1.getInt("id"));

                    e.setScheduled(rs1.getDate("scheduled"));
                    e.setCourse_department_id(rs1.getInt("course_department_id"));
                    e.setTitle(rs1.getString("title"));
                    e.setCreated_at(rs1.getDate("created_at"));
                    ispitiZaPrijavu.add(e);
                    Log.d("Ispit id je", String.valueOf(e.getId()));
                }

                rs.close();
                rs1.close();
                naP = new StudentExamsAdapter(this, ispitiZaPrijavu,Integer.parseInt(id));
                lvR.setAdapter(naP);

            }
        }
        catch(Exception e){
                Log.e("Geska", e.getMessage());
            }
    }



}
