package com.example.dragnic.bpums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class NoticesActivity extends AppCompatActivity {

ArrayList<Notice> poruke = new ArrayList<>();
//dobaviti logovanog usera id njegov

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);
        ListView lv = (ListView) findViewById(R.id.listview);
        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");
        Button btnNova = (Button) findViewById(R.id.butnNovaObavijest);
        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= null;
                intent= new Intent(NoticesActivity.this, newNoticeActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NoticesActivity.this.startActivity(intent);
            }
        });

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
                String q = "SELECT * FROM notices"; //+id;
                Log.d("Upit", q);
                rs = db.execute(q);
                //rs.beforeFirst();
                while (rs.next()) {
                    Notice poruka = new Notice();
                    //Log.d("Poruka je", rs.getString("title"));
                    poruka.setId(rs.getInt("id"));
                    poruka.setTitle(rs.getString("title"));
                    poruka.setText(rs.getString("text"));
                    poruka.setCreated_at(rs.getDate("created_at"));
                    if (String.valueOf(rs.getInt("user_id")).equals(id)) {
                        poruke.add(poruka);
                        Log.d("Poruka je", poruka.getTitle());
                    }

                }
                rs.close();

                Log.d("LISTA velicina", String.valueOf(poruke.size()));
                ArrayAdapter<Notice> na = new NoticesAdapter(this, poruke);
                lv.setAdapter(na);
                btnNova.setVisibility(View.VISIBLE);



            } else {
                String q = "SELECT * FROM notices"; //+id;
                Log.d("Upit", q);
                rs = db.execute(q);
                //rs.beforeFirst();
                while (rs.next()) {
                    Notice poruka = new Notice();
                    //Log.d("Poruka je", rs.getString("title"));
                    poruka.setId(rs.getInt("id"));
                    poruka.setTitle(rs.getString("title"));
                    poruka.setText(rs.getString("text"));
                    poruka.setCreated_at(rs.getDate("created_at"));
                    if (slusaKurs(id, rs.getInt("course_id"))) {
                        poruke.add(poruka);
                        Log.d("Poruka je", poruka.getTitle());
                    }

                }
                rs.close();
                Log.d("LISTA velicina", String.valueOf(poruke.size()));
                ArrayAdapter<Notice> na = new NoticesAdapter(this, poruke);
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

            String q1 = "SELECT * FROM course_departments WHERE course_id= "+course_id;
            Log.d("Upit",q1);

            rs = db.execute(q1);

            if (rs.next()) {
            idDep= rs.getInt("id");
            }
            String q= "SELECT * FROM user_enrollments WHERE user_id= "+user_id+" AND course_department_id= "+idDep;
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
