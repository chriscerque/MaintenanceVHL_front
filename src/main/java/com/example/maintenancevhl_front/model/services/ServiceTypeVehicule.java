package com.example.maintenancevhl_front.model.services;

import com.example.maintenancevhl_front.model.entities.TypeVehicule;

import java.util.List;

public interface ServiceTypeVehicule {
    public TypeVehicule findOne(Long id);

    public List<TypeVehicule> findAll();

    public TypeVehicule save(TypeVehicule typeVehicule);

    public void delete(TypeVehicule typeVehicule);
}
