package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurEditForm;
import fr.oc.nico.clambering.DTO.VoieEditForm;
import fr.oc.nico.clambering.model.Voie;
import org.springframework.stereotype.Service;

@Service("VoieService")
public class VoieServiceImpl implements VoieService {
    @Override
    public VoieEditForm mapVoie(Voie voie) {
        return new VoieEditForm(voie.getVoieId(), voie.getVoieLibelle());
    }

    @Override
    public VoieEditForm getNewVoie() {
        VoieEditForm voieEditForm = new VoieEditForm();
        voieEditForm.addLongueur(new LongueurEditForm());
        return voieEditForm;
    }

    @Override
    public Voie updateVoie(VoieEditForm voieEditForm) {
        if (voieEditForm.getVoieId() == null) {
            return new Voie(voieEditForm.getVoieNom());
        }
        return new Voie(voieEditForm.getVoieId(), voieEditForm.getVoieNom());
    }


}
