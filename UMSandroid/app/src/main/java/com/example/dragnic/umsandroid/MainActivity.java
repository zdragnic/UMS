package com.example.dragnic.umsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    public MainActivity() throws SQLException {

    }
    private final String TAG = MainActivity.class.getSimpleName();
    public Connection konekcija () throws SQLException {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url;
            url = "jdbc:postgresql://127.0.0.1/UniversityManagementSystem_development";
            con= DriverManager.getConnection(url, "bp", "bp2017");
            System.out.println("Connection Successfull");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connection c= null;
        try {
            c = konekcija();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql ="SELECT name FROM roles where id=1";

        Statement st = null;
        try {
            st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs= st.executeQuery(sql);
                while(rs.next()){

            String name = rs.getString("name");

                    Log.d(TAG, name);

                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
