package com.example.dragnic.bpums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent= getIntent();
        String username = intent.getStringExtra("username");
        TextView _txt_username = (TextView) findViewById(R.id.txt_username);
        _txt_username.setText(username);
    }
}
