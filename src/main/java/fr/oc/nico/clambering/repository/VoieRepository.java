package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Voie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité voie géré par le framework SpringData
 */
@Repository
public interface VoieRepository extends JpaRepository<Voie, Integer> {
}
