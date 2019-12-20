package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité utilisateur géré par le framework SpringData
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByPseudo(String pseudo);

    Utilisateur findByEmail(String email);
}
