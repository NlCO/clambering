package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurFormRegistration;
import fr.oc.nico.clambering.model.Longueur;
import org.springframework.stereotype.Service;

@Service("LongueurService")
public class LongueurServiceImpl implements LongueurService {

    @Override
    public LongueurFormRegistration mapLongueur(Longueur longueur) {
        return new LongueurFormRegistration(longueur.getLongueurId(), longueur.getLongueurLibelle(),longueur.getHauteur(),longueur.getCotation(),longueur.getDegaine(),longueur.getIdRelaiDebut(),longueur.getIdRelaiFin());
    }

    @Override
    public Longueur updateLongueur(LongueurFormRegistration longueurFormRegistration) {
        if (longueurFormRegistration.getLongueurId() == null) {
            return new Longueur(longueurFormRegistration.getLongueurNom(), longueurFormRegistration.getHauteur(), longueurFormRegistration.getCotation(), longueurFormRegistration.getDegaine());
        }
        return new Longueur(longueurFormRegistration.getLongueurId(), longueurFormRegistration.getLongueurNom(), longueurFormRegistration.getHauteur(), longueurFormRegistration.getCotation(), longueurFormRegistration.getDegaine());
    }
}
