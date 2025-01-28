package org.project.repository;


import org.springframework.stereotype.Service;

@Service
public class UserModel {

    public UserModel() {
    }

    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private int id;
    private String name;
    private String password;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
