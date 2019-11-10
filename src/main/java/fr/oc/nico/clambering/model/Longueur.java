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
    private Voie voie;

    private String longueurLibelle;

    private Integer hauteur;

    private String cotation;

    private Integer idRelaiDebut;

    private Integer idRelaiFin;

    private Integer degaine;

    public Longueur() {
    }

    public Longueur(String longueurLibelle, Integer hauteur, String cotation, Integer degaine) {
        this.longueurLibelle = longueurLibelle;
        this.hauteur = hauteur;
        this.cotation = cotation;
        this.degaine = degaine;
    }

    public Longueur(Integer longueurId,  String longueurLibelle, Integer hauteur, String cotation, Integer degaine) {
        this.longueurId = longueurId;
        this.longueurLibelle = longueurLibelle;
        this.hauteur = hauteur;
        this.cotation = cotation;
        this.degaine = degaine;
    }
}
