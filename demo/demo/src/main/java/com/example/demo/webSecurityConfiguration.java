package com.example.demo;

// import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
// import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class webSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService UserDetailsService;

    // @Bean
    // public AuthenticationProvider authenticationProvider(){
    //     DaoAuthenticationProvider provider
    //     = new DaoAuthenticationProvider();
    //     provider.setUserDetailsService(UserDetailsService);
    //     provider.setPasswordEncoder(new BCryptPasswordEncoder());
    //     return provider;
    // }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(UserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .antMatchers("/home")
        .hasAuthority("USER")
        .antMatchers("/admin")
        .hasAuthority("ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
    }
    
}
// package com.example.demo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class webSecurityConfiguration extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private UserDetailsService userDetailsService;

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .authorizeRequests()
//             .antMatchers("/authenticate").permitAll()
//             .antMatchers("/admin").hasRole("ADMIN")
//             .anyRequest().authenticated()
//             .and().sessionManagement();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
