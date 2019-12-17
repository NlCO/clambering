package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.model.Topo;
import fr.oc.nico.clambering.repository.TopoRepository;
import fr.oc.nico.clambering.service.TopoService;
import org.junit.Assert;


public class F10Stepdefs {

    private final TopoService topoService;
    private final TopoRepository topoRepository;

    private Topo topo;

    public F10Stepdefs(TopoService topoService, TopoRepository topoRepository) {
        this.topoService = topoService;
        this.topoRepository = topoRepository;
    }


    @Given("Une réservation du topo (.*) par (.*)$")
    public void uneReservationDuTopoPar(String topoLibelle, String emprunteur) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        topoService.reserverTopo(emprunteur, topo.getTopoId());
    }

    @When("le proprietaire (.*) valide la réservation du topo (.*)$")
    public void leProprietaireValideLaReservationDuTopo(String proprietaire, String topoLibelle) {
        topoService.confirmerReservation(proprietaire, topo.getTopoId());
    }

    @Then("le status du topo (.*) a changé en indisponible")
    public void leStatusDuTopoAChangeEnIndisponible(String topoLibelle) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        Assert.assertFalse(topo.getDispo());
    }

    @Given("le topo (.*) en cours de prêt")
    public void leTopoEnCoursDePret(String topoLibelle) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        Assert.assertFalse(topo.getDispo());
        Assert.assertNotNull(topo.getEmprunteur());
    }

    @When("le proprietaire (.*) confirme que le topo (.*) est rendu")
    public void leProprietaireConfirmeQueLeTopoEstRendu(String proprietaire, String topoLibelle) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        topoService.retourPret(proprietaire, topo.getTopoId());
    }

    @Then("le topo (.*) n'est plus emprunté")
    public void leTopoNEstPlusEmprunte(String topoLibelle) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        Assert.assertNull(topo.getEmprunteur());
    }

    @And("son statut est de nouveau disponible")
    public void sonStatutEstDeNouveauDisponible() {
        Assert.assertTrue(topo.getDispo());
    }

    @When("le proprietaire (.*) refuse la réservation du topo (.*)$")
    public void leProprietaireRefuseLaReservationDuTopo(String proprietaire, String topoLibelle) {
        topoService.refuserReservation(proprietaire, topo.getTopoId());
    }
}
