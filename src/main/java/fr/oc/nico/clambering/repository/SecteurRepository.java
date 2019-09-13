package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Integer> {
}
