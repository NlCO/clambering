package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import fr.oc.nico.clambering.DTO.TopoEditForm;
import fr.oc.nico.clambering.model.Topo;
import fr.oc.nico.clambering.repository.TopoRepository;
import fr.oc.nico.clambering.service.TopoService;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class F8StepDef {

    private final TopoRepository topoRepository;
    private final TopoService topoService;

    private TopoEditForm topoEditForm;
    private int nbTopos;
    private Topo topo;



    public F8StepDef(TopoRepository topoRepository, TopoService topoService) {
        this.topoRepository = topoRepository;
        this.topoService = topoService;
    }

    @Given("le topo (.*) sur la region (.*) paru le (.*) avec la description (.*)$")
    public void leTopoSurLaRegionParuLeAvecLaDescription(String topoName, String region, String parution, String description) {
        Date dateParution = null;
        try {
            dateParution = new SimpleDateFormat("dd/MM/yyyy").parse(parution);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        topoEditForm = new TopoEditForm(topoName, description, region, dateParution);
    }

    @And("une base de données avec n topos")
    public void uneBaseDeDonneesAvecNTopos() {
        nbTopos = topoRepository.findAll().size();
    }

    @When("(.*) enregistre le nouveau topo")
    public void EnregistreLeNouveauTopo(String user) {
        topoService.ajouterTopo(user, topoEditForm);
    }

    @Then("la base contient {int} topo de plus")
    public void laBaseContientTopoDePlus(int nb) {
        Assert.assertEquals(nbTopos + nb, topoRepository.findAll().size());
    }

    @And("le topo (.*) est présent dans la base")
    public void leTopoEstPresentDansLaBase(String topoName) {
        Assert.assertTrue(topoRepository.findByTopoLibelle(topoName).isPresent());
    }

    @Given("le (.*) est indisponible")
    public void leTopoEstIndisponible(String topoName) {
        topo = topoRepository.findByTopoLibelle(topoName).orElse(null);
        Assert.assertEquals(false, topo.getDispo());
    }

    @When("azerty rends sont statut disponible")
    public void azertyRendsSontStatutDisponible() {
        topoService.switchDispo(topo);
    }

    @Then("le (.*) est maintenant disponible")
    public void leTopoEstMaintenantDisponible(String topoName) {
        topo = topoRepository.findByTopoLibelle(topoName).orElse(null);
        Assert.assertEquals(true, topo.getDispo());
    }

    @Given("le (.*) est disponible")
    public void leTopoEstDisponible(String topoName) {
        topo = topoRepository.findByTopoLibelle(topoName).orElse(null);
        Assert.assertEquals(true, topo.getDispo());
    }

    @When("azerty rends sont statut indisponible")
    public void azertyRendsSontStatutIndisponible() {
        topoService.switchDispo(topo);
    }

    @Then("le (.*) est maintenant indisponible")
    public void leTopoEstMaintenantIndisponible(String topoName) {
        topo = topoRepository.findByTopoLibelle(topoName).orElse(null);
        Assert.assertEquals(false, topo.getDispo());
    }

    @Given("le topo (.*) existe")
    public void leTopoTopoExiste(String topoName) {
        topo = topoRepository.findByTopoLibelle(topoName).orElse(null);
        Assert.assertNotNull(topo);
    }

    @When("azerty supprimre le topo")
    public void azertySupprimreLeTopo() {
        topoService.supprimerTopo(topo);
    }

    @Then("le topo (.*) n'existe plus")
    public void leTopoTopoNExistePlus(String topoName) {
        topo = topoRepository.findByTopoLibelle(topoName).orElse(null);
        Assert.assertNull(topo);
    }
}
