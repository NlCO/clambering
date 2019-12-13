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

@Controller
public class TopoController {

    private TopoService topoService;

    @Autowired
    public TopoController(TopoService topoService) {
        this.topoService = topoService;
    }

    @GetMapping("/perso")
    public String pageperso(Model model, HttpServletRequest request, @ModelAttribute("topoEditForm") TopoEditForm topoEditForm) {
        String user = request.getRemoteUser();
        model.addAttribute("topos", topoService.getMesTopos(user));
        model.addAttribute("formData", topoService.getFormData());
        return "perso";
    }

    @RequestMapping(value = "/topo/switchdispo/{topoId}")
    public String switchDispo(@PathVariable Integer topoId) {
        topoService.switchDispo(topoId);
        return "redirect:/perso";
    }

    @RequestMapping(value = "/topo/addNew")
    public String addTopo(HttpServletRequest request, TopoEditForm topoEditForm) {
        topoService.ajouterTopo(request.getRemoteUser(), topoEditForm);
        return "redirect:/perso";
    }

    @RequestMapping(value = "/topo/delete/{topoId}")
    public String deleteTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.supprimerTopo(request.getRemoteUser(), topoId);
        return "redirect:/perso";
    }

    @GetMapping("/topos")
    public String topos(Model model, HttpServletRequest request) {
        String user = request.getRemoteUser();
        model.addAttribute("topos", topoService.getToposDispo(user));
        return "topos";
    }

    @RequestMapping(value = "/topos/{topoId}/reserve")
    public String reserveTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.reserverTopo(request.getRemoteUser(), topoId);
        return "redirect:/topos";
    }

    @RequestMapping(value = "/topos/{topoId}/annuler")
    public String annuleResaTopo(@PathVariable Integer topoId, HttpServletRequest request) {
        topoService.annulerReservationTopo(request.getRemoteUser(), topoId);
        return "redirect:/topos";
    }
}
