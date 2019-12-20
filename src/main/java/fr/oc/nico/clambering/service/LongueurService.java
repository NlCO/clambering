package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurEditForm;
import fr.oc.nico.clambering.model.Longueur;
import org.springframework.stereotype.Service;

/**
 * Interface de gestion de la couche service liés aux longueurs
 */
@Service
public interface LongueurService {
    /**
     * Génére le DTO lié à une longueur
     *
     * @param longueur longueur à convertir
     * @return le DTO
     */
    LongueurEditForm mapLongueur(Longueur longueur);

    /**
     * Génère une longueur à partir du formulaire
     *
     * @param longueurFormRegistration le formulaire
     * @return la longueur
     */
    Longueur updateLongueur(LongueurEditForm longueurFormRegistration);
}
