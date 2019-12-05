package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.TopoEditForm;
import fr.oc.nico.clambering.model.Topo;
import org.springframework.stereotype.Service;

@Service
public interface TopoService {
    void ajouterTopo(String user, TopoEditForm topoEditForm);

    void switchDispo(Topo topo);

    void supprimerTopo(Topo topo);
}
