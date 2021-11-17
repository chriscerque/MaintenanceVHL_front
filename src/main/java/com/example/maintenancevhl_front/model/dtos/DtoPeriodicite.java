package com.example.maintenancevhl_front.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DtoPeriodicite {

    public Long id;

    public int months;

    public int hours;

    public int km;
}
