package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité secteur géré par le framework SpringData
 */
@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Integer> {
}
