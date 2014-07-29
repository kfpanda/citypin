package com.util.security.token;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Repository;

@Repository
public class RestAuthSuccessHandler implements AuthenticationSuccessHandler{
	@Resource
	RestTokenRepositoryImpl restTokenRepositoryImpl;
	public final String HEADER_SECURITY_TOKEN = "X-RestToken";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		@SuppressWarnings("unchecked")
		String tokenValue = restTokenRepositoryImpl.createToken(request.getLocalAddr(), 
				authentication.getPrincipal(), (Collection<GrantedAuthority>) authentication.getAuthorities());
		
		response.addHeader(HEADER_SECURITY_TOKEN, tokenValue);
		response.getOutputStream().write("{r:1}".getBytes());
	}

}
