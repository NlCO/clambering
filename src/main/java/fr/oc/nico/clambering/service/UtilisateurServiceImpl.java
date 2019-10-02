package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Utilisateur;
import fr.oc.nico.clambering.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("UtilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String inscrire(Utilisateur utilisateur) {
        try {
            utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
            utilisateur.setMembreAssociation(false);
            utilisateurRepository.save(utilisateur);
            return "Utilisateur créé";
        } catch (DataIntegrityViolationException e) {
            return "Echec - Email déjà utilisé";
        }
    }

    @Override
    public Utilisateur findByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
