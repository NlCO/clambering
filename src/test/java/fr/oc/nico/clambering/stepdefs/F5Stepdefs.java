package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.SpotRepository;
import fr.oc.nico.clambering.service.SpotService;
import org.junit.Assert;

public class F5Stepdefs {

    private SpotService spotService;
    private SpotRepository spotRepository;

    private Spot spot;

    public F5Stepdefs(SpotService spotService, SpotRepository spotRepository){
        this.spotService = spotService;
        this.spotRepository = spotRepository;
    }

    @Given("Sur le spot (.*)$")
    public void surLeSpotNewark(String spotName) {
        spot = spotRepository.findBySpotLibelle(spotName).orElse(null);
    }

    @When("l'utilisateur (.*) post le commentaire (.*)$")
    public void lUtilisateurPostLeCommentaire(String user, String message) {
        spotService.addCommentToSpot(spot.getSpotId(), user, message);
    }

    @Then("le spot Newark a {int} commentaire")
    public void leSpotNewarkACommentaire(int nbCommentaire) {
        Assert.assertEquals(nbCommentaire, spot.getCommentaires().size());
    }
}
