package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.DTO.SpotFormRegistration;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.RegionRepository;
import fr.oc.nico.clambering.repository.SpotRepository;
import fr.oc.nico.clambering.service.SpotService;
import org.junit.Assert;

public class F4Stepdefs {

    private final SpotService spotService;
    private final SpotRepository spotRepository;
    private final RegionRepository regionRepository;

    private SpotFormRegistration spot1;

    private Spot saveSpot;

    public F4Stepdefs(SpotService spotService, SpotRepository spotRepository, RegionRepository regionRepository) {
        this.spotService = spotService;
        this.spotRepository = spotRepository;
        this.regionRepository = regionRepository;
        this.spot1 = new SpotFormRegistration();
    }

    @Given("un spot inexistant (.*) de (.*)$")
    public void unSpotInexistantFEATURESPOT(String spotName, String region) {
        Assert.assertFalse("Le spot " + spotName + "n'existe pas", spotRepository.findBySpotLibelle(spotName).isPresent());
        spot1.setSpotNom(spotName);
        spot1.setRegion(region);
    }

    @And("contenant un secteur (.*)$")
    public void contenantUnSecteurFEATURESECTEUR(String secteurName) {
        spot1.setSecteurNom(secteurName);
    }

    @And("contenant une voie (.*)$")
    public void contenantUneVoieFEATUREVOIE(String voieName) {
        spot1.setVoieNom(voieName);
    }

    @And("contenant une longueur (.*), de longueur (.*), de cotation (.*) avec (.*) degaines")
    public void contenantUneLongueurFEATURELONGUEURDeLongueurFDeCotationAAvecDegaine(String longueurName, int hauteur, String cotation, int degaine) {
        spot1.setLongueurNom(longueurName);
        spot1.setHauteur(hauteur);
        spot1.setCotation(cotation);
        spot1.setDegaine(degaine);
    }

    @When("le formulaire est rempli et envoyé")
    public void leFormulaireEstRempliEtEnvoye() {
        saveSpot = spotService.ajouterSpot(spot1);
    }

    @Then("le spot (.*) est présent dans la base")
    public void leSpotFEATURESPOTEstPresentDansLaBase(String addSpot) {
        Assert.assertTrue("Le spot " + addSpot + " est présent dans la base", spotRepository.findBySpotLibelle(addSpot).isPresent());
    }

    @And("il contient (.*) secteur nommé (.*)$")
    public void ilContientSecteurNommeFEATURESECTEUR(int nbSecteur, String secteurName) {
        saveSpot = spotRepository.findBySpotLibelle(spot1.getSpotNom()).orElseThrow(null) ;
        Assert.assertEquals(nbSecteur, saveSpot.getSecteurs().size());
        Assert.assertEquals(secteurName, saveSpot.getSecteurs().get(0).getSecteurLibelle());
    }

    @And("sa hauteur max et mini est de (.*) m")
    public void saHauteurMaxEtMiniEstDeM(int hauteur) {
        Assert.assertEquals(saveSpot.getHauteurMin(), hauteur, 0);
        Assert.assertEquals(saveSpot.getHauteurMax(), hauteur, 0);
    }

    @And("sa cotation max et min est (.*)")
    public void saCotationMaxEtMinEstA(String cotation) {
        Assert.assertEquals(cotation, saveSpot.getCotationMin());
        Assert.assertEquals(cotation, saveSpot.getCotationMax());
    }
}
