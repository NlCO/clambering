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

import java.util.Date;

@Service("TopoService")
public class TopoServiceImpl implements TopoService {

    private TopoRepository topoRepository;
    private RegionRepository regionRepository;
    private UtilisateurRepository utilisateurRepository;

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
        Date dateParution = topoEditForm.getDateParution();
        Utilisateur proprietaire = utilisateurRepository.findByPseudo(user);
        Topo topo = new Topo(topoLibelle, lieu, description, dateParution, proprietaire);
        topoRepository.save(topo);
    }

    @Override
    public void switchDispo(Topo topo) {
        if (topo.getDispo()) {
            topo.setDispo(false);
        } else {
            topo.setDispo(true);
        }
        topoRepository.save(topo);
    }

    @Override
    public void supprimerTopo(Topo topo) {
        topoRepository.delete(topo);
    }
}
