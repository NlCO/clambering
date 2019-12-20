package fr.oc.nico.clambering.service;


import fr.oc.nico.clambering.DTO.*;
import fr.oc.nico.clambering.model.Longueur;
import fr.oc.nico.clambering.model.Secteur;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.model.Voie;
import fr.oc.nico.clambering.repository.PaysRepository;
import fr.oc.nico.clambering.repository.RegionRepository;
import fr.oc.nico.clambering.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Implémantation de l'interface de gestion de la couche service liés aux spots
 */
@Service("SpotService")
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;

    private final PaysRepository paysRepository;

    private final RegionRepository regionRepository;

    private final LongueurService longueurService;

    private final VoieService voieService;

    private final SecteurService secteurService;

    private final CommentaireService commentaireService;

    @Autowired
    public SpotServiceImpl(SpotRepository spotRepository, PaysRepository paysRepository, RegionRepository regionRepository, LongueurService longueurService, VoieService voieService, SecteurService secteurService, CommentaireService commentaireService) {
        this.spotRepository = spotRepository;
        this.paysRepository = paysRepository;
        this.regionRepository = regionRepository;
        this.longueurService = longueurService;
        this.voieService = voieService;
        this.secteurService = secteurService;
        this.commentaireService = commentaireService;
    }

    /**
     * Fournit les informations d'un spot à partir de son ID
     *
     * @param spotId id du spot
     * @return le spot
     */
    @Override
    public Spot spotInfo(Integer spotId) {
        return spotRepository.findById(spotId)
                .orElseThrow(() -> new EntityNotFoundException("Le spot " + spotId + " n'exite pas"));
    }

    /**
     * Fournit la liste des spot correspondant au critères du formulaire
     *
     * @param criterias données du formulaire
     * @return la liste des spots
     */
    @Override
    public List<Spot> filterSpots(SpotFormCriterias criterias) {
        return spotRepository.multiCriteriaSpotSearch(criterias);
    }

    /**
     * Fournit les données pré-remplies pour le formulaire d'edition de spot
     *
     * @return les données du formulaire
     */
    @Override
    public SpotFormInfo getSpotFormInfo() {
        List<String> orientations = Arrays.asList("Nord", "Nord-Est", "Est", "Sud-Est", "Sud", "Sud-Ouest", "Ouest", "Nord-Ouest");
        List<String> cotations = Arrays.asList("3", "4", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b", "7c", "8a", "8b", "8c", "9a", "9b", "9c");
        return new SpotFormInfo(paysRepository.findAll(), regionRepository.findAll(), orientations, cotations);
    }

    /**
     * Fournit un formulaire vierge de création de spot
     *
     * @return un formulaire vierge
     */
    @Override
    public SpotEditForm getSpotEditForm() {
        LongueurEditForm emptyLongueur = new LongueurEditForm();
        VoieEditForm emptyVoie = new VoieEditForm();
        emptyVoie.addLongueur(emptyLongueur);
        SecteurEditForm emptySecteur = new SecteurEditForm();
        emptySecteur.addvoie(emptyVoie);
        SpotEditForm spotEditForm = new SpotEditForm();
        spotEditForm.addSecteur(emptySecteur);
        return spotEditForm;
    }

    /**
     * Fournit un formulaire pré-rempli d'édition de spot
     *
     * @param spot information à passer dans le formulaire
     * @return un formulaire pré-remplis
     */
    @Override
    public SpotEditForm getSpotEditForm(Spot spot) {
        SpotEditForm spotEditForm = new SpotEditForm(spot.getSpotId(), spot.getRegion().getRegionLibelle(), spot.getSpotLibelle(), spot.getSpotDescription(), spot.getAcces(), spot.getOrientation(), spot.getLongitude(), spot.getLatitude(), spot.getImage());
        for (Secteur secteur : spot.getSecteurs()) {
            SecteurEditForm formSecteur = secteurService.mapSecteur(secteur);
            for (Voie voie : secteur.getVoies()) {
                VoieEditForm formVoie = voieService.mapVoie(voie);
                for (Longueur longueur : voie.getLongueurs()) {
                    formVoie.addLongueur(longueurService.mapLongueur(longueur));
                }
                formSecteur.addvoie(formVoie);
            }
            spotEditForm.addSecteur(formSecteur);
        }
        return spotEditForm;
    }

    /**
     * Ajoute un secteur au formulaire du spot
     *
     * @param spotEditForm formulaire du spot
     */
    @Override
    public void addNewSecteurToSpot(SpotEditForm spotEditForm) {
        spotEditForm.addSecteur(secteurService.getNewSecteur());
    }

    /**
     * Ajoute une voie à un secteur du formulaire d'édition du spot
     *
     * @param spotEditForm formulaire à modifier
     * @param addVoie      voie à ajouter
     * @return le formulaire mis à jour
     */
    @Override
    public SpotEditForm addNewVoieToSpot(SpotEditForm spotEditForm, String addVoie) {
        spotEditForm.getSecteurs().get(Integer.parseInt(addVoie)).addvoie(voieService.getNewVoie());
        return spotEditForm;
    }

    /**
     * Ajoute une longueur à une voie d'un secteur dans le formulaire d'édition du spot
     *
     * @param spotEditForm fomulaire à modifier
     * @param addLongueur  longueur à ajouter
     * @return le formulaire mis à jour
     */
    @Override
    public SpotEditForm addNewLongueurToSpot(SpotEditForm spotEditForm, String addLongueur) {
        String[] indexes = addLongueur.split("_");
        spotEditForm.getSecteurs().get(Integer.parseInt(indexes[0])).getVoies().get(Integer.parseInt(indexes[1])).addLongueur(new LongueurEditForm());
        return spotEditForm;
    }

    /**
     * Retire un secteur au formulaire du spot
     *
     * @param spotEditForm  formulaire à modifier
     * @param removeSecteur secteur à retirer
     * @return le formulaire mis à jour
     */
    @Override
    public SpotEditForm removeSecteurToSpot(SpotEditForm spotEditForm, String removeSecteur) {
        spotEditForm.getSecteurs().remove(Integer.parseInt(removeSecteur));
        return spotEditForm;
    }

    /**
     * Retire une voie à un secteur dans le formulaire d'édition du spot
     *
     * @param spotEditForm formulaire à modifier
     * @param removeVoie   voie à retirer
     * @return le formulaire mis à jour
     */
    @Override
    public SpotEditForm removeVoieToSpot(SpotEditForm spotEditForm, String removeVoie) {
        String[] indexes = removeVoie.split("_");
        spotEditForm.getSecteurs().get(Integer.parseInt(indexes[0])).getVoies().remove(Integer.parseInt(indexes[1]));
        return spotEditForm;
    }

    /**
     * Retire une longueur à une voie d'un secteur dans le formulaire d'édition du spot
     *
     * @param spotEditForm   formulaire à modifier
     * @param removeLongueur longueur à retirer
     * @return le formulaire mis à jour
     */
    @Override
    public SpotEditForm removeLongueurToSpot(SpotEditForm spotEditForm, String removeLongueur) {
        String[] indexes = removeLongueur.split("_");
        spotEditForm.getSecteurs().get(Integer.parseInt(indexes[0])).getVoies().get(Integer.parseInt(indexes[1])).getLongueurs().remove(Integer.parseInt(indexes[2]));
        return spotEditForm;
    }

    /**
     * Mets à jour le spot avec les donnée du formulaire
     *
     * @param spotEditForm formulaire
     * @return le spot mis à jour
     */
    @Override
    public Spot updateSpot(SpotEditForm spotEditForm) {
        Spot updateSpot = new Spot(spotEditForm.getSpotId(), spotEditForm.getSpotNom(), regionRepository.findByRegionLibelle(spotEditForm.getRegion()), spotEditForm.getSpotDescription(), spotEditForm.getAcces(), spotEditForm.getOrientation(), spotEditForm.getLongitude(), spotEditForm.getLatitude(), spotEditForm.getImage());
        for (SecteurEditForm secteurEditForm : spotEditForm.getSecteurs()) {
            Secteur secteur = secteurService.updateSecteur(secteurEditForm);
            for (VoieEditForm voieEditForm : secteurEditForm.getVoies()) {
                Voie voie = voieService.updateVoie(voieEditForm);
                for (LongueurEditForm longueurFormRegistration : voieEditForm.getLongueurs()) {
                    voie.addLongueur(longueurService.updateLongueur(longueurFormRegistration));
                }
                secteur.addVoie(voie);
            }
            updateSpot.addSecteur(secteur);
        }
        return spotRepository.save(updateSpot);
    }

    /**
     * Ajoute un commentaire à un spot
     *
     * @param commentaireForm formulaire d'ajout de commentaire
     */
    @Override
    public void addCommentToSpot(CommentaireForm commentaireForm) {
        Spot spotToComment = spotRepository.findById(commentaireForm.getSpotId()).orElse(null);
        commentaireService.addComment(spotToComment, commentaireForm.getUser(), commentaireForm.getComment());
    }

    /**
     * Fournit un formulaire de commentaire vide
     *
     * @return un formulaire vierge
     */
    @Override
    public CommentaireForm getEmptyCommentForm() {
        return new CommentaireForm();
    }

    /**
     * Inverse le statut du tag officiel des amis de l'escalade
     *
     * @param spotId Id du spot concerné
     */
    @Override
    public void switchOfficialTag(Integer spotId) {
        Spot spot = spotRepository.findById(spotId).orElse(null);
        if (spot.isTagAmiEscalade()) {
            spot.setTagAmiEscalade(false);
        } else {
            spot.setTagAmiEscalade(true);
        }
        spotRepository.save(spot);
    }
}
