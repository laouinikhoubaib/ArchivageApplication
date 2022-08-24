package com.spring.archivageapplication.Security.config;


import com.spring.archivageapplication.Repository.AuthRepository;
import com.spring.archivageapplication.Security.Jwt.JwtAuthorizationFilter;
import com.spring.archivageapplication.Security.Services.UserDetailssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailssService userService;
    private AuthRepository userRepository;

    @Autowired
    public SpringSecurityConfig(UserDetailssService userService, AuthRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
        //récupèrer les détails de l'utilisateur à partir d'un fichier UserDetailsService
    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
        http.authorizeRequests().antMatchers("/api/admin/**").hasAuthority("SuperAdmin");
        http.authorizeRequests().antMatchers("/api/admin/**").hasAuthority("Admin");
        http.authorizeRequests().antMatchers("/api/user/**").permitAll();
        http.authorizeRequests().antMatchers("/api/auth/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();



    }
    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**","/**",
            "/api/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/**",
            "/api/user/**",
            "/api/auth/**",
            "/api/admin/**"
    };


}

