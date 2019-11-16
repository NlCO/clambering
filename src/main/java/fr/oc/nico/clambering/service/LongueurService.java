package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurEditForm;
import fr.oc.nico.clambering.model.Longueur;
import org.springframework.stereotype.Service;

@Service
public interface LongueurService {
    LongueurEditForm mapLongueur(Longueur longueur);

    Longueur updateLongueur(LongueurEditForm longueurFormRegistration);
}
