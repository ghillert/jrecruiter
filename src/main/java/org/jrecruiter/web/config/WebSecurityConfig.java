/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrecruiter.web.config;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jrecruiter.spring.SecurityEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Gunnar Hillert
 * @since 3.0
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired UserDetailsService userDetailsService;

	@Autowired PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //TODO Refactor login form

		.authorizeRequests().antMatchers("/admin/show-users.html**").hasAnyAuthority("ADMIN").and()

		.authorizeRequests().antMatchers("/admin/add-job.html**").hasAnyAuthority("MANAGER,ADMIN").and()
		.authorizeRequests().antMatchers("/admin/saveAddJob.html**").hasAnyAuthority("MANAGER,ADMIN").and()
		.authorizeRequests().antMatchers("/admin/show-jobs.html**").hasAnyAuthority("MANAGER,ADMIN").and()
		.authorizeRequests().antMatchers("/admin/show-jobs-ajax.html**").hasAnyAuthority("MANAGER,ADMIN").and()

		.authorizeRequests().antMatchers("/admin/edit-job.html**").hasAnyAuthority("MANAGER,ADMIN").and()
		.authorizeRequests().antMatchers("/admin/show-statistics.html**").hasAnyAuthority("MANAGER,ADMIN").and()
		.authorizeRequests().antMatchers("/admin/edit-user.html**").hasAnyAuthority("MANAGER,ADMIN").and()
		.authorizeRequests().antMatchers("/admin/index.html**").hasAnyAuthority("MANAGER,ADMIN").and()
		.authorizeRequests().antMatchers("/admin/delete-jobs.html**").hasAnyAuthority("MANAGER,ADMIN").and()

		.authorizeRequests().antMatchers("/admin/delete-user.html**").hasAnyAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/admin/add-user.html**").hasAnyAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ADMIN").and()

		.authorizeRequests().antMatchers("/admin/edit-settings.html**").hasAnyAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/admin/search-index.html**").hasAnyAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/admin/setup-demo.html**").hasAnyAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/admin/logging.html**").hasAnyAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/admin/*.html**").hasAnyAuthority("MANAGER,ADMIN").and()

		.authorizeRequests().antMatchers("/**").permitAll().anyRequest().anonymous().and()

		.logout().logoutSuccessUrl("/index.html").logoutUrl("/logout.html").permitAll().and()
		.requiresChannel().antMatchers("/admin/**").requiresSecure().and()
		.formLogin().loginProcessingUrl("/login.html").defaultSuccessUrl("/admin/index.html").loginPage("/login.html").failureUrl("/login.html?status=error").permitAll();
	}

	//<security:concurrency-control max-sessions="1" error-if-maximum-exceeded="false"/>
	//<security:custom-filter ref="openIDFilter"                   position="OPENID_FILTER" />

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder);
	}

	@Bean
	public SecurityEventListener securityEventListener() {
		return new SecurityEventListener();
	}
}
