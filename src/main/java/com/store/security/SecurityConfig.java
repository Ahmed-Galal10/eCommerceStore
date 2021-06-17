package com.store.security;

import com.store.security.excptionHandlers.CustomAccessDeniedHandler;
import com.store.security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        * /Customer POST - GET - PUT -DELETE
        * CUSTOMER/ID  PUT
        * */

        http
                .csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/login","/shop/**").permitAll()
                .antMatchers(HttpMethod.GET,"/customers").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/customers/*").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/customers/").permitAll()
                .antMatchers(HttpMethod.POST,"/customers/**").hasAnyAuthority("ROLE_CUSTOMER")
                .antMatchers(HttpMethod.PUT,"/customers/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/customers/**").hasAuthority( "ROLE_ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/swagger-ui/**",
                "/webjars/**");
    }



}
