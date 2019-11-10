package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LongueurFormRegistration {

    private Integer longueurId;

    private String longueurNom = "";

    private Integer hauteur;

    private String cotation = "";

    private Integer idRelaiDebut = 0;

    private Integer idRelaiFin = 1;

    private Integer degaine;

    public LongueurFormRegistration() {
    }

    public LongueurFormRegistration(Integer longueurId, String longueurNom, Integer hauteur, String cotation, Integer degaine, Integer idRelaiDebut, Integer idRelaiFin) {
        this.longueurId = longueurId;
        this.longueurNom = longueurNom;
        this.hauteur = hauteur;
        this.cotation = cotation;
        this.idRelaiDebut = idRelaiDebut;
        this.idRelaiFin = idRelaiFin;
        this.degaine = degaine;
    }
}
