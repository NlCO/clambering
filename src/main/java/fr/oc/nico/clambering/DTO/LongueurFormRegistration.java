package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LongueurFormRegistration {

    private String longueurNom;

    private Integer hauteur;

    private String cotation;

    private Integer idRelaiDebut = 0;

    private Integer idRelaiFin = 1;

    private Integer degaine;

}
