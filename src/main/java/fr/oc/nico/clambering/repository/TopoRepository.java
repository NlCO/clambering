package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Topo;
import fr.oc.nico.clambering.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour l'entité topo géré par le framework SpringData
 */
@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {
    Optional<Topo> findByTopoLibelle(String topoName);

    List<Topo> findAllByProprietaire(Utilisateur proprietaire);

    List<Topo> findAllByProprietaireNotAndDispoIsTrue(Utilisateur proprietaire);
}
