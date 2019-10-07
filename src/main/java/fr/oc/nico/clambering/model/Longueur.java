package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Longueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer longueurId;

    @ManyToOne
    @JoinColumn
    private Voie voie;

    private String nom;

    private Integer hauteur;

    private String cotation;

    private Integer idRelaiDebut;

    private Integer idRelaiFin;

    private Integer degaine;

}
