package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paysId;

    private String paysLibelle;

    private String codePays;

    @OneToMany(mappedBy = "pays")
    private List<Region> regions;
}

