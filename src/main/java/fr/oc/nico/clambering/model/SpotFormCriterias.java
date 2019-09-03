package fr.oc.nico.clambering.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotFormCriterias {

    private String pays;

    private String region;

    private String orientation;

    private String cotationMin;

    private String cotationMax;

    private Boolean multiSecteurs;
}
