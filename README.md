# Clambering

Les sources sont disponibles sur : https://github.com/NlCO/clambering 

## Contexte

Ce projet a été développé en 2019 dans le cadre du cursus "Développeur d'application Java" d'OpenClassrooms.
Il consiste en la création d’un site communautaire autour de l’escalade pour l’association “Les amis de l’escalade”


## Contenu
Ce site aura pour but la mise en relation et le partage d’informations. Il permettra de donner de la visibilité 
à l’association afin d’encourager des grimpeurs indépendants à y adhérer.


## Pré-requis
Version de java : 1.8 (jdk utilisé : jdk1.8.0_202)
Maven 3.6

## Installation et déploiement
1.Configuration

L'application est livrée avec 2 modes de fonctionnement gérés par le paramètre "spring.profiles.active" dans le fichier src\resources\application.properties :

  * **dev** (défaut) : utilisant une base de données "in-Memory" (H2) recréée et peuplée avec un jeu de test à chaque lancement.
  
  * **prod** : utilisant une base PostgresSQL (pré-installation nécessaire) à paramétrer et peupler (optionnel).
  Les paramètres de connection à la base sont à modifier dans le fichier src\resources\application-prod.properties (spring.datasource.url, spring.datasource.username et spring.datasource.password)
  Par défaut, un utilisateur "admin" est créé avec le mot de passe "azerty".
  La base contient aucune données. Le jeu de test src\resources\static\sql\demodata.sql est disponible via exécution des requêtes SQL.
    
2.Déploiement

Au choix :

  * application standalone intégrant un conteneur web (grace à SpringBoot)
  
        mvn clean install spring-boot:run
    
  * utilisation d'un webapp (**war**) dans un conteneur web (comme Tomcat) ou lancer via une commande java
    - création du package à la racine du projet
     
            mvn clean package
          
    - lancement du package généré dans le sous-répertoire target
     
            java -jar target\clambering-0.0.1-SNAPSHOT.war

3.Accès

L'application est accessible par http://localhost:8080/

 