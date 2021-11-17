package com.example.maintenancevhl_front.model.facades;

import com.example.maintenancevhl_front.model.dtos.DtoPeriodicite;

import java.util.List;
import java.util.Optional;

public interface FacadePeriodiciteClientRest {
    List<DtoPeriodicite> list();

    Optional<DtoPeriodicite> findById(Long id);

    DtoPeriodicite create(DtoPeriodicite dtoPeriodicite) throws Exception;

    DtoPeriodicite update(DtoPeriodicite dtoPeriodicite) throws Exception;

    void delete(DtoPeriodicite dtoPeriodicite) throws Exception;
}
