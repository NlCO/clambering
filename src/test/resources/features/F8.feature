Feature: F8 - Topos

  Un utilisateur connecté doit pouvoir enregistrer dans son espace
  personnel les topos qu’ils possèdent et préciser si ces derniers sont
  disponibles pour être prêtés ou non.
  Un topo est défini par un nom, une description, un lieu et une date de
  parution.

  Background: Un user azerty connecté sur son profil

  Scenario: Ajout d'un topo
    Given le topo topo1 sur la region Piemonte paru le 15/12/2019 avec la description les meilleurs spots
    And une base de données avec n topos
    When azerty enregistre le nouveau topo
    Then la base contient 1 topo de plus
    And le topo topo1 est présent dans la base

  Scenario: Rendre disponible un topo
    Given le topo1 est indisponible
    When azerty rends sont statut disponible
    Then le topo1 est maintenant disponible

  Scenario: Rendre indisponible un topo
    Given le topo1 est disponible
    When azerty rends sont statut indisponible
    Then le topo1 est maintenant indisponible

  Scenario: Suppression d'un topo
    Given le topo topo1 existe
    When azerty supprimre le topo
    Then le topo topo1 n'existe plus