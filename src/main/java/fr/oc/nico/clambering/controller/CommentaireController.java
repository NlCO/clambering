package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.service.CommentaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur concernant les entités commentaire des spots
 */
@Controller
public class CommentaireController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotController.class);

    private CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    /**
     * Requete permattant à un membre de supprimer un commentaire
     *
     * @param spotId        Id du spot contenant le commentaire
     * @param commentaireId Id du commentà supprimer
     * @return redirection sur la vue du spot
     */
    @RequestMapping(value = "/spot/{spotId}/commentaire/{commentaireId}/del")
    public String deleteComment(@PathVariable Integer spotId, @PathVariable Integer commentaireId) {
        commentaireService.deleteComment(commentaireId);
        return "redirect:/spots/" + spotId;
    }

    /**
     * Requete permattant à un memndre d'éditer un commentaire
     *
     * @param spotId        Id du spot contenant le commentaire
     * @param commentaireId Id du commentaire à modifier
     * @param req           ServletRequest contenant les informations de modifications
     * @return redirectino sur la vue du spot
     */
    @RequestMapping(value = "/spot/{spotId}/commentaire/{commentaireId}/edit")
    public String editComment(@PathVariable Integer spotId, @PathVariable Integer commentaireId, final HttpServletRequest req) {
        commentaireService.modifyComment(commentaireId, req.getRemoteUser(), req.getParameter("commentEdit"));
        return "redirect:/spots/" + spotId;
    }
}
