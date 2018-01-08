package com.example.dragnic.bpums;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class newRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");
        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerZahtjevi);

        Button btnNovaO = (Button)findViewById(R.id.btnNovi);


        // Spinner Drop down elements
        List<String> categories = dajZahtjeve();



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        btnNovaO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vrsta = spinner.getSelectedItem().toString();
                Integer i = 1;
                if(vrsta.equals("Potvrda o redovnom studiju- prevoz")){i=2;}
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    Request r = new Request();
                    Integer user = Integer.parseInt(id);
                    r.setUser_id(user);
                    r.setStatus(0);
                    r.setCreated_at(sqlDate);
                    r.setCredential_id(i);

                    //trebao bi dio koda da provjerava jel fkt dodao u bazu
                    r.save();
                    Context context = getApplicationContext();
                    CharSequence text = "Zahtjev poslan!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = null;
                    intent = new Intent(newRequestActivity.this, RequestsActivity.class);
                    intent.putExtra("id", id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    newRequestActivity.this.startActivity(intent);

            }
        });


    }

    public ArrayList<String> dajZahtjeve (){
        ArrayList<String> z = new ArrayList<>();
        DatabseHelper db = new DatabseHelper();
        ResultSet rs= null;
        ArrayList<String> categories = new ArrayList<>();
        try {
            String q = "SELECT * FROM credentials";
            Log.d("Upit", q);
            rs = db.execute(q);
            //rs.beforeFirst();
            while (rs.next()) {
                z.add(rs.getString("title"));

            }

            rs.close();
            Log.d("LISTA velicina", String.valueOf(z.size()));

        }

        catch(Exception e){
            Log.e("Geska", e.getMessage());
        }
        return z;

    }
}
