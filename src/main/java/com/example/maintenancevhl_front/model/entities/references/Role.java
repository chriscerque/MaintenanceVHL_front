package com.example.maintenancevhl_front.model.entities.references;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {

    USER("Utilisateur"),
    ADMIN("Administrateur");

    @Getter
    private String libelle;
}
