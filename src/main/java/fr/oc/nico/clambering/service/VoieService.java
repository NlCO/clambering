package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.VoieEditForm;
import fr.oc.nico.clambering.model.Voie;
import org.springframework.stereotype.Service;

@Service
public interface VoieService {
    VoieEditForm mapVoie(Voie voie);

    VoieEditForm getNewVoie();

    Voie updateVoie(VoieEditForm voieEditForm);

}
