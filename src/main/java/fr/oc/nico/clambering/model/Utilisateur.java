package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Utilisateur {

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

    @Transient
    public String passwordConfirm;
}
