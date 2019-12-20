package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour l'entité spot géré par le framework SpringData
 */
@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>, SpotCustomRepository {

    Optional<Spot> findBySpotLibelle(String spotLibelle);
}
