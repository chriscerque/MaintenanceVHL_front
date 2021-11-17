package com.example.maintenancevhl_front.model.facades;

import com.example.maintenancevhl_front.model.dtos.DtoTypeVehicule;
import com.example.maintenancevhl_front.model.entities.TypeVehicule;
import com.example.maintenancevhl_front.model.entities.Vehicule;

import java.util.List;
import java.util.Optional;

public interface FacadeTypeVehiculeClientRest {
    List<DtoTypeVehicule> list();

    Optional<DtoTypeVehicule> findById(Long id);

    Vehicule create(TypeVehicule typeVehicule) throws Exception;

    TypeVehicule update(TypeVehicule typeVehicule) throws Exception;

    void delete(TypeVehicule typeVehicule) throws Exception;
}
