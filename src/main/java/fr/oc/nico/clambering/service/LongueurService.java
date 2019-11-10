package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurFormRegistration;
import fr.oc.nico.clambering.model.Longueur;
import org.springframework.stereotype.Service;

@Service
public interface LongueurService {
    LongueurFormRegistration mapLongueur(Longueur longueur);

    Longueur updateLongueur(LongueurFormRegistration longueurFormRegistration);
}
