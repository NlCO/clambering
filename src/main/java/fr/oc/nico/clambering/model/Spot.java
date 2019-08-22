package fr.oc.nico.clambering.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
public class Spot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spotId;

    private String nom;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String acces;

    private String orientation;

    private String image;

    private Float longitude;

    private Float latitude;

    private boolean tagAmiEscalade;

    @OneToMany(mappedBy = "spot")
    private List<Secteur> secteurs;

    @ManyToOne
    @JoinColumn
    private Region region;

    @Transient
    private String cotationMin;

    @Transient
    private String cotationMax;

    @Transient
    public String getCotationMax() {
        setCotationMax();
        return cotationMax;
    }
    @Transient
    private Float hauteurMin;

    @Transient
    private Float hauteurMax;

    @Transient
    public void setCotationMax() {
        this.cotationMax = secteurs.stream().max(Comparator.comparing(Secteur::getCotationMax)).orElseThrow(NoSuchElementException::new).getCotationMax();;
    }

    @Transient
    public String getCotationMin() {
        setCotationMin();
        return cotationMin;
    }

    @Transient
    public void setCotationMin() {
        this.cotationMin = secteurs.stream().min(Comparator.comparing(Secteur::getCotationMin)).orElseThrow(NoSuchElementException::new).getCotationMin();
    }

    @Transient
    public Float getHauteurMin() {
        setHauteurMin();
        return hauteurMin;
    }

    @Transient
    public void setHauteurMin() {
        this.hauteurMin = secteurs.stream().min(Comparator.comparing(Secteur::getHauteurMin)).orElseThrow(NoSuchElementException::new).getHauteurMin();
    }

    @Transient
    public Float getHauteurMax() {
        setHauteurMax();
        return hauteurMax;
    }

    @Transient
    public void setHauteurMax() {
        this.hauteurMax = secteurs.stream().max(Comparator.comparing(Secteur::getHauteurMax)).orElseThrow(NoSuchElementException::new).getHauteurMax();
    }
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

    public String getAcces() {
        return acces;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
