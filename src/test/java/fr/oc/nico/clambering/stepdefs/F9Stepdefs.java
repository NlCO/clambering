package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.model.Topo;
import fr.oc.nico.clambering.repository.TopoRepository;
import fr.oc.nico.clambering.service.TopoService;
import org.junit.Assert;

import java.util.List;

public class F9Stepdefs {

    private final TopoService topoService;
    private final TopoRepository topoRepository;

    private List<Topo> topos;
    private Topo topo;

    public F9Stepdefs(TopoService topoService, TopoRepository topoRepository) {
        this.topoService = topoService;
        this.topoRepository = topoRepository;
    }

    @Given("une base contenant (.*) topos")
    public void uneBaseDeTopos(Integer nombreTopos) {
        Integer nombreToposBase = topoRepository.findAll().size();
        Assert.assertEquals(nombreTopos, nombreToposBase);
    }

    @When("(.*) consulte la liste des topos disponibles")
    public void azertyConsulteLaListeDesToposDisponibles(String user) {
        topos = topoService.getToposDispo(user);
    }

    @Then("une liste de (.*) topos disponibles est retournée")
    public void uneListeDeToposDisponiblesEstRetournee(int nbTopoDispo) {
        Assert.assertEquals(nbTopoDispo, topos.size());
    }

    @Given("la liste des topos disponibles à la reservation pour (.*)$")
    public void laListeDesToposDisponiblesALaReservationPourAzerty(String user) {
        topos = topoService.getToposDispo(user);
    }

    @And("le topo (.*) est disposnible à la réservation")
    public void leTopoEstDisposnibleALaReservation(String topoLibelle) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
    }

    @When("le user (.*) demande de reserver le topo")
    public void leUserAzertyDemandeDeReserverLeTopoErat(String user) {
        Integer topoId = topo.getTopoId();
        topoService.reserverTopo(user, topoId);
    }

    @Then("le topo (.*) contient une réservation pour (.*)$")
    public void leTopoContientUneReservationPour(String topoLibelle, String user) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        Assert.assertEquals(user, topo.getEmprunteur().getPseudo());
    }

    @And("le topo (.*) apparait toujours disponible")
    public void leTopoApparaitToujoursDisponible(String topoLibelle) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        Assert.assertTrue(topo.getDispo());
    }

    @Given("le topo (.*) est deja reservé par (.*)")
    public void leTopoEstDejaReservePar(String topoLibelle, String user) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        Assert.assertEquals(user, topo.getEmprunteur().getPseudo());
        Assert.assertTrue(topo.getDispo());
    }

    @When("(.*) demande d'annuler sa reservation du topo")
    public void DemandeDAnnulerSaReservationDuTopo(String user) {
        topoService.annulerReservationTopo(user, topo.getTopoId());
    }

    @Then("le topo (.*) ne contient plus de réservation")
    public void leTopoNeContientPlusDeReservation(String topoLibelle) {
        topo = topoRepository.findByTopoLibelle(topoLibelle).orElse(null);
        Assert.assertNull(topo.getEmprunteur());
    }


}
