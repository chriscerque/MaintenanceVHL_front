package com.example.maintenancevhl_front.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DtoOperationEntretien {

    public Long id;

    public String nom;

    public String typeOperation;

    public Integer chargeEnHeures;

    public Long periodiciteId;

}
