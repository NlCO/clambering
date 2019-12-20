package fr.oc.nico.clambering.service;

import org.springframework.stereotype.Service;

/**
 * Interface de gestion de la couche service utilisée par le framework SpringSecurity
 */
@Service
public interface SecurityService {
    /**
     * Permet de retourner le pseudo de la perssone loggée
     *
     * @return le pseudo
     */
    String findLoggedInPseudo();

    /**
     * Gestion de l'autologin pour le framework SpringSecurity
     *
     * @param pseudo   utilisateur
     * @param password password
     */
    void autoLogin(String pseudo, String password);
}
