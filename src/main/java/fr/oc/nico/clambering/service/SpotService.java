package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.CommentaireForm;
import fr.oc.nico.clambering.DTO.SpotEditForm;
import fr.oc.nico.clambering.DTO.SpotFormCriterias;
import fr.oc.nico.clambering.DTO.SpotFormInfo;
import fr.oc.nico.clambering.model.Spot;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface de gestion de la couche service liés aux spots
 */
@Service
public interface SpotService {

    /**
     * Fournit les informations d'un spot à partir de son ID
     *
     * @param spotId id du spot
     * @return le spot
     */
    Spot spotInfo(Integer spotId);

    /**
     * Fournit la liste des spot correspondant au critères du formulaire
     *
     * @param criterias données du formulaire
     * @return la liste des spots
     */
    List<Spot> filterSpots(SpotFormCriterias criterias);

    /**
     * Fournit les données pré-remplies pour le formulaire d'edition de spot
     *
     * @return les données du formulaire
     */
    SpotFormInfo getSpotFormInfo();

    /**
     * Fournit un formulaire vierge de création de spot
     *
     * @return un formulaire vierge
     */
    SpotEditForm getSpotEditForm();

    /**
     * Fournit un formulaire pré-rempli d'édition de spot
     *
     * @param spot information à passer dans le formulaire
     * @return le formulaire pré-rempli
     */
    SpotEditForm getSpotEditForm(Spot spot);

    /**
     * Ajoute un secteur au formulaire du spot
     *
     * @param spotEditForm formulaire du spot
     */
    void addNewSecteurToSpot(SpotEditForm spotEditForm);

    /**
     * Ajoute une voie à un secteur du formulaire d'édition du spot
     *
     * @param spotEditForm formulaire à modifier
     * @param addVoie      voie à ajouter
     * @return le formulaire avec une nouvelle voie
     */
    SpotEditForm addNewVoieToSpot(SpotEditForm spotEditForm, String addVoie);

    /**
     * Ajoute une longueur à une voie d'un secteur dans le formulaire d'édition du spot
     *
     * @param spotEditForm fomulaire à modifier
     * @param addLongueur  longueur à ajouter
     * @return le formulaire avec une nouvelle longueur
     */
    SpotEditForm addNewLongueurToSpot(SpotEditForm spotEditForm, String addLongueur);

    /**
     * Retire un secteur au formulaire du spot
     *
     * @param spotEditForm  formulaire à modifier
     * @param removeSecteur secteur à retirer
     * @return le formulaire mis à jour
     */
    SpotEditForm removeSecteurToSpot(SpotEditForm spotEditForm, String removeSecteur);

    /**
     * Retire une voie à un secteur dans le formulaire d'édition du spot
     *
     * @param spotEditForm formulaire à modifier
     * @param removeVoie   voie à retirer
     * @return le formulaire mis à jour
     */
    SpotEditForm removeVoieToSpot(SpotEditForm spotEditForm, String removeVoie);

    /**
     * Retire une longueur à une voie d'un secteur dans le formulaire d'édition du spot
     *
     * @param spotEditForm   formulaire à modifier
     * @param removeLongueur longueur à retirer
     * @return le formulaire mis à jour
     */
    SpotEditForm removeLongueurToSpot(SpotEditForm spotEditForm, String removeLongueur);

    /**
     * Mets à jour le spot avec les donnée du formulaire
     *
     * @param spotEditForm formulaire
     * @return le spot mis à jour
     */
    Spot updateSpot(SpotEditForm spotEditForm);

    /**
     * Ajoute un commentaire à un spot
     *
     * @param commentaireForm formulaire d'ajout de commentaire
     */
    void addCommentToSpot(CommentaireForm commentaireForm);

    /**
     * Fournit un formulaire de commentaire vide
     *
     * @return un formulaire vierge
     */
    CommentaireForm getEmptyCommentForm();

    /**
     * Inverse le statut du tag officiel des amis de l'escalade
     *
     * @param spotId Id du spot concerné
     */
    void switchOfficialTag(Integer spotId);
}
