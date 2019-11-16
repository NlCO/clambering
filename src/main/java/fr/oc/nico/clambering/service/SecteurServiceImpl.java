package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.SecteurEditForm;
import fr.oc.nico.clambering.model.Secteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SecteurService")
public class SecteurServiceImpl implements SecteurService {

    private final VoieService voieService;

    @Autowired
    public SecteurServiceImpl(VoieService voieService) {
        this.voieService = voieService;
    }

    @Override
    public SecteurEditForm mapSecteur(Secteur secteur) {
        return new SecteurEditForm(secteur.getSecteurId(), secteur.getSecteurLibelle(),secteur.getSecteurDescription());
    }

    @Override
    public SecteurEditForm getNewSecteur() {
        SecteurEditForm secteurEditForm = new SecteurEditForm();
        secteurEditForm.addvoie(voieService.getNewVoie());
        return secteurEditForm;
    }

    @Override
    public Secteur updateSecteur(SecteurEditForm secteur) {
        if (secteur.getSecteurId() == null) {
            return new Secteur(secteur.getSecteurNom(),secteur.getSecteurDescription());
        }
        return new Secteur(secteur.getSecteurId(), secteur.getSecteurNom(),secteur.getSecteurDescription());
    }
}
