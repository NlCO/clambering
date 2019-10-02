Feature: F3 - Ajout utilisateur

  Un utilisateur doit pouvoir s’inscrire gratuitement sur le site.

  Background: un jeu de test est chargé

    Scenario: inscription avec détails valides
      Given un utilisateur souhaite s'incrire avec le pseudo "4Test", le password "test" et un email "for@test.fr" non existant
      When il soumet la demande d'inscription
      Then l'utilisateur est présent dans la base de données

    Scenario: inscription avec un email existant
      Given un utilisateur souhaite s'incrire avec le pseudo "4Test", le password "test" et un email "for@test.fr" non existant
      When il soumet la demande d'inscription
      Then l\'utilsateur n\'est pas présent dans la base de données
