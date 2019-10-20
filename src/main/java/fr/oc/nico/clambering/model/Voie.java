package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Getter
@Setter
public class Voie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voieId;

    private String voieLibelle;

    @ManyToOne
    private Secteur secteur;
    @OneToMany(mappedBy = "voie", cascade = CascadeType.ALL)
    private List<Longueur> longueurs = new ArrayList<>();
    @Transient
    private String cotationMin;
    @Transient
    private String cotationMax;
    @Transient
    private Integer hauteur;

    public Voie() {
    }

    public Voie(String voieLibelle) {
        this.voieLibelle = voieLibelle;
    }

    public void addLongueur(Longueur longueur) {
        this.longueurs.add(longueur);
        longueur.setVoie(this);
    }

    public String getCotationMin() {
        return longueurs.stream().min(Comparator.comparing(Longueur::getCotation)).orElseThrow(NoSuchElementException::new).getCotation();
    }

    public void setCotationMin() {
        this.cotationMin = getCotationMin();
    }

    public String getCotationMax() {
        return longueurs.stream().max(Comparator.comparing(Longueur::getCotation)).orElseThrow(NoSuchElementException::new).getCotation();
    }

    public void setCotationMax() {
        this.cotationMax = getCotationMax();
    }

    public Integer getHauteur() {
        return longueurs.stream().map(Longueur::getHauteur).reduce(0, Integer::sum);
    }

    public void setHauteur() {
        this.hauteur = getHauteur();
    }
}
