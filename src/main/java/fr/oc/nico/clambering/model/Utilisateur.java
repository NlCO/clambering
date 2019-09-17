package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer utilisateurId;

    @Column(unique = true)
    private String pseudo;

    private String password;

    @Column(unique = true)
    private String email;
}
