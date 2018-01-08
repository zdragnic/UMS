package com.example.dragnic.bpums;

import android.util.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.xml.transform.Result;

/**
 * Created by Dragnic on 1/6/2018.
 */

public class Notice {
    private int id;
    private String text;
    private String title;
    private int user_id;
    private int course_id;
    private Date created_at;

    public Notice(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void save(){
        String q = "";
        if (this.getId() == 0){
            q = String.format("INSERT INTO notices (title, text, user_id, course_id, created_at, updated_at) VALUES ('%s','%s','%d', '%d','%s','%s');",
                    this.getTitle(),this.getText(),this.getUser_id(), this.getCourse_id(), this.getCreated_at(),this.getCreated_at());
        }
        Log.d("Upit save", q);
        DatabseHelper db = new DatabseHelper();

        db.execute(q);
        Log.d("Upit save", "Dodao");

    }

    public void delete(){
        String comando = String.format("DELETE FROM notices WHERE id = %d;",this.getId());
        DatabseHelper db = new DatabseHelper();
        db.execute(comando);

    }
}
