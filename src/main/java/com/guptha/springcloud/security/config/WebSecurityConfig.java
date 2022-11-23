package com.guptha.springcloud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.guptha.springcloud.security.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.mvcMatchers(HttpMethod.GET, "/couponapi/coupons/{code:^[A-Z]*$}", "/index", "/getCoupon",
						"/showGetCoupon", "/couponDetails")
				.hasAnyRole("USER", "ADMIN")
				.mvcMatchers(HttpMethod.GET, "/showCreateCoupon", "/createCoupon", "/createResponse")
				.hasAnyRole("ADMIN").mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("ADMIN", "USER")
				.mvcMatchers(HttpMethod.POST, "/couponapi/coupons", "/getCoupon", "/saveCoupon").hasAnyRole("ADMIN")
				.mvcMatchers("/login", "/", "/showReg", "/registerUser", "/allUsers", "/swagger-ui/index.html")
				.permitAll().anyRequest().denyAll().and().csrf().disable().logout().logoutSuccessUrl("/");
	}

	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
