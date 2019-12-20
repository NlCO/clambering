package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Entité model des topos
 */
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

    @ManyToOne
    private Utilisateur emprunteur;

    public Topo() {
    }

    public Topo(String topoLibelle, Region lieu, String description, Date dateParution, Utilisateur proprietaire) {
        this.topoLibelle = topoLibelle;
        this.proprietaire = proprietaire;
        this.lieu = lieu;
        this.description = description;
        this.dateParution = dateParution;
    }
}
