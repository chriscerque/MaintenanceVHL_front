package com.example.maintenancevhl_front.model.entities;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class TypeVehicule {

    @Getter
    @Setter
    public Long id;

    @Getter
    @Setter
    public String codeEmat;

    @Getter
    @Setter
    public String nomType;

    @Getter
    @Setter
    public Set<OperationEntretien> operationEntretiens = new HashSet<>();

    public void ajouterOperationEntretien(OperationEntretien operationEntretien) {
        this.operationEntretiens.add(operationEntretien);
    }
}
