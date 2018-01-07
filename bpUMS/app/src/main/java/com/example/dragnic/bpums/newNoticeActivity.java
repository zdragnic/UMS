package com.example.dragnic.bpums;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class newNoticeActivity extends AppCompatActivity {
    DatabseHelper db = new DatabseHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);

        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");
        final EditText etnaslov = (EditText) findViewById(R.id.editTextNaslov);
        final EditText etporuka = (EditText) findViewById(R.id.editTextTekst);



        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        Button btnNovaO = (Button)findViewById(R.id.buttonNova);
        btnNovaO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kurs = spinner.getSelectedItem().toString();
                Integer c_id = dajKurs(kurs);
                String naslov = etnaslov.getText().toString(); //editTextNaslov
                String tekst = etporuka.getText().toString(); //editTextTekst
                boolean ispravno = validate();

                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                try{

                    ResultSet rs = db.select("SELECT * FROM courses");
                    rs.moveToInsertRow();
                    rs.updateString("title", naslov);
                    rs.updateString("text", tekst);
                    rs.updateInt("user_id", Integer.getInteger(id));
                    rs.updateInt("course_id", c_id);
                    rs.updateDate("created_at",sqlDate);
                    rs.updateDate("updated_at",sqlDate);

                    rs.insertRow();
                    rs.moveToCurrentRow();
                    rs.close();

                    Context context = getApplicationContext();
                    CharSequence text = "Uspjesno objavljenot!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                catch(Exception e){
                    Log.e("Geska", e.getMessage());
                }

            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();


        ResultSet rs= null;
        try {
        String q = "SELECT * FROM courses WHERE responsible = "+id;
        Log.d("Upit", q);
        rs = db.execute(q);
        //rs.beforeFirst();
        while (rs.next()) {
         categories.add(rs.getString("title"));

        }
        rs.close();
        Log.d("LISTA velicina", String.valueOf(categories.size()));

    }

        catch(Exception e){
                Log.e("Geska", e.getMessage());
                }



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);




    }


    public boolean validate() {
        boolean valid = true;

        final EditText etnaslov = (EditText) findViewById(R.id.editTextNaslov);
        final EditText etporuka = (EditText) findViewById(R.id.editTextTekst);
        String naslov = etnaslov.getText().toString(); //editTextNaslov
        String tekst = etporuka.getText().toString(); //editTextTekst


        if (naslov.isEmpty()) {
            etnaslov.setError("Unesite naslov");
            valid = false;
        }

        else {
            etnaslov.setError(null);
        }

        if (tekst.isEmpty() || tekst.length() < 3 || tekst.length() > 300) {
            etporuka.setError("Unesite poruku");
            valid = false;
        }

        else {
            etporuka.setError(null);
        }

        return valid;
    }

    public Integer dajKurs (String id) {
        DatabseHelper db = new DatabseHelper();
        ResultSet rs1 = null;
        Integer c_id = 0;

        try {
            String q1 = "SELECT * FROM courses WHERE title = '" + id + "'";

            Log.d("Upit", q1);
            rs1 = db.select(q1);
            if (rs1.next()) {
                c_id = rs1.getInt("id");
                Log.d("ID", String.valueOf(c_id));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return c_id;
    }
    }

