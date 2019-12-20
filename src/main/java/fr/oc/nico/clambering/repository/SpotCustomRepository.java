package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.DTO.SpotFormCriterias;
import fr.oc.nico.clambering.model.Spot;

import java.util.List;

/**
 * Repository pour l'entité spot géré par le framework SpringData
 */
public interface SpotCustomRepository {

    List<Spot> multiCriteriaSpotSearch(SpotFormCriterias criterias);
}
