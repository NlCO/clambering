package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.CucumberTest;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.SpotRepository;
import org.junit.Assert;

import javax.persistence.EntityNotFoundException;


public class F1StepDef extends CucumberTest {

    private SpotRepository spotRepository;

    F1StepDef(SpotRepository spotRepository){
        this.spotRepository = spotRepository;
    }

    private Spot spot;

    @When("Je consulte les informations du spot ayant l'(\\d+)$")
    public void jeConsulteLesInformationsDuSpotAyantLId(int id) {
        spot = spotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Le spot " + id + " n'exite pas"));
    }

    @Then("Sa cotation max est (.+)$")
    public void saCotationMaxEstCotation(String cotation) {
        Assert.assertEquals(cotation,spot.getCotationMax());
    }

    @Then("Sa cotation min est (.+)$")
    public void saCotationMinEstCotation(String cotation) {
        Assert.assertEquals(cotation,spot.getCotationMin());
    }

    @Then("Sa hauteur max est {float}")
    public void saHauteurMaxEstHauteur(Float hauteur) {
        Assert.assertEquals(hauteur,spot.getHauteurMax());
    }

    @Then("Sa hauteur min est {float}")
    public void saHauteurMinEstHauteur(Float hauteur) {
        Assert.assertEquals(hauteur,spot.getHauteurMin());
    }
}
