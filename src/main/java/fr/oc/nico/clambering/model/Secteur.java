package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
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

    private String nom;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    private Spot spot;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secteur")
    private List<Voie> voies;

    @Transient
    private String cotationMin;

    @Transient
    private String cotationMax;

    @Transient
    private Integer hauteurMin;

    @Transient
    private Integer hauteurMax;

    public String getCotationMax() {
        return voies.stream().max(Comparator.comparing(Voie::getCotationMax)).orElseThrow(NoSuchElementException::new).getCotationMax();
    }

    public void setCotationMax() {
        this.cotationMax = getCotationMax();
    }

    public String getCotationMin() {
        return voies.stream().min(Comparator.comparing(Voie::getCotationMin)).orElseThrow(NoSuchElementException::new).getCotationMin();
    }

    public void setCotationMin() {
        this.cotationMin = getCotationMin();
    }

    public Integer getHauteurMin() {
        return voies.stream().min(Comparator.comparing(Voie::getHauteur)).orElseThrow(NoSuchElementException::new).getHauteur();
    }

    public void setHauteurMin() {
        this.hauteurMin = getHauteurMin();
    }

    public Integer getHauteurMax() {
        return voies.stream().max(Comparator.comparing(Voie::getHauteur)).orElseThrow(NoSuchElementException::new).getHauteur();
    }

    public void setHauteurMax() {
        this.hauteurMax = getHauteurMax();
    }
}
