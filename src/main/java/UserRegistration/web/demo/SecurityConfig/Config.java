package UserRegistration.web.demo.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import UserRegistration.web.demo.UserRepository;

import java.util.List;

@Configuration
@EnableWebSecurity
public class Config{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws RuntimeException{
        http.authorizeHttpRequests(authz ->
            authz
            .requestMatchers("/api/user/welcome").authenticated()
                    .anyRequest().permitAll()
        )
                .formLogin(form-> form.permitAll().defaultSuccessUrl("/welcome"))
            .csrf(csr -> csr.disable())
        ;
        return http.build();
    }
    @Bean
    public CustomUserDetailsService userDetailsService(UserRepository userRepository){
        return new CustomUserDetailsService(userRepository);
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserRepository userRepository){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider(userDetailsService(userRepository));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(UserRepository userRepository){
        return new ProviderManager(List.of(authenticationProvider(userRepository)));
    }


}
