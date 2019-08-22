package fr.oc.nico.clambering.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
public class Voie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voieId;

    private String nom;

    @ManyToOne
    @JoinColumn
    private Secteur secteur;

    @OneToMany(mappedBy = "voie")
    private List<Longueur> longueurs;

    @Transient
    private String cotationMin;

    @Transient
    private String cotationMax;

    @Transient
    private Float hauteur;

    public List<Longueur> getLongueurs() {
        return longueurs;
    }

    public void setLongueurs(List<Longueur> longueurs) {
        this.longueurs = longueurs;
    }

    public Integer getVoieId() {
        return voieId;
    }

    public void setVoieId(Integer voieId) {
        this.voieId = voieId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    @Transient
    public String getCotationMin() {
        setCotationMin();
        return cotationMin;
    }

    @Transient
    public void setCotationMin() {
        this.cotationMin = longueurs.stream().min(Comparator.comparing(Longueur::getCotation)).orElseThrow(NoSuchElementException::new).getCotation();
    }

    @Transient
    public String getCotationMax() {
        setCotationMax();
        return cotationMax;
    }

    @Transient
    public void setCotationMax() {
        this.cotationMax = longueurs.stream().max(Comparator.comparing(Longueur::getCotation)).orElseThrow(NoSuchElementException::new).getCotation();
    }

    @Transient
    public Float getHauteur() {
        setHauteur();
        return hauteur;
    }

    @Transient
    public void setHauteur() {
        this.hauteur = longueurs.stream().map(Longueur::getHauteur).reduce(0.0f, (a, b) -> a + b);
    }
}
