package com.resp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean(name = "customPasswordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                                   .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    // Define Security Filter Chain (WebSecurity Configuration)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf().disable()
            .authorizeHttpRequests()
            
            // Allow POST requests to /api/login/loginUser (login endpoint) for all users
            .requestMatchers("/realestate/login/loginUser","/userRegister","/realestate/login/logout").permitAll()
            
            // Allow POST requests to /add (for admin or seller)
            .requestMatchers("/notifpost/NgetAll","/notifpost/NdeleteById/{id}","/notifpost/Ninsert","/rentpost/Rinsert","rentpost/RgetAll"
            ,"/rentpost/RdeleteById/{id}","/rentpost/Rupdate/{id}","/salepost/Sinsert","/salepost/SdeleteById/{id}","/salepost/SupdateSalepost/{id}","/salepost/SgetAll",
            "/userdetails/UDinsert","/userdetails/UDgetById/{id}","/userdetails/UDupdateById/{id}","/user/update/{id}","/adminc/add",
            "/getSalepostBy/{id}").permitAll()
//          .hasAnyRole("SELLER","BUYER","AGENT")
            
            .requestMatchers("/deleteUserById/{id}","/rentpost/RdeleteById/{id}","/salepost/SdeleteById/{id}","/getAllRegisterUD",
             "/rentpost/RgetAll","/salepost/SgetAll","/adminc/getAll","/adminc/delete/{id}")
            .permitAll()
//           .hasRole("ADMIN")
            
            
            // Any other requests must be authenticated
            .anyRequest().authenticated(); 
            
        return http.build();
    }
}
