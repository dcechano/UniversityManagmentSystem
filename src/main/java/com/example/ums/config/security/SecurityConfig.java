package com.example.ums.config.security;

import com.example.ums.enums.RoleEnum;
import com.example.ums.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PersonService personService;

    private CustomAuthentication customAuthentication;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //        TODO remove these comments later
        //
        //                try {
        //            PasswordEncoder passwordEncoder = passwordEncoder();
        //            auth.inMemoryAuthentication()
        //                    .passwordEncoder(passwordEncoder)
        //                    .withUser("dylan")
        //                    .password(passwordEncoder.encode("password"))
        //                    .roles("USER")
        //                    .and()
        //                    .withUser("admin")
        //                    .password(passwordEncoder.encode("password"))
        //                    .roles("USER, ADMIN");
        //        } catch (Exception e) {
        //            throw new ConfigurationException("In-Memory authentication was not configured.");
        //        }
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**",
                "/static/**");
    }

    //    TODO fix the allowed roles so that the Person "Tperson" saved in the database can view landing
    @Override
    protected void configure(HttpSecurity http) throws Exception {
// TODO remove the substring() in the allowed roles and replace it with something more proper
        http.authorizeRequests()
                .antMatchers("/**").hasAnyRole(
                        RoleEnum.ROLE_STUDENT.simpleName(),
                RoleEnum.ROLE_FACULTY_MEMBER.simpleName(),
                RoleEnum.ROLE_STAFF_MEMBER.simpleName())

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/userAuth")
                .successHandler(customAuthentication)
                .loginPage("/login")
                .failureUrl("/login?auth_error=1")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .csrf().csrfTokenRepository(repo());
    }

    @Bean
    public CsrfTokenRepository repo() {
        HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
        repo.setParameterName("_csrf");
        repo.setHeaderName("X-CSRF-TOKEN");
        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(personService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setCustomAuthentication(CustomAuthentication customAuthentication) {
        this.customAuthentication = customAuthentication;
    }
}
