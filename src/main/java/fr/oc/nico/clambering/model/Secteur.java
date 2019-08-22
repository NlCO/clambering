package fr.oc.nico.clambering.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
public class Secteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer secteurId;

    private String nom;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn
    private Spot spot;

    @OneToMany(mappedBy = "secteur")
    private List<Voie> voies;

    @Transient
    private String cotationMin;

    @Transient
    private String cotationMax;

    @Transient
    private Float hauteurMin;

    @Transient
    private Float hauteurMax;

    @Transient
    public String getCotationMax() {
        setCotationMax();
        return cotationMax;
    }

    @Transient
    public void setCotationMax() {
        this.cotationMax = voies.stream().max(Comparator.comparing(Voie::getCotationMax)).orElseThrow(NoSuchElementException::new).getCotationMax();;
    }

    @Transient
    public String getCotationMin() {
        setCotationMin();
        return cotationMin;
    }

    @Transient
    public void setCotationMin() {
        this.cotationMin = voies.stream().min(Comparator.comparing(Voie::getCotationMin)).orElseThrow(NoSuchElementException::new).getCotationMin();
    }

    @Transient
    public Float getHauteurMin() {
        setHauteurMin();
        return hauteurMin;
    }

    @Transient
    public void setHauteurMin() {
        this.hauteurMin = voies.stream().min(Comparator.comparing(Voie::getHauteur)).orElseThrow(NoSuchElementException::new).getHauteur();
    }

    @Transient
    public Float getHauteurMax() {
        setHauteurMax();
        return hauteurMax;
    }

    @Transient
    public void setHauteurMax() {
        this.hauteurMax = voies.stream().max(Comparator.comparing(Voie::getHauteur)).orElseThrow(NoSuchElementException::new).getHauteur();
    }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
