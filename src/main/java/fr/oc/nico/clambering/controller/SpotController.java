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

    @GetMapping("/spots")
    public String spotsListe(Model model, @ModelAttribute("spotFormCriterias") SpotFormCriterias spotFormCriterias) {
        model.addAttribute("spots", spotService.filterSpots(spotFormCriterias));
        LOGGER.debug("affichage de la liste des spots");
        return "spots";
    }

    @GetMapping("/spots/{spotId}")
    public String spotInfo(Model model, @PathVariable Integer spotId) {
        model.addAttribute("spot", spotService.spotInfo(spotId));
        model.addAttribute("commentForm", spotService.getEmptyCommentForm());
        LOGGER.debug("affichage de la les infos du spots");
        return "spot";
    }

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

    @PostMapping("/spots/{spotId}/spotEdition")
    public String spotEdition(Model model, @PathVariable Integer spotId, final SpotEditForm spotEditForm) {
        Spot spot = spotService.updateSpot(spotEditForm);
        return "redirect:/spots/" + spot.getSpotId();
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addSecteur"})
    public String addSecteur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId) {
        spotService.addNewSecteurToSpot(spotEditForm);
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addVoie"})
    public String addVoie(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.addNewVoieToSpot(spotEditForm, req.getParameter("addVoie"));
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addLongueur"})
    public String addLongueur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.addNewLongueurToSpot(spotEditForm, req.getParameter("addLongueur"));
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeSecteur"})
    public String removeSecteur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.removeSecteurToSpot(spotEditForm, req.getParameter("removeSecteur"));
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeVoie"})
    public String removeVoie(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.removeVoieToSpot(spotEditForm, req.getParameter("removeVoie"));
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeLongueur"})
    public String removeLongueur(final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req) {
        spotService.removeLongueurToSpot(spotEditForm, req.getParameter("removeLongueur"));
        return "spotEdition";
    }

    @PostMapping(value = "/spots/{spotId}/addCommentToSpot")
    public String addCommentToSpot(@PathVariable Integer spotId, final CommentaireForm commentaireForm) {
        spotService.addCommentToSpot(commentaireForm);
        return "redirect:/spots/" + spotId;
    }

    @RequestMapping(value = "/spots/{spotId}", params = {"switchTag"})
    public String switchTagOfficiel(final Model model, @PathVariable Integer spotId) {
        spotService.SwitchOfficialTag(spotId);
        return "redirect:/spots/{spotId}";
    }
}
