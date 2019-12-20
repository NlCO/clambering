package fr.oc.nico.clambering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe Principale conteenant le proint d'entrée de l'application
 */
@SpringBootApplication
public class ClamberingApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClamberingApplication.class);

    /**
     * Methode Main : Lancement de l'application via le framework SpringBoot
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ClamberingApplication.class, args);
        LOGGER.info("L'application a démarré");
    }
}
