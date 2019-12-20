package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurEditForm;
import fr.oc.nico.clambering.DTO.VoieEditForm;
import fr.oc.nico.clambering.model.Voie;
import org.springframework.stereotype.Service;

/**
 * Implémantation de l'interface de gestion de la couche service liés aux voies
 */
@Service("VoieService")
public class VoieServiceImpl implements VoieService {
    /**
     * Génére le DTO lié à une voie
     *
     * @param voie voie à convertir
     * @return le DTO
     */
    @Override
    public VoieEditForm mapVoie(Voie voie) {
        return new VoieEditForm(voie.getVoieId(), voie.getVoieLibelle());
    }

    /**
     * Crée une nouvelle voie
     *
     * @return le formulaire avec une nouvelle voie
     */
    @Override
    public VoieEditForm getNewVoie() {
        VoieEditForm voieEditForm = new VoieEditForm();
        voieEditForm.addLongueur(new LongueurEditForm());
        return voieEditForm;
    }

    /**
     * Mets à jour un secteur à partir du formulaire
     *
     * @param voieEditForm formulaire
     * @return la voie mise à jour
     */
    @Override
    public Voie updateVoie(VoieEditForm voieEditForm) {
        if (voieEditForm.getVoieId() == null) {
            return new Voie(voieEditForm.getVoieNom());
        }
        return new Voie(voieEditForm.getVoieId(), voieEditForm.getVoieNom());
    }


}
