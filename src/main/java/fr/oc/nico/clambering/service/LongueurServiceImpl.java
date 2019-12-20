package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.DTO.LongueurEditForm;
import fr.oc.nico.clambering.model.Longueur;
import org.springframework.stereotype.Service;

/**
 * Implémantation de l'interface de gestion de la couche service liés aux longueur
 */
@Service("LongueurService")
public class LongueurServiceImpl implements LongueurService {

    /**
     * Génére le DTO lié à une longueur
     *
     * @param longueur longueur à convertir
     * @return le DTO
     */
    @Override
    public LongueurEditForm mapLongueur(Longueur longueur) {
        return new LongueurEditForm(longueur.getLongueurId(), longueur.getLongueurLibelle(), longueur.getHauteur(), longueur.getCotation(), longueur.getDegaine(), longueur.getIdRelaiDebut(), longueur.getIdRelaiFin());
    }

    /**
     * Génère une longueur à partir du formulaire
     *
     * @param longueurFormRegistration formulaire
     * @return la longueur
     */
    @Override
    public Longueur updateLongueur(LongueurEditForm longueurFormRegistration) {
        if (longueurFormRegistration.getLongueurId() == null) {
            return new Longueur(longueurFormRegistration.getLongueurNom(), longueurFormRegistration.getHauteur(), longueurFormRegistration.getCotation(), longueurFormRegistration.getDegaine());
        }
        return new Longueur(longueurFormRegistration.getLongueurId(), longueurFormRegistration.getLongueurNom(), longueurFormRegistration.getHauteur(), longueurFormRegistration.getCotation(), longueurFormRegistration.getDegaine());
    }
}
