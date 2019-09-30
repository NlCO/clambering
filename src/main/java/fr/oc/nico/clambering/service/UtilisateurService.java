package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface UtilisateurService {
    String inscrire(Utilisateur utilisateur);

    Utilisateur findByPseudo(String pseudo);

    Utilisateur findByEmail(String email);
}
