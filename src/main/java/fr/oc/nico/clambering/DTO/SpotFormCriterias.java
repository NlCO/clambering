package fr.oc.nico.clambering.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * Object contenant les champs du formulaire liés aux filtre des spots
 */
@Getter
@Setter
public class SpotFormCriterias {

    private String pays = "";

    private String region = "";

    private String orientation = "";

    private String cotationMin = "";

    private String cotationMax = "";

    private Boolean multiSecteurs = false;

    private Optional<Boolean> tagOfficiel = Optional.empty();
}
