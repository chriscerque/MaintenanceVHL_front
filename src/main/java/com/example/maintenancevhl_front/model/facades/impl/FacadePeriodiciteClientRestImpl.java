package com.example.maintenancevhl_front.model.facades.impl;


import com.example.maintenancevhl_front.model.dtos.DtoPeriodicite;
import com.example.maintenancevhl_front.model.facades.FacadePeriodiciteClientRest;

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
public class FacadePeriodiciteClientRestImpl implements FacadePeriodiciteClientRest, Serializable {

    public static final String REQUEST_URL_PERIODICITE = "http://localhost:8080/MaintenanceVHL-1.0-SNAPSHOT/api/periodicite";

    private Client client = ClientBuilder.newClient();

    @Override
    public List<DtoPeriodicite> list() {
        List<DtoPeriodicite> list = this.client.target(REQUEST_URL_PERIODICITE).request(MediaType.APPLICATION_JSON).get(new GenericType<List<DtoPeriodicite>>() {
        });
        //TODO SOUT
        for (DtoPeriodicite dtoPeriodicite : list) {
            System.out.println("############# vehicule : " + dtoPeriodicite);

        }


        return list;
    }

    @Override
    public Optional<DtoPeriodicite> findById(Long id) {
        try {
            DtoPeriodicite v = this.client.target(REQUEST_URL_PERIODICITE).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(DtoPeriodicite.class);
            return Optional.ofNullable(v);
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public DtoPeriodicite create(DtoPeriodicite dtoPeriodicite) throws Exception {

        System.out.println("Client Rest create : ");
        System.out.println(">>>>>>>>>>>>>>>>>vehicule : " + dtoPeriodicite);
        Response resp = this.client.target(REQUEST_URL_PERIODICITE).request(MediaType.APPLICATION_JSON).post(Entity.entity(dtoPeriodicite, MediaType.APPLICATION_JSON));
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

        return resp.readEntity(DtoPeriodicite.class);
    }

    @Override
    public DtoPeriodicite update(DtoPeriodicite dtoPeriodicite) throws Exception {
        Response resp = this.client.target(REQUEST_URL_PERIODICITE).path(String.valueOf(dtoPeriodicite.getId())).request(MediaType.APPLICATION_JSON).put(Entity.entity(dtoPeriodicite, MediaType.APPLICATION_JSON));
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

        return resp.readEntity(DtoPeriodicite.class);
    }


    @Override
    public void delete(DtoPeriodicite dtoPeriodicite) throws Exception {
        int status = this.client.target(REQUEST_URL_PERIODICITE).path(String.valueOf(dtoPeriodicite.getId())).request(MediaType.APPLICATION_JSON).delete().getStatus();
        if (status != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new Exception("Erreur lors de la suppression du véhicule");
        }
    }


}
