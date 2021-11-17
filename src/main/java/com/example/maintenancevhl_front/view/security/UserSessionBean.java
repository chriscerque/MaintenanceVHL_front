package com.example.maintenancevhl_front.view.security;

import com.example.maintenancevhl_front.model.entities.User;
import com.example.maintenancevhl_front.model.facades.FacadeUser;
import lombok.Getter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {

    @Getter
    private User user;

    @Inject
    private FacadeUser facadeUser;

    public void login(String login, String password) {
//        if (login.equals("ADMIN") && password.equals("ADMIN")) {
//            this.user = "ADMIN";
//        }
        this.user = facadeUser.connecter(login, password);
    }

    public void logout() throws IOException {
        this.user = null;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/index.xhtml?faces-redirect=true");
    }

    public boolean hasRole(String role) {
        if (user == null) return false;

        if (user.getRole().equals(UserRole.valueOf(role))) return true;

        return false;
    }
}
