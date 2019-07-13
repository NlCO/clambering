package fr.oc.nico.clambering.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Spot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spotId;

    private String nom;

    @Column(columnDefinition = "text")
    private String description;

    private String image;

    private Float longitude;

    private Float latitude;

    private boolean tagAmiEscalade;

    @OneToMany(mappedBy = "spot")
    private List<Secteur> secteurs;


    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public boolean isTagAmiEscalade() {
        return tagAmiEscalade;
    }

    public void setTagAmiEscalade(boolean tagAmiEscalade) {
        this.tagAmiEscalade = tagAmiEscalade;
    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }
}
