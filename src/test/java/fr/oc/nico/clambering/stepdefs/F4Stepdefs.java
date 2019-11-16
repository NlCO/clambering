package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.DTO.LongueurEditForm;
import fr.oc.nico.clambering.DTO.SpotEditForm;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.SpotRepository;
import fr.oc.nico.clambering.service.SpotService;
import org.junit.Assert;

public class F4Stepdefs {

    private final SpotService spotService;
    private final SpotRepository spotRepository;

    private SpotEditForm spotEditForm;
    private Spot saveSpot;
    private Spot spot;
    private Spot updateSpot;

    public F4Stepdefs(SpotService spotService, SpotRepository spotRepository) {
        this.spotService = spotService;
        this.spotRepository = spotRepository;
    }

    @Given("un spot inexistant (.*)$")
    public void unSpotInexistantFEATURESPOT(String spotName) {
        Assert.assertFalse("Le spot " + spotName + "n'existe pas", spotRepository.findBySpotLibelle(spotName).isPresent());
    }

    @When("je rempli le formulaire avec son nom (.*) et sa region (.*)$")
    public void jeRempliLeFormulaireAvecSonNomEtSaRegion(String spotName, String region) {
        spotEditForm = spotService.getSpotEditForm();
        spotEditForm.setSpotNom(spotName);
        spotEditForm.setRegion(region);
    }

    @And("contenant un secteur (.*)$")
    public void contenantUnSecteurFEATURESECTEUR(String secteurName) {
        spotEditForm.getSecteurs().get(0).setSecteurNom(secteurName);
    }

    @And("contenant une voie (.*)$")
    public void contenantUneVoieFEATUREVOIE(String voieName) {
        spotEditForm.getSecteurs().get(0).getVoies().get(0).setVoieNom(voieName);
    }

    @And("contenant une longueur (.*), de longueur (.*), de cotation (.*) avec (.*) degaines")
    public void contenantUneLongueurFEATURELONGUEURDeLongueurFDeCotationAAvecDegaine(String longueurName, int hauteur, String cotation, int degaines) {
        spotEditForm.getSecteurs().get(0).getVoies().get(0).getLongueurs().get(0).setLongueurNom(longueurName);
        spotEditForm.getSecteurs().get(0).getVoies().get(0).getLongueurs().get(0).setHauteur(hauteur);
        spotEditForm.getSecteurs().get(0).getVoies().get(0).getLongueurs().get(0).setCotation(cotation);
        spotEditForm.getSecteurs().get(0).getVoies().get(0).getLongueurs().get(0).setDegaine(degaines);
    }

    @And("le formulaire est rempli et envoyé")
    public void leFormulaireEstRempliEtEnvoye() {
        saveSpot = spotService.updateSpot(spotEditForm);
    }

    @Then("le spot (.*) est présent dans la base")
    public void leSpotFEATURESPOTEstPresentDansLaBase(String addSpot) {
        Assert.assertTrue("Le spot " + addSpot + " est présent dans la base", spotRepository.findBySpotLibelle(addSpot).isPresent());
    }

    @And("il contient (.*) secteur nommé (.*)$")
    public void ilContientSecteurNommeFEATURESECTEUR(int nbSecteur, String secteurName) {
        saveSpot = spotRepository.findBySpotLibelle(spotEditForm.getSpotNom()).orElseThrow(null) ;
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

    @Given("un spot existant (.*)$")
    public void unSpotExistantFEATURESPOT(String spotName) {
        spot = spotRepository.findBySpotLibelle(spotName).orElse(null);
    }

    @When("je modifie son nom en (.*)$")
    public void jeModifieSonNomEnSPOTFEATURE4(String newName) {
        spotEditForm = spotService.getSpotEditForm(spot);
        spotEditForm.setSpotNom(newName);
    }

    @And("une longueur (.*), de longueur (.*), de cotation (.*) avec (.*) degaines est ajoutée")
    public void uneLongueurFEATURELONGUEURDeLongueurDeCotationAAvecDegainesEstAjoutee(String newLongueurName, int hauteur, String cotation, int degaines) {
        LongueurEditForm newLongueur = new LongueurEditForm();
        newLongueur.setLongueurNom(newLongueurName);
        newLongueur.setHauteur(hauteur);
        newLongueur.setCotation(cotation);
        newLongueur.setDegaine(degaines);
        spotEditForm.getSecteurs().get(0).getVoies().get(0).addLongueur(newLongueur);
    }

    @And("la modification est soumise")
    public void laModificationEstSoumise() {
        updateSpot = spotService.updateSpot(spotEditForm);

    }

    @Then("le spot (.*) est absent dans la base")
    public void leSpotFEATURESPOTEstAbsentDansLaBase(String spotName) {
        Assert.assertFalse("Le spot " + spotName + " n'existe pas", spotRepository.findBySpotLibelle(spotName).isPresent());
    }

    @And("le nouveau spot (.*) est présent dans la base")
    public void leNouveauSpotSPOTFEATUREEstPresentDansLaBase(String spotName) {
        Assert.assertTrue("Le spot " + spotName + " n'existe pas", spotRepository.findBySpotLibelle(spotName).isPresent());
    }

    @And("il contient {int} longueurs")
    public void ilContientLongueurs(int nbLongueur) {
        Assert.assertEquals(nbLongueur, updateSpot.getSecteurs().get(0).getVoies().get(0).getLongueurs().size());
    }

    @And("sa hauteur max est de (.*) m")
    public void saHauteurMaxM(Integer hauteurMax) {
        Assert.assertEquals(hauteurMax, updateSpot.getHauteurMax());
    }

    @And("sa cotation max est de (.*)$")
    public void saCotationMaxEstDeA(String coteMax) {
        Assert.assertEquals(coteMax, updateSpot.getCotationMax());
    }

    @And("sa cotation min est de (.*)$")
    public void saCotationMinEstDeA(String coteMin) {
        Assert.assertEquals(coteMin, updateSpot.getCotationMin());
    }


}
