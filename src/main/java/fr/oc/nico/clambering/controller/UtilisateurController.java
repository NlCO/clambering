package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.configuration.UtilisateurValidator;
import fr.oc.nico.clambering.model.Utilisateur;
import fr.oc.nico.clambering.service.SecurityService;
import fr.oc.nico.clambering.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlleur gérant les requetes liées à la gestion des utilisateurs
 */
@Controller
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    private SecurityService securityService;

    private UtilisateurValidator utilisateurValidator;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, SecurityService securityService, UtilisateurValidator utilisateurValidator) {
        this.utilisateurService = utilisateurService;
        this.securityService = securityService;
        this.utilisateurValidator = utilisateurValidator;
    }

    /**
     * Permet la demande de creation d'un nouvel utilisateur
     *
     * @param model données Model pour constituer la vue
     * @return la page d'enregistrement
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("utilisateurForm", new Utilisateur());
        return "registration";
    }

    /**
     * Enregistre un nouvel utilisateur
     *
     * @param utilisateurForm formulaire de saisie des informations du nouvel utilisateur
     * @param bindingResult   resultat du controle de saisie
     * @return redirection vers la page de bienvenue
     */
    @PostMapping("/registration")
    public String registration(@ModelAttribute("utilisateurForm") Utilisateur utilisateurForm, BindingResult bindingResult) {
        utilisateurValidator.validate(utilisateurForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        utilisateurService.inscrire(utilisateurForm);
        securityService.autoLogin(utilisateurForm.getPseudo(), utilisateurForm.getPasswordConfirm());
        return "redirect:/welcome";
    }

    /**
     * Affiche le formulaire de connection
     *
     * @param model  données Model pour constituer la vue
     * @param error  message d'erreur
     * @param logout message de déconnection
     * @return la page de login
     */
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Saisie incorrect de votre pseudo et password");
        if (logout != null)
            model.addAttribute("message", "Vous avez été déconnecté.");
        return "login";
    }

    /**
     * Affiche la page de bienvenue
     *
     * @param model données Model pour constituer la vue
     * @return la page de bienvenue
     */
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
