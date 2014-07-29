package com.util.security.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RestAuthenticationToken extends AbstractAuthenticationToken{
	private static final long serialVersionUID = 6847236518285601498L;
	private final Object principal;
    Collection<GrantedAuthority>  authorities;
    
	public RestAuthenticationToken(RestToken token) {
		super(null);
		super.setAuthenticated(true); // must use super, as we override
		if(token != null){
			this.principal = token.getPrincipal();
			this.authorities = token.getAuthorities();
		}else{
			this.principal = null;
			this.authorities = null;
		}
	}
 
    @Override
    public Object getCredentials() {
        return "";
    }
 
    @Override
    public Object getPrincipal() {
        return principal;
    }
 
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}