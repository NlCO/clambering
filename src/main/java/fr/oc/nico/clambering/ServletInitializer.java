package fr.oc.nico.clambering;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Permet d'initialiser l'application à l'aide du framework SpringBoot
     *
     * @param application SpringApplicationBuilder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClamberingApplication.class);
    }
}
