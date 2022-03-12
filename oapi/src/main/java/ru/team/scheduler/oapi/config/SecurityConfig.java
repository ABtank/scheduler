package ru.team.scheduler.oapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtFilter jwtFilter;

    private static final String[] PUBLIC_URLS = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/api/v1/auth/**"
    };
    private static final String[] AUTH_URLS = {
            "/api/v1/disciplines/**",
            "/api/v1/students/**",
            "/api/v1/users/**",
            "/api/v1/roles/**",
            "/api/v1/support/**",
    };

    @Autowired
    public void setJwtFilter(JwtFilter filter) {
        this.jwtFilter = filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(AUTH_URLS).authenticated()
                .antMatchers(PUBLIC_URLS).permitAll()
                //.antMatchers("/api/v1/score/get/{\\d+}").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
