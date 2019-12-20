package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.VoieEditForm;
import fr.oc.nico.clambering.model.Voie;
import org.springframework.stereotype.Service;

/**
 * Interface de gestion de la couche service liés aux voies
 */
@Service
public interface VoieService {
    /**
     * Génére le DTO lié à une voie
     *
     * @param voie voie à convertir
     * @return le DTO
     */
    VoieEditForm mapVoie(Voie voie);

    /**
     * Crée une nouvelle voie
     *
     * @return le formulaire avec une nouvelle voie
     */
    VoieEditForm getNewVoie();

    /**
     * Mets à jour un secteur à partir du formulaire
     *
     * @param voieEditForm formulaire
     * @return la voie mise à jour
     */
    Voie updateVoie(VoieEditForm voieEditForm);

}
