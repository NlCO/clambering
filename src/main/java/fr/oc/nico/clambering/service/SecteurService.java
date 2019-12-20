package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.SecteurEditForm;
import fr.oc.nico.clambering.model.Secteur;
import org.springframework.stereotype.Service;

/**
 * Interface de gestion de la couche service liés aux secteurs
 */
@Service
public interface SecteurService {
    /**
     * Génére le DTO lié à un secteur
     *
     * @param secteur secteur à convertir
     * @return le DTO
     */
    SecteurEditForm mapSecteur(Secteur secteur);

    /**
     * Crée un nouveau secteur
     *
     * @return un nouveau secteur
     */
    SecteurEditForm getNewSecteur();

    /**
     * Mets à jour un secteur à partir du formulaire
     *
     * @param secteur formulaire
     * @return le secteru mis à jour
     */
    Secteur updateSecteur(SecteurEditForm secteur);
}
