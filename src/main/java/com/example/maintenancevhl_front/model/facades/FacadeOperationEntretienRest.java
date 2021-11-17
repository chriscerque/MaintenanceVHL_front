package com.example.maintenancevhl_front.model.facades;

import com.example.maintenancevhl_front.model.dtos.DtoOperationEntretien;

import java.util.List;
import java.util.Optional;

public interface FacadeOperationEntretienRest {
    List<DtoOperationEntretien> list();

    Optional<DtoOperationEntretien> findById(Long id);

    DtoOperationEntretien create(DtoOperationEntretien dtoOperationEntretien) throws Exception;

    DtoOperationEntretien update(DtoOperationEntretien dtoOperationEntretien) throws Exception;

    void delete(DtoOperationEntretien dtoOperationEntretien) throws Exception;
}
