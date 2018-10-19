package com.mcc.firebaseexample;

public class Model {

   String userId,name,email,image,phone;

    public Model() {
    }

    public Model(String name , String email, String image,String phone) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.phone = phone;
    }
   /* public Model(String user_id, String name, String email,String image,String phone)
    {
        this.userId = user_id;
        this.name = name;
        this.email = email;
        this.image = image;
        this.phone = phone;
    }*/

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
