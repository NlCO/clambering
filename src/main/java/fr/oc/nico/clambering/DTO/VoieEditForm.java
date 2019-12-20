package fr.oc.nico.clambering.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Object contenant les champs du formulaire liés aux voies
 */
@Getter
@Setter
public class VoieEditForm {

    private Integer voieId;

    private String voieNom;

    private List<LongueurEditForm> longueurs = new ArrayList<>();

    public VoieEditForm() {
    }

    public VoieEditForm(Integer voieId, String voieNom) {
        this.voieId = voieId;
        this.voieNom = voieNom;
    }

    public void addLongueur(LongueurEditForm longueur) {
        this.longueurs.add(longueur);
    }
}
