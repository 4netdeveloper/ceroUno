package com.example.asokah.mobilepresence.Model;

/**
 * Created by asokah on 21/09/2018.
 */

public class User {

    private String email, status;

    public User(){

    }

    public User(String email, String status){

        this.email = email;
        this.status = status;
    }

    public String getEmail(){

        return email;
    }


    public String getStatus() {
        return status;
    }
}
