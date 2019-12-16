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

    @Override
    public void supprimerTopo(String user, Integer topoId) {
        topo = getTopo(topoId);
        String propietaire = topo.getProprietaire().getPseudo();
        if (propietaire.equals(user)) {
            topoRepository.delete(topo);
        }
    }

    @Override
    public List<Topo> getMesTopos(String user) {
        Utilisateur proprietaire = utilisateurRepository.findByPseudo(user);
        return topoRepository.findAllByProprietaire(proprietaire);
    }

    @Override
    public List<Region> getFormData() {
        return regionRepository.findAll();
    }

    @Override
    public List<Topo> getToposDispo(String user) {
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(user);
        return topoRepository.findAllByProprietaireNotAndDispoIsTrue(utilisateur);
    }

    @Override
    public void reserverTopo(String user, Integer topoId) {
        topo = getTopo(topoId);
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(user);
        if (topo.getEmprunteur() == null) {
            topo.setEmprunteur(utilisateur);
        }
        topoRepository.save(topo);
    }

    @Override
    public void annulerReservationTopo(String user, Integer topoId) {
        topo = getTopo(topoId);
        if (topo.getDispo() && user.equals(topo.getEmprunteur().getPseudo())) {
            topo.setEmprunteur(null);
        }
        topoRepository.save(topo);
    }

    @Override
    public void confirmerReservation(String proprietaire, Integer topoId) {
        topo = getTopo(topoId);
        if (proprietaire.equals(topo.getProprietaire().getPseudo())) {
            topo.setDispo(false);
        }
        topoRepository.save(topo);
    }

    @Override
    public void retourPret(String proprietaire, Integer topoId) {
        topo = getTopo(topoId);
        if (proprietaire.equals(topo.getProprietaire().getPseudo()) && !topo.getDispo()) {
            topo.setDispo(true);
            topo.setEmprunteur(null);
        }
        topoRepository.save(topo);
    }

    private Topo getTopo(Integer topoId) {
        return topoRepository.findById(topoId).orElse(null);
    }
}
