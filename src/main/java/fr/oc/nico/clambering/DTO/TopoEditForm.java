package fr.oc.nico.clambering.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TopoEditForm {
    private Integer topoId;

    private String topoLibelle;

    private String description;

    private String lieu;

    private Date dateParution;

    public TopoEditForm() {
    }

    public TopoEditForm(String topoLibelle, String description, String lieu, Date dateParution) {
        this.topoLibelle = topoLibelle;
        this.description = description;
        this.lieu = lieu;
        this.dateParution = dateParution;
    }
}
