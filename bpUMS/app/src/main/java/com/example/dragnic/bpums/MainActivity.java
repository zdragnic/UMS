package com.example.dragnic.bpums;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabseHelper db = new DatabseHelper();
        ResultSet rs = null;
        try {
            rs = db.execute("SELECT * FROM users where id=1");
            if (rs.next()) {
                Log.d("Username sa idem 1 je:", rs.getString("username"));
            }
        }
        catch (Exception e){
            Log.d("Geska: ",e.getMessage());
            }
        }

    }
