package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurEditForm;
import fr.oc.nico.clambering.model.Longueur;
import org.springframework.stereotype.Service;

@Service("LongueurService")
public class LongueurServiceImpl implements LongueurService {

    @Override
    public LongueurEditForm mapLongueur(Longueur longueur) {
        return new LongueurEditForm(longueur.getLongueurId(), longueur.getLongueurLibelle(), longueur.getHauteur(), longueur.getCotation(), longueur.getDegaine(), longueur.getIdRelaiDebut(), longueur.getIdRelaiFin());
    }

    @Override
    public Longueur updateLongueur(LongueurEditForm longueurFormRegistration) {
        if (longueurFormRegistration.getLongueurId() == null) {
            return new Longueur(longueurFormRegistration.getLongueurNom(), longueurFormRegistration.getHauteur(), longueurFormRegistration.getCotation(), longueurFormRegistration.getDegaine());
        }
        return new Longueur(longueurFormRegistration.getLongueurId(), longueurFormRegistration.getLongueurNom(), longueurFormRegistration.getHauteur(), longueurFormRegistration.getCotation(), longueurFormRegistration.getDegaine());
    }
}
