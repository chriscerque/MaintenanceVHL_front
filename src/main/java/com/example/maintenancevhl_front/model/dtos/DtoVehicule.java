package com.example.maintenancevhl_front.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class DtoVehicule {
    public Long id;
    public String immat;
    public Long typeVehiculeId;
}
