package com.example.maintenancevhl_front.model.services;

import com.example.maintenancevhl_front.model.dtos.DtoTypeVehicule;
import com.example.maintenancevhl_front.model.entities.TypeVehicule;
import com.example.maintenancevhl_front.model.facades.FacadeTypeVehiculeClientRest;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceTypeVehiculeImpl implements ServiceTypeVehicule, Serializable {


    @Inject
    FacadeTypeVehiculeClientRest facadeTypeVehiculeClientRest;


    @Override
    public TypeVehicule findOne(Long id) {
        return toObject(this.facadeTypeVehiculeClientRest.findById(id).get());
    }

    @Override
    public List<TypeVehicule> findAll() {
        return this.facadeTypeVehiculeClientRest.list().stream().map(typeVehicule -> toObject(typeVehicule)).collect(Collectors.toList());
    }

    @Override
    public TypeVehicule save(TypeVehicule typeVehicule) {
        return null;
    }

    @Override
    public void delete(TypeVehicule typeVehicule) {

    }

    private DtoTypeVehicule toDto(TypeVehicule typeVehicule) {
        DtoTypeVehicule dtoTypeVehicule = new DtoTypeVehicule();
        dtoTypeVehicule.id = typeVehicule.getId();
        dtoTypeVehicule.codeEmat = typeVehicule.getCodeEmat();
        dtoTypeVehicule.nomType = typeVehicule.getNomType();
//        dtoTypeVehicule.operationEntretiens = typeVehicule.getOperationEntretiens().stream().map(o -> o.getId().toString()).reduce(":", (a, b) -> a.concat(b));

        dtoTypeVehicule.operations = typeVehicule.getOperationEntretiens().stream().map(o -> o.getId()).collect(Collectors.toList()).toArray(Long[]::new);
        //TODO SOUT
        System.out.println("dtoTypeVehicule.operationEntretiens : " + dtoTypeVehicule.operationEntretiens);
        return dtoTypeVehicule;
    }

    //TODO retirer try catch et mettre businessexception en ejbexception
    private TypeVehicule toObject(DtoTypeVehicule dtoTypeVehicule) {
        TypeVehicule typeVehicule = new TypeVehicule();
        typeVehicule.setId(dtoTypeVehicule.id);
        typeVehicule.setCodeEmat(dtoTypeVehicule.codeEmat);
        typeVehicule.setNomType(dtoTypeVehicule.nomType);
//        Arrays.stream(dtoTypeVehicule.operationEntretiens.split(":")).forEach(o -> typeVehicule.ajouterOperationEntretien(this.facadeOperationEntretien.findOne(Long.valueOf(o)).get()));
        Arrays.stream(dtoTypeVehicule.operations).forEach(o -> {
            typeVehicule.ajouterOperationEntretien(this.facadeOperationEntretien.findOne(o).get());
        });
        return typeVehicule;

    }
}
