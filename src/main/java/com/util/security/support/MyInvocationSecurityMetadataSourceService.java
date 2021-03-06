package com.util.security.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.kfpanda.citypin.bean.RoleRes;
import com.kfpanda.citypin.mapper.RoleMapper;

/**
 * 
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 注意，我例子中使用的是AntUrlPathMatcher这个path matcher来检查URL是否与资源定义匹配，
 * 事实上你还要用正则的方式来匹配，或者自己实现一个matcher。
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 * 说明：对于方法的spring注入，只能在方法和成员变量里注入，
 * 如果一个类要进行实例化的时候，不能注入对象和操作对象，
 * 所以在构造函数里不能进行操作注入的数据。
 */
@Service
public class MyInvocationSecurityMetadataSourceService  implements
		FilterInvocationSecurityMetadataSource {
	
	private RequestMatcher pathMatcher;
	@Resource
	private RoleMapper roleMapper;
	private Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	
	public MyInvocationSecurityMetadataSourceService(){
	}
	
	@PostConstruct
	private void loadResource(){
		List<RoleRes> resList = roleMapper.findRoleRes();
		for(RoleRes res : resList){
			if(StringUtils.isNotBlank(res.getUrl())){
				if(resourceMap.containsKey(res.getUrl())){
					ConfigAttribute ca = new SecurityConfig(res.getRole());
					resourceMap.get(res.getUrl()).add(ca);
				}else{
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					ConfigAttribute ca = new SecurityConfig(res.getRole());
					atts.add(ca);
					resourceMap.put(res.getUrl(), atts);
				}
			}
		}
	}
	
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		//获得请求url
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		String url = ((FilterInvocation) object).getRequestUrl();
		url = url.indexOf("?") > 0 ? url.substring(0,url.indexOf("?")) : url;
		for(Iterator<String> itr = resourceMap.keySet().iterator(); itr.hasNext();){
			String resUrl = itr.next();
			pathMatcher = new AntPathRequestMatcher(resUrl);
			if(pathMatcher.matches(request)){
				return resourceMap.get(resUrl);
			}
		}
		return null;
	}
	
	/**
	 * 根据此用户所有资源中，获得符合此用户资源的权限集合
	 */
	/*public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		//获得用户的角色
		Collection rolesColl = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		//获得请求url
		String url = ((FilterInvocation) object).getRequestUrl();
		url = url.indexOf("?") > 0 ? url.substring(0,url.indexOf("?")) : url;
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		//遍历角色，获得具有请求url权限的角色名
		for(Iterator iter = rolesColl.iterator();iter.hasNext();){
			Role role = null;
			Object obj = iter.next();
			if(obj instanceof Role){
				role = (Role) obj;
			}else{
				//Admin_non异名用户
				GrantedAuthority grantAuth = (GrantedAuthority) obj;
				role = new Role();
				role.setRole(grantAuth.getAuthority());
			}
			List<PubAuth> authList = role.getAuths();
			for(int i = 0 ; authList != null && i < authList.size() ; i++){
				PubAuth auth = authList.get(i);
				//去除未启用的权限，由于java引用机制，会同时去除用户下的权限信息。
				if(auth.getEnableFlag() != 1){
					authList.remove(i);		i--;	continue;
				}
				String authURL = auth.getAuthURL();
				//获得 具有请求url权限的角色名
				if(urlMatcher.pathMatchesUrl(authURL, url)){
					ConfigAttribute ca = new SecurityConfig(role.getRoleName());
					atts.add(ca);
				}
			}
		}
		return atts ;
	}*/
	
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}
