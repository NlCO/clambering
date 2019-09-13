package fr.oc.nico.clambering.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpotFormInfo {

    List<Pays> pays;

    List<Region> regions;

    List<String> orientations;

    List<String> cotations;

    public SpotFormInfo(List<Pays> pays, List<Region> regions, List<String> orientations, List<String> cotations) {
        this.pays = pays;
        this.regions = regions;
        this.orientations = orientations;
        this.cotations = cotations;
    }
}
