package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.DTO.SpotFormRegistration;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.model.SpotFormCriterias;
import fr.oc.nico.clambering.service.SpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotController.class);

    private final SpotService spotService;

    @Autowired
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @GetMapping("/spots")
    public String spotsListe(Model model, @ModelAttribute("spotFormCriterias") SpotFormCriterias spotFormCriterias) {
        model.addAttribute("spots", spotService.listeSpots());
        model.addAttribute("formData", spotService.getSpotFormInfo());

        LOGGER.debug("affichage de la liste des spots");
        return "spots";
    }

    @PostMapping("/spots")
    public String spotsFilter(Model model, @ModelAttribute("spotFormCriterias") SpotFormCriterias spotFormCriterias) {
        model.addAttribute("spots", spotService.filterSpots(spotFormCriterias));
        model.addAttribute("formData", spotService.getSpotFormInfo());

        LOGGER.debug("affichage de la liste des spots filtrés");
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
        model.addAttribute("formData", spotService.getSpotFormInfo());
        model.addAttribute("spotRegistrationForm", spotService.getEmptySpot());

        LOGGER.debug("affichage de la page de création de spot");
        return "spotRegistration";
    }

    @PostMapping("/spotRegistration")
    public String spotRegistration(Model model, @ModelAttribute("spotRegistrationForm") SpotFormRegistration newSpot) {
        model.addAttribute("formData", spotService.getSpotFormInfo());
        Spot saveSpot = spotService.ajouterSpot(newSpot);

        if (saveSpot != null) {
            return "redirect:/spots/" + saveSpot.getSpotId();
        }

        LOGGER.debug("affichage du nouveau post");
        return "spotRegistration";
    }
}
