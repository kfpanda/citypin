package com.util.security.token;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class RestToken {
	
	private final Object principal;
//    private final String series;
	private final String ip;
    private final String tokenValue;
    private final Long expireTime;
    private Collection<GrantedAuthority>  authorities;
    
    public RestToken(Object principal, String ip, String tokenValue, 
    		Long expireTime, Collection<GrantedAuthority>  authorities) {
        this.principal = principal;
        this.ip = ip;
        this.tokenValue = tokenValue;
        this.expireTime = expireTime;
        this.authorities = authorities;
    }
    
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public String getIp() {
		return ip;
	}
	public Object getPrincipal() {
		return principal;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public Long getExpireTime() {
		return expireTime;
	}
}
