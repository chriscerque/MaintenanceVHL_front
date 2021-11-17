package com.example.maintenancevhl_front.model.facades;


import com.example.maintenancevhl_front.model.entities.User;
import com.example.maintenancevhl_front.view.security.UserRole;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class FacadeUserImpl implements FacadeUser, Serializable {
//    @Inject
//    User user;

    @Override
    public User connecter(String login, String pwd) {
        if (login.equals("ADMIN") && pwd.equals("ADMIN")) {
            return new User(login, UserRole.ADMIN);
        }
        if (login.equals("USER") && pwd.equals("USER")) {
            return new User(login, UserRole.USER);
        }
        return null;
    }
}
