package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Utilisateur {

    private String pseudo;

    private String password;

    private String email;
}
