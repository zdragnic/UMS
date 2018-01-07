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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


        // Spinner Drop down elements
        List<String> categories = dajKurseve(db, id);
        Log.d("DB",db._mensagem);
        Log.d("DB", String.valueOf(db._status));


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        btnNovaO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kurs = spinner.getSelectedItem().toString();
               Integer c_id = dajKurs(kurs,db);
                String naslov = etnaslov.getText().toString(); //editTextNaslov
                String tekst = etporuka.getText().toString(); //editTextTekst
                boolean ispravno = validate();

                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                try{
                    ResultSet r = db.execute("SELECT * FROM courses");
                    Log.d("DB1",db._mensagem);
                    Log.d("DB1", String.valueOf(db._status));
                    r.moveToInsertRow();
                    r.updateString("title", naslov);
                    r.updateString("text", tekst);
                    r.updateInt("user_id", Integer.getInteger(id));
                    r.updateInt("course_id", c_id);
                    r.updateDate("created_at",sqlDate);
                    r.updateDate("updated_at",sqlDate);

                    r.insertRow();
                    r.moveToCurrentRow();

                    Context context = getApplicationContext();
                    CharSequence text = "Uspjesno objavljenot!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    r.close();

                }

                catch(Exception e){
                    Log.e("Geska", e.getMessage());
                }

            }
        });


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

    public Integer dajKurs (String id, DatabseHelper db) {
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
    }

