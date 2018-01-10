package com.example.dragnic.bpums;

import android.util.Log;

import java.util.Date;

/**
 * Created by Dragnic on 1/9/2018.
 */

public class Exam {
    private int id;
    private String title;
    private Date scheduled;
    private int course_department_id;
    private Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getScheduled() {
        return scheduled;
    }

    public void setScheduled(Date scheduled) {
        this.scheduled = scheduled;
    }

    public int getCourse_department_id() {
        return course_department_id;
    }

    public void setCourse_department_id(int course_department_id) {
        this.course_department_id = course_department_id;
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
            q = String.format("INSERT INTO exams (title, scheduled, course_department_id, created_at, updated_at) VALUES ('%s','%s','%d','%s','%s');",
                    this.getTitle(),this.getScheduled(),this.getCourse_department_id(), this.getCreated_at(),this.getCreated_at());
        }
        Log.d("Upit save", q);
        DatabseHelper db = new DatabseHelper();

        db.execute(q);
        Log.d("Upit save", "Dodao");

    }

    public void delete(){
        String q = String.format("DELETE FROM exams WHERE id = %d;",this.getId());
        DatabseHelper db = new DatabseHelper();
        db.execute(q);

    }
}
