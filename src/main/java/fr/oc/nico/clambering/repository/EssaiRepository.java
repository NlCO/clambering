package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.entity.Essai;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EssaiRepository extends JpaRepository<Essai, Integer> {
}
