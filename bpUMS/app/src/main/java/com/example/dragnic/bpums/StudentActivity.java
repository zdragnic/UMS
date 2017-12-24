package com.example.dragnic.bpums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent intent= getIntent();
        String username = intent.getStringExtra("username");
        View v= (View) findViewById(R.id.Logout);

        //TextView _txt_username = (TextView) findViewById(R.id.txt_username);
        //Button _logoutButton = (Button)findViewById(R.id.btn_logout);
       // _txt_username.setText(username);

       v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    public  void logout(){
     // back button will stay at LoginActivity(MainActivity) since the activity stack is cleared
     Intent intent  = new Intent(this, MainActivity.class);
     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(false);
    }
}
