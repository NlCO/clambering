package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Spot;
import org.springframework.stereotype.Service;

@Service
public interface CommentaireService {
    void addComment(Spot spotId, String user, String message);

    void modifyComment(Integer commentaireId, String membre, String updateContent);

    void deleteComment(Integer commentIdToDelete);
}
