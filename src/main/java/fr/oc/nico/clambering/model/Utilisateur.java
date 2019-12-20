package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Entité model des utilisateur
 */
@Entity
@Getter
@Setter
public class Utilisateur {

    @Transient
    public String passwordConfirm;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer utilisateurId;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String pseudo;
    private String password;
    @Column(columnDefinition = "boolean default false")
    private Boolean MembreAssociation;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;
    @OneToMany(mappedBy = "moderateur", cascade = CascadeType.ALL)
    private List<Commentaire> moderations;
    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL)
    private List<Topo> topos;
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private List<Topo> emprunts;
}
