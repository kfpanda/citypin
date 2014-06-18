package com.util.security.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Repository;

@Repository("jsonAccessDeniedHandler")
public class JsonAccessDeniedHandler implements AccessDeniedHandler{
	private final Logger logger = Logger.getLogger(JsonAccessDeniedHandler.class);

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        if (!response.isCommitted()) {
        	String result = "{r:\"-1\",msg:\"" + accessDeniedException.getMessage() + "\"}";
            response.sendError(HttpServletResponse.SC_FORBIDDEN, result);
        }
    }

}
