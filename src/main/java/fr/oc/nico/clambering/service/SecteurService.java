package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.SecteurEditForm;
import fr.oc.nico.clambering.model.Secteur;
import org.springframework.stereotype.Service;

@Service
public interface SecteurService {
    SecteurEditForm mapSecteur(Secteur secteur);

    SecteurEditForm getNewSecteur();

    Secteur updateSecteur(SecteurEditForm secteur);
}
