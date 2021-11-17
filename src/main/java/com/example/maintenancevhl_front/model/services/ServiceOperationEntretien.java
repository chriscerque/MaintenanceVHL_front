package com.example.maintenancevhl_front.model.services;

import com.example.maintenancevhl_front.model.entities.OperationEntretien;

import java.util.List;

public interface ServiceOperationEntretien {

    public OperationEntretien findOne(Long id);

    public List<OperationEntretien> findAll();

    public OperationEntretien save(OperationEntretien operationEntretien) throws Exception;

    public void delete(OperationEntretien operationEntretien) throws Exception;
}
