Feature: F4 - Partager des spots

  Un utilisateur connecté doit pouvoir partager des informations sur un site d’escalade (secteurs, voies, longueurs, etc .)

  Background: L'utilisateur est connecté

  Scenario: Ajout de spot
    Given un spot inexistant FEATURE 4 SPOT de Piemonte
    And contenant un secteur FEATURE 4 SECTEUR
    And contenant une voie FEATURE 4 VOIE
    And contenant une longueur FEATURE 4 LONGUEUR, de longueur 10, de cotation 4a avec 3 degaines
    When le formulaire est rempli et envoyé
    Then le spot FEATURE 4 SPOT est présent dans la base
    And il contient 1 secteur nommé FEATURE 4 SECTEUR
    And sa hauteur max et mini est de 10 m
    And sa cotation max et min est 4a

  Scenario: Modification de site
    Given un spot existant FEATURE 4 SPOT
    When je modifie son nom en SPOT FEATURE 4
    And une longueur FEATURE 4 LONGUEUR2, de longueur 5, de cotation 6a avec 1 degaines est ajoutée
    And la modification est soumise
    Then le spot FEATURE 4 SPOT est absent dans la base
    And le nouveau spot SPOT FEATURE 4 est présent dans la base
    And il contient 2 longueurs
    And sa hauteur max est de 15 m
    And sa cotation max est de 6a
    And sa cotation min est de 4a