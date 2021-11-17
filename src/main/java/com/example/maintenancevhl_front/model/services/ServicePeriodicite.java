package com.example.maintenancevhl_front.model.services;

import com.example.maintenancevhl_front.model.entities.Periodicite;

import java.util.List;

public interface ServicePeriodicite {

    public Periodicite findOne(Long id);

    public List<Periodicite> findAll();

    public Periodicite save(Periodicite periodicite) throws Exception;

    public void delete(Periodicite periodicite) throws Exception;
}
