package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Utilisateur;
import fr.oc.nico.clambering.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("UserDetailsService")
@Primary
//annotation Primary pour résoudre les erreurs d'autowired : there is more than one UserDetailsService
public class UserDetailsServiceImpl implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);
        if (utilisateur == null) throw new UsernameNotFoundException(pseudo);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        String utilisateurRole = (utilisateur.getMembreAssociation() ? "Membre" : "Internaute");
        grantedAuthorities.add(new SimpleGrantedAuthority(utilisateurRole));

        return new org.springframework.security.core.userdetails.User(utilisateur.getPseudo(), utilisateur.getPassword(), grantedAuthorities);
    }
}
