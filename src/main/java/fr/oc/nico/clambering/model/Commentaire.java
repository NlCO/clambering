package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CommentaireId;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Spot spot;

    @Column(columnDefinition = "text")
    private String commentaire;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date();

    public Commentaire() {}

    public Commentaire(Utilisateur utilisateur, Spot spot, String commentaire) {
        this.utilisateur = utilisateur;
        this.spot = spot;
        this.commentaire = commentaire;
    }
}
