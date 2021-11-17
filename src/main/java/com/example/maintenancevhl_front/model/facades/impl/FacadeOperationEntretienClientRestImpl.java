package com.example.maintenancevhl_front.model.facades.impl;


import com.example.maintenancevhl_front.model.dtos.DtoOperationEntretien;
import com.example.maintenancevhl_front.model.facades.FacadeOperationEntretienRest;

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
public class FacadeOperationEntretienClientRestImpl implements FacadeOperationEntretienRest, Serializable {

    public static final String REQUEST_URL_OPERATION_ENTRETIEN = "http://localhost:8080/MaintenanceVHL-1.0-SNAPSHOT/api/operationEntretien";

    private Client client = ClientBuilder.newClient();

    @Override
    public List<DtoOperationEntretien> list() {
        List<DtoOperationEntretien> list = this.client.target(REQUEST_URL_OPERATION_ENTRETIEN).request(MediaType.APPLICATION_JSON).get(new GenericType<List<DtoOperationEntretien>>() {
        });
        //TODO SOUT
        for (DtoOperationEntretien dtoVehicule : list) {
            System.out.println("############# vehicule : " + dtoVehicule);

        }


        return list;
    }

    @Override
    public Optional<DtoOperationEntretien> findById(Long id) {
        try {
            DtoOperationEntretien v = this.client.target(REQUEST_URL_OPERATION_ENTRETIEN).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(DtoOperationEntretien.class);
            return Optional.ofNullable(v);
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public DtoOperationEntretien create(DtoOperationEntretien dtoOperationEntretien) throws Exception {

        System.out.println("Client Rest create : ");
        System.out.println(">>>>>>>>>>>>>>>>>vehicule : " + dtoOperationEntretien);
        Response resp = this.client.target(REQUEST_URL_OPERATION_ENTRETIEN).request(MediaType.APPLICATION_JSON).post(Entity.entity(dtoOperationEntretien, MediaType.APPLICATION_JSON));
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

        return resp.readEntity(DtoOperationEntretien.class);
    }

    @Override
    public DtoOperationEntretien update(DtoOperationEntretien dtoOperationEntretien) throws Exception {
        Response resp = this.client.target(REQUEST_URL_OPERATION_ENTRETIEN).path(String.valueOf(dtoOperationEntretien.getId())).request(MediaType.APPLICATION_JSON).put(Entity.entity(dtoOperationEntretien, MediaType.APPLICATION_JSON));
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

        return resp.readEntity(DtoOperationEntretien.class);
    }


    @Override
    public void delete(DtoOperationEntretien dtoOperationEntretien) throws Exception {
        int status = this.client.target(REQUEST_URL_OPERATION_ENTRETIEN).path(String.valueOf(dtoOperationEntretien.getId())).request(MediaType.APPLICATION_JSON).delete().getStatus();
        if (status != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new Exception("Erreur lors de la suppression du véhicule");
        }
    }


}
