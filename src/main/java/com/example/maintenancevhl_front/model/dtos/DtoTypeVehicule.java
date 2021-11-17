package com.example.maintenancevhl_front.model.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DtoTypeVehicule {

    public Long id;

    public String codeEmat;

    public String nomType;

    public Long[] operations;
}
