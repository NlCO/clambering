package fr.oc.nico.clambering.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VoieEditForm {

    private Integer voieId;

    private String voieNom;

    private List<LongueurFormRegistration> longueurs = new ArrayList<>();

    public void addLongueur(LongueurFormRegistration longueur) {
        this.longueurs.add(longueur);
    }

    public VoieEditForm() {
    }

    public VoieEditForm(Integer voieId, String voieNom) {
        this.voieId = voieId;
        this.voieNom = voieNom;
    }
}
