package fr.oc.nico.clambering.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paysId;

    private String nom;

    private String code;

    @OneToMany(mappedBy = "pays")
    private List<Region> regions;

    public Integer getPaysId() {
        return paysId;
    }

    public void setPaysId(Integer paysId) {
        this.paysId = paysId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
