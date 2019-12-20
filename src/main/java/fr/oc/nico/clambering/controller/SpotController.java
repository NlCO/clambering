package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.DTO.CommentaireForm;
import fr.oc.nico.clambering.DTO.SpotEditForm;
import fr.oc.nico.clambering.DTO.SpotFormCriterias;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.service.SpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur gérant les requetes liées aux spots
 */
@Controller
public class SpotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotController.class);

    private final SpotService spotService;

    @Autowired
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @ModelAttribute
    public void addFormData(Model model) {
        model.addAttribute("formData", spotService.getSpotFormInfo());
    }

    /**
     * Methode GET permettant d'afficher la page de la liste des spots avec les données du filtre
     *
     * @param model             données Model pour constituer la vue
     * @param spotFormCriterias DTO contenant les données du formulaire de filtre
     * @return la page de la liste des posts
     */
    @GetMapping("/spots")
    public String spotsListe(Model model, @ModelAttribute("spotFormCriterias") SpotFormCriterias spotFormCriterias) {
        model.addAttribute("spots", spotService.filterSpots(spotFormCriterias));
        LOGGER.debug("affichage de la liste des spots");
        return "spots";
    }

    /**
     * Méthode GET permettant d'afficher les infos détaillées d'un spot
     *
     * @param model  info du spot
     * @param spotId Id du spot
     * @return la page du spot
     */
    @GetMapping("/spots/{spotId}")
    public String spotInfo(Model model, @PathVariable Integer spotId) {
        model.addAttribute("spot", spotService.spotInfo(spotId));
        model.addAttribute("commentForm", spotService.getEmptyCommentForm());
        LOGGER.debug("affichage de la les infos du spots");
        return "spot";
    }

    /**
     * Méthode GET permettant d'afficher le formulaire d'édition ou de création d'un spot
     *
     * @param model  model pour crée la vue
     * @param spotId Id du spot
     * @return la page d'édition du spot
     */
    @GetMapping("/spots/{spotId}/spotEdition")
    public String spotEdition(Model model, @PathVariable Integer spotId) {
        if (spotId != 0) {
            model.addAttribute("spotEditForm", spotService.getSpotEditForm(spotService.spotInfo(spotId)));
            LOGGER.debug("affichage de la page d'édition d'un spot");
        } else {
            model.addAttribute("spotEditForm", spotService.getSpotEditForm());
            LOGGER.debug("affichage de la page de création de spot");
        }
        return "spotEdition";
    }

    /**
     * Methode POST permattant de prendre en compte les informations du formaulaire de d'édition ou création du spot
     *
     * @param model        model pour crée la vue
     * @param spotId       Id du spot édité
     * @param spotEditForm données saisies par l'utilisateur
     * @return redirection sur la page d'édition ou création du spot
     */
    @PostMapping("/spots/{spotId}/spotEdition")
    public String spotEdition(Model model, @PathVariable Integer spotId, final SpotEditForm spotEditForm) {
        Spot spot = spotService.updateSpot(spotEditForm);
        return "redirect:/spots/" + spot.getSpotId();
    }

    /**
     * Permet d'ajouter un secteur dans la page du formulaire d'édition d'un spot
     *
     * @param spotEditForm  formulaire de saisie
     * @param bindingResult resultat de la requete
     * @param model         model pour crée la vue
     * @param spotId        Id du spot édité
     * @return retour sur la page d'édition ou création du spot
     */
    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addSecteur"})
    public String addSecteur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId) {
        spotService.addNewSecteurToSpot(spotEditForm);
        return "spotEdition";
    }

    /**
     * Permet d'ajouter une voie à un secteur dans la page du formulaire d'édition d'un spot
     *
     * @param spotEditForm  formulaire de saisie
     * @param bindingResult resultat de la requete
     * @param model         model pour crée la vue
     * @param spotId        Id du spot édité
     * @param req           ServletRequest contenant les informations de modifications
     * @return retour sur la page d'édition ou création du spot
     */
    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addVoie"})
    public String addVoie(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.addNewVoieToSpot(spotEditForm, req.getParameter("addVoie"));
        return "spotEdition";
    }

    /**
     * Permet d'ajouter une longueur à une voie dans la page du formulaire d'édition d'un spot
     *
     * @param spotEditForm  formulaire de saisie
     * @param bindingResult resultat de la requete
     * @param model         model pour crée la vue
     * @param spotId        Id du spot édité
     * @param req           ServletRequest contenant les informations de modifications
     * @return retour sur la page d'édition ou création du spot
     */
    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addLongueur"})
    public String addLongueur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.addNewLongueurToSpot(spotEditForm, req.getParameter("addLongueur"));
        return "spotEdition";
    }

    /**
     * Permet dr retirer un secteur dans la page du formulaire d'édition d'un spot
     *
     * @param spotEditForm  formulaire de saisie
     * @param bindingResult resultat de la requete
     * @param model         model pour crée la vue
     * @param spotId        Id du spot édité
     * @param req           ServletRequest contenant les informations de modifications
     * @return retour sur la page d'édition ou création du spot
     */
    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeSecteur"})
    public String removeSecteur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.removeSecteurToSpot(spotEditForm, req.getParameter("removeSecteur"));
        return "spotEdition";
    }

    /**
     * Permet de retirer une voie à un secteur dans la page du formulaire d'édition d'un spot
     *
     * @param spotEditForm  formulaire de saisie
     * @param bindingResult resultat de la requete
     * @param model         model pour crée la vue
     * @param spotId        Id du spot édité
     * @param req           ServletRequest contenant les informations de modifications
     * @return retour sur la page d'édition ou création du spot
     */

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeVoie"})
    public String removeVoie(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.removeVoieToSpot(spotEditForm, req.getParameter("removeVoie"));
        return "spotEdition";
    }

    /**
     * Permet de retirer une longueur à une voie dans la page du formulaire d'édition d'un spot
     *
     * @param spotEditForm  formulaire de saisie
     * @param bindingResult resultat de la requete
     * @param model         model pour crée la vue
     * @param spotId        Id du spot édité
     * @param req           ServletRequest contenant les informations de modifications
     * @return retour sur la page d'édition ou création du spot
     */
    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeLongueur"})
    public String removeLongueur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.removeLongueurToSpot(spotEditForm, req.getParameter("removeLongueur"));
        return "spotEdition";
    }

    /**
     * Permet d'ajouter un commentaire à un spot
     *
     * @param spotId          Id du spot a commenter
     * @param commentaireForm formulaire contenant le commentaire
     * @return redirection sur la page du spot commenté
     */
    @PostMapping(value = "/spots/{spotId}/addCommentToSpot")
    public String addCommentToSpot(@PathVariable Integer spotId, final CommentaireForm commentaireForm) {
        spotService.addCommentToSpot(commentaireForm);
        return "redirect:/spots/" + spotId;
    }

    /**
     * Permet de switcher le tag "Officiel Ami de l'escalade"
     *
     * @param model  model pour crée la vue
     * @param spotId Id du spot a modifier
     * @return redirection sur la page du spot modifié
     */
    @RequestMapping(value = "/spots/{spotId}", params = {"switchTag"})
    public String switchTagOfficiel(final Model model, @PathVariable Integer spotId) {
        spotService.switchOfficialTag(spotId);
        return "redirect:/spots/{spotId}";
    }
}
