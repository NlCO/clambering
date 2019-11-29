package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.service.CommentaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentaireController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotController.class);

    private CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @RequestMapping(value = "/spot/{spotId}/commentaire/{commentaireId}/del")
    public String deleteComment(@PathVariable Integer spotId, @PathVariable Integer commentaireId) {
        commentaireService.deleteComment(commentaireId);
        return "redirect:/spots/" + spotId;
    }

}
