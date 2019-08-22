package fr.oc.nico.clambering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClamberingApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClamberingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClamberingApplication.class, args);
        LOGGER.info("L'application a démarré");

    }

    @Override
    public void run(String... args) throws Exception {
    }

}
