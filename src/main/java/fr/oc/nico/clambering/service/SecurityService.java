package fr.oc.nico.clambering.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String findLoggedInPseudo();

    void autoLogin(String pseudo, String password);
}
