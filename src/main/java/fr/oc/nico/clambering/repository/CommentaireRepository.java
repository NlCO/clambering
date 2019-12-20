package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité commentaire géré par le framework SpringData
 */
@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
}
