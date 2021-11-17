package com.example.maintenancevhl_front.model.entities;

import com.example.maintenancevhl_front.model.entities.references.TypeOperation;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class OperationEntretien {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private Integer chargeEnHeures;

    @Getter
    @Setter
    private Periodicite periodicite;

    @Getter
    @Setter
    private TypeOperation typeOperation;

}
