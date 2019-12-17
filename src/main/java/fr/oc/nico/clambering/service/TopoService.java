package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.TopoEditForm;
import fr.oc.nico.clambering.model.Region;
import fr.oc.nico.clambering.model.Topo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopoService {
    void ajouterTopo(String user, TopoEditForm topoEditForm);

    void switchDispo(Integer topoId);

    void supprimerTopo(String user, Integer topoId);

    List<Topo> getMesTopos(String user);

    List<Region> getFormData();

    List<Topo> getToposDispo(String user);

    void reserverTopo(String user, Integer topoId);

    void annulerReservationTopo(String user, Integer topoId);

    void confirmerReservation(String proprietaire, Integer topoId);

    void retourPret(String proprietaire, Integer topoId);

    void refuserReservation(String proprietaire, Integer topoId);
}