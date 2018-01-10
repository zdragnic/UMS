package com.example.dragnic.bpums;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dragnic on 1/8/2018.
 */

public class Request {
    private int id;
    private int credential_id;
    private int status;
    private int user_id;
    private Date created_at;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredential_id() {
        return credential_id;
    }

    public void setCredential_id(int credential_id) {
        this.credential_id = credential_id;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
            q = String.format("INSERT INTO requests (status,user_id, credential_id, created_at, updated_at) VALUES ('%d','%d','%d','%s','%s');",
                    this.isStatus(),this.getUser_id(),this.getCredential_id(), this.getCreated_at(),this.getCreated_at());
        }
        else{
            q = String.format("UPDATE requests SET status = '%d' WHERE id = %d;",
                    1,this.getId());
        }
        Log.d("Upit save", q);
        DatabseHelper db = new DatabseHelper();

        db.execute(q);
        Log.d("Upit save", "Dodao");

    }

    public void delete(){
        String q = String.format("DELETE FROM requests WHERE id = %d;",this.getId());
        DatabseHelper db = new DatabseHelper();
        db.execute(q);

    }

}
