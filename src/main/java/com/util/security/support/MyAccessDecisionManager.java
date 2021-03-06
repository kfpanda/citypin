package com.util.security.support;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

	//In this method, need to compare authentication with configAttributes.
    // 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
    // 2, Check authentication has attribute in permission configuration (configAttributes)
    // 3, If not match corresponding authentication, throw a AccessDeniedException.
    @Override
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
    	
        if(configAttributes == null){
        	throw new AccessDeniedException("user no right");//无此权限
        }
        
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            if(authentication.getAuthorities() != null){
	            for(GrantedAuthority ga:authentication.getAuthorities()){
	                if(needRole.equals(ga.getAuthority())){  //ga is user's role.
	                    return;
	                }
	            }
            }
        }
        throw new AccessDeniedException("user no right");
    }

}
