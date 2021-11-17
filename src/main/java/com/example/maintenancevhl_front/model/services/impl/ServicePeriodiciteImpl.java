package com.example.maintenancevhl_front.model.services.impl;

import com.example.maintenancevhl_front.model.dtos.DtoPeriodicite;
import com.example.maintenancevhl_front.model.entities.Periodicite;
import com.example.maintenancevhl_front.model.facades.FacadePeriodiciteClientRest;
import com.example.maintenancevhl_front.model.services.ServicePeriodicite;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ServicePeriodiciteImpl implements ServicePeriodicite, Serializable {


    @Inject
    FacadePeriodiciteClientRest facadePeriodiciteClientRest;


    @Override
    public Periodicite findOne(Long id) {
        return toObject(this.facadePeriodiciteClientRest.findById(id).get());
    }

    @Override
    public List<Periodicite> findAll() {
        return this.facadePeriodiciteClientRest.list().stream().map(typeVehicule -> toObject(typeVehicule)).collect(Collectors.toList());
    }

    @Override
    public Periodicite save(Periodicite periodicite) {
        return null;
    }

    @Override
    public void delete(Periodicite periodicite) {

    }

    private DtoPeriodicite toDto(Periodicite periodicite) {
        DtoPeriodicite dtoPeriodicite = new DtoPeriodicite();
        dtoPeriodicite.id = periodicite.getId();
        dtoPeriodicite.months = periodicite.getMois();
        dtoPeriodicite.hours = periodicite.getHeure();
        dtoPeriodicite.km = periodicite.getKm();
        return dtoPeriodicite;
    }

    private Periodicite toObject(DtoPeriodicite dtoPeriodicite) {
        Periodicite periodicite = new Periodicite();
        periodicite.setId(dtoPeriodicite.id);
        periodicite.setMois(dtoPeriodicite.months);
        periodicite.setHeure(dtoPeriodicite.hours);
        periodicite.setKm(dtoPeriodicite.km);
        return periodicite;

    }
}
