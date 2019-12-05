package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Topo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topoId;

    private String topoLibelle;

    @ManyToOne
    private Utilisateur proprietaire;

    @ManyToOne
    private Region lieu;

    @Column(columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateParution;

    private Boolean dispo = false;
}
