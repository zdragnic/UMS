package com.example.dragnic.bpums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NastavnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nastavno);

        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");
        View v=  (View)  findViewById(R.id.Logout);
        View vo= (View) findViewById(R.id.Obavijesti);
        View vc= (View) findViewById(R.id.courses);
        View vp= (View) findViewById(R.id.myprofile);
        View ve= (View) findViewById(R.id.exams);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        vo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= null;
                intent= new Intent(NastavnoActivity.this, NoticesActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NastavnoActivity.this.startActivity(intent);
            }
        });

        vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= null;
                intent= new Intent(NastavnoActivity.this, CoursesActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NastavnoActivity.this.startActivity(intent);
            }
        });

        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= null;
                intent= new Intent(NastavnoActivity.this, ProfileActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NastavnoActivity.this.startActivity(intent);
            }
        });

        ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= null;
                intent= new Intent(NastavnoActivity.this, newExamActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NastavnoActivity.this.startActivity(intent);
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


