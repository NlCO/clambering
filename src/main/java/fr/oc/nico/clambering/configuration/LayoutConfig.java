package fr.oc.nico.clambering.configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration du Layout Dialect de Thymeleaf
 */
@Configuration
public class LayoutConfig {

    /**
     * Methode de configuration de la strategie pour le Lyaout Dialect de Thymeleaf
     *
     * @return La startégie du LayoutDialect
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect(new GroupingStrategy());
    }
}
