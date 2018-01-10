package com.example.dragnic.bpums;

import android.util.Log;

import java.util.Date;

/**
 * Created by Dragnic on 1/9/2018.
 */

public class ExamStudent {
    private int id;
    private int exam_id;
    private int user_id;
    private int status;
    private Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
            q = String.format("INSERT INTO exam_students (exam_id, user_id, status, created_at, updated_at) VALUES ('%d','%d','%d','%s','%s');",
                    this.getExam_id(),this.getUser_id(),this.getStatus(), this.getCreated_at(),this.getCreated_at());
        }
        else{
            q = String.format("UPDATE exam_students SET status = '%d' WHERE id = %d;",
                    this.getStatus(),this.getId());
        }
        Log.d("Upit save", q);
        DatabseHelper db = new DatabseHelper();

        db.execute(q);
        Log.d("Upit save", "Dodao");

    }

    public void delete(){
        String q = String.format("DELETE FROM exam_students WHERE id = %d;",this.getId());
        DatabseHelper db = new DatabseHelper();
        db.execute(q);

    }
}
