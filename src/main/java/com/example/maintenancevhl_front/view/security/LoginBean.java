package com.example.maintenancevhl_front.view.security;


import com.example.maintenancevhl_front.view.jsfUtils.JsfUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    private UserSessionBean userSessionBean;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    public void valider() throws IOException {
        this.userSessionBean.login(this.login, this.password);
        if (this.userSessionBean.getUser() != null) {
            System.out.println(">>>>>>>>>>>>>>>>>>" + FacesContext.getCurrentInstance().getExternalContext());
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml?faces-redirect=true");
        }
        JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Login ou password incorrect !!!");


    }

}
