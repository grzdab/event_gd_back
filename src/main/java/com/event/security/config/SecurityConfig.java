package com.event.security.config;

//import com.event.security.auth.ApplicationUserService;
import com.event.security.controller.CustomLogoutSuccessHandler;
import com.event.security.filter.JwtAuthorizationFilter;
import com.event.security.filter.UserAuthenticationFilter;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
//    private final ApplicationUserService applicationUserService;


//    @Autowired
//    public SecurityConfig(PasswordEncoder passwordEncoder,
//                                     ApplicationUserService applicationUserService
//                                     ) {
//        this.passwordEncoder = passwordEncoder;
//        this.applicationUserService = applicationUserService;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // it tells Spring how to look for the users
        // UserServiceImplementation implements UserDetailsService
        // and overrides loadUserByUsername method
        // which fetches users from database with UserRepository class
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter(authenticationManagerBean());
        userAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.logout().logoutUrl("/logout").permitAll();
        http.logout().logoutSuccessHandler(logoutSuccessHandler());
        http.logout().deleteCookies("refreshToken", "accessToken");
        http.logout().clearAuthentication(true);
        http.logout().invalidateHttpSession(true);

        http.authorizeRequests()
            .antMatchers("/", "/api/**",  "/index", "/css/*", "/js/*", "/welcome").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/user/**", "/users/**", "/client/**").hasAnyRole("ADMIN", "GUEST")
            .antMatchers("/equipment/**").hasAnyRole("ADMIN", "USER")
            .antMatchers("/equipment-category/**").hasRole("ADMIN")
            .anyRequest().authenticated();

        http.addFilter(userAuthenticationFilter); // adds filter that authenticates users when they log in
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); // this filter comes before other filters thanks to Before

//            http.formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/app", true)
//                .passwordParameter("password")
//                .usernameParameter("username")
//            .and()
//            .rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(3))
//                .key("sH9nc97ag5K7lPK3mUdsQYEvsX?K4y4Q") // key to hash username and expiration time in remember-me cookie
//                .rememberMeParameter("remember-me")
//            .and()
//            .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");
    }



//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(applicationUserService);
//        return provider;
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of(
            "http://localhost:3000", "http://localhost:3001"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD","OPTIONS",
            "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of(
            "Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

