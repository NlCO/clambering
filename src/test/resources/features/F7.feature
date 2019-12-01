Feature: F7 - Modification/Suppression des commentaires

  Un membre de l'association doit pouvoir modifier et supprimer un
  commentaire.

  Background: l'utilisateur qsdfgh est membre de l'association et s'est connecté

    Scenario: Modification d'un commentaire
      Given le commentaire 1 du spot Menai Bridge
      And ce dernier contient test
      When qsdfgh modifie sont contenu par change content
      Then le spot contient toujours 1 commentaire
      And le nouveau comentaire est change content

    Scenario: Suppression d'un commentaire
      Given le commentaire 1 du spot Menai Bridge
      And ayant son id
      When qsdfgh le supprime
      Then le spot contient maintenant 0 commentaire