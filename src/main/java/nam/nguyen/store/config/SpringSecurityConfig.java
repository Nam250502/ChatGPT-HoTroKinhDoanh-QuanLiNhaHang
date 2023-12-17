package nam.nguyen.store.config;

import lombok.AllArgsConstructor;
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
                                .requestMatchers("/css/**", "/js/**","/libs/**","/scss/**","/assets/**","/images/**").permitAll()
                                .requestMatchers("/customer/**").permitAll()
                                .requestMatchers("/ws-example/**").permitAll()
                                .requestMatchers("/chef/**").hasAnyAuthority("ROLE_CHEF","ROLE_ADMIN","ROLE_STAFF")
                                .requestMatchers("/staff/**").hasAnyAuthority("ROLE_CHEF","ROLE_ADMIN","ROLE_STAFF")
                                .requestMatchers("/store/**").hasAnyAuthority("ROLE_CHEF","ROLE_ADMIN","ROLE_STAFF")
                                .requestMatchers("/admin/**").hasAnyAuthority("ROLE_CHEF","ROLE_ADMIN","ROLE_STAFF")
                                .requestMatchers("/user/**").authenticated()
                                .requestMatchers("/help/**").permitAll()
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/feedback/**").permitAll()


                ) .formLogin(
                        form -> form

                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler(new CustomAuthenticationSuccessHandler())
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