package fr.oc.nico.clambering.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Secteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer secteurId;

    private String nom;

    @ManyToOne
    @JoinColumn
    private Spot spot;

    @OneToMany(mappedBy = "secteur")
    private List<Voie> voies;

    public Integer getSecteurId() {
        return secteurId;
    }

    public void setSecteurId(Integer secteurId) {
        this.secteurId = secteurId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public List<Voie> getVoies() {
        return voies;
    }

    public void setVoies(List<Voie> voies) {
        this.voies = voies;
    }
}
