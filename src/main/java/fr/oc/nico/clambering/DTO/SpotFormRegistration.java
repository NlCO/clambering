package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotFormRegistration extends SecteurFormRegistration {

    private String region;

    private String spotNom;

    private String spotDescription;

    private String acces;

    private String orientation;

    //private String image;

    private Float longitude;

    private Float latitude;

}
