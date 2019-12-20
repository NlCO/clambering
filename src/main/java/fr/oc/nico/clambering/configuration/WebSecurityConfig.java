package fr.oc.nico.clambering.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe de configuration du framework SpringSecurity.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Methode permmettant de créer un encodeur de mot de pasee
     *
     * @return un encoder de password
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Methode permettant de configurer les règles d'accès aux pages HTML gérées par Spring Security
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/spots/{\\d+}/spotEdition/**", "/perso", "/topo/**", "topos/**").authenticated()
                .antMatchers("/spot/{\\d+}/commentaire/{\\d+}/del", "/spot/{\\d+}/commentaire/{\\d+}/edit").hasAuthority("Membre")
                .antMatchers("/", "/spots/**", "/resources/**", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * Methode premettant de configurer les règles d'accès au serveurs WEB gérées par Spring Security
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/h2-console/**")
                .antMatchers("/img/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/webjars/**");
    }

    /**
     * Methode permettant à SpringSecurity d'implemetner un Manager d'authentification
     *
     * @return un manager d'authentification
     * @throws Exception
     */
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    /**
     * Methode permaettant de configuerer la couche Service et encodage pour l'authentification de SpringSecurity
     *
     * @param auth Builder de manager d'authentification
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
