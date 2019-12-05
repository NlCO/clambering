package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {
    Optional<Topo> findByTopoLibelle(String topoName);
}
