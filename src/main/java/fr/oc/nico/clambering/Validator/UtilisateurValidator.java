package fr.oc.nico.clambering.Validator;

import fr.oc.nico.clambering.model.Utilisateur;
import fr.oc.nico.clambering.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Implementation custom des validations utilisées par SpringSecurity pour l'authentification
 */
@Component
public class UtilisateurValidator implements Validator {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurValidator(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Utilisateur.class.equals(aClass);
    }

    /**
     * Permet de valider un objet
     * @param o objets à valider
     * @param errors list des erreurs constatées
     */
    @Override
    public void validate(Object o, Errors errors) {
        Utilisateur utilisateur = (Utilisateur) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (utilisateurService.findByEmail(utilisateur.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.utilisateurForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pseudo", "NotEmpty");
        if (utilisateur.getPseudo().length() < 6 || utilisateur.getPseudo().length() > 12) {
            errors.rejectValue("pseudo", "Size.utilisateurForm.pseudo");
        }
        if (utilisateurService.findByPseudo(utilisateur.getPseudo()) != null) {
            errors.rejectValue("pseudo", "Duplicate.utilisateurForm.pseudo");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (utilisateur.getPassword().length() < 8 || utilisateur.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.utilisateurForm.password");
        }

        if (!utilisateur.getPasswordConfirm().equals(utilisateur.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.utilisateurForm.passwordConfirm");
        }
    }
}
