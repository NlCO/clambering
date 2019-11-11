package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.DTO.SpotEditForm;
import fr.oc.nico.clambering.DTO.SpotFormRegistration;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.DTO.SpotFormCriterias;
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
        LOGGER.debug("affichage de la les infos du spots");
        return "spot";
    }

    @GetMapping("/spotRegistration")
    public String spotRegistration(Model model) {
        model.addAttribute("spotRegistrationForm", spotService.getEmptySpot());
        LOGGER.debug("affichage de la page de création de spot");
        return "spotRegistration";
    }

    @PostMapping("/spotRegistration")
    public String spotRegistration(Model model, @ModelAttribute("spotRegistrationForm") SpotFormRegistration newSpot) {
        Spot saveSpot = spotService.ajouterSpot(newSpot);
        if (saveSpot != null) {
            return "redirect:/spots/" + saveSpot.getSpotId();
        }
        LOGGER.debug("affichage du nouveau post");
        return "spotRegistration";
    }

    @GetMapping("/spots/{spotId}/spotEdition")
    public String spotEdition(Model model, @PathVariable Integer spotId) {
        model.addAttribute("spotEdit", spotId);
        model.addAttribute("spotEditForm", spotService.getSpotEditForm(spotService.spotInfo(spotId)));
        LOGGER.debug("affichage de la page de création de spot");
        return "spotEdition";
    }

    @PostMapping("/spots/{spotId}/spotEdition")
    public String spotEdition(Model model, @PathVariable Integer spotId, final SpotEditForm spotEditForm) {
        spotService.updateSpot(spotId, spotEditForm);
        return "redirect:/spots/{spotId}";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addSecteur"})
    public String addSecteur (final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId){
        spotService.addNewSecteurToSpot(spotEditForm);
        model.addAttribute("spotEdit", spotId);
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addVoie"})
    public String addVoie (final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req){
        spotService.addNewVoieToSpot(spotEditForm, req.getParameter("addVoie"));
        model.addAttribute("spotEdit", spotId);
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"addLongueur"})
    public String addLongueur (final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req){
        spotService.addNewLongueurToSpot(spotEditForm, req.getParameter("addLongueur"));
        model.addAttribute("spotEdit", spotId);
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeSecteur"})
    public String removeSecteur (final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req){
        spotService.removeSecteurToSpot(spotEditForm, req.getParameter("removeSecteur"));
        model.addAttribute("spotEdit", spotId);
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeVoie"})
    public String removeVoie (final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req){
        spotService.removeVoieToSpot(spotEditForm, req.getParameter("removeVoie"));
        model.addAttribute("spotEdit", spotId);
        return "spotEdition";
    }

    @RequestMapping(value = "/spots/{spotId}/spotEdition", params = {"removeLongueur"})
    public String removeLongueur (final SpotEditForm spotEditForm, final BindingResult bindingResult, final Model model, @PathVariable Integer spotId, final HttpServletRequest req){
        spotService.removeLongueurToSpot(spotEditForm, req.getParameter("removeLongueur"));
        model.addAttribute("spotEdit", spotId);
        return "spotEdition";
    }
}
