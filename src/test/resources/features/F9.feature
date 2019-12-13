Feature: F9 - Réservation Topos

  Un utilisateur connecté doit pouvoir consulter la liste des topos
  disponibles par d’autres utilisateurs et faire une demande de réservation.
  La réservation n’inclut pas les notions de date de début et date de fin.

  Scenario: Affichage de la liste des topos disponibles
    Given une base contenant 10 topos
    When azerty consulte la liste des topos disponibles
    Then une liste de 5 topos disponibles est retournée

  Scenario: Demande de réservation de topo
    Given la liste des topos disponibles à la reservation pour azerty
    And le topo erat est disposnible à la réservation
    When le user azerty demande de reserver le topo
    Then le topo erat contient une réservation pour azerty
    And le topo erat apparait toujours disponible

  Scenario: Annulation de la réservation du topo
    Given le topo erat est deja reservé par azerty
    When azerty demande d'annuler sa reservation du topo
    Then le topo erat ne contient plus de réservation
    And le topo erat apparait toujours disponible