package com.example.maintenancevhl_front.model.facades.impl;


import com.example.maintenancevhl_front.model.dtos.DtoTypeVehicule;
import com.example.maintenancevhl_front.model.entities.TypeVehicule;
import com.example.maintenancevhl_front.model.entities.Vehicule;
import com.example.maintenancevhl_front.model.facades.FacadeTypeVehiculeClientRest;

import javax.enterprise.context.Dependent;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Dependent
public class FacadeTypeVehiculeClientRestImpl implements Serializable, FacadeTypeVehiculeClientRest {

    public static final String REQUEST_URL_TYPE_VEHICULE = "http://localhost:8080/MaintenanceVHL-1.0-SNAPSHOT/api/typeVehicule";

    private Client client = ClientBuilder.newClient();

    @Override
    public List<DtoTypeVehicule> list() {
        List<DtoTypeVehicule> list = this.client.target(REQUEST_URL_TYPE_VEHICULE).request(MediaType.APPLICATION_JSON).get(new GenericType<List<DtoTypeVehicule>>() {
        });
        return list;
    }

    @Override
    public Optional<DtoTypeVehicule> findById(Long id) {
        try {
            DtoTypeVehicule dtoTypeVehicule = this.client.target(REQUEST_URL_TYPE_VEHICULE).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(DtoTypeVehicule.class);
            return Optional.ofNullable(dtoTypeVehicule);
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Vehicule create(TypeVehicule typeVehicule) throws Exception {

        System.out.println("Client Rest create : ");
        System.out.println(">>>>>>>>>>>>>>>>>vehicule : " + typeVehicule);
        Response resp = this.client.target(REQUEST_URL_TYPE_VEHICULE).request(MediaType.APPLICATION_JSON).post(Entity.entity(typeVehicule, MediaType.APPLICATION_JSON));
        if (resp.getStatus() == Response.Status.NOT_ACCEPTABLE.getStatusCode()) {
            Map<String, List<String>> map = resp.readEntity(new GenericType<Map<String, List<String>>>() {
            });
            StringBuilder sb = new StringBuilder();
            map.forEach((k, v) -> {
                sb.append(k + ": ");
                v.forEach(vv -> sb.append(vv + ", "));
            });
            throw new Exception(sb.toString());
        }

        if (resp.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            throw new Exception("Erreur lors de l'enregistrement du type de véhicule");
        }

        return resp.readEntity(Vehicule.class);
    }

    @Override
    public TypeVehicule update(TypeVehicule typeVehicule) throws Exception {
        Response resp = this.client.target(REQUEST_URL_TYPE_VEHICULE).path(String.valueOf(typeVehicule.getId())).request(MediaType.APPLICATION_JSON).put(Entity.entity(typeVehicule, MediaType.APPLICATION_JSON));
        if (resp.getStatus() == Response.Status.NOT_ACCEPTABLE.getStatusCode()) {
            Map<String, List<String>> map = resp.readEntity(new GenericType<Map<String, List<String>>>() {
            });
            StringBuilder sb = new StringBuilder();
            map.forEach((k, v) -> {
                sb.append(k + ": ");
                v.forEach(vv -> sb.append(vv + ", "));
            });
            throw new Exception(sb.toString());
        }

        if (resp.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            throw new Exception("Erreur lors de l'enregistrement du type de véhicule");
        }

        return resp.readEntity(TypeVehicule.class);
    }


    @Override
    public void delete(TypeVehicule typeVehicule) throws Exception {
        int status = this.client.target(REQUEST_URL_TYPE_VEHICULE).path(String.valueOf(typeVehicule.getId())).request(MediaType.APPLICATION_JSON).delete().getStatus();
        if (status != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new Exception("Erreur lors de la suppression du type de véhicule");
        }
    }
}
