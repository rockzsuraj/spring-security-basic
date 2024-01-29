package com.spring.security.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration  {

    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.builder().username("user")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//        UserDetails user2 = User.builder().username("user2")
//                .password("password2")
//                .roles("USER")
//                .build();
//        UserDetails user3 = User.builder().username("user3")
//                .password("password3")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, user2, user3);
//
//    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {

//                UserDetails user = User.builder().username("user")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//        UserDetails user2 = User.builder().username("user2")
//                .password("password2")
//                .roles("USER")
//                .build();
//        UserDetails user3 = User.builder().username("user3")
//                .password("password3")
//                .roles("USER")
//                .build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

//        users.createUser(user);
//        users.createUser(user2);
//        users.createUser(user3);

        return users;
    }


    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated())
                    .formLogin(withDefaults());
        return http.build();
    }



    @Bean
    public PasswordEncoder PasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
