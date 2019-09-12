package fr.oc.nico.clambering.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionId;

    private String nom;

    @ManyToOne
    @JoinColumn
    private Pays pays;

    @OneToMany(mappedBy = "region")
    private List<Spot> spots;

}
