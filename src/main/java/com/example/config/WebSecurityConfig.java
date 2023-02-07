package com.example.config;


import com.example.customUserDetailsService.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
 public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

         @Autowired
         private UserDetailsService userDetailsService;
         @Bean
         public PasswordEncoder passwordEncoder() {
             return new BCryptPasswordEncoder();
         }

//         @Bean
//         public DaoAuthenticationProvider authentificationProvider() {
//             DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//             provider.setUserDetailsService(userDetailsService());
////             provider.setPasswordEncoder(new BCryptPasswordEncoder());
////             provider.setUserDetailsService(this.getUserDetailsService());
//             provider.setPasswordEncoder(passwordEncoder());
//             return provider;
//         }

//         @Override
//         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//             auth.authenticationProvider(authentificationProvider());
//     }

//     @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .and()
//                .authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/user").hasAnyRole("USER","ADMIN")
////                .and()
////                .antMatchers("/").permitAll()
//                .and().formLogin()
//                .and().csrf().disable();
//         }


         @Bean
         AuthenticationProvider authenticationProvider() {
             DaoAuthenticationProvider provider
                     = new DaoAuthenticationProvider();
             provider.setUserDetailsService(userDetailsService);
             provider.setPasswordEncoder(new BCryptPasswordEncoder());
             return  provider;
         }

         @Override
         protected void configure(HttpSecurity http) throws Exception {
             http.authorizeRequests()
                     .antMatchers("/")
                     .permitAll()
                     .antMatchers("/user")
                     .hasAnyAuthority("USER", "ADMIN")
                     .antMatchers("/admin")
                     .hasAuthority("ADMIN")
                     .anyRequest()
                     .authenticated()
                     .and()
                     .httpBasic();
         }

     }