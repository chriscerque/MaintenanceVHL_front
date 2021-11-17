package com.example.maintenancevhl_front.model.services.impl;

import com.example.maintenancevhl_front.model.dtos.DtoVehicule;
import com.example.maintenancevhl_front.model.entities.Vehicule;
import com.example.maintenancevhl_front.model.facades.impl.FacadeVehiculeClientRestImpl;
import com.example.maintenancevhl_front.model.services.ServiceTypeVehicule;
import com.example.maintenancevhl_front.model.services.ServiceVehicule;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceVehiculeImpl implements ServiceVehicule, Serializable {

    @Inject
    FacadeVehiculeClientRestImpl facadeVehiculeClientRest;

    @Inject
    ServiceTypeVehicule serviceTypeVehicule;

    @Override
    public Vehicule findOne(Long id) {
        return toObject(this.facadeVehiculeClientRest.findById(id).get());
    }

    @Override
    public List<Vehicule> findAll() {
        List<DtoVehicule> lst = this.facadeVehiculeClientRest.list();

        System.out.println("ServiceVehiculeImpl / findAll");
        for (DtoVehicule dtoVehicule : lst) {
            System.out.println("dtoVehicule : " + dtoVehicule);
        }

        return lst.stream().map(v -> toObject(v)).collect(Collectors.toList());

    }

    @Override
    public Vehicule save(Vehicule vehicule) throws Exception {
        if (vehicule.getId() != null) {
            return toObject(this.facadeVehiculeClientRest.update(toDto(vehicule)));
        } else {
            return toObject(this.facadeVehiculeClientRest.create(toDto(vehicule)));
        }

    }

    @Override
    public void delete(Vehicule vehicule) throws Exception {
        this.facadeVehiculeClientRest.delete(toDto(vehicule));
    }

    private DtoVehicule toDto(Vehicule vehicule) {
        DtoVehicule dtoVehicule = new DtoVehicule();
        dtoVehicule.id = vehicule.getId();
        dtoVehicule.immat = vehicule.getImmat();
        dtoVehicule.typeVehiculeId = vehicule.getTypeVehicule().getId();
        return dtoVehicule;
    }

    private Vehicule toObject(DtoVehicule dtoVehicule) {
        Vehicule vehicule = new Vehicule();
        vehicule.setId(dtoVehicule.id);
        vehicule.setImmat(dtoVehicule.immat);
        vehicule.setTypeVehicule(this.serviceTypeVehicule.findOne(dtoVehicule.typeVehiculeId));
        return vehicule;

    }

}
