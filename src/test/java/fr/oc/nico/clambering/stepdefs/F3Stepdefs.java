package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import fr.oc.nico.clambering.model.Utilisateur;
import fr.oc.nico.clambering.service.UtilisateurService;
import org.junit.Assert;


public class F3Stepdefs {

    private UtilisateurService utilisateurService;

    private Utilisateur utilisateur;

    private String retourInscritpion;

    F3Stepdefs(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @Given("un utilisateur souhaite s'incrire avec le pseudo {string}, le password {string} et un email {string} non existant")
    public void unUtilisateurSouhaiteSIncrireAvecLePseudoLePasswordEtUnEmailNonExistant(String pseudo, String password, String email) {
        utilisateur = new Utilisateur();
        utilisateur.setPseudo(pseudo);
        utilisateur.setPassword(password);
        utilisateur.setEmail(email);
    }

    @When("il soumet la demande d'inscription")
    public void ilSoumetLaDemandeDInscription() {
        retourInscritpion = utilisateurService.inscrire(utilisateur);
    }

    @Then("l'utilisateur est présent dans la base de données")
    public void lUtilisateurEstPresentDansLaBaseDeDonnees() {
        Assert.assertEquals("Utilisateur créé", retourInscritpion);
    }

    @Then("l\\'utilsateur n\\'est pas présent dans la base de données")
    public void lUtilsateurNEstPasPresentDansLaBaseDeDonnees() {
        Assert.assertEquals("Echec - Email déjà utilisé", retourInscritpion);
    }
}
