package com.example.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import com.example.jwt.JwtAuthenticationEntryPoint;
import com.example.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/authenticate", "/register", "/login/oauth2/code/google").permitAll()
//				.anyRequest().authenticated().and().
//				// make sure we use stateless session; session won't be used to
//				// store user's state.
//		exceptionHandling().authenticationEntryPoint(entryPoint).and().
//				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();

		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()).oauth2Login();
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ClientRegistrationRepository regRepo() {
		return new InMemoryClientRegistrationRepository(this.clientRegistration());
	}

	private ClientRegistration clientRegistration() {
		System.out.println("***************************");
		return CommonOAuth2Provider.GOOGLE.getBuilder("google")
				.clientId("174560673037-53d0ls0dod1mseblb2rivfiaf0ffh0hb.apps.googleusercontent.com")
				.clientSecret("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")
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).build();
	}

//	@Bean
//	public RegisteredClientRepository registeredClientRepository() {
//		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//				.clientId("174560673037-53d0ls0dod1mseblb2rivfiaf0ffh0hb.apps.googleusercontent.com")
//				.clientSecret("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")
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//				.redirectUri("http://127.0.0.1:8080/login/oauth2/code/articles-client-oidc")
//				.redirectUri("http://127.0.0.1:8080/hi").build();
//		return new InMemoryRegisteredClientRepository(registeredClient);
//	}

//	@Bean
//	@Order(1)
//	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults()); // Enable OpenID
//																										// Connect 1.0
//		http
//		// Redirect to the OAuth 2.0 Login endpoint when not authenticated
//		// from the authorization endpoint
////			.exceptionHandling((exceptions) -> exceptions
////				.defaultAuthenticationEntryPointFor( 
////					new LoginUrlAuthenticationEntryPoint("/oauth2/authorization/my-client"),
////					new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
////				)
////			)
//				// Accept access tokens for User Info and/or Client Registration
//				.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//
//		return http.build();
//	}
//
//	@Bean
//	@Order(2)
//	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//				// OAuth2 Login handles the redirect to the OAuth 2.0 Login endpoint
//				// from the authorization server filter chain
//				.oauth2Login(Customizer.withDefaults());
//
//		return http.build();
//	}

//	 @Bean
//	    public JwtDecoder jwtDecoder() {
//	        // Configure the JWT decoder implementation
//	        return NimbusJwtDecoder.withJwkSetUri("https://www.googleapis.com/oauth2/v3/certs").build();
//	    }
//
//	@Bean
//	public AuthorizationServerSettings authorizationServerSettings() {
//	    return AuthorizationServerSettings.builder()
//	            .build();
//	}
}

////    TO BE ADDED LATER	///

//http.csrf().disable().authorizeHttpRequests((auth) -> auth.requestMatchers("/authenticate", "/register")
//.permitAll().requestMatchers("/**").hasAuthority("EMPLOYEE").anyRequest().authenticated());
//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//return http.build();