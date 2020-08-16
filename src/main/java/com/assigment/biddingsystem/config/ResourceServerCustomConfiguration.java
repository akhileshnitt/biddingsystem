package com.assigment.biddingsystem.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Profile("prod")
@EnableResourceServer
public class ResourceServerCustomConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/h2-console","/oauth/token", "/oauth/authorize**", "/api/v1/auctions").permitAll();
//			 .anyRequest().authenticated();
        http.requestMatchers().antMatchers("/api/v1/placeBid")
                .and().authorizeRequests()
                .antMatchers("/api/v1/placeBid").access("hasRole('USER')")
                .and().requestMatchers().antMatchers("/admin")
                .and().authorizeRequests()
                .antMatchers("/admin").access("hasRole('ADMIN')");
    }
}
