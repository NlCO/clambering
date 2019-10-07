package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.model.SpotFormCriterias;
import fr.oc.nico.clambering.model.SpotFormInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotService {
    List<Spot> listeSpots();

    Spot spotInfo(Integer spotId);

    List<Spot> filterSpots(SpotFormCriterias criterias);

    SpotFormInfo getSpotFormInfo();

    Spot ajouterSpot(Spot newSpot);
}
