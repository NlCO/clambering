package fr.oc.nico.clambering.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Voie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voieId;

    private String nom;

    @ManyToOne
    @JoinColumn
    private Secteur secteur;

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
}
