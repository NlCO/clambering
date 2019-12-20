package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Object contenant les champs du formulaire liés aux commentaires
 */
@Getter
@Setter
public class CommentaireForm {
    private Integer spotId;

    private String user;

    private String comment;

}
