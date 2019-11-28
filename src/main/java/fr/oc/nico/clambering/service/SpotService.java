package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.CommentaireForm;
import fr.oc.nico.clambering.DTO.SpotEditForm;
import fr.oc.nico.clambering.DTO.SpotFormCriterias;
import fr.oc.nico.clambering.DTO.SpotFormInfo;
import fr.oc.nico.clambering.model.Spot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotService {
    Spot spotInfo(Integer spotId);

    List<Spot> filterSpots(SpotFormCriterias criterias);

    SpotFormInfo getSpotFormInfo();

    SpotEditForm getSpotEditForm();

    SpotEditForm getSpotEditForm(Spot spot);

    void addNewSecteurToSpot(SpotEditForm spotEditForm);

    SpotEditForm addNewVoieToSpot(SpotEditForm spotEditForm, String addVoie);

    SpotEditForm addNewLongueurToSpot(SpotEditForm spotEditForm, String addLongueur);

    SpotEditForm removeSecteurToSpot(SpotEditForm spotEditForm, String removeSecteur);

    SpotEditForm removeVoieToSpot(SpotEditForm spotEditForm, String removeVoie);

    SpotEditForm removeLongueurToSpot(SpotEditForm spotEditForm, String removeLongueur);

    Spot updateSpot(SpotEditForm spotEditForm);

    void addCommentToSpot(CommentaireForm commentaireForm);

    CommentaireForm getEmptyCommentForm();

    void SwitchOfficialTag(Integer spotId);
}
