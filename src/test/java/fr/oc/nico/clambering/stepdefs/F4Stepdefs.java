package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.model.Longueur;
import fr.oc.nico.clambering.model.Secteur;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.model.Voie;
import fr.oc.nico.clambering.repository.SpotRepository;
import fr.oc.nico.clambering.service.SpotService;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class F4Stepdefs {

    private SpotService spotService;
    private SpotRepository spotRepository;

    private Spot spot1;
    private Secteur secteur1;
    private Voie voie1;
    private Longueur longueur1;

    private Spot saveSpot;

    public F4Stepdefs(SpotService spotService, SpotRepository spotRepository) {
        this.spotService = spotService;
        this.spotRepository = spotRepository;
    }

    @Given("un spot inexistant (.*)$")
    public void unSpotInexistantFEATURESPOT(String spotName) {
        Assert.assertFalse("Le spot " + spotName + "n'existe pas", spotRepository.findByNom(spotName).isPresent());
        spot1 = new Spot();
        spot1.setNom(spotName);
    }

    @And("contenant un secteur (.*)$")
    public void contenantUnSecteurFEATURESECTEUR(String secteurName) {
        secteur1 = new Secteur();
        secteur1.setNom(secteurName);
    }

    @And("contenant une voie (.*)$")
    public void contenantUneVoieFEATUREVOIE(String voieName) {
        voie1 = new Voie();
        voie1.setNom(voieName);
    }

    @And("contenant une longueur (.*), de longueur (.*), de cotation (.*) avec (.*) degaines")
    public void contenantUneLongueurFEATURELONGUEURDeLongueurFDeCotationAAvecDegaine(String longueurName, int hauteur, String cotation, int degaine) {
        longueur1 = new Longueur();
        longueur1.setNom(longueurName);
        longueur1.setHauteur(hauteur);
        longueur1.setCotation(cotation);
        longueur1.setDegaine(degaine);
    }

    @When("le formulaire est rempli et envoyé")
    public void leFormulaireEstRempliEtEnvoye() {
        List<Longueur> longueurs = new ArrayList<>();
        longueurs.add(longueur1);
        voie1.setLongueurs(longueurs);

        List<Voie> voies = new ArrayList<>();
        voies.add(voie1);
        secteur1.setVoies(voies);

        List<Secteur> secteurs = new ArrayList<>();
        secteurs.add(secteur1);
        spot1.setSecteurs(secteurs);

        saveSpot = spotService.ajouterSpot(spot1);
    }

    @Then("le spot (.*) est présent dans la base")
    public void leSpotFEATURESPOTEstPresentDansLaBase(String addSpot) {
        Assert.assertTrue("Le spot " + addSpot + " est présent dans la base", spotRepository.findByNom(addSpot).isPresent());
    }

    @And("il contient (.*) secteur nommé (.*)$")
    public void ilContientSecteurNommeFEATURESECTEUR(int nbSecteur, String secteurName) {
        Assert.assertEquals(nbSecteur, saveSpot.getSecteurs().size());
        Assert.assertEquals(secteurName, saveSpot.getSecteurs().get(0).getNom());
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
