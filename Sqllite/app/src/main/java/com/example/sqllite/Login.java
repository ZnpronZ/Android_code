package com.example.sqllite;

public class Login {
    private  int Id;
    private String Username;
    private String Password;
    public Login(int id, String username, String password) {
        Id = id;
        Username = username;
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
