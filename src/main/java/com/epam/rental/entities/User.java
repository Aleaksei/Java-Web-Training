package com.epam.rental.entities;

import com.epam.rental.entities.enums.UserRole;

public class User implements Identifiable {

    private Long id;
    private String login;
    private String password;
    private UserRole role;

    public User(){
    }
    public User(Long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public Long getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
