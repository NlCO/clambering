package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.TopoEditForm;
import fr.oc.nico.clambering.model.Region;
import fr.oc.nico.clambering.model.Topo;
import fr.oc.nico.clambering.model.Utilisateur;
import fr.oc.nico.clambering.repository.RegionRepository;
import fr.oc.nico.clambering.repository.TopoRepository;
import fr.oc.nico.clambering.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Implémantation de l'interface de gestion de la couche service liés aux secteurs
 */
@Service("TopoService")
public class TopoServiceImpl implements TopoService {

    private TopoRepository topoRepository;
    private RegionRepository regionRepository;
    private UtilisateurRepository utilisateurRepository;

    private Topo topo;

    @Autowired
    public TopoServiceImpl(TopoRepository topoRepository, RegionRepository regionRepository, UtilisateurRepository utilisateurRepository) {
        this.topoRepository = topoRepository;
        this.regionRepository = regionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Permet d'ajouter un topo à sa liste de topo personnel
     *
     * @param user         proprietaire
     * @param topoEditForm formulaire contenant les informations du top oà enregistrer
     */
    @Override
    public void ajouterTopo(String user, TopoEditForm topoEditForm) {
        String topoLibelle = topoEditForm.getTopoLibelle();
        Region lieu = regionRepository.findByRegionLibelle(topoEditForm.getLieu());
        String description = topoEditForm.getDescription();
        Date dateParution = null;
        try {
            dateParution = new SimpleDateFormat("yyyy-MM-dd").parse(topoEditForm.getDateParution());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Utilisateur proprietaire = utilisateurRepository.findByPseudo(user);
        Topo topo = new Topo(topoLibelle, lieu, description, dateParution, proprietaire);
        topoRepository.save(topo);
    }

    /**
     * Permet de switcher la disponiilté du topo
     *
     * @param topoId Id du top à modifier
     */
    @Override
    public void switchDispo(Integer topoId) {
        topo = getTopo(topoId);
        if (topo.getDispo()) {
            topo.setDispo(false);
        } else {
            topo.setDispo(true);
        }
        topoRepository.save(topo);
    }

    /**
     * Permet de supprimer un topo de sa liste personnel
     *
     * @param user   proprietaire
     * @param topoId id du topo
     */
    @Override
    public void supprimerTopo(String user, Integer topoId) {
        topo = getTopo(topoId);
        String propietaire = topo.getProprietaire().getPseudo();
        if (propietaire.equals(user)) {
            topoRepository.delete(topo);
        }
    }

    /**
     * Renvoi la liste des topos du user
     *
     * @param user utilisateur
     * @return la liste des topos perso
     */
    @Override
    public List<Topo> getMesTopos(String user) {
        Utilisateur proprietaire = utilisateurRepository.findByPseudo(user);
        return topoRepository.findAllByProprietaire(proprietaire);
    }

    /**
     * Renvoi la liste des regions existantes pour le formulaire de création de topo
     *
     * @return liste des régions
     */
    @Override
    public List<Region> getFormData() {
        return regionRepository.findAll();
    }

    /**
     * Renvoi la liste des topo reservable pour un utilisateur
     *
     * @param user utilisateur
     * @return liste de topos
     */
    @Override
    public List<Topo> getToposDispo(String user) {
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(user);
        return topoRepository.findAllByProprietaireNotAndDispoIsTrue(utilisateur);
    }

    /**
     * Demande de reservation de topo
     *
     * @param user   demander
     * @param topoId id du topo
     */
    @Override
    public void reserverTopo(String user, Integer topoId) {
        topo = getTopo(topoId);
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(user);
        if (topo.getEmprunteur() == null) {
            topo.setEmprunteur(utilisateur);
        }
        topoRepository.save(topo);
    }

    /**
     * Demande de reservation de topo
     *
     * @param user   utilisateur
     * @param topoId id du topo
     */
    @Override
    public void annulerReservationTopo(String user, Integer topoId) {
        topo = getTopo(topoId);
        if (topo.getDispo() && user.equals(topo.getEmprunteur().getPseudo())) {
            topo.setEmprunteur(null);
        }
        topoRepository.save(topo);
    }

    /**
     * Confirme la reservation d'un topo
     * Demande d'annulation de réservation du topo
     *
     * @param proprietaire proprietaire
     * @param topoId       id du topo
     */
    @Override
    public void confirmerReservation(String proprietaire, Integer topoId) {
        topo = getTopo(topoId);
        if (proprietaire.equals(topo.getProprietaire().getPseudo())) {
            topo.setDispo(false);
        }
        topoRepository.save(topo);
    }

    /**
     * Confirme la reservation d'un topo
     *
     * @param proprietaire propriétaire
     * @param topoId       id du topo
     */
    @Override
    public void retourPret(String proprietaire, Integer topoId) {
        topo = getTopo(topoId);
        if (proprietaire.equals(topo.getProprietaire().getPseudo()) && !topo.getDispo()) {
            topo.setDispo(true);
            topo.setEmprunteur(null);
        }
        topoRepository.save(topo);
    }


    /**
     * Refuse le pret d'un topo
     *
     * @param proprietaire propriétaire
     * @param topoId       id du topo
     */
    @Override
    public void refuserReservation(String proprietaire, Integer topoId) {
        topo = getTopo(topoId);
        if (proprietaire.equals(topo.getProprietaire().getPseudo())) {
            topo.setEmprunteur(null);
        }
        topoRepository.save(topo);
    }

    /**
     * Retourne le top oa partir de son Id
     *
     * @param topoId id du topo
     * @return le tpopo
     */
    private Topo getTopo(Integer topoId) {
        return topoRepository.findById(topoId).orElse(null);
    }
}
