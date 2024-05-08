package com.example.demo.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.demo.domain.user.service.UserService;
import com.example.demo.security.filter.CustomBasicAuthFilter;
import com.example.demo.user.impl.UserDetailServiceImpl;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ConfigSecurity {

	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.sessionManagement( sessionmangement ->  sessionmangement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.cors(cors -> {
			cors.configurationSource(new CorsConfigurationSource() {
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					
					CorsConfiguration cfg = new CorsConfiguration();
					cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
					cfg.setAllowedMethods(Collections.singletonList("*"));
					cfg.setAllowCredentials(true);
					cfg.setAllowedHeaders(Collections.singletonList("*"));
					cfg.setExposedHeaders(Arrays.asList("Authorization"));
					
				   return cfg;
					
				}
			});
		})
			
		.authorizeHttpRequests(
				(auth)-> auth
				.requestMatchers(HttpMethod.POST,"/cadastrar").permitAll()
				.requestMatchers(HttpMethod.POST,"/auth").permitAll()
				.requestMatchers(HttpMethod.GET, "/teste").permitAll()
				.anyRequest().authenticated()
				)
		.csrf(csrf -> csrf.ignoringRequestMatchers("/**")
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable()
				)
		.addFilterBefore(new CustomBasicAuthFilter(), BasicAuthenticationFilter.class)
		.httpBasic(Customizer.withDefaults())
		.formLogin(formLogin -> formLogin
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/logado", true));
		
		 return httpSecurity.build();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	        return new ProviderManager(daoAuthenticationProvider);

	}
	
	@Autowired
    private UserService userService;
    
    @Bean
    public UserService userServiceBean() {
    	return userService;
    }
	
}
