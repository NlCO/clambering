package fr.oc.nico.clambering.controller;

import fr.oc.nico.clambering.DTO.TopoEditForm;
import fr.oc.nico.clambering.service.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur gérant les requetes liées aux topos
 */
@Controller
public class TopoController {

    private TopoService topoService;

    @Autowired
    public TopoController(TopoService topoService) {
        this.topoService = topoService;
    }

    /**
     * Permet d'afficher la liste des topos d'un utilisateur et d'en ajouter
     *
     * @param model        données Model pour constituer la vue
     * @param request      ServletRequest permettant de récupérer les infos de la requete
     * @param topoEditForm Object contenant les données du formulaire
     * @return la page perso
     */
    @GetMapping("/perso")
    public String pageperso(Model model, HttpServletRequest request, @ModelAttribute("topoEditForm") TopoEditForm topoEditForm) {
        String user = request.getRemoteUser();
        model.addAttribute("topos", topoService.getMesTopos(user));
        model.addAttribute("formData", topoService.getFormData());
        return "perso";
    }

    /**
     * Permet de switcher la disponibilté d'un topo
     *
     * @param topoId Id du topo à modifier
     * @return redirection sur la page perso
     */
    @RequestMapping(value = "/topo/switchdispo/{topoId}")
    public String switchDispo(@PathVariable Integer topoId) {
        topoService.switchDispo(topoId);
        return "redirect:/perso";
    }

    /**
     * Permet d'ajouter un nouveau topo à sa liste
     *
     * @param request      ServletRequest permettant de récupérer les infos de la requete
     * @param topoEditForm Object contenant les données du formulaire
     * @return redirection sur la page perso
     */
    @RequestMapping(value = "/topo/addNew")
    public String addTopo(HttpServletRequest request, TopoEditForm topoEditForm) {
        topoService.ajouterTopo(request.getRemoteUser(), topoEditForm);
        return "redirect:/perso";
    }

    /**
     * Permet de retirer un topo à sa liste
     *
     * @param topoId  Id du topo à supprimer
     * @param request ServletRequest permettant de récupérer les infos de la requete
     * @return redirection sur la page perso
     */
    @RequestMapping(value = "/topo/delete/{topoId}")
    public String deleteTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.supprimerTopo(request.getRemoteUser(), topoId);
        return "redirect:/perso";
    }

    /**
     * Permet d'afficher la liste des topos disponibles
     *
     * @param model   données Model pour constituer la vue
     * @param request ServletRequest permettant de récupérer les infos de la requete
     * @return la page topo
     */
    @GetMapping("/topos")
    public String topos(Model model, HttpServletRequest request) {
        String user = request.getRemoteUser();
        model.addAttribute("topos", topoService.getToposDispo(user));
        return "topos";
    }

    /**
     * Permet de reserver un topo
     *
     * @param topoId  Id du topo à réserver
     * @param request ServletRequest permettant de récupérer les infos de la requete
     * @return redirection sur la page des topos
     */
    @RequestMapping(value = "/topos/{topoId}/reserve")
    public String reserveTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.reserverTopo(request.getRemoteUser(), topoId);
        return "redirect:/topos";
    }

    /**
     * Permet d'annuler la réservation d'un topo
     *
     * @param topoId  Id du topo concerné
     * @param request ServletRequest permettant de récupérer les infos de la requete
     * @return redirection sur la page des topos
     */
    @RequestMapping(value = "/topos/{topoId}/annuler")
    public String annuleResaTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.annulerReservationTopo(request.getRemoteUser(), topoId);
        return "redirect:/topos";
    }

    /**
     * Permet d'accepter la réservation d'un topo
     *
     * @param topoId  Id du topo concerné
     * @param request ServletRequest permettant de récupérer les infos de la requete
     * @return redirection sur la page perso
     */
    @RequestMapping(value = "/topos/{topoId}/reserveaccepte")
    public String reserveAccepteeTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.confirmerReservation(request.getRemoteUser(), topoId);
        return "redirect:/perso";
    }

    /**
     * Permet de refuser la réservation d'un topo
     *
     * @param topoId  Id du topo concerné
     * @param request ServletRequest permettant de récupérer les infos de la requete
     * @return redirection sur la page perso
     */
    @RequestMapping(value = "/topos/{topoId}/reserverefuse")
    public String reserveRefuseeTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.refuserReservation(request.getRemoteUser(), topoId);
        return "redirect:/perso";
    }

    /**
     * Permet de retablir le statut d'un topo après un retour de prêt
     *
     * @param topoId  Id du topo concerné
     * @param request ServletRequest permettant de récupérer les infos de la requete
     * @return redirection sur la page perso
     */
    @RequestMapping(value = "/topos/{topoId}/retourner")
    public String retournerTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.retourPret(request.getRemoteUser(), topoId);
        return "redirect:/perso";
    }
}
