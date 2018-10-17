package com.gmail.vpshulgaa.config;

import com.gmail.vpshulgaa.controllers.handlers.AppSuccessHandler;
import com.gmail.vpshulgaa.util.URLPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationSuccessHandler appSuccessHandler;

    @Autowired
    public WebSecurityConfig(
            @Qualifier(value = "userDetailService") UserDetailsService userDetailsService,
            AppSuccessHandler appSuccessHandler
    ) {
        this.userDetailsService = userDetailsService;
        this.appSuccessHandler = appSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(URLPrefix.WEB_PREFIX + "/login**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers(URLPrefix.WEB_PREFIX + "/registration").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage(URLPrefix.WEB_PREFIX + "/login")
                .loginProcessingUrl(URLPrefix.WEB_PREFIX + "/login")
                .successHandler(appSuccessHandler)
                .failureUrl(URLPrefix.WEB_PREFIX + "/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl(URLPrefix.WEB_PREFIX + "/logout")
                .logoutSuccessUrl(URLPrefix.WEB_PREFIX + "/login?logout")
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
