package com.example.maintenancevhl_front.view.vehicule;

import com.example.maintenancevhl_front.model.entities.Vehicule;
import com.example.maintenancevhl_front.model.services.ServiceVehicule;
import com.example.maintenancevhl_front.view.jsfUtils.JsfUtils;
import com.example.maintenancevhl_front.view.refs.C_VIEW;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListVehiculeBean implements Serializable {

    @Inject
    private ServiceVehicule serviceVehicule;

    @Getter
    @Setter
    private List<Vehicule> vehicules;

    @PostConstruct
    public void init() {
        this.vehicules = this.serviceVehicule.findAll();
        this.vehicules.forEach(System.out::println);
//        System.out.println(this.facadeVehiculeClientRest.findById(200L).isPresent());
//
//
//        Vehicule v = new Vehicule();
//        v.setImmat("AA123CCC");
//        v.setTypeVehiculeId(1L);
//        try {
//            System.out.println("////////////////////////////////////////////");
//            System.out.println("Vehicule v2 = this.facadeVehiculeClientRest.create(v);");
//            Vehicule v2 = this.facadeVehiculeClientRest.create(v);
//            System.out.println("v2 : " + v2);
//
////            v2.setImmat("AA999CC");
////            System.out.println("////////////////////////////////////////////");
////            System.out.println("Vehicule v2 = this.facadeVehiculeClientRest.create(v);");
////            Vehicule v3 = this.facadeVehiculeClientRest.update(v2);
////            System.out.println("v3 : " + v3);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    public void supprimer(Vehicule v) {
        try {
            serviceVehicule.delete(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier(Vehicule v) {
        JsfUtils.putInFlashScope(C_VIEW.KEY_VHL_MODIF_FLASH_SCOPE, v);
    }
}
