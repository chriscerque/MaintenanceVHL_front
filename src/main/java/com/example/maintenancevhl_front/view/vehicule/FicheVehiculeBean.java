package com.example.maintenancevhl_front.view.vehicule;

import com.example.maintenancevhl_front.model.entities.TypeVehicule;
import com.example.maintenancevhl_front.model.entities.Vehicule;
import com.example.maintenancevhl_front.model.services.ServiceTypeVehicule;
import com.example.maintenancevhl_front.model.services.ServiceVehicule;
import com.example.maintenancevhl_front.view.jsfUtils.JsfUtils;
import com.example.maintenancevhl_front.view.refs.C_VIEW;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FicheVehiculeBean implements Serializable {

    @Inject
    private ServiceVehicule serviceVehicule;

    @Inject
    private ServiceTypeVehicule serviceTypeVehicule;

    @Getter
    @Setter
    private Vehicule vehicule;

    @Getter
    private List<TypeVehicule> typeVehiculeList;

    @PostConstruct
    public void init() {
        Vehicule v = (Vehicule) JsfUtils.getFromFlashScope(C_VIEW.KEY_VHL_MODIF_FLASH_SCOPE);
        if (v != null) {
            this.vehicule = v;
        }
        this.typeVehiculeList = this.serviceTypeVehicule.findAll();

    }

    public void enregistrer() {
        try {
            this.serviceVehicule.save(this.vehicule);
            JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Véhicule créé");
        } catch (Exception e) {
//            e.printStackTrace();
            JsfUtils.sendMessage(null, FacesMessage.SEVERITY_ERROR, "ca plante !!!!!");
        }

    }
}
