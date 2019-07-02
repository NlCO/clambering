package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
