package com.example.dragnic.bpums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    ArrayList<Course> kursevi = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        ListView lv = (ListView) findViewById(R.id.lvcourses);
        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");

        DatabseHelper db = new DatabseHelper();
        ResultSet rs= null;
        ResultSet rs1=null;


        try {
            String q1 = "SELECT * FROM users WHERE id= " + id;
            Log.d("Upit", q1);
            rs1 = db.execute(q1);
            Integer role = 0;
            if (rs1.next()) {
                role = rs1.getInt("role_id");
            }
            if (role == 4) {
                String q = "SELECT * FROM courses c WHERE EXISTS (SELECT * FROM users n WHERE "+id+" = c.responsible)"; //+id;
                Log.d("Upit", q);
                rs = db.execute(q);
                //rs.beforeFirst();
                while (rs.next()) {
                    Course c = new Course();
                    //Log.d("Poruka je", rs.getString("title"));
                    c.setId(rs.getInt("id"));
                    c.setTitle(rs.getString("title"));
                    c.setCode(rs.getString("code"));
                    c.setResponsible(rs.getInt("responsible"));
                    c.setCreated_at(rs.getDate("created_at"));
                   // if (String.valueOf(rs.getInt("responsible")).equals(id)) {
                        kursevi.add(c);
                        Log.d("Kurs je", c.getTitle());
                   // }

                }
                rs.close();
                rs1.close();

                Log.d("LISTA velicina", String.valueOf(kursevi.size()));
                ArrayAdapter<Course> na = new CoursesAdapter(this, kursevi);
                lv.setAdapter(na);



            } else {
                String q = "SELECT * FROM courses"; //+id;
                Log.d("Upit", q);
                rs = db.execute(q);
                //rs.beforeFirst();
                while (rs.next()) {
                    Course c = new Course();
                    //Log.d("Poruka je", rs.getString("title"));
                    c.setId(rs.getInt("id"));
                    c.setTitle(rs.getString("title"));
                    c.setCode(rs.getString("code"));
                    c.setResponsible(rs.getInt("responsible"));
                    c.setCreated_at(rs.getDate("created_at"));
                    if (slusaKurs(id, rs.getInt("id"))) {
                        kursevi.add(c);
                        Log.d("Kurs je", c.getTitle());
                    }

                }
                rs.close();
                Log.d("LISTA velicina", String.valueOf(kursevi.size()));
                ArrayAdapter<Course> na = new CoursesAdapter(this, kursevi);
                lv.setAdapter(na);
            }
        }
        catch(Exception e){
            Log.e("Geska", e.getMessage());
        }

    }

    public boolean slusaKurs (String user_id, Integer course_id){
        boolean slusa = false;
        DatabseHelper db = new DatabseHelper();
        ResultSet rs= null;
        ResultSet rs1= null;
        int idDep= -1;
        try {

            String q1 = "SELECT id FROM course_departments WHERE course_id= "+course_id;
            Log.d("Upit",q1);

            rs = db.execute(q1);

            if (rs.next()) {
                idDep= rs.getInt("id");
            }
            String q= "SELECT id FROM user_enrollments WHERE user_id= "+user_id+" AND course_department_id= "+idDep;
            Log.d("Upit",q);
            rs1 = db.execute(q);
            if (rs1.next()) {
                slusa = true;
            }
            rs.close();
            rs1.close();
            Log.d("SLUSA", String.valueOf(slusa));
        }
        catch (Exception e){
            Log.e("Geska",e.getMessage());
        }

        return slusa;

    }

}
