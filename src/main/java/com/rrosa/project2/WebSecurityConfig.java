package com.rrosa.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(
            "/css/**",
            "/h2",
            "/",
            "/index",
            "/directors",
            "/movies/**",
            "/director/**",
            "/movie/**",
            "/login/**",
            "/register")
        .permitAll()
        .antMatchers("/newdirector", "/newmovie", "/my_logout")
        .hasAnyRole("USER", "ADMIN")
        .antMatchers("/deletedirector", "/deletemovie")
        .hasRole("ADMIN")
        .antMatchers("/api", "/myapi")
        .hasAnyRole("API")
        .anyRequest()
        .authenticated();
    // login/logout config
    http.formLogin()
        .loginPage("/my_login")
        .loginProcessingUrl("/my_login")
        .failureUrl("/login-error")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/my_logout")
        .logoutSuccessUrl("/")
        .and()
        .httpBasic()
        .and()
        .exceptionHandling()
        .accessDeniedPage("/403");
    // disabling csrf protection for convenience
    http.csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired UserDetailsService userDetailsService;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
}
