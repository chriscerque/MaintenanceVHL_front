package com.example.maintenancevhl_front.model.facades.impl;


import com.example.maintenancevhl_front.model.dtos.DtoVehicule;
import com.example.maintenancevhl_front.model.facades.FacadeVehiculeClientRest;

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
public class FacadeVehiculeClientRestImpl implements FacadeVehiculeClientRest, Serializable {

    public static final String REQUEST_URL_VEHICULE = "http://localhost:8080/MaintenanceVHL-1.0-SNAPSHOT/api/vehicule";

    private Client client = ClientBuilder.newClient();

    @Override
    public List<DtoVehicule> list() {
        List<DtoVehicule> list = this.client.target(REQUEST_URL_VEHICULE).request(MediaType.APPLICATION_JSON).get(new GenericType<List<DtoVehicule>>() {
        });
        //TODO SOUT
        for (DtoVehicule dtoVehicule : list) {
            System.out.println("############# vehicule : " + dtoVehicule);

        }


        return list;
    }

    @Override
    public Optional<DtoVehicule> findById(Long id) {
        try {
            DtoVehicule v = this.client.target(REQUEST_URL_VEHICULE).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(DtoVehicule.class);
            return Optional.ofNullable(v);
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public DtoVehicule create(DtoVehicule dtoVehicule) throws Exception {

        System.out.println("Client Rest create : ");
        System.out.println(">>>>>>>>>>>>>>>>>vehicule : " + dtoVehicule);
        Response resp = this.client.target(REQUEST_URL_VEHICULE).request(MediaType.APPLICATION_JSON).post(Entity.entity(dtoVehicule, MediaType.APPLICATION_JSON));
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
            throw new Exception("Erreur lors de l'enregistrement du véhicule");
        }

        return resp.readEntity(DtoVehicule.class);
    }

    @Override
    public DtoVehicule update(DtoVehicule dtoVehicule) throws Exception {
        Response resp = this.client.target(REQUEST_URL_VEHICULE).path(String.valueOf(dtoVehicule.getId())).request(MediaType.APPLICATION_JSON).put(Entity.entity(dtoVehicule, MediaType.APPLICATION_JSON));
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
            throw new Exception("Erreur lors de l'enregistrement du véhicule");
        }

        return resp.readEntity(DtoVehicule.class);
    }


    @Override
    public void delete(DtoVehicule dtoVehicule) throws Exception {
        int status = this.client.target(REQUEST_URL_VEHICULE).path(String.valueOf(dtoVehicule.getId())).request(MediaType.APPLICATION_JSON).delete().getStatus();
        if (status != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new Exception("Erreur lors de la suppression du véhicule");
        }
    }


}
