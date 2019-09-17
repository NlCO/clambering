package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Utilisateur;
import fr.oc.nico.clambering.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service("UtilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public String inscrire(Utilisateur utilisateur) {
        try {
            utilisateurRepository.save(utilisateur);
            return "Utilisateur créé";
        } catch (DataIntegrityViolationException e) {
            return "Echec - Email déjà utilisé";
        }
    }
}
