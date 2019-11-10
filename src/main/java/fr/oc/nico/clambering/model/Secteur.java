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
public class Secteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer secteurId;

    private String secteurLibelle;

    @Column(columnDefinition = "text")
    private String secteurDescription;

    @ManyToOne
    private Spot spot;

    @OneToMany(mappedBy = "secteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voie> voies = new ArrayList<>();
    @Transient
    private String cotationMin;
    @Transient
    private String cotationMax;
    @Transient
    private Integer hauteurMin;
    @Transient
    private Integer hauteurMax;

    public Secteur(String secteurLibelle, String secteurDescription) {
        this.secteurLibelle = secteurLibelle;
        this.secteurDescription = secteurDescription;
    }

    public Secteur(Integer secteurId, String secteurLibelle, String secteurDescription) {
        this.secteurId = secteurId;
        this.secteurLibelle = secteurLibelle;
        this.secteurDescription = secteurDescription;
    }

    public Secteur() {
    }

    public void addVoie(Voie voie) {
        this.voies.add(voie);
        voie.setSecteur(this);
    }

    String getCotationMax() {
        return voies.stream().max(Comparator.comparing(Voie::getCotationMax)).orElseThrow(NoSuchElementException::new).getCotationMax();
    }

    public void setCotationMax() {
        this.cotationMax = getCotationMax();
    }

    String getCotationMin() {
        return voies.stream().min(Comparator.comparing(Voie::getCotationMin)).orElseThrow(NoSuchElementException::new).getCotationMin();
    }

    public void setCotationMin() {
        this.cotationMin = getCotationMin();
    }

    Integer getHauteurMin() {
        return voies.stream().min(Comparator.comparing(Voie::getHauteur)).orElseThrow(NoSuchElementException::new).getHauteur();
    }

    public void setHauteurMin() {
        this.hauteurMin = getHauteurMin();
    }

    Integer getHauteurMax() {
        return voies.stream().max(Comparator.comparing(Voie::getHauteur)).orElseThrow(NoSuchElementException::new).getHauteur();
    }

    public void setHauteurMax() {
        this.hauteurMax = getHauteurMax();
    }
}
