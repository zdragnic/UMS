package com.example.dragnic.bpums;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dragnic on 1/16/2018.
 */

public class StudentExamsAdapter  extends ArrayAdapter<Exam> {


    private final Context context;
    private List<Exam> values;
    private int id;





    public StudentExamsAdapter(Context context, ArrayList<Exam> values,int id) {
        super(context, 0, values);
        this.context = context;
        this.values = values;
        this.id =id;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder mainViewholder = null;
        Exam e = values.get(position);

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listelement2,null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.childTextView);
            viewHolder.setTitle(e.getTitle());
            viewHolder.button = (Button) convertView.findViewById(R.id.childButton);
            convertView.setTag(viewHolder);
        }
        mainViewholder = (ViewHolder) convertView.getTag();
        mainViewholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //kada klikne na odredjeno dugme dohvati item i u bazi spoji studenta i taj ispit
                Exam e = values.get(position);
                ExamStudent es = new ExamStudent();
                //provjera jel vec prijavio ako jeste poruku da ispise da je vec prijavljen ispit
                DatabseHelper db = new DatabseHelper();
                ResultSet rs = null;
                boolean status=false;
                try{
                  String q = "SELECT status FROM exam_students WHERE exam_id= "+e.getId()+" AND user_id= "+id;
                  rs=db.execute(q);
                  if(rs.next()){
                      status= rs.getBoolean("status");
                  }
                }
                catch(Exception ex){
                    Log.e("Geska", ex.getMessage());
                }
                if (status == false) {
                    es.setStatus(1);
                    es.setExam_id(e.getId());
                    es.setUser_id(id);
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    es.setCreated_at(sqlDate);
                    es.save();
                    Toast.makeText(getContext(), "Prijavljen ispit " + e.getTitle(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "Vec ste prijaviti ovaj ispit " + e.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public class ViewHolder {

        TextView title;
        Button button;

        public void setTitle(String s){title.setText(s);}

    }
}


