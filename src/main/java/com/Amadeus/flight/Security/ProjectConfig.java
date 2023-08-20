package com.Amadeus.flight.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class ProjectConfig {

        @Bean
        public UserDetailsService userDetailsService() {
            var imud = new InMemoryUserDetailsManager();
            imud.createUser(User.withUsername("user").password("user").authorities("read").build());
            imud.createUser(User.withUsername("admin").password("admin").authorities("admin").build());
            return imud;
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }
        @Bean
        public SecurityFilterChain configuration(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .authorizeRequests()// admin hem dataya erişir hem editler.
                    //Burada bir sıkıntı oldu yorum satırına aldım.Admin kullanıcısı bütün endpointlerde 403 alıyordu.
                    //Araştırılacak.
                    /*.requestMatchers("/api/1.0/flights/**")
                    .hasAuthority("read")
                    .requestMatchers("/api/1.0/airports/**")
                    .hasAuthority("read")*/
                    .anyRequest()
                    .hasAuthority("admin")
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable()
                    .build();

        }
}

