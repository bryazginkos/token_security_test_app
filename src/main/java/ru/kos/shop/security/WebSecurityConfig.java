package ru.kos.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.kos.shop.controller.UrlList;

/**
 * Created by Константин on 05.04.2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new AuthenticationTokenFilter(), BasicAuthenticationFilter.class)
                .antMatcher(UrlList.PREFIX + "/*")
                .authenticationProvider(tokenAuthenticationProvider)
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}