package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spot")
    private List<Secteur> secteurs;

    @ManyToOne
    @JoinColumn
    private Region region;

    @Transient
    private String cotationMin;

    @Transient
    private String cotationMax;

    @Transient
    private Integer hauteurMin;

    @Transient
    private Integer hauteurMax;

    public String getCotationMax() {
        return secteurs.stream().max(Comparator.comparing(Secteur::getCotationMax)).orElseThrow(NoSuchElementException::new).getCotationMax();
    }

    public void setCotationMax() {
        this.cotationMax = getCotationMax();
    }

    public String getCotationMin() {
        return secteurs.stream().min(Comparator.comparing(Secteur::getCotationMin)).orElseThrow(NoSuchElementException::new).getCotationMin();
    }

    public void setCotationMin() {
        this.cotationMin = getCotationMin();
    }

    public Integer getHauteurMin() {
        return secteurs.stream().min(Comparator.comparing(Secteur::getHauteurMin)).orElseThrow(NoSuchElementException::new).getHauteurMin();
    }

    public void setHauteurMin() {
        this.hauteurMin = getHauteurMin();
    }

    public Integer getHauteurMax() {
        return secteurs.stream().max(Comparator.comparing(Secteur::getHauteurMax)).orElseThrow(NoSuchElementException::new).getHauteurMax();
    }

    public void setHauteurMax() {
        this.hauteurMax = getHauteurMax();
    }
}
