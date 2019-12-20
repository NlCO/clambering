package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Spot;
import org.springframework.stereotype.Service;

/**
 * Interface de gestion de la couche service liés aux commentaires
 */
@Service
public interface CommentaireService {

    /**
     * Permet d'ajouter un commentaire à un spot
     *
     * @param spot    ID du spot à modifier
     * @param user    utilisateur effectuant le commentaire
     * @param message message posté par l'utilisateur
     */
    void addComment(Spot spot, String user, String message);

    /**
     * Permet à un membre de modifier un commentaire
     *
     * @param commentaireId ID du commentaire à éditer
     * @param membre        mebre effectuant l'édition
     * @param updateContent nouveau message
     */
    void modifyComment(Integer commentaireId, String membre, String updateContent);

    /**
     * Suppression d'un commentaire
     *
     * @param commentIdToDelete ID du commentaire à supprimer
     */
    void deleteComment(Integer commentIdToDelete);
}
