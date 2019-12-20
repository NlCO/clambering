package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Object contenant les champs du formulaire liés aux secteurs
 */
@Getter
@Setter
public class SecteurEditForm {

    private Integer secteurId;

    private String secteurNom = "";

    private String secteurDescription = "";

    private List<VoieEditForm> voies = new ArrayList<>();

    public SecteurEditForm() {
    }

    public SecteurEditForm(Integer secteurId, String secteurNom, String secteurDescription) {
        this.secteurId = secteurId;
        this.secteurNom = secteurNom;
        this.secteurDescription = secteurDescription;
    }

    public void addvoie(VoieEditForm voie) {
        this.voies.add(voie);
    }
}
