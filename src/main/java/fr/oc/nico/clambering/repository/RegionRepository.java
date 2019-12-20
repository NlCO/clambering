package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité region géré par le framework SpringData
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region findByRegionLibelle(String region);
}
