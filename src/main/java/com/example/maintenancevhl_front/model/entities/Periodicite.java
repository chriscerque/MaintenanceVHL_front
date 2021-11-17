package com.example.maintenancevhl_front.model.entities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Periodicite {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private int mois;

    @Getter
    @Setter
    private int heure;

    @Getter
    @Setter
    private int km;

}
