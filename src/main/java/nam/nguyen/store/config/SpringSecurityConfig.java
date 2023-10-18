package com.hutech.BEFoodStore.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/product/**").permitAll()
                                .requestMatchers("/webjars/**", "/assets/**","/css/**","/img/**","/js/**","/scss/**","/vendor/**").permitAll()
                                .requestMatchers("/shoppingcart/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers("/service/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers("/rest/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers("/user/naptien").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers("/user/viewnaptien").hasAnyAuthority("ROLE_ADMIN")


                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/product/home")
                                .permitAll()


                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}