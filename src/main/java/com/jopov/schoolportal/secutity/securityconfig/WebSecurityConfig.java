package com.jopov.schoolportal.secutity.securityconfig;

import com.jopov.schoolportal.secutity.handlers.AuthSuccessHandler;
import com.jopov.schoolportal.secutity.services.UserService;
import com.jopov.schoolportal.secutity.enums.ERole;

import com.jopov.schoolportal.secutity.jwt.AuthEntryPointJWT;
import com.jopov.schoolportal.secutity.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthEntryPointJWT unauthorizedHandler;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/login", "/login**", "/login/**", "/VAADIN/**", "/HEARTBEAT/**",
                        "/UIDL/**", "/resources/**", "/", "/logout", "/manifest.json", "/icons/**",
                        "/images/**", "/registration", "/registration/**", "/grid",
                        "/school-project/home", "/ws/**", "/vaadinServlet/**", "/signin", "/signup", "/api/teachers").permitAll()
                .antMatchers("/school-project/director/**").hasAuthority(ERole.ROLE_DIRECTOR.name())
                .antMatchers("/school-project/head-teacher/**").hasAuthority(String.valueOf(ERole.ROLE_HEAD_TEACHER))
                .antMatchers("/school-project/teacher/**").hasAuthority(String.valueOf(ERole.ROLE_TEACHER))
                .antMatchers("/school-project/pupil/**").hasAuthority(String.valueOf(ERole.ROLE_PUPIL))
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(authSuccessHandler).permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

