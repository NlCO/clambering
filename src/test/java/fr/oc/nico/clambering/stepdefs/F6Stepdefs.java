package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.SpotRepository;
import fr.oc.nico.clambering.service.SpotService;
import org.junit.Assert;

public class F6Stepdefs {

    private Spot testSpot;

    private Spot switchSpot;

    private SpotService spotService;
    private SpotRepository spotRepository;

    public F6Stepdefs(SpotRepository spotRepository, SpotService spotService) {
        this.spotRepository = spotRepository;
        this.spotService = spotService;
    }

    @Given("le spot (.*) sans tag")
    public void leSpotXSansTag(String spotName) {
        testSpot = spotRepository.findBySpotLibelle(spotName).orElse(null);
        Assert.assertFalse("Le spot " + spotName + "est non tagué", testSpot.isTagAmiEscalade());
    }

    @When("je le tague")
    public void jeLeTague() {
        spotService.SwitchOfficialTag(testSpot.getSpotId());
    }

    @Then("le spot est tagué")
    public void leSpotEstTague() {
        switchSpot = spotRepository.findBySpotLibelle(testSpot.getSpotLibelle()).orElse(null);
        Assert.assertTrue("Le spot " + testSpot.getSpotLibelle() + "est ", switchSpot.isTagAmiEscalade());
    }

    @And("les autres informations n'ont pas été impactées")
    public void lesAutresInfoNonImpactes() {
        Assert.assertEquals(testSpot.getSecteurs().size(), switchSpot.getSecteurs().size());
        Assert.assertEquals(testSpot.getCommentaires().size(), switchSpot.getCommentaires().size());
    }


    @Given("le spot (.*) avec tag")
    public void leSpotXAvecTag(String spotName) {
        testSpot = spotRepository.findBySpotLibelle(spotName).orElse(null);
        Assert.assertTrue("Le spot " + spotName + "est tagué", testSpot.isTagAmiEscalade());
    }

    @When("je le detague")
    public void jeLeDetague() {
        spotService.SwitchOfficialTag(testSpot.getSpotId());
    }

    @Then("le spot X est detagué")
    public void leSpotXEstDetague() {
        switchSpot = spotRepository.findBySpotLibelle(testSpot.getSpotLibelle()).orElse(null);
        Assert.assertFalse("Le spot " + testSpot.getSpotLibelle() + "est ", switchSpot.isTagAmiEscalade());
    }

}
