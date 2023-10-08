package com.eidiko.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eidiko.filter.SecurityFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private InvalidUserAuthEntryPoint authenticationEntryPoint;

	@Autowired
	private SecurityFilter securityFilter;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/user/save", "/user/login", "/emp/addEmployee")
				.permitAll().antMatchers("/emp/home").permitAll()

				.antMatchers("/getAllUser").permitAll().antMatchers("/user/welcome").authenticated()
				.antMatchers("/main/admin")

				.hasAuthority("ADMIN").antMatchers("/main/employee").hasAuthority("EMPLOYEE").antMatchers("/main/hr")
				.hasAuthority("HR").antMatchers("/main/manager")

				.hasAuthority("MANAGER").antMatchers("/main/visitor").hasAnyAuthority("ADMIN", "VISITOR")
				.antMatchers("/main/common").hasAnyAuthority("EMPLOYEE", "MANAGER").anyRequest()

				.authenticated().and().formLogin().defaultSuccessUrl("/user/welcome", true)

				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

				.and().exceptionHandling().accessDeniedPage("/user/accessDenied").

				authenticationEntryPoint(authenticationEntryPoint)

				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// register filter for 2nd request onwards...
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
