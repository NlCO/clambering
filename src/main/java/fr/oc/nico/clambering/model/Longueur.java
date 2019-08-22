package fr.oc.nico.clambering.model;

import javax.persistence.*;

@Entity
public class Longueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer longueurId;

    @ManyToOne
    @JoinColumn
    private Voie voie;

    private String nom;

    private Float hauteur;

    private String cotation;

    private Integer idRelaiDebut;

    private Integer idRelaiFin;

    private Integer degaine;

    public Integer getLongueurId() {
        return longueurId;
    }

    public void setLongueurId(Integer longueurId) {
        this.longueurId = longueurId;
    }

    public Voie getVoie() {
        return voie;
    }

    public void setVoie(Voie voie) {
        this.voie = voie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getHauteur() {
        return hauteur;
    }

    public void setHauteur(Float hauteur) {
        this.hauteur = hauteur;
    }

    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    public Integer getIdRelaiDebut() {
        return idRelaiDebut;
    }

    public void setIdRelaiDebut(Integer idRelaiDebut) {
        this.idRelaiDebut = idRelaiDebut;
    }

    public Integer getIdRelaiFin() {
        return idRelaiFin;
    }

    public void setIdRelaiFin(Integer idRelaiFin) {
        this.idRelaiFin = idRelaiFin;
    }

    public Integer getDegaine() {
        return degaine;
    }

    public void setDegaine(Integer degaine) {
        this.degaine = degaine;
    }
}
