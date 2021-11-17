package com.example.maintenancevhl_front.model.services.impl;

import com.example.maintenancevhl_front.model.dtos.DtoOperationEntretien;
import com.example.maintenancevhl_front.model.entities.OperationEntretien;
import com.example.maintenancevhl_front.model.entities.references.TypeOperation;
import com.example.maintenancevhl_front.model.facades.FacadeOperationEntretienRest;
import com.example.maintenancevhl_front.model.services.ServiceOperationEntretien;
import com.example.maintenancevhl_front.model.services.ServicePeriodicite;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceOperationEntretienImpl implements ServiceOperationEntretien, Serializable {

    @Inject
    FacadeOperationEntretienRest facadeOperationEntretienRest;

    @Inject
    ServicePeriodicite servicePeriodicite;

    @Override
    public OperationEntretien findOne(Long id) {
        return toObject(this.facadeOperationEntretienRest.findById(id).get());
    }

    @Override
    public List<OperationEntretien> findAll() {
        return this.facadeOperationEntretienRest.list().stream().map(operationEntretien -> toObject(operationEntretien)).collect(Collectors.toList());

    }

    @Override
    public OperationEntretien save(OperationEntretien operationEntretien) throws Exception {
        if (operationEntretien.getId() != null) {
            return toObject(this.facadeOperationEntretienRest.update(toDto(operationEntretien)));
        } else {
            return toObject(this.facadeOperationEntretienRest.create(toDto(operationEntretien)));
        }

    }

    @Override
    public void delete(OperationEntretien operationEntretien) throws Exception {
        this.facadeOperationEntretienRest.delete(toDto(operationEntretien));
    }

    private DtoOperationEntretien toDto(OperationEntretien operationEntretien) {
        DtoOperationEntretien dtoOperationEntretien = new DtoOperationEntretien();
        dtoOperationEntretien.id = operationEntretien.getId();
        dtoOperationEntretien.nom = operationEntretien.getNom();
        dtoOperationEntretien.typeOperation = operationEntretien.getTypeOperation().name();
        dtoOperationEntretien.chargeEnHeures = operationEntretien.getChargeEnHeures();
        dtoOperationEntretien.periodiciteId = operationEntretien.getPeriodicite().getId();
        return dtoOperationEntretien;
    }

    private OperationEntretien toObject(DtoOperationEntretien dtoOperationEntretien) {
        System.out.println("--------------////////////////---------------");
        System.out.println(dtoOperationEntretien);
        OperationEntretien operationEntretien = new OperationEntretien();
        operationEntretien.setId(dtoOperationEntretien.id);
        operationEntretien.setNom(dtoOperationEntretien.nom);
        operationEntretien.setTypeOperation(TypeOperation.valueOf(dtoOperationEntretien.typeOperation));
        operationEntretien.setPeriodicite(this.servicePeriodicite.findOne(dtoOperationEntretien.periodiciteId));
        return operationEntretien;


    }

}
