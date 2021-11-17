package com.example.maintenancevhl_front.model.facades;

import com.example.maintenancevhl_front.model.dtos.DtoVehicule;

import java.util.List;
import java.util.Optional;

public interface FacadeVehiculeClientRest {
    List<DtoVehicule> list();

    Optional<DtoVehicule> findById(Long id);

    DtoVehicule create(DtoVehicule dtoVehicule) throws Exception;

    DtoVehicule update(DtoVehicule dtoVehicule) throws Exception;

    void delete(DtoVehicule dtoVehicule) throws Exception;
}
