package com.example.dragnic.bpums;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RequestsActivity extends AppCompatActivity {
    ArrayList<Request> zahtjevi = new ArrayList<>();
    ArrayAdapter<Request> na = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        Intent intent= getIntent();
        final String id = intent.getStringExtra("id");

        ListView lvR = (ListView) findViewById(R.id.lvRequests);
        Button btnNova = (Button) findViewById(R.id.btnNoviRequest);

        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= null;
                intent= new Intent(RequestsActivity.this, newRequestActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                RequestsActivity.this.startActivity(intent);
            }
        });

        DatabseHelper db = new DatabseHelper();
        ResultSet rs= null;
        ResultSet rs1=null;


        try {
            String q1 = "SELECT role_id FROM users WHERE id= " + id;
            Log.d("Upit", q1);
            rs1 = db.execute(q1);
            Integer role = 0;
            if (rs1.next()) {
                role = rs1.getInt("role_id");
            }
            //studentska
            if (role == 3) {

                //brisanje

                lvR.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                   int position, long arg3) {

                        final Request r = na.getItem(position);
                        Log.d("Brisem zahtjev id", String.valueOf(r.getId()));
                        AlertDialog.Builder builder = new AlertDialog.Builder(RequestsActivity.this);

                        builder.setTitle("Zahtjev obradjen");
                        builder.setMessage("Da li ste sigurni?");

                        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                r.save();
                                na.notifyDataSetChanged();

                                dialog.dismiss();
                                //refresh aktivnosti
                                finish();
                                startActivity(getIntent());
                            }
                        });

                        builder.setNegativeButton("NE", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                        return false;
                    }

                });
                String q = "SELECT * FROM requests"; //+id;
                Log.d("Upit", q);
                rs = db.execute(q);
                //rs.beforeFirst();
                while (rs.next()) {
                    Request r = new Request();
                    //Log.d("Poruka je", rs.getString("title"));
                    r.setId(rs.getInt("id"));

                    r.setStatus(rs.getInt("status"));
                    r.setUser_id(rs.getInt("user_id"));
                    r.setCredential_id(rs.getInt("credential_id"));
                    r.setCreated_at(rs.getDate("created_at"));
                    zahtjevi.add(r);
                    Log.d("Zahtjev id je", String.valueOf(r.getId()));


                }
                rs.close();
                rs1.close();

                Log.d("LISTA velicina", String.valueOf(zahtjevi.size()));
                ArrayAdapter<Request> na = new RequestsAdapter(this, zahtjevi);
                lvR.setAdapter(na);



            //student
            } else {

                //brisanje

                lvR.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                   int position, long arg3) {

                        final Request r = na.getItem(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(RequestsActivity.this);

                        builder.setTitle("Potvrdite brisanje");
                        builder.setMessage("Da li ste sigurni?");

                        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                r.delete();
                                na.notifyDataSetChanged();

                                dialog.dismiss();
                                //refresh aktivnosti
                                finish();
                                startActivity(getIntent());
                            }
                        });

                        builder.setNegativeButton("NE", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                        return false;
                    }

                });

                String q = "SELECT * FROM requests r WHERE EXISTS (SELECT * FROM users n WHERE "+id+" = r.user_id)";
                Log.d("Upit", q);
                rs = db.execute(q);
                //rs.beforeFirst();
                while (rs.next()) {
                    Request r = new Request();
                    //Log.d("Poruka je", rs.getString("title"));
                    r.setId(rs.getInt("id"));
                    r.setStatus(rs.getInt("status"));
                    r.setUser_id(rs.getInt("user_id"));
                    r.setCredential_id(rs.getInt("credential_id"));
                    r.setCreated_at(rs.getDate("created_at"));
                   // if(rs.getInt("user_id")== Integer.parseInt(id)) {
                        zahtjevi.add(r);
                        Log.d("Zahtjev id je", String.valueOf(r.getId()));
                   // }
                    }
                btnNova.setVisibility(View.VISIBLE);

            }
                rs.close();
            na = new RequestsAdapter(this, zahtjevi);
            lvR.setAdapter(na);
            }

        catch(Exception e){
            Log.e("Geska", e.getMessage());
        }


    }
}
