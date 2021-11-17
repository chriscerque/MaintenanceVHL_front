package com.example.maintenancevhl_front.model.services;

import com.example.maintenancevhl_front.model.dtos.DtoVehicule;
import com.example.maintenancevhl_front.model.entities.Vehicule;
import com.example.maintenancevhl_front.model.facades.FacadeVehiculeClientRest;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceVehiculeImpl implements ServiceVehicule, Serializable {

    @Inject
    FacadeVehiculeClientRest facadeVehiculeClientRest;

    @Inject
    ServiceTypeVehicule serviceTypeVehicule;

    @Override
    public Vehicule findOne(Long id) {
        return this.facadeVehiculeClientRest.findById(id).get();
    }

    @Override
    public List<Vehicule> findAll() {
        List<DtoVehicule> lst = this.facadeVehiculeClientRest.list();

        return lst.stream().map(v -> toObject(v)).collect(Collectors.toList());

    }

    @Override
    public Vehicule save(Vehicule vehicule) throws Exception {
        if (vehicule.getId() != null) {
            return this.facadeVehiculeClientRest.update(vehicule);
        } else {
            return this.facadeVehiculeClientRest.create(vehicule);
        }

    }

    @Override
    public void delete(Vehicule vehicule) throws Exception {
        this.facadeVehiculeClientRest.delete(vehicule);
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
