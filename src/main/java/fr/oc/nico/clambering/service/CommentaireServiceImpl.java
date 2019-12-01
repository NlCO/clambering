package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Commentaire;
import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.CommentaireRepository;
import fr.oc.nico.clambering.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("CommentaireService")
public class CommentaireServiceImpl implements CommentaireService {

    private CommentaireRepository commentaireRepository;
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public CommentaireServiceImpl(CommentaireRepository commentaireRepository, UtilisateurRepository utilisateurRepository) {
        this.commentaireRepository = commentaireRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void addComment(Spot spot, String user, String message) {
        Commentaire comment = new Commentaire(utilisateurRepository.findByPseudo(user), spot, message);
        commentaireRepository.save(comment);
    }

    @Override
    public void modifyComment(Integer commentaireId, String membre, String updateContent) {
        Commentaire commentaireToModify = commentaireRepository.findById(commentaireId).orElse(null);
        commentaireToModify.setModerateur(utilisateurRepository.findByPseudo(membre));
        commentaireToModify.setDateModeration(new Date());
        commentaireToModify.setCommentaire(updateContent);
        commentaireRepository.save(commentaireToModify);
    }

    @Override
    public void deleteComment(Integer commentIdToDelete) {
        Commentaire commentaireToDelete = commentaireRepository.findById(commentIdToDelete).orElse(null);
        commentaireRepository.delete(commentaireToDelete);
    }
}
