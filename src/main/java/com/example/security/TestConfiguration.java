/*package com.example.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurer.RequestMatchers;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

//@Configuration
//@EnableAutoConfiguration
//@EnableWebSecurity
public class TestConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		super.configure(http);
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().anyRequest()
				.authenticated().and().csrf()
				.csrfTokenRepository(csrfTokenRepository()).and()
				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
				.logout().logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/");
	}
	
	
	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request
						.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = new Cookie("XSRF-TOKEN",
							csrf.getToken());
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				filterChain.doFilter(request, response);
			}
		};
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}
*/