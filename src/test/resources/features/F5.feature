Feature: F5 - Ajout de commentaire

  Un utilisateur connecté doit pouvoir laisser des commentaires sur les
  pages des sites d’escalade.

  Background: L'utilisateur azerty est connecté

  Scenario: Ajouter un commentaire à un site
    Given Sur le spot Newark
    When l'utilisateur azerty post le commentaire super
    Then le spot Newark a 4 commentaire
