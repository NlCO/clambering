package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Commentaire;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.CommentaireRepository;
import fr.oc.nico.clambering.repository.SpotRepository;
import fr.oc.nico.clambering.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CommentaireService")
public class CommentaireServiceImpl implements CommentaireService {

    private CommentaireRepository commentaireRepository;
    private SpotRepository spotRepository;
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public CommentaireServiceImpl(CommentaireRepository commentaireRepository, SpotRepository spotRepository, UtilisateurRepository utilisateurRepository) {
        this.commentaireRepository = commentaireRepository;
        this.spotRepository = spotRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void addComment(Spot spot, String user, String message) {
        Commentaire comment = new Commentaire(utilisateurRepository.findByPseudo(user),spot, message);
        commentaireRepository.save(comment);
    }
}
