package com.util.security.support;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface WebGrantedAuthority extends GrantedAuthority{
	public Collection<?> getAuths();
}
