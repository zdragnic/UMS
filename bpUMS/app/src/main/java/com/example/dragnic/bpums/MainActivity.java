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
        Connection konekcija= null;
        Boolean issucces= false;
        try{
            konekcija = Konekcija("bp", "bp2017");
            String query = "SELECT * FROM users where id=1";
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                issucces= true;
                Log.d("Username sa idem 1 je:", rs.getString("username"));
            }
        }
        catch (Exception e){
            issucces=false;
            Log.e("NEMA UPITA:", e.getMessage());
        }
    }


    public Connection Konekcija (String user, String password){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String connectionURL = null;
        try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            connectionURL = "jdbc:postgresql://10.0.2.2:5432/UniversityManagementSystem_development";
            //connectionURL = "jdbc:oracle:thin:@80.65.65.66:1521:ETFLAB";

            connection = DriverManager.getConnection(connectionURL,"bp","bp2017");
        }
        catch (SQLException se){
            Log.e("Greska 1", se.getMessage());

        }
        catch (ClassNotFoundException ce){
            Log.e("Greska 2", ce.getMessage());
        }
        catch(Exception e){
        Log.e("Greska 3", e.getMessage());
        }
        return connection;
    }
}
