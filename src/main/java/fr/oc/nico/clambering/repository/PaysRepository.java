package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaysRepository extends JpaRepository<Pays, Integer> {
}
