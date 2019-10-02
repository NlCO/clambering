package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.Validator.UtilisateurValidator;
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

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("utilisateurForm", new Utilisateur());

        return "registration";
    }

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

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Saisie incorrect de votre pseudo et password");

        if (logout != null)
            model.addAttribute("message", "Vous avez été déconnecté.");

        return "login";
    }

    @GetMapping({"/","/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
