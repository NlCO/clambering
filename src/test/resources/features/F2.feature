Feature: F2 - Recherche multi-critères

  Un utilisateur doit pouvoir faire une recherche à l’aide de plusieurs
  critères pour trouver un site de grimpe et consulter le résultat de cette
  recherche. Les critères peuvent être le lieu, la cotation, le nombre de
  secteurs, etc.

  Background: Un jeu de test est chargé.

    Scenario: Aucun filtre
      Given aucun critère est choisi
      When le filtre est appliqué
      Then la liste contient 20 spots

    Scenario Outline: Combinaisons de filtre
      Given les critères <pays>, <region>, <orientation>, <secteur>, <cotationMin>, <cotationMax> sont choisis
      When le filtre est appliqué
      Then la liste contient <resultat> spots

      Examples:
      |pays    |region             |orientation|secteur|cotationMin|cotationMax|resultat|
      |France  |null               |null       |false  |null       |null       |11      |
      |null    |null               |null       |true   |null       |null       |14      |
      |Espagne |null               |Sud-Est    |false  |null       |null       |2       |
      |Italie  |Navarra            |null       |false  |null       |null       |0       |
      |null    |null               |null       |false  |6a         |null       |17      |
      |null    |null               |null       |false  |null       |7a         |16      |