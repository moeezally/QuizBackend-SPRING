package org.moeez.models;

public class user {

    private int id;
    private String name;
    private String email;
    private String password;
    private String type;

    public user(int id,String name, String email, String password, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public user(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }
    public user() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  "Mr/Mrs. " + name +
                ", your email is '" + email +
                ", and you are a " + type+"." ;
    }
}
