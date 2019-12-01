package fr.oc.nico.clambering.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.oc.nico.clambering.model.Commentaire;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.SpotRepository;
import fr.oc.nico.clambering.service.CommentaireService;
import org.junit.Assert;

public class F7Stepdefs {

    private Commentaire commentaire;
    private Spot spot;
    private Integer commentIdToDelete;

    private CommentaireService commentaireService;
    private SpotRepository spotRepository;

    public F7Stepdefs(CommentaireService commentaireService, SpotRepository spotRepository) {
        this.commentaireService = commentaireService;
        this.spotRepository = spotRepository;
    }

    @Given("le commentaire (.*) du spot (.*)$")
    public void leCommentaireDuSpot(Integer nbComment, String spotName) {
        spot = spotRepository.findBySpotLibelle(spotName).orElse(null);
        commentaire = spot.getCommentaires().get(nbComment - 1);
    }

    @And("ce dernier contient (.*)$")
    public void ceDernierContientTest(String commentLibelle) {
        Assert.assertEquals(commentLibelle, commentaire.getCommentaire());
    }

    @When("(.*) modifie sont contenu par (.*)")
    public void modifieSontContenu(String membre, String updateContent) {
        commentaireService.modifyComment(commentaire.getCommentaireId(), membre, updateContent);
        spot = spotRepository.findById(spot.getSpotId()).orElse(null);
    }

    @Then("le spot contient toujours (.*) commentaire")
    public void leSpotContientToujoursCommentaire(Integer nbComment) {
        Integer result = spot.getCommentaires().size();
        Assert.assertEquals(nbComment, result);
    }

    @And("le nouveau comentaire est (.*)$")
    public void ilContientChangeContent(String comment) {
        String result = spot.getCommentaires().get(0).getCommentaire();
        Assert.assertEquals(comment, result);
    }

    @And("ayant son id")
    public void ayantSonId() {
        commentIdToDelete = spot.getCommentaires().get(0).getCommentaireId();
    }

    @When("qsdfgh le supprime")
    public void qsdfghLeSupprime() {
        commentaireService.deleteComment(commentIdToDelete);
        spot = spotRepository.findById(spot.getSpotId()).orElse(null);
    }

    @Then("le spot contient maintenant (.*) commentaire")
    public void leSpotContientMaintenantCommentaire(Integer nbComment) {
        Integer result = spot.getCommentaires().size();
        Assert.assertEquals(nbComment, result);
    }

}
