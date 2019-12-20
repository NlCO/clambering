package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Utilisateur;
import org.springframework.stereotype.Service;

/**
 * Interface de gestion de la couche service liés aux utilisateurs
 */
@Service
public interface UtilisateurService {

    /**
     * Permet l'inscription d'un utilisateur
     *
     * @param utilisateur utilisateur
     * @return
     */
    String inscrire(Utilisateur utilisateur);

    /**
     * Retourne l'utilisateur à partir de son pseudo
     *
     * @param pseudo pseudo
     * @return l'utilisateur
     */
    Utilisateur findByPseudo(String pseudo);

    /**
     * Retourne l'utilisateur à partir de son email
     *
     * @param email email
     * @return l'utilisateur
     */
    Utilisateur findByEmail(String email);
}
