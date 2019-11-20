package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentaireForm {
    private Integer spotId;

    private String user;

    private String comment;

}
