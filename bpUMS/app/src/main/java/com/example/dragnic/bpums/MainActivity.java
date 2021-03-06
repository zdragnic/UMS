package com.example.dragnic.bpums;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    EditText _emailText = null;
    EditText _passwordText = null;
    Button _loginButton = null;
    User currentUser = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         _emailText = (EditText) findViewById (R.id.input_email);
         _passwordText = (EditText) findViewById (R.id.input_password);
         _loginButton = (Button)findViewById(R.id.btn_login);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
        public void login() {
            Log.d("Login activity", "Login");

            if (!validate()) {
                onLoginFailed();
                return;
            }

            _loginButton.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                    R.style.Theme_AppCompat_DayNight_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            String username = _emailText.getText().toString();
            String password = _passwordText.getText().toString();
            Log.d("Uneseni username je", username);
            Log.d("Uneseni password je", password);

            // TODO: Implement your own authentication logic here.
            DatabseHelper db = new DatabseHelper();
            ResultSet rs= null;
            try {
                String q= "SELECT * FROM users WHERE username= '"+username+"' AND password='"+password+"'";
                Log.d("Upit",q);
                rs = db.execute(q);
                if (rs.next()) {
                    Log.d("Username logovanog je", rs.getString("id"));
                    currentUser.setId(rs.getInt("id"));
                    currentUser.setPassword(rs.getString("password"));
                    currentUser.setRole_id(rs.getInt("role_id"));
                    currentUser.setUsername(rs.getString("username"));
                }
                rs.close();
            }
            catch (Exception e){
                Log.e("Geska",e.getMessage());
            }

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }

        public void onLoginSuccess() {
            _loginButton.setEnabled(true);
            Intent intent = null;
            if(currentUser.getRole_id() == 2){
            intent= new Intent(MainActivity.this, StudentActivity.class);
            }
            else if (currentUser.getRole_id()==3){
            intent= new Intent(MainActivity.this, StudentskaActivity.class);
            }
            else if(currentUser.getRole_id()==4){
            intent= new Intent(MainActivity.this, NastavnoActivity.class);
            }
            else {
            intent= new Intent(MainActivity.this, AdminActivity.class);
            }
            String s= String.valueOf(currentUser.getId());
            intent.putExtra("id", s);
            Log.d("User id je", s);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            MainActivity.this.startActivity(intent);

            //finish(); ovo sam maknulaa izadje iz aplikacije ne znam sta radi hahah
        }

        public void onLoginFailed() {
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

            _loginButton.setEnabled(true);
        }

        public boolean validate() {
            boolean valid = true;
            boolean pogresanUsername=true;
            boolean pogresanPass=true;
            String email = _emailText.getText().toString();
            String password = _passwordText.getText().toString();
            DatabseHelper db = new DatabseHelper();
            ResultSet rs= null;
            ResultSet rs1=null;
            try {
                String q = "SELECT * FROM users WHERE username= '" + email + "'";
                rs = db.execute(q);
                String q1 = "SELECT * FROM users WHERE password= '" + password + "'";
                rs1 = db.execute(q1);
                if (rs.next()) {
                    pogresanUsername=false;
                }
                if(rs1.next()){
                    pogresanPass=false;
                }
                rs.close();
                rs1.close();
            }
                catch(Exception e){
                    e.printStackTrace();
                }

            if (email.isEmpty()) {
                _emailText.setError("enter a username");
                valid = false;

            }
            else if(pogresanUsername){ _emailText.setError("wrong username"); valid = false;}
            else {
                _emailText.setError(null);
            }

            if (password.isEmpty() || password.length() < 3 || password.length() > 10) {
                _passwordText.setError("between 3 and 10 alphanumeric characters");
                valid = false;
            }
            else if(pogresanPass){ _passwordText.setError("wrong password"); valid = false;}
            else {
                _passwordText.setError(null);
            }

            return valid;
        }


    }
