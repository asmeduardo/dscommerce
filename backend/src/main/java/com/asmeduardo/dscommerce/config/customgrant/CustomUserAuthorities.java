package com.asmeduardo.dscommerce.config.customgrant;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserAuthorities {

	private final String username;
	private final Collection<? extends GrantedAuthority> authorities;

	public CustomUserAuthorities(String username, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
