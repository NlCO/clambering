package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>, SpotCustomRepository {
}
