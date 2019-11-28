Feature: F6 - Taguer un spot Officiel

  Un membre de l'association doit pouvoir taguer un site d’escalade
  enregistré sur la plateforme comme « Officiel Les amis de l’escalade ».

  Background: l'utilisateur qsdfgh est membre de l'association et s'est connecté

    Scenario: taguer un spot
      Given le spot Menai Bridge sans tag
      When je le tague
      Then le spot est tagué
      And les autres informations n'ont pas été impactées

    Scenario: detagué un spot
      Given le spot Menai Bridge avec tag
      When je le detague
      Then le spot X est detagué
      And les autres informations n'ont pas été impactées