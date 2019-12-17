Feature: F10 - Pret TOPO

  Un utilisateur connecté doit pouvoir accepter une demande de
  réservation. Si une réservation est acceptée, automatiquement le topo
  devient indisponible. L’utilisateur connecté pourra ensuite de nouveau
  changer le statut du topo en « disponible ».
  La plateforme se contente de mettre en contact les deux parties pour le
  prêt d’un topo (par échange de coordonnées).

  Scenario: Accepter une réservation
    Given Une réservation du topo erat par azerty
    When le proprietaire qsdfgh valide la réservation du topo erat
    Then le status du topo erat a changé en indisponible

  Scenario: Retour de prêt
    Given le topo erat en cours de prêt
    When le proprietaire qsdfgh confirme que le topo erat est rendu
    Then le topo erat n'est plus emprunté
    And son statut est de nouveau disponible

  Scenario: Refuser une réservation
    Given Une réservation du topo erat par azerty
    When le proprietaire qsdfgh refuse la réservation du topo erat
    Then le topo erat n'est plus emprunté