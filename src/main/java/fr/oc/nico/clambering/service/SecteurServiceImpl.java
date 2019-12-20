package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.SecteurEditForm;
import fr.oc.nico.clambering.model.Secteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implémantation de l'interface de gestion de la couche service liés aux secteurs
 */
@Service("SecteurService")
public class SecteurServiceImpl implements SecteurService {

    private final VoieService voieService;

    @Autowired
    public SecteurServiceImpl(VoieService voieService) {
        this.voieService = voieService;
    }

    /**
     * Génére le DTO lié à un secteur
     *
     * @param secteur secteur à convertir
     * @return le DTO
     */
    @Override
    public SecteurEditForm mapSecteur(Secteur secteur) {
        return new SecteurEditForm(secteur.getSecteurId(), secteur.getSecteurLibelle(), secteur.getSecteurDescription());
    }

    /**
     * Crée un nouveau secteur dasn le formulaire
     *
     * @return un nouveau formulaire de secteur
     */
    @Override
    public SecteurEditForm getNewSecteur() {
        SecteurEditForm secteurEditForm = new SecteurEditForm();
        secteurEditForm.addvoie(voieService.getNewVoie());
        return secteurEditForm;
    }

    /**
     * Génère une longueur à partir du formulaire
     *
     * @param secteur formulaire
     * @return le secteur mis à jour
     */
    @Override
    public Secteur updateSecteur(SecteurEditForm secteur) {
        if (secteur.getSecteurId() == null) {
            return new Secteur(secteur.getSecteurNom(), secteur.getSecteurDescription());
        }
        return new Secteur(secteur.getSecteurId(), secteur.getSecteurNom(), secteur.getSecteurDescription());
    }
}
