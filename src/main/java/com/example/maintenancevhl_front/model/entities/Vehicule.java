package com.example.maintenancevhl_front.model.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Vehicule {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String immat;

    @Getter
    @Setter
    private TypeVehicule typeVehicule;

}
