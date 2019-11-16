package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.DTO.SpotFormCriterias;

import java.util.List;

public interface SpotCustomRepository {

    List<Spot> multiCriteriaSpotSearch(SpotFormCriterias criterias);
}
