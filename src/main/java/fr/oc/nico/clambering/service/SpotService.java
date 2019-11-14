package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.SpotEditForm;
import fr.oc.nico.clambering.DTO.SpotFormRegistration;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.DTO.SpotFormCriterias;
import fr.oc.nico.clambering.DTO.SpotFormInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotService {
    List<Spot> listeSpots();

    Spot spotInfo(Integer spotId);

    List<Spot> filterSpots(SpotFormCriterias criterias);

    SpotFormInfo getSpotFormInfo();

    Spot ajouterSpot(SpotFormRegistration newSpot);

    SpotFormRegistration getEmptySpot();

    SpotEditForm getSpotEditForm(Spot spot);

    void addNewSecteurToSpot(SpotEditForm spotEditForm);

    SpotEditForm addNewVoieToSpot(SpotEditForm spotEditForm, String addVoie);

    SpotEditForm addNewLongueurToSpot(SpotEditForm spotEditForm, String addLongueur);

    SpotEditForm removeSecteurToSpot(SpotEditForm spotEditForm, String removeSecteur);

    SpotEditForm removeVoieToSpot(SpotEditForm spotEditForm, String removeVoie);

    SpotEditForm removeLongueurToSpot(SpotEditForm spotEditForm, String removeLongueur);

    Spot updateSpot(SpotEditForm spotEditForm);
}
