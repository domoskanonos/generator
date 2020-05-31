package com.dbr.generator.springboot.system.auth.config;

import com.dbr.generator.springboot.system.auth.handler.OAuth2AuthenticationFailureHandler;
import com.dbr.generator.springboot.system.auth.handler.OAuth2AuthenticationSuccessHandler;
import com.dbr.generator.springboot.system.auth.handler.SpringBootBasicAuthenticationFailureHandler;
import com.dbr.generator.springboot.system.auth.handler.SpringBootBasicAuthenticationSuccessHandler;
import com.dbr.generator.springboot.system.auth.service.AuthenticationService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ConditionalOnProperty(name = "spring.security.enabled", havingValue = "true", matchIfMissing = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String KEY_JSESSION = "JSESSIONID";

    private static final String LOGIN_PAGE = "/login.html";
    private static final String LOGIN_PROCESSING_URL = "/dologin";
    private static final String LOGIN_SUCCESS_URL = "/index.html";

    public static final String LOGOUT_URL = "/dologout";
    public static final String LOGOUT_SUCCESS_URL = "/login.html";
    public static final String ACCESS_DENIED_PAGE = "/403";

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final AuthenticationService authenticationService;

    final SpringBootBasicAuthenticationSuccessHandler authenticationSuccessHandler;

    final SpringBootBasicAuthenticationFailureHandler authenticationFailureHandler;

    final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationService authenticationService, SpringBootBasicAuthenticationSuccessHandler authenticationSuccessHandler, SpringBootBasicAuthenticationFailureHandler authenticationFailureHandler, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationService = authenticationService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/SYSTEM/AUTH/REGISTER", "/SYSTEM/AUTH/RESET_PASSWORD", "/SYSTEM/AUTH/ACTIVATE_USER")
                .permitAll().anyRequest().authenticated().and().csrf().disable().formLogin().loginPage(LOGIN_PAGE)
                .loginProcessingUrl(LOGIN_PROCESSING_URL).successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler).defaultSuccessUrl(LOGIN_SUCCESS_URL, true).permitAll()
                .and().logout().logoutUrl(LOGOUT_URL).invalidateHttpSession(true).deleteCookies(KEY_JSESSION)
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL).permitAll();
        http.exceptionHandling().accessDeniedPage(ACCESS_DENIED_PAGE);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/ajax/**",
                "/data/**", "/fonts/**", "/image/**");
    }

}