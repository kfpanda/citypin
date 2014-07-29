package com.util.security.token;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

//@Repository
public class RestTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger logger = LoggerFactory.getLogger(RestTokenAuthenticationFilter.class);
    public final String HEADER_SECURITY_TOKEN = "X-RestToken";
    
	public RestTokenAuthenticationFilter() {
		super("");
//		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(
//				defaultFilterProcessesUrl));
//		setAuthenticationManager(n);
//		setAuthenticationSuccessHandler(new TokenSimpleUrlAuthenticationSuccessHandler());
	}

	/**
	 * Attempt to authenticate request - basically just pass over to another
	 * method to authenticate request headers
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		String token = request.getHeader(HEADER_SECURITY_TOKEN);
		logger.info("token found:" + token);
		AbstractAuthenticationToken userAuthenticationToken = authUserByToken(
				token, request.getLocalAddr());
		if (userAuthenticationToken == null)
			throw new AuthenticationServiceException(MessageFormat.format(
					"Error | {0}", "Bad Token"));
		return userAuthenticationToken;
	}

	/**
	 * authenticate the user based on token
	 * 
	 * @return
	 */
	private AbstractAuthenticationToken authUserByToken(String token, String ip) {
		if (token == null) {
			return null;
		}
		AbstractAuthenticationToken authToken = null; //new RestAuthenticationToken(
//				token, ip);
		try {
			return authToken;
		} catch (Exception e) {
			logger.error("Authenticate user by token error: ", e);
		}
		return authToken;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		super.doFilter(req, res, chain);
	}
 
}
