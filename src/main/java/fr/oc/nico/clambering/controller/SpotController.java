package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.service.SpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class SpotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotController.class);

    @Autowired
    private SpotService spotService;

    @GetMapping("/")
    public String essai(Model model) {
        LOGGER.debug("Homapage");
        return "test";
    }

    @GetMapping("/spots")
    public String spotsListe(Model model) {

        //List<Spot> spots = spotService.listeSpots();

        model.addAttribute("spots", spotService.listeSpots());

        LOGGER.debug("affichage de la liste des spots");
        return "spots";
    }

    @GetMapping("/spots/{spotId}")
    public String spotInfo(Model model, @PathVariable Integer spotId) {

        //List<Spot> spots = spotService.listeSpots();

        model.addAttribute("spot", spotService.spotInfo(spotId));

        LOGGER.debug("affichage de la les infos du spots");
        return "spot";
    }
}
