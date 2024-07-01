package taskmanagement.taskmanagement.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // habilita la seguridad
@EnableMethodSecurity(securedEnabled = true) // habilita la seguridad a nivel de metodos
@Configuration // le dice a spring que es una clase de configuracion
public class WebSecurityConfiguration {

	@Autowired
	DataSource dataSource;

	// @Autowired
	// UserDetailsServiceImpl userDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/**","/dist/**", "/customFunction/**", "/plugins/**", "/error/**",
								"/templates/templateBase/**")
						.permitAll()

						.requestMatchers("/","/home").authenticated().requestMatchers("/user/**", "/group/**","/task/**")
						.hasAnyAuthority("ADMIN", "READ", "CREATE", "UPDATE", "DELETE")

				/* .anyRequest().authenticated() */
				)

				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/error/403")) // Configura
																											// la página
																											// de error
																											// personalizada

				.formLogin(

						formlogin -> {
							try {
								formlogin.loginPage("/login").permitAll().loginProcessingUrl("/logincheck");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						})
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true))

				.build();

	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enabled from users where username = ?")
				.authoritiesByUsernameQuery(
						"select username,authority from authorities INNER JOIN users on authorities.user_id=users.user_id where users.username=? ")

				.groupAuthoritiesByUsername(
						"select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id");

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
