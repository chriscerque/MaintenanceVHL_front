package com.example.maintenancevhl_front.model.facades;


import com.example.maintenancevhl_front.model.entities.User;

public interface FacadeUser {
    User connecter(String login, String pwd);
}
