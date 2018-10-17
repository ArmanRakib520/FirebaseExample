package com.mcc.firebaseexample;


import android.widget.EditText;

public class Model {

   String name;
   String email;

    public Model(String name , String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
