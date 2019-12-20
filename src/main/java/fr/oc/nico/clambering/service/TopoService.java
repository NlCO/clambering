package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.TopoEditForm;
import fr.oc.nico.clambering.model.Region;
import fr.oc.nico.clambering.model.Topo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface de gestion de la couche service liés aux topos
 */
@Service
public interface TopoService {
    /**
     * Permet d'ajouter un topo à sa liste de topo personnel
     *
     * @param user         proprietaire
     * @param topoEditForm formulaire contenant les informations du top oà enregistrer
     */
    void ajouterTopo(String user, TopoEditForm topoEditForm);

    /**
     * Permet de switcher la disponiilté du topo
     *
     * @param topoId Id du top à modifier
     */
    void switchDispo(Integer topoId);

    /**
     * Permet de supprimer un topo de sa liste personnel
     *
     * @param user   proprietaire
     * @param topoId id du topo
     */
    void supprimerTopo(String user, Integer topoId);

    /**
     * Renvoi la liste des topos du user
     *
     * @param user utilisateur
     * @return la liste des topos perso
     */
    List<Topo> getMesTopos(String user);

    /**
     * Renvoi la liste des regions existantes pour le formulaire de création de topo
     *
     * @return liste des régions
     */
    List<Region> getFormData();

    /**
     * Renvoi la liste des topo reservable pour un utilisateur
     *
     * @param user utilisateur
     * @return liste de topos
     */
    List<Topo> getToposDispo(String user);

    /**
     * Demande de reservation de topo
     *
     * @param user   demander
     * @param topoId id du topo
     */
    void reserverTopo(String user, Integer topoId);

    /**
     * Demande d'annulation de réservation du topo
     *
     * @param user   utilisateur
     * @param topoId id du topo
     */
    void annulerReservationTopo(String user, Integer topoId);

    /**
     * Confirme la reservation d'un topo
     *
     * @param proprietaire proprietaire
     * @param topoId       id du topo
     */
    void confirmerReservation(String proprietaire, Integer topoId);

    /**
     * Confirme la reservation d'un topo
     *
     * @param proprietaire propriétaire
     * @param topoId       id du topo
     */
    void retourPret(String proprietaire, Integer topoId);

    /**
     * Refuse le pret d'un topo
     *
     * @param proprietaire propriétaire
     * @param topoId       id du topo
     */
    void refuserReservation(String proprietaire, Integer topoId);
}