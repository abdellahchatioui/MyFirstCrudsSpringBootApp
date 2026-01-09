package com.crud_app.demo;

public class User {
    private int id ;
    private String name ;
    private String email;

    //Constructor
    public User(int id,String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    //Getters And Setters
    //Id
    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}
    //Name
    public String getName(){return this.name;};
    public void setName(String name){this.name = name;};
    //Email
    public String getEmail(){return this.email;};
    public void setEmail(String email){this.email = email;};

}
