package com.sincco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sincco.security.AppUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		

	}
	
	
	public void configure(WebSecurity web) {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/tipologia").permitAll()
			.antMatchers("/servico/itens").permitAll()
			
			.antMatchers("/cepServico/novo").hasAuthority("CADASTRA_CEP")
			.antMatchers("/servico/novo").hasAuthority("CADASTRA_SERVICO")
			
			.antMatchers("/servico").hasAuthority("CADASTRA_SERVICO_PESQUISA")
			.antMatchers("/servico/cliente").hasAuthority("CADASTRA_SERVICO_PESQUISA_CL")

			.antMatchers("/usuario/**").hasAuthority("CADASTRA_USUARIO")
			.antMatchers("/tipologia/**").hasAuthority("CADASTRA_TIPOLOGIA")
			.antMatchers("/produto/**").hasAuthority("CADASTRA_PRODUTO")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/", true)
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
			.csrf().disable();
	}
	
	/*

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/", true)
				.permitAll()
				.and()
			.csrf().disable();
	}
	
		 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
