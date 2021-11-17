package com.example.maintenancevhl_front.model.services;

import com.example.maintenancevhl_front.model.entities.Vehicule;

import java.util.List;

public interface ServiceVehicule {

    public Vehicule findOne(Long id);

    public List<Vehicule> findAll();

    public Vehicule save(Vehicule vehicule) throws Exception;

    public void delete(Vehicule vehicule) throws Exception;
}
