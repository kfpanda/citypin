package com.util.security.token;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean implements ApplicationEventPublisherAware {
	private static final Logger logger = LoggerFactory.getLogger(RestTokenAuthenticationFilter.class);
    public final String HEADER_SECURITY_TOKEN = "X-RestToken";
//    AuthenticationManager authenticationManager;
    private ApplicationEventPublisher eventPublisher;
    @Resource
	RestTokenRepositoryImpl restTokenRepositoryImpl;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
        	String token = request.getHeader(HEADER_SECURITY_TOKEN);
    		logger.info("token found:" + token);
    		Authentication userAuthenticationToken = authUserByToken(
    				token, request.getLocalAddr());

            if (userAuthenticationToken != null) {
                // Attempt authenticaton via AuthenticationManager
                try {
//                	userAuthenticationToken = authenticationManager.authenticate(userAuthenticationToken);

                    // Store to SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);

//                    onSuccessfulAuthentication(request, response, userAuthenticationToken);
                    
                    logger.debug("SecurityContextHolder populated with rest token: {}", 
                    		SecurityContextHolder.getContext().getAuthentication());

                    // Fire event
                    if (this.eventPublisher != null) {
                        eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
                                SecurityContextHolder.getContext().getAuthentication(), this.getClass()));
                    }

//                    if (successHandler != null) {
//                        successHandler.onAuthenticationSuccess(request, response, rememberMeAuth);
//
//                        return;
//                    }

                } catch (AuthenticationException authenticationException) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("SecurityContextHolder not populated with rest token, as "
                                + "AuthenticationManager rejected Authentication returned by RestTokenRepository: '"
                                + userAuthenticationToken + "'; invalidating rest token", authenticationException);
                    }

//                    rememberMeServices.loginFail(request, response);
//
//                    onUnsuccessfulAuthentication(request, response, authenticationException);
                }
            }

            chain.doFilter(request, response);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("SecurityContextHolder not populated with rest token, as it already contained: '"
                    + SecurityContextHolder.getContext().getAuthentication() + "'");
            }

            chain.doFilter(request, response);
        }
    }
    
    private Authentication authUserByToken(String tokenValue, String ip) {
		if (tokenValue == null) {
			return null;
		}
		RestToken token = restTokenRepositoryImpl.getTokenForSeries(tokenValue, ip);
		Authentication authToken = new RestAuthenticationToken(token);
		try {
			return authToken;
		} catch (Exception e) {
			logger.error("Authenticate user by token error: ", e);
		}
		return authToken;
	}

//	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//	}

	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}
}
