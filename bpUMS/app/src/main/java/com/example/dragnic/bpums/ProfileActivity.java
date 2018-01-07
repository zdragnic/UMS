package com.example.dragnic.bpums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.ResultSet;

public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView tv1 = (TextView) findViewById(R.id.name);
        TextView tv2 = (TextView) findViewById(R.id.username);
        TextView tv3 = (TextView) findViewById(R.id.adress);
        TextView tv4 = (TextView) findViewById(R.id.bplace);
        TextView tv5 = (TextView) findViewById(R.id.birth);
        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");

        DatabseHelper db = new DatabseHelper();
        ResultSet rs= null;



        try {
            String q1 = "SELECT * FROM users WHERE id= " + id;
            Log.d("Upit", q1);
            rs = db.execute(q1);

            if (rs.next()) {
                tv1.setText(rs.getString("name") +" "+ rs.getString("lastname"));
                tv2.setText(rs.getString("username"));
                tv3.setText(rs.getString("address"));
                tv4.setText(rs.getString("birthplace"));
                String datum = String.valueOf(rs.getDate("birthDate"));
                tv5.setText(datum);

            }
            rs.close();
        }
        catch(Exception e){
            Log.e("Geska", e.getMessage());
        }
    }
}
