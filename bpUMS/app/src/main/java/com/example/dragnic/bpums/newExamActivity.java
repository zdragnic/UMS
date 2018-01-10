package com.example.dragnic.bpums;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class newExamActivity extends AppCompatActivity {
    DatabseHelper db = new DatabseHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exam);

        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");


        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        Button btnNovaO = (Button)findViewById(R.id.btnZakazi);
        final DatePicker dp = (DatePicker) findViewById(R.id.datePicker3);
        final EditText etnaslov = (EditText) findViewById(R.id.editTextTitle);

        List<String> categories = dajKurseve(db, id);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        btnNovaO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kurs = spinner.getSelectedItem().toString();
                Integer c_id = dajCourseDepartmentID(kurs,db);
                String naslov = etnaslov.getText().toString(); //editTextNaslov

                boolean ispravno = validate();
                if(ispravno) {
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    int   day  = dp.getDayOfMonth();
                    int   month= dp.getMonth();
                    int   year = dp.getYear() - 1900;
                    java.util.Date dpDate = new java.util.Date(year,month,day);
                    java.sql.Date sqldpDate = new java.sql.Date(dpDate.getTime());

                    Exam e = new Exam();
                    e.setCourse_department_id(c_id);
                    e.setTitle(naslov);
                    e.setCreated_at(sqlDate);
                    e.setScheduled(sqldpDate);
                    //trebao bi dio koda da provjerava jel fkt dodao u bazu
                    e.save();
                    Context context = getApplicationContext();
                    CharSequence text = "Objavljen ispit!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = null;
                    intent = new Intent(newExamActivity.this, NastavnoActivity.class);
                    intent.putExtra("id", id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    newExamActivity.this.startActivity(intent);
                }

            }
        });


    }
    public boolean validate() {
        boolean valid = true;

        final EditText etnaslov = (EditText) findViewById(R.id.editTextTitle);

        String naslov = etnaslov.getText().toString(); //editTextNaslov


        if (naslov.isEmpty()) {
            etnaslov.setError("Unesite naslov");
            valid = false;
        }

        else {
            etnaslov.setError(null);
        }

        return valid;
    }
    public ArrayList<String> dajKurseve (DatabseHelper db, String id){
        ResultSet rs= null;
        ArrayList<String> categories = new ArrayList<>();
        try {
            String q = "SELECT * FROM courses WHERE responsible = "+id;
            Log.d("Upit", q);
            rs = db.execute(q);
            //rs.beforeFirst();
            while (rs.next()) {
                categories.add(rs.getString("title"));

            }
            Log.d("DB2",db._mensagem);
            Log.d("DB2", String.valueOf(db._status));
            rs.close();
            Log.d("LISTA velicina", String.valueOf(categories.size()));

        }

        catch(Exception e){
            Log.e("Geska", e.getMessage());
        }

        return categories;
    }
    public Integer dajCourseDepartmentID (String id, DatabseHelper db) {
        ResultSet rs1 = null;
        Integer c_id = 0;
        ResultSet rs2 = null;
        Integer cdId=0;

        try {
            String q1 = "SELECT * FROM courses WHERE title = '" + id + "'";

            Log.d("Upit", q1);
            rs1 = db.select(q1);
            if (rs1.next()) {
                c_id = rs1.getInt("id");
                Log.d("ID", String.valueOf(c_id));
            }

            String q2=  "SELECT * FROM course_departments WHERE course_id = '" + c_id + "'";
            Log.d("Upit", q2);
            rs2 = db.select(q2);
            if (rs2.next()) {
                cdId = rs2.getInt("id");
                Log.d("ID", String.valueOf(cdId));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return cdId;
    }
}

