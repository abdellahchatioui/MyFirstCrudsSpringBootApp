package com.crud_app.demo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {
    private int id ;
    @NotBlank
    @Size(min = 4)
    private String name;

    @NotBlank
    @Size(min = 5)
    @Email
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
