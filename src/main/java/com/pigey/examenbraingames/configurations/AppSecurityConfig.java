package com.pigey.examenbraingames.configurations;

import com.pigey.examenbraingames.users.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {


    private final AppPasswordConfig bcrypt;
    private final UserModelService userModelService;

    @Autowired
    public AppSecurityConfig(AppPasswordConfig bcrypt, UserModelService userModelService) {
        this.bcrypt = bcrypt;
        this.userModelService = userModelService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(
                        authorizeHttpRequests ->{
                            authorizeHttpRequests
                                    .requestMatchers("/", "/login", "/logout", "error", "/register", "/static/**", "/find", "/loginAgain").permitAll()
                                    .requestMatchers("/testAdmin").hasRole("ADMIN")
                                    .requestMatchers("/nummerMemory").hasAnyRole("USER","ADMIN")
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .formLogin(
                        formlogin ->{
                            formlogin
                                    .loginPage("/login")
                                    .defaultSuccessUrl("/", true);
                        }
                )
                .rememberMe(
                        rememberMe ->{
                            rememberMe
                                    .rememberMeParameter("remember-me")
                                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) //3 weeks
                                    .key("someSecureKey")
                                    .userDetailsService(userModelService);
                        }
                )
                .logout(
                        logout ->{
                            logout
                                    .logoutUrl("/logout")
                                    .logoutSuccessUrl("/login")
                                    .clearAuthentication(true)
                                    .invalidateHttpSession(true)
                                    .deleteCookies("remember-me", "JSESSIONID");

                        }
                )
                .authenticationProvider(authenticationOverride());


        return http.build();
    }

    public DaoAuthenticationProvider authenticationOverride(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userModelService);
        provider.setPasswordEncoder(bcrypt.bCryptPasswordEncoder());

        return provider;

    }


}
