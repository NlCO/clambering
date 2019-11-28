package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.DTO.SpotFormCriterias;
import fr.oc.nico.clambering.service.SpotService;
import org.junit.Assert;

import java.util.List;
import java.util.Optional;

public class F2StepDef {

    private SpotService spotService;
    private SpotFormCriterias spotFormCriterias;
    private List<Spot> spots;
    F2StepDef(SpotService spotService) {
        this.spotService = spotService;
    }

    @Given("aucun critère est choisi")
    public void aucunCritereEstChoisi() {
        spotFormCriterias = new SpotFormCriterias();
    }

    @When("le filtre est appliqué")
    public void leFiltreEstApplique() {
        spots = spotService.filterSpots(spotFormCriterias);
    }

    @Then("la liste contient {int} spots")
    public void laListeContientSpots(int resultat) {
        Assert.assertEquals(resultat, spots.size());
    }

    private String setEmptyIfNull(String value) {
        return (value.equals("null") ? "" : value);
    }

    @Given("les critères (.*), (.*), (.*), (.*), (.*), (.*), (.*) sont choisis")
    public void lesCriteresPaysRegionOrientationSecteurCotationMinCotationMaxSontChoisis(String pays, String region, String orientation, String tagOfficiel, String secteur, String cotationMin, String cotationMax) {
        spotFormCriterias = new SpotFormCriterias();
        spotFormCriterias.setPays(setEmptyIfNull(pays));
        spotFormCriterias.setRegion(setEmptyIfNull(region));
        spotFormCriterias.setOrientation(setEmptyIfNull(orientation));
        spotFormCriterias.setMultiSecteurs((secteur.equals("true")));
        spotFormCriterias.setCotationMin(setEmptyIfNull(cotationMin));
        spotFormCriterias.setCotationMax(setEmptyIfNull(cotationMax));
        spotFormCriterias.setTagOfficiel(!tagOfficiel.equals("null") ? Optional.of(tagOfficiel.equals("true")) : Optional.empty());
    }
}
