package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SpotEditForm {

    private Integer spotId;

    private String region = "";

    private String spotNom = "";

    private String spotDescription = "";

    private String acces = "";

    private String orientation = "";

    private String image;

    private Float longitude;

    private Float latitude;

    private List<SecteurEditForm> secteurs = new ArrayList<>();

    public void addSecteur(SecteurEditForm secteur) {
        this.secteurs.add(secteur);
    }

    public SpotEditForm() {
    }

    public SpotEditForm(Integer spotId, String region, String spotNom, String spotDescription, String acces, String orientation, Float longitude, Float latitude, String image ) {
        this.spotId = spotId;
        this.region = region;
        this.spotNom = spotNom;
        this.spotDescription = spotDescription;
        this.acces = acces;
        this.orientation = orientation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.image = image;
    }
}
