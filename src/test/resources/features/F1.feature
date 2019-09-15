Feature: F1 - Affiche les inforations des spots

  Un utilisateur doit pouvoir consulter les informations des sites d’escalades (secteurs, voies, longueurs, etc.).


  Background:
    Un jeu de test est chargé.

    Scenario Outline: Cotation Max d'un spot
      When Je consulte les informations du spot ayant l'<id>
      Then Sa cotation max est <cotation>

      Examples:
        |id|cotation|
        |11|9a      |
        |6 |9c      |
        |19|8a      |
        |3 |8c      |

    Scenario Outline: Cotation Min d'un spot
      When Je consulte les informations du spot ayant l'<id>
      Then Sa cotation min est <cotation>

      Examples:
        |id|cotation|
        |3 |6a      |
        |1 |4       |
        |7 |3       |
        |4 |5c      |

    Scenario Outline: Hauteur Max d'un spot
      When Je consulte les informations du spot ayant l'<id>
      Then Sa hauteur max est <hauteur>

      Examples:
        |id|hauteur|
        |2 |52.83  |
        |4 |20.18  |
        |12|36.65  |
        |20|21.29  |

    Scenario Outline: Hauteur Min d'un spot
      When Je consulte les informations du spot ayant l'<id>
      Then Sa hauteur min est <hauteur>

      Examples:
        |id|hauteur|
        |2 |19.39  |
        |4 |20.18  |
        |12|18.27  |
        |20|16.66  |
